import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class start {
	AudioClip audio;
	Image bg=Toolkit.getDefaultToolkit().getImage("images/bg.jpg");
	Image button=Toolkit.getDefaultToolkit().getImage("images/button.png");
	start(){
	}
	public void show(Graphics g){
		g.drawImage(bg, 0, 0, 1200, 720, null);
		g.drawImage(button, 450, 400, null);
	}
}
