package com.itkzhan.shoot;

import java.awt.image.BufferedImage;

/** Ӣ�ۻ�:�Ƿ�����*/
public class Hero extends FlyingObject{
	private int life;//��
	private int doubleFire;//����ֵ
	private BufferedImage[] images;//ͼƬ
	private int index;//ͼƬ�л���Ƶ��
	/** Hero���췽��*/
	public Hero(){
		image=ShootGame.hero0;//ͼƬ
		width=image.getWidth();//��
		height=image.getHeight();//��
		x=150;//�̶�150
		y=400;//�̶���400
		life=3;//��ֵΪ3
		doubleFire=0;//����ֵΪ0,��������
		images=new BufferedImage[]{ShootGame.hero0,ShootGame.hero1};
		index=0;
	}
	public void step() {
		index++;
		int a=index/10;//ÿ100m b=0,1
		int b=a%2;
		image=images[b];
		image=images[b];
	}
	/** �����ӵ�*/
	public Bullet[] shoot(){
		int xStep=this.width/4;//1/4Ӣ�ۻ��Ŀ��
		int yStep=20;//�ӵ���ɻ��ľ���
		if(doubleFire>0){//˫������
			Bullet[] bullets=new Bullet[2];
			bullets[0]=new Bullet(this.x+xStep,this.y-yStep);
			bullets[1]=new Bullet(this.x+3*xStep,this.y-yStep);
			doubleFire-=-2;
			return bullets;
		}else{//��������
			Bullet[] bullets=new Bullet[1];
			bullets[0]=new Bullet(this.x+2*xStep,this.y-yStep);
			return bullets;
		}
	}
	/** Ӣ�ۻ���������ƶ�x������x���� y����y����*/
	public void moveTo(int x,int y){
		this.x=x-this.width/2;
		this.y=y-this.height/2;
	}
	
	/**��ȡ��*/
	public int getLife(){
		return life;
	}
	/** ����*/
	public void subtractLife(){
		life--;
	}
	
	public void addLife(){
		life++;
	}
	/** ������ֵ*/
	public void addDoubleFire(){
		doubleFire+=40;
	}
	/** ���û���ֵ*/
	public void setDoubleFire(int doubleFire){
		this.doubleFire=doubleFire;
	}
	@Override
	public boolean outBounds() {
		return false;//����Խ��
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
