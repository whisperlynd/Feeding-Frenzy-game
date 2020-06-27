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
	protected int screen_flag=0;//屏幕状态：0为起始，正数为关卡，-1为游戏结束
	protected score score_now=new score();
	protected all_fish f=new all_fish();
	protected nimo n;//玩家控制角色
	start screen_0=new start();//游戏开始画面
	game screen_1=new game();//游戏进行画面
	stop screen_f1=new stop();//游戏结束画面
	Thread newThread;    //代表新线程对象
	Image offScreen;      //次画面
	Graphics offScreenGraphics;   //次画面上的图形工具对象
	AudioClip audio1;//音效
	AudioClip audio2;
	AudioClip audio3;
    private int count=0;//计时器
    public void init(){    
    	this.setSize(1200, 720);
    	offScreen = this.createImage(getWidth(), getHeight());
    	offScreenGraphics = offScreen.getGraphics();//主类是个窗口，也就是一个键盘事件源，需要添加键盘事件、鼠标事件、鼠标移动的倾听者
    	this.addKeyListener(this);
    	this.addMouseListener(this);
    	this.addMouseMotionListener(this);
    	this.setFocusable(true);    //让本游戏窗口获得焦点
    	
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
    //重写Applet类的update方法，禁止它刷新残留
    public void update(Graphics g){
    	paint(g);
    }
    //本方法的功能变得很单纯
    public void paint(Graphics g){ 
    	//首先对次画面清屏
    	offScreenGraphics.setColor(Color.white);
    	offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());
    	if(screen_flag==-1) {
    		//结束画面
    		screen_f1.show(offScreenGraphics);
        	g.drawImage(offScreen,0,0,this);
    	}
    	if(screen_flag==0) {
    		//开屏动画
    		screen_0.show(offScreenGraphics);
    		g.drawImage(offScreen,0,0,this);
    	}
    	if(screen_flag>=1) {
    		//将游戏世界渲染到次画面上
    		screen_1.show(offScreenGraphics,this);
	    	g.drawImage(offScreen,0,0,this);
	    	if(score_now.score==100) {
	    		this.screen_flag=10;
	    	}
	    	if(score_now.score%20==19&&this.screen_flag>=0) {
				System.out.println("升级了");
				this.screen_flag++;
				score_now.score++;
				System.out.print(this.screen_flag);
				this.audio3.play();
			}
    	}
    }
	@Override
	public void run() {
		while( newThread != null ){    //游戏动画循环
			if(screen_flag>0) {
			//开始游戏
			count++;
			//更新
			n.update();
	    	//碰撞检测
			n.collision(this);
			f.update_fish(this);
			f.add_fish(this);
			}
			//游戏动画暂停
	    	try {
				Thread.sleep(24);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();//请求系统重画
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
		//开始游戏
		if(screen_flag==0)
		{
			int mx = (int) e.getX();
			int my = (int) e.getY();
			System.out.println(mx+" "+my);
			if(mx<750&&mx>450&&my<650&&my>350) {
				this.screen_0.audio.stop();
				n=new nimo(600,0,0,5,2,"1.png","2.png");//新建玩家角色
				this.audio1.play();
				screen_flag=1;
			}
		}
		//结束游戏
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
				screen_flag=0;//暂停，回到开始画面，分数保留
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
		//游戏开始之后小鱼追逐鼠标移动而移动
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
