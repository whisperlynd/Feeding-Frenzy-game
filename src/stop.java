import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class stop {
	Image bg=Toolkit.getDefaultToolkit().getImage("images/bg1.jpg");
	Image lose=Toolkit.getDefaultToolkit().getImage("images/lose.png");
	Image lose2=Toolkit.getDefaultToolkit().getImage("images/lose2.png");
	Image again=Toolkit.getDefaultToolkit().getImage("images/start_again.png");
	public void show(Graphics g){
		g.drawImage(bg, 0, 0, 1200, 720, null);
		g.drawImage(lose, 530, 110, null);
		g.drawImage(lose2,620, 110,null);
		g.drawImage(again, 580, 300,null);
	}
}
