package com.itkzhan.shoot;
/** 奖励*/
public interface Award {
	int DOUBLE_FIRE=0;//双倍火力值
	int LIFE=1;//命
	int getType();//得奖励类型（0或1）
}
