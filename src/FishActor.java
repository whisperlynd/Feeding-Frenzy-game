import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

//��Ϸ��ɫ��
public abstract class FishActor {
	public int cx,cy;   //����
	public int zx,zy;	//���������
	public int w,h;
	public int state;//��Ĵ�С
	protected String imageName_you;  //����ͼƬ�����֣������ң�
	protected String imageName_zuo;  //����ͼƬ�����֣�������
	protected Image image_y;      //����ͼƬ      
	protected Image image_z;
	ClassLoader classLoader= this.getClass().getClassLoader();
	protected AudioClip steps=Applet.newAudioClip(classLoader.getResource("music/��·��.wav"));
	public int sx,sy;   //�ٶ�
	protected int direction;  //�������ң�0Ϊ��
	public FishActor(int cx,int cy,int sx,int sy,int state,String imageName_you,String imageName_zuo){
		this.cx = cx;
		this.cy = cy;
		this.zx = cx;
		this.zy = cy;
		this.sx = sx;
		this.sy = sy;
		this.state=state;
		this.direction = 0;     //ȱʡ����Ϊ��
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
	//���̿�����Ӧ����
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
