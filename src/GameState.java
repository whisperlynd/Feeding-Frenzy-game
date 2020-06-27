import java.awt.event.MouseEvent;

public interface GameState {
	public void update();
	public void draw();
	public void transactionState();
	public void keyPressed();
	public void mouseClicked(MouseEvent me);
}
