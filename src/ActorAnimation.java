import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class ActorAnimation extends Applet implements Runnable, KeyListener,MouseListener,MouseMotionListener{	
	private static final long serialVersionUID = 1L;
	protected int screen_flag=0;//��Ļ״̬��0Ϊ��ʼ������Ϊ�ؿ���-1Ϊ��Ϸ����
	protected score score_now=new score();
	protected all_fish f=new all_fish();
	protected nimo n;//��ҿ��ƽ�ɫ
	start screen_0=new start();//��Ϸ��ʼ����
	game screen_1=new game();//��Ϸ���л���
	stop screen_f1=new stop();//��Ϸ��������
	Thread newThread;    //�������̶߳���
	Image offScreen;      //�λ���
	Graphics offScreenGraphics;   //�λ����ϵ�ͼ�ι��߶���
	AudioClip audio1;//��Ч
	AudioClip audio2;
	AudioClip audio3;
    private int count=0;//��ʱ��
    public void init(){    
    	this.setSize(1200, 720);
    	offScreen = this.createImage(getWidth(), getHeight());
    	offScreenGraphics = offScreen.getGraphics();//�����Ǹ����ڣ�Ҳ����һ�������¼�Դ����Ҫ��Ӽ����¼�������¼�������ƶ���������
    	this.addKeyListener(this);
    	this.addMouseListener(this);
    	this.addMouseMotionListener(this);
    	this.setFocusable(true);    //�ñ���Ϸ���ڻ�ý���
    	
		//music
		this.screen_0.audio=getAudioClip(getCodeBase(),"music/start.wav");
		this.screen_0.audio.loop();
		this.screen_1.audio=getAudioClip(getCodeBase(),"music/bgsound.wav");
		this.audio1=getAudioClip(getCodeBase(),"music/yurushui.wav");
		this.audio2=getAudioClip(getCodeBase(),"music/eat.wav");
		this.audio3=getAudioClip(getCodeBase(),"music/up.wav");
    }
    public void start(){
    	newThread = new Thread(this);
    	newThread.start();
    }
    public void stop(){
    	newThread = null;
    }
    //��дApplet���update��������ֹ��ˢ�²���
    public void update(Graphics g){
    	paint(g);
    }
    //�������Ĺ��ܱ�úܵ���
    public void paint(Graphics g){ 
    	//���ȶԴλ�������
    	offScreenGraphics.setColor(Color.white);
    	offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
    	if(screen_flag==-1) {
    		//��������
    		screen_f1.show(offScreenGraphics);
        	g.drawImage(offScreen,0,0,this);
    	}
    	if(screen_flag==0) {
    		//��������
    		screen_0.show(offScreenGraphics);
    		g.drawImage(offScreen,0,0,this);
    	}
    	if(screen_flag>=1) {
    		//����Ϸ������Ⱦ���λ�����
    		screen_1.show(offScreenGraphics,this);
	    	g.drawImage(offScreen,0,0,this);
	    	if(score_now.score==100) {
	    		this.screen_flag=10;
	    	}
	    	if(score_now.score%20==19&&this.screen_flag>=0) {
				System.out.println("������");
				this.screen_flag++;
				score_now.score++;
				System.out.print(this.screen_flag);
				this.audio3.play();
			}
    	}
    }
	@Override
	public void run() {
		while( newThread != null ){    //��Ϸ����ѭ��
			if(screen_flag>0) {
			//��ʼ��Ϸ
			count++;
			//����
			n.update();
	    	//��ײ���
			n.collision(this);
			f.update_fish(this);
			f.add_fish(this);
			}
			//��Ϸ������ͣ
	    	try {
				Thread.sleep(24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();//����ϵͳ�ػ�
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//��ʼ��Ϸ
		if(screen_flag==0)
		{
			int mx = (int) e.getX();
			int my = (int) e.getY();
			System.out.println(mx+" "+my);
			if(mx<750&&mx>450&&my<650&&my>350) {
				this.screen_0.audio.stop();
				n=new nimo(600,0,0,5,2,"1.png","2.png");//�½���ҽ�ɫ
				this.audio1.play();
				screen_flag=1;
			}
		}
		//������Ϸ
		if(screen_flag==-1) {
			int mx = (int) e.getX();
			int my = (int) e.getY();
			System.out.println(mx+" "+my);
			if(mx<700&&mx>580&&my<450&&my>300) {
				screen_flag=0;this.score_now.score=0;
			}
		}
		if(screen_flag>=1) {
			int mx = (int) e.getX();
			int my = (int) e.getY();
			if(mx>1130&&my<70) {
				screen_flag=0;//��ͣ���ص���ʼ���棬��������
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		//��Ϸ��ʼ֮��С��׷������ƶ����ƶ�
		if(this.screen_flag>0) {
			int x = n.cx;
	        int y = n.cy;
	        if(x!=e.getX()) {
	        	n.sx=(e.getX()-x)/15;
	        	n.sy=(e.getY()-y)/15;
	        	if(x<e.getX()) {
	        		n.direction=0;
	        	}
	        	if(x>e.getX()) {
	        		n.direction=1;
	        	}
	        }
		}
	}
}
