import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class score {
	protected int score=0;
	protected Image score_p;
	protected Image score_num;
	int lie=0;
	public score() {
		score_p = Toolkit.getDefaultToolkit().getImage("images/"+"score.png");
		score_num = Toolkit.getDefaultToolkit().getImage("images/"+"num.png");
		
	}
	public void show(Graphics g) {
		g.drawImage(score_p, 0+3,0+7, null);
		int score_temp=score;
		for(int i=0;score_temp>0;i++) {
			lie=score_temp%10;
			g.drawImage(score_num, 60+(2-i)*24, 0,84+(2-i)*24,30, 
				lie*24, 0,lie*24+24,30,null);
			score_temp=score_temp/10;
		}
		
	}
}
