package com.itkzhan.shoot;

import java.util.Random;

/** 蜜蜂*/
public class Bee extends FlyingObject implements Award{
	private int xSpeed=1;//x坐标走步步数
	private int ySpeed=2;//y坐标走步步数
	private int awardType;//奖励类型
	/** Bee构造方法*/
	public Bee(){
		image=ShootGame.bee;//图片
		width=image.getWidth();//图片的宽
		height=image.getHeight();//图片的高
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
		y=-this.height;
		awardType=rand.nextInt(2);
	}
	public int getType() {//奖励类型
		return awardType;
	}
	public void step() {
		x+=xSpeed;
		y+=ySpeed;
		if(this.x>=ShootGame.WIDTH-this.width){
			xSpeed=-1;
		}
		if(x<=0){
			xSpeed=1;
		}
	}
	public boolean outBounds() {
		return this.y>ShootGame.HEIGHT;
	}

}
