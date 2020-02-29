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

public class ShootGame extends JPanel{//���
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
	
	public Hero hero=new Hero();//Ӣ�ۻ�����
	private FlyingObject[] flyings={};//�������飨�л�+�۷䣩
	private Bullet[] bullets={};//�ӵ�����
	
	public static final int START=0;//����״̬
	public static final int RUNNING=1;//����
	public static final int PAUSE=2;//��ͣ
	public static final int GAME_OVER=3;//����
	private int state=0; //�洢��ǰ״̬
	
	static { // ��̬����飬��ʼ��ͼƬ��Դ
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
		/** ������ɵ��˶��󣨵л���С�۷䣩*/
		public static FlyingObject nextOne(){
			Random rand=new Random();
			int type=rand.nextInt(20);//����[0,20)�����
			if(type==0){
				return new Bee();//��Ϊ0�򷵻��۷����
			}else{
				return new Airplane();//1��19�򷵻صл�����
			}
			
		}
		private Timer timer;//������ʱ������
		private int intervel=10;//��ʱ����ʱ����(�Ժ���Ϊ��λ)
		int flyEnteredIndex=0;//�����볡����
		/** �����볡���л���С�۷�)*/
        public void enterAction(){//10������һ��
        	flyEnteredIndex++;
        	if(flyEnteredIndex%40==0){//ÿ400������һ��
        		FlyingObject o=nextOne();
        		flyings=Arrays.copyOf(flyings, flyings.length+1);//����
        		flyings[flyings.length-1]=o;//��������뵽������
        	}
			
		}
        /** ��������һ��*/
        public void stepAction(){
        	hero.step();
        	for(FlyingObject f:flyings){
        		f.step();
        	}
        	for(Bullet b:bullets){
        		b.step();
        	}
        }
        int shootIndex=0;//�ӵ��������
        /** Ӣ�ۻ������ӵ�--�ӵ��볡*/
        public void shootAction(){
        	shootIndex++;
        	if(shootIndex%30==0){
        		Bullet[] bs=hero.shoot();
        		bullets=Arrays.copyOf(bullets, bullets.length+bs.length);
        		System.arraycopy(bs,0,bullets, bullets.length-bs.length,bs.length);
        	}
        }
        int score=0;
		/**�����ӵ������е��˵���ײ */
        public void bangAction(){
        	for(Bullet b:bullets){
        		bang(b);//
        	}
        }
        /** һ���ӵ������е��˵���ײ*/
        public void bang(Bullet bullet){
        	int index=-1;
        	for(int i=0;i<flyings.length;i++){
        		FlyingObject obj=flyings[i];
        		if(obj.shootBy(bullet)){
        			index=i;
        			break;
        		}
        	}
        	if(index!=-1){//��ײ����
        		FlyingObject one=flyings[index];
        		if(one instanceof Enemy){//�л�
        			Enemy e=(Enemy)one;
        			score+=e.getScore();
        		}
        		if(one instanceof Award){//����
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
        		flyings=Arrays.copyOf(flyings, flyings.length-1);//����
        	}
        }
        
        public void outOfBoundsAction(){
        	//Խ�������
        	FlyingObject[] flyingLives=new FlyingObject[flyings.length];//���ŵ�
        	int index=0;
        	for(int i=0;i<flyings.length;i++){
        		FlyingObject obj=flyings[i];
        		if(!obj.outBounds()){//��Խ��
        			flyingLives[index]=obj;//�洢��flyingLives
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
        /** �����Ϸ����*/
        public void checkGameOverAction(){
        	if(isGameOver()){//����Ϸ����
        		state=GAME_OVER; 
        	}
        }
        /** �ж���Ϸ�Ƿ����*/
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
        			//����ײ�ĵ���ɾ��
        			FlyingObject t=flyings[index];
        			flyings[index]=flyings[flyings.length-1];
        			flyings[flyings.length-1]=t;
        			flyings=Arrays.copyOf(flyings, flyings.length-1);
        		}
        	}
        	return hero.getLife()<=0;
        }
        
		/** ���������̿���*/
		public void action(){
			//�������¼�
			MouseAdapter l=new MouseAdapter() {
				/** ����ƶ��¼�*/
				public void mouseMoved(MouseEvent e) {
					if(state==RUNNING){
					int x=e.getX();//��ȡ����x����
					int y=e.getY();//��ȡ����y����
					hero.moveTo(x, y);//Ӣ�ۻ�������ƶ�
					}
				}
				public void mouseClicked(MouseEvent e) {//������¼�
					switch (state) {//�жϵ�ǰ״̬
					case START:
						state=RUNNING;
						break;
					case GAME_OVER:
						flyings = new FlyingObject[0]; // ��շ�����
						bullets = new Bullet[0]; // ����ӵ�
						hero = new Hero(); // ���´���Ӣ�ۻ�
						score = 0; // ��ճɼ�
						state = START; // ״̬����Ϊ����
					
					}
				}
				public void mouseExited(MouseEvent e) {//����ƿ��¼�
					if(state==RUNNING){
						state=PAUSE;
					}
				}
				public void mouseEntered(MouseEvent e) {//�����¼�
					if(state==PAUSE){
						state=RUNNING;
					}
				}
			};
			
			this.addMouseListener(l);//�������һ�����
			this.addMouseMotionListener(l);//������껬��
			timer=new Timer();//������ʱ������
			timer.schedule(new TimerTask(){
				public void run() {
					if(state==RUNNING){
					enterAction();//�����볡
					stepAction();//��������һ��
					shootAction();//Ӣ�ۻ����ӵ�
					bangAction();//�ӵ��͵��˵���ײ
					outOfBoundsAction();//ɾ���������
					checkGameOverAction();//�����Ϸ����  
					}
					repaint();
				}}, intervel,intervel);//��һ��intervel:������������һ�θ��µļ��
										//�ڶ���intervel:ÿ�θ��µļ��
		}
		
		//g������
		public void paint(Graphics g) {
			g.drawImage(background,0,0,null);//���ĸ���������Ҫ�ܣ��������޹�
			paintHero(g);
			paintFlyingObjects(g);
			paintBullets(g);
			paintScore(g);
			paintState(g);//��״̬
		}
		//���ӵ�
	private void paintBullets(Graphics g) {
			for(Bullet b:bullets){
				g.drawImage(b.image,b.x,b.y,null);
			}
			
		}
	//������
	private void paintFlyingObjects(Graphics g) {
			for(FlyingObject f:flyings){
				g.drawImage(f.image,f.x,f.y,null);
			}
			
		}
	//��Ӣ�ۻ�
	private void paintHero(Graphics g) {
			g.drawImage(hero.image,hero.x,hero.y,null);
			
		}
	//���ֺͻ���
	private void paintScore(Graphics g){
		g.setColor(new Color(0xFF0000));//������ɫ--��ɫ
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));//����,�Ӵ�,�ֺ�
		g.drawString("SCORE: "+score, 10, 25);
		g.drawString("LIFE: "+hero.getLife(), 10, 45);
	}
	//��״̬
	public void paintState(Graphics g){
		switch (state) {//�ж�״̬
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
		frame.add(game);//�������ӵ�������
		frame.setSize(WIDTH,HEIGHT);
		frame.setAlwaysOnTop(true);//���ǾӶ�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����Ĭ�Ϲرղ���
		frame.setLocationRelativeTo(null);//����λ������룬Ĭ�����λ�þ����м��
		frame.setVisible(true);//1.���ô��ڿɼ� 2.�������paint()����
		game.action();//����ִ��
	}

}
