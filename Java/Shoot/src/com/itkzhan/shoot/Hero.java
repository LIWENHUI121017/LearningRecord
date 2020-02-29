package com.itkzhan.shoot;

import java.awt.image.BufferedImage;

/** 英雄机:是飞行物*/
public class Hero extends FlyingObject{
	private int life;//命
	private int doubleFire;//火力值
	private BufferedImage[] images;//图片
	private int index;//图片切换的频率
	/** Hero构造方法*/
	public Hero(){
		image=ShootGame.hero0;//图片
		width=image.getWidth();//宽
		height=image.getHeight();//高
		x=150;//固定150
		y=400;//固定的400
		life=3;//命值为3
		doubleFire=0;//火力值为0,单倍火力
		images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		index=0;
	}
	public void step() {
		index++;
		int a=index/10;//每100m b=0,1
		int b=a%2;
		image=images[b];
		image=images[b];
	}
	/** 发射子弹*/
	public Bullet[] shoot(){
		int xStep=this.width/4;//1/4英雄机的宽度
		int yStep=20;//子弹离飞机的距离
		if(doubleFire>0){//双倍火力
			Bullet[] bullets=new Bullet[2];
			bullets[0]=new Bullet(this.x+xStep,this.y-yStep);
			bullets[1]=new Bullet(this.x+3*xStep,this.y-yStep);
			doubleFire-=-2;
			return bullets;
		}else{//单倍火力
			Bullet[] bullets=new Bullet[1];
			bullets[0]=new Bullet(this.x+2*xStep,this.y-yStep);
			return bullets;
		}
	}
	/** 英雄机随着鼠标移动x：鼠标的x坐标 y鼠标的y坐标*/
	public void moveTo(int x,int y){
		this.x=x-this.width/2;
		this.y=y-this.height/2;
	}
	
	/**获取命*/
	public int getLife(){
		return life;
	}
	/** 减命*/
	public void subtractLife(){
		life--;
	}
	
	public void addLife(){
		life++;
	}
	/** 增火力值*/
	public void addDoubleFire(){
		doubleFire+=40;
	}
	/** 设置火力值*/
	public void setDoubleFire(int doubleFire){
		this.doubleFire=doubleFire;
	}
	@Override
	public boolean outBounds() {
		return false;//永不越界
	}
	public boolean hit(FlyingObject other){
		int x1=other.x-this.width/2;
		int x2=other.x+other.width+this.width/2;
		int y1=other.y-this.height/2;
		int y2=other.y+other.height/2;
		int hx=this.x+this.width/2;// 
		int hy=this.y+this.height/2;
		return hx>x1 && hx<x2
		       &&
		       hy>y1 && hy<y2;
	}

}
