package com.itkzhan.shoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootGame extends JPanel{//面板
	public static final int WIDTH=400;
	public static final int HEIGHT=654;
	public static BufferedImage background;
	public static BufferedImage start;
	public static BufferedImage airplane;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage hero0;
	public static BufferedImage hero1;
	public static BufferedImage pause;
	public static BufferedImage gameover;
	
	public Hero hero=new Hero();//英雄机对象
	private FlyingObject[] flyings={};//敌人数组（敌机+蜜蜂）
	private Bullet[] bullets={};//子弹数组
	
	public static final int START=0;//启动状态
	public static final int RUNNING=1;//运行
	public static final int PAUSE=2;//暂停
	public static final int GAME_OVER=3;//结束
	private int state=0; //存储当前状态
	
	static { // 静态代码块，初始化图片资源
		try {
			background = ImageIO.read(ShootGame.class
					.getResource("background.png"));
			start = ImageIO.read(ShootGame.class.getResource("start.png"));
			airplane = ImageIO
					.read(ShootGame.class.getResource("airplane.png"));
			bee = ImageIO.read(ShootGame.class.getResource("bee.png"));
			bullet = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			hero0 = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1 = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			pause = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover = ImageIO
					.read(ShootGame.class.getResource("gameover.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		/** 随机生成敌人对象（敌机、小蜜蜂）*/
		public static FlyingObject nextOne(){
			Random rand=new Random();
			int type=rand.nextInt(20);//生成[0,20)随机数
			if(type==0){
				return new Bee();//若为0则返回蜜蜂对象
			}else{
				return new Airplane();//1到19则返回敌机对象
			}
			
		}
		private Timer timer;//声明定时器对象
		private int intervel=10;//定时器的时间间隔(以毫秒为单位)
		int flyEnteredIndex=0;//敌人入场计数
		/** 敌人入场（敌机、小蜜蜂)*/
        public void enterAction(){//10毫秒走一次
        	flyEnteredIndex++;
        	if(flyEnteredIndex%40==0){//每400毫秒走一次
        		FlyingObject o=nextOne();
        		flyings=Arrays.copyOf(flyings, flyings.length+1);//扩容
        		flyings[flyings.length-1]=o;//将对象加入到数组中
        	}
			
		}
        /** 飞行物走一步*/
        public void stepAction(){
        	hero.step();
        	for(FlyingObject f:flyings){
        		f.step();
        	}
        	for(Bullet b:bullets){
        		b.step();
        	}
        }
        int shootIndex=0;//子弹发射计数
        /** 英雄机发射子弹--子弹入场*/
        public void shootAction(){
        	shootIndex++;
        	if(shootIndex%30==0){
        		Bullet[] bs=hero.shoot();
        		bullets=Arrays.copyOf(bullets, bullets.length+bs.length);
        		System.arraycopy(bs,0,bullets, bullets.length-bs.length,bs.length);
        	}
        }
        int score=0;
		/**所有子弹与所有敌人的碰撞 */
        public void bangAction(){
        	for(Bullet b:bullets){
        		bang(b);//
        	}
        }
        /** 一发子弹和所有敌人的碰撞*/
        public void bang(Bullet bullet){
        	int index=-1;
        	for(int i=0;i<flyings.length;i++){
        		FlyingObject obj=flyings[i];
        		if(obj.shootBy(bullet)){
        			index=i;
        			break;
        		}
        	}
        	if(index!=-1){//被撞上了
        		FlyingObject one=flyings[index];
        		if(one instanceof Enemy){//敌机
        			Enemy e=(Enemy)one;
        			score+=e.getScore();
        		}
        		if(one instanceof Award){//奖励
        			Award a=(Award)one;
        			int type=a.getType();
        			switch (type) {
					case Award.DOUBLE_FIRE:
						hero.addDoubleFire();
						break;
					case Award.LIFE:
						hero.addLife();
						break;
					}
        		}
        		FlyingObject t=flyings[index];
        		flyings[index]=flyings[flyings.length-1];
        		flyings[flyings.length-1]=t;
        		flyings=Arrays.copyOf(flyings, flyings.length-1);//缩容
        	}
        }
        
        public void outOfBoundsAction(){
        	//越界飞行物
        	FlyingObject[] flyingLives=new FlyingObject[flyings.length];//活着的
        	int index=0;
        	for(int i=0;i<flyings.length;i++){
        		FlyingObject obj=flyings[i];
        		if(!obj.outBounds()){//不越界
        			flyingLives[index]=obj;//存储在flyingLives
        			index++;
        		}
        	}
        	flyings=Arrays.copyOf(flyingLives, index);
        	index=0;
        	Bullet[] bulletLives=new Bullet[bullets.length];
        	for(int i=0;i<bullets.length;i++){
        		Bullet b=bullets[i];
        		if(!b.outBounds()){
        			bulletLives[index]=b;
        			index++;
        		}
        	}
        	bullets=Arrays.copyOf(bulletLives, index);
        }
        /** 检查游戏结束*/
        public void checkGameOverAction(){
        	if(isGameOver()){//若游戏结束
        		state=GAME_OVER; 
        	}
        }
        /** 判断游戏是否结束*/
        public boolean isGameOver(){
        	for(int i=0;i<flyings.length;i++){
        		int index=-1;
        		FlyingObject f=flyings[i]; 
        		if(hero.hit(f)){
        			hero.subtractLife();
        			hero.setDoubleFire(0);
        			index=i;
        		}
        		if(index!=-1){
        			//将被撞的敌人删除
        			FlyingObject t=flyings[index];
        			flyings[index]=flyings[flyings.length-1];
        			flyings[flyings.length-1]=t;
        			flyings=Arrays.copyOf(flyings, flyings.length-1);
        		}
        	}
        	return hero.getLife()<=0;
        }
        
		/** 主程序流程控制*/
		public void action(){
			//鼠标操作事件
			MouseAdapter l=new MouseAdapter() {
				/** 鼠标移动事件*/
				public void mouseMoved(MouseEvent e) {
					if(state==RUNNING){
					int x=e.getX();//获取鼠标的x坐标
					int y=e.getY();//获取鼠标的y坐标
					hero.moveTo(x, y);//英雄机随鼠标移动
					}
				}
				public void mouseClicked(MouseEvent e) {//鼠标点击事件
					switch (state) {//判断当前状态
					case START:
						state=RUNNING;
						break;
					case GAME_OVER:
						flyings = new FlyingObject[0]; // 清空飞行物
						bullets = new Bullet[0]; // 清空子弹
						hero = new Hero(); // 重新创建英雄机
						score = 0; // 清空成绩
						state = START; // 状态设置为启动
					
					}
				}
				public void mouseExited(MouseEvent e) {//鼠标移开事件
					if(state==RUNNING){
						state=PAUSE;
					}
				}
				public void mouseEntered(MouseEvent e) {//移入事件
					if(state==PAUSE){
						state=RUNNING;
					}
				}
			};
			
			this.addMouseListener(l);//处理鼠标一般操作
			this.addMouseMotionListener(l);//处理鼠标滑动
			timer=new Timer();//创建定时器对象
			timer.schedule(new TimerTask(){
				public void run() {
					if(state==RUNNING){
					enterAction();//敌人入场
					stepAction();//飞行物走一步
					shootAction();//英雄机发子弹
					bangAction();//子弹和敌人的碰撞
					outOfBoundsAction();//删除出界对象
					checkGameOverAction();//检查游戏结束  
					}
					repaint();
				}}, intervel,intervel);//第一个intervel:程序启动到第一次干事的间隔
										//第二个intervel:每次干事的间隔
		}
		
		//g：画笔
		public void paint(Graphics g) {
			g.drawImage(background,0,0,null);//第四个参数不需要管，跟程序无关
			paintHero(g);
			paintFlyingObjects(g);
			paintBullets(g);
			paintScore(g);
			paintState(g);//画状态
		}
		//画子弹
	private void paintBullets(Graphics g) {
			for(Bullet b:bullets){
				g.drawImage(b.image,b.x,b.y,null);
			}
			
		}
	//画敌人
	private void paintFlyingObjects(Graphics g) {
			for(FlyingObject f:flyings){
				g.drawImage(f.image,f.x,f.y,null);
			}
			
		}
	//画英雄机
	private void paintHero(Graphics g) {
			g.drawImage(hero.image,hero.x,hero.y,null);
			
		}
	//画分和画命
	private void paintScore(Graphics g){
		g.setColor(new Color(0xFF0000));//设置颜色--红色
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));//字体,加粗,字号
		g.drawString("SCORE: "+score, 10, 25);
		g.drawString("LIFE: "+hero.getLife(), 10, 45);
	}
	//画状态
	public void paintState(Graphics g){
		switch (state) {//判断状态
		case START:
			g.drawImage(start,0,0,null);
			break;
		case PAUSE:
			g.drawImage(pause,0,0,null);
			break;
		case GAME_OVER: 
			g.drawImage(gameover,0,0,null);
			break;
		}
	}
	public static void main(String[] args) {
		JFrame frame=new JFrame("Fly");
		ShootGame game=new ShootGame();
		frame.add(game);//将面板添加到窗口中
		frame.setSize(WIDTH,HEIGHT);
		frame.setAlwaysOnTop(true);//总是居顶
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置默认关闭操作
		frame.setLocationRelativeTo(null);//设置位置相对与，默认相对位置就是中间点
		frame.setVisible(true);//1.设置窗口可见 2.尽快调用paint()方法
		game.action();//启动执行
	}

}
