package com.day07;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 
 * 模拟商户商户端处理异步通知
 * @author linql
 */
@Controller
public class NotifyCallBackController {
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	private final static String charset = "UTF-8";

	/**
	 * 注意：异步通知是通过Post方式
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = { "/test/notifyCallBack" }, method = { RequestMethod.POST })
	public void notifyCallBack(ModelMap model, HttpServletRequest request) {
		final Map<String, String> params = getParameterMap(request);
		log.info("异步通知信息:{}", params);
		/*
		 * 异步通知信息包含的内容请查询接口文档
		 * 异步通知信息示例:
		 * orderNo=201801051726113, 
			signatureInfo=lI28ev8Rpv7mVjvfczs55ukwZVMlIEq71ZxrUH8hbBqKTf+KNv38qAA+nFIMALkjX5aIdDw/lg2lRYa8YfHJAtX7bWWWiUMSsReGDpo6cmulYt28S8cDx6hslJV22zj5ZCpEjgX0PFe2N2ppgUtR9lnT1W6naTkqgn5SzTgwhBGX6OouZbtwqnc8eIu4om/zUcwxR3VqdHHwevxuOttVTVCceFQXUaunQxpGdwuGpeMDnGQ4e5WMAAMz2zsDmCsoV/mtIHK87hn1off+L0QNRkGpMlY2ovuPpqmy1nHO6zfyE1cbe9iOuby7l5ds79P5G2wGoEfjFiTOJ7e7KBjH4A==, 
			status=1, callerIp=10.37.20.46, signatureAlgorithm=RSA, reconStatus=1, settlementAmount=1, checkDate=20180105, language=zh_CN, channelNo=4100002085065669341735018696704
		*/
		if (params != null && params.size() > 0) {
			//1. 验证签名
			boolean verifySign = verifySign("/home/admin/pfx.cer", params, params.get("signatureInfo"));
			if (!verifySign) {
				//验签不通过处理
			} else {
				//2.验签通过，进行业务处理...
			}
		}
	}

	/**
	 * <p>获取request中所有参数封装成map返回</p>
	 * 
	 * @param request http请求对象
	 * @return map集合的所有请求参数
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameterMap(final ServletRequest request) {
		final Map<String, String> map;
		if (request == null) {
			map = MapUtils.EMPTY_MAP;
		} else {
			map = new HashMap<>();
			final Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				final String parameterName = parameterNames.nextElement();
				map.put(parameterName, request.getParameter(parameterName));
			}
		}
		return map;
	}

	/**
	 * 
	 * <p>使用快付通的公钥进行验签</p>
	 * @param cerPath
	 * @param param
	 * @param signatureInfo
	 * @return
	 */
	public static boolean verifySign(String cerPath, Map<String, String> param, String signatureInfo) {
		boolean verifySign = false;
		try {
			Map<String, String> parameters = paramsFilter(param);
			String createLinkString = createLinkString(parameters);
			byte[] datas = createLinkString.getBytes(charset);
			// 得到的报文中的签名
			String signMsg = signatureInfo;
			byte[] sign = Base64.decodeBase64(signMsg);
			verifySign = verifySign(datas, sign, cerPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return verifySign;
	}

	/**
	 * 除去参数中的空值和签名参数
	 * 
	 * @param parameters
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	private static Map<String, String> paramsFilter(final Map<String, String> parameters) {
		if (parameters == null || parameters.size() == 0) {
			return new HashMap<String, String>();
		}
		final Map<String, String> result = new HashMap<String, String>(parameters.size());
		String value = null;
		for (final String key : parameters.keySet()) {
			value = parameters.get(key);
			if (value == null || "".equals(value) || key.equalsIgnoreCase("signatureAlgorithm")
					|| key.equalsIgnoreCase("signatureInfo")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 对请求参数排序，并按照接口规范中所述"参数名=参数值"的模式用"&"字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数
	 * @return 拼接后字符串
	 */
	private static String createLinkString(final Map<String, String> params) {

		final List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		final StringBuilder sb = new StringBuilder();
		String key = null;
		String value = null;
		for (int i = 0; i < keys.size(); i++) {
			key = keys.get(i);
			value = params.get(key);
			sb.append(key).append("=").append(value);
			// 最后一组参数,结尾不包括'&'
			if (i < keys.size() - 1) {
				sb.append("&");
			}
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 验证签名,从证书中获取公钥来验证签名是否正确
	 * </p>
	 * 
	 * @param data
	 *            传输的数据
	 * @param sign
	 *            对传输数据的签名
	 * @param certificateContent
	 *            证书内容的2进制形式
	 * @return
	 * @throws Exception
	 */
	public static boolean verifySign(byte[] data, byte[] sign, String certificateContent) throws Exception {
		X509Certificate certificate = (X509Certificate) getCertificate(certificateContent);
		Signature signature = Signature.getInstance(certificate.getSigAlgName());
		// 由证书初始化签名,使用证书中的公钥
		signature.initVerify(certificate);
		signature.update(data);
		return signature.verify(sign);
	}

	/**
	 * <p>
	 * 从证书文件读取证书.'.crt'和'.cer'文件都可以读取 .cer是IE导出的公钥证书（der格式）
	 * </p>
	 * 
	 * @param certificatePath
	 *            证书文件路径:可以直接加载指定的文件,例如"file:C:/kft.cer"
	 * @throws Exception
	 */
	private static Certificate getCertificate(String certificatePath) throws Exception {
		File certificate = new File(certificatePath);
		if (certificate == null || (certificate.exists() && certificate.isDirectory())) {
			throw new IllegalArgumentException("certificatePath[" + certificatePath + "]必须是一个已经存在的文件,不能为空,且不能是一个文件夹");
		}
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(certificate);
			// 实例化证书工厂
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			Certificate cert = cf.generateCertificate(inputStream);
			return cert;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
}
