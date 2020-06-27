import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class game {
	AudioClip audio;
	Image stop=Toolkit.getDefaultToolkit().getImage("images/stop.png");
	Image water=Toolkit.getDefaultToolkit().getImage("images/water_bg.jpg");
	Image tong=Toolkit.getDefaultToolkit().getImage("images/tong.png");
	public void show(Graphics g,ActorAnimation zhu){
    	g.drawImage(water, 0,0, null);
    	g.drawImage(stop, 1130, 4, null);
    	for( int i = 0; i < zhu.f.actors.size(); i++ ){
    		FishActor s = zhu.f.actors.get(i);    		
    		s.draw(g);    //传入次画面上的图形工具对象
    	}   
    	zhu.n.draw(g);
    	zhu.score_now.show(g);
    	if(zhu.screen_flag==1) {}
    	if(zhu.screen_flag==2) {
    		zhu.n.state=3;
    		zhu.n.image_y = Toolkit.getDefaultToolkit().getImage("images/fish3"+"/"+"1.png");
    		zhu.n.image_z = Toolkit.getDefaultToolkit().getImage("images/fish3"+"/"+"2.png");
    	}
    	if(zhu.screen_flag==3) {
    		zhu.n.state=4;
    		zhu.n.image_y = Toolkit.getDefaultToolkit().getImage("images/fish4"+"/"+"1.png");
    		zhu.n.image_z = Toolkit.getDefaultToolkit().getImage("images/fish4"+"/"+"2.png");
    		
    	}
    	if(zhu.screen_flag==4) {
    		zhu.n.state=5;
    		zhu.n.image_y = Toolkit.getDefaultToolkit().getImage("images/fish5"+"/"+"1.png");
    		zhu.n.image_z = Toolkit.getDefaultToolkit().getImage("images/fish5"+"/"+"2.png");
    		
    	}
    	if(zhu.screen_flag==5) {
    		zhu.n.state=6;
    		zhu.n.image_y = Toolkit.getDefaultToolkit().getImage("images/fish6"+"/"+"1.png");
    		zhu.n.image_z = Toolkit.getDefaultToolkit().getImage("images/fish6"+"/"+"2.png");
    		
    	}
    	if(zhu.screen_flag==10) {
			//通关
			g.drawImage(tong, 500, 100, null);
		}
    	
	}
}
