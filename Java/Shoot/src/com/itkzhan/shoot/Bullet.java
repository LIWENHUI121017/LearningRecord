package com.itkzhan.shoot;
/** 子弹*/
public class Bullet extends FlyingObject{
	private int speed=3;//走步的步数
	/** Bullet构造方法*/
	public Bullet(int x,int y){
		image=ShootGame.bullet;
		width=image.getWidth();
		height=image.getHeight();
		this.x=x;//x坐标
		this.y=y;//y坐标
	}
	public void step() {
		y-=speed;
		
	}
	public boolean outBounds() {
		return this.y<-height;
	}
}
