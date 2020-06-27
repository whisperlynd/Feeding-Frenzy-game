import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//游戏角色类
public abstract class FishActor {
	public int cx,cy;   //坐标
	public int zx,zy;	//鱼嘴的坐标
	public int w,h;
	public int state;//鱼的大小
	protected String imageName_you;  //形象图片的名字（方向朝右）
	protected String imageName_zuo;  //形象图片的名字（方向朝左）
	protected Image image_y;      //形象图片      
	protected Image image_z;
	ClassLoader classLoader= this.getClass().getClassLoader();
	protected AudioClip steps=Applet.newAudioClip(classLoader.getResource("music/走路声.wav"));
	public int sx,sy;   //速度
	protected int direction;  //方向（左右）0为右
	public FishActor(int cx,int cy,int sx,int sy,int state,String imageName_you,String imageName_zuo){
		this.cx = cx;
		this.cy = cy;
		this.zx = cx;
		this.zy = cy;
		this.sx = sx;
		this.sy = sy;
		this.state=state;
		this.direction = 0;     //缺省方向为右
		if(sx<0)this.direction=1;
		if(sx>0)this.direction=0;
		this.imageName_you = imageName_you;
		this.imageName_zuo = imageName_zuo;
		image_y = Toolkit.getDefaultToolkit().getImage("images/fish"+String.valueOf(state)+"/"+imageName_you);
		image_z = Toolkit.getDefaultToolkit().getImage("images/fish"+String.valueOf(state)+"/"+imageName_zuo);
		System.out.println("images/fish"+String.valueOf(state)+"/"+imageName_you);
	}
	public void update(){
    	cx += sx;
    	cy += sy;
    	zx = cx;
		zy = cy;
		if(sx<0)this.direction=1;
		if(sx>0)this.direction=0;
	}
    abstract public void draw(Graphics g);
	//键盘控制相应方法
	public void keyPressed(int key){
	}
	public void keyReleased(int key) {
	}
	public FishActor collideActor(FishActor s){
		System.out.println("peng!");
		System.out.println(zx+" "+s.zx);
		if(s.state>this.state) {
			if (s.zx<zx+20 && s.zx>zx-20
	    			&&zy>=s.zy-20 && zy<=s.zy+20) {
    			return this;
			}
    	}
		if(s.state<=this.state) {
    	if (zx-10<s.cx+s.w && zx+10>s.cx
    			&&zy+10>s.cy && zy-10<=s.cy+s.h) {
    		System.out.println("p!");
    			return s;
    		}
    	}
        return null;
    }
	protected abstract void update_speed(ActorAnimation actorAnimation);
}
