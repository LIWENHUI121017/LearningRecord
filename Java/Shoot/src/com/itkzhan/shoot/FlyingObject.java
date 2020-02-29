package com.itkzhan.shoot;

import java.awt.image.BufferedImage;

/**
 * 飞行物
 * @author HP
 *
 */
public abstract class FlyingObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage image;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public abstract void step();
	//敌人被子弹打
	public  boolean shootBy(Bullet bullet){
		int x=bullet.x;//子弹的x
		int y=bullet.y;//子弹的y
		return x>this.x&&x<this.x+this.width
		&&
		y>this.y&&y<this.y+this.height;
	};
	public abstract boolean outBounds();
}

