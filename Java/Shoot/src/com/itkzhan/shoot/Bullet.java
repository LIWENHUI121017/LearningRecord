package com.itkzhan.shoot;
/** �ӵ�*/
public class Bullet extends FlyingObject{
	private int speed=3;//�߲��Ĳ���
	/** Bullet���췽��*/
	public Bullet(int x,int y){
		image=ShootGame.bullet;
		width=image.getWidth();
		height=image.getHeight();
		this.x=x;//x����
		this.y=y;//y����
	}
	public void step() {
		y-=speed;
		
	}
	public boolean outBounds() {
		return this.y<-height;
	}
}
