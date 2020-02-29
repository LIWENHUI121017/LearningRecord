package com.itkzhan.shoot;

import java.util.Random;

/** �л�*/
public class Airplane extends FlyingObject implements Enemy{
	private int speed=2;//�߲��Ĳ���
	public Airplane(){
		image=ShootGame.airplane;
		width=image.getWidth();
		height=image.getHeight();
		Random rand=new Random();
		x=rand.nextInt(ShootGame.WIDTH-this.width);
		y=-height;//y����
	}
	public int getScore() {
		return 5;
	}
	public void step() {
		y+=speed;
	}
	public boolean outBounds() {
		
		return this.y>ShootGame.HEIGHT;
	}
	
}
