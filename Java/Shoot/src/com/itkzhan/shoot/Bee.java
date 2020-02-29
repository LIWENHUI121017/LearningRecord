package com.itkzhan.shoot;

import java.util.Random;

/** �۷�*/
public class Bee extends FlyingObject implements Award{
	private int xSpeed=1;//x�����߲�����
	private int ySpeed=2;//y�����߲�����
	private int awardType;//��������
	/** Bee���췽��*/
	public Bee(){
		image=ShootGame.bee;//ͼƬ
		width=image.getWidth();//ͼƬ�Ŀ�
		height=image.getHeight();//ͼƬ�ĸ�
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
		y=-this.height;
		awardType=rand.nextInt(2);
	}
	public int getType() {//��������
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
