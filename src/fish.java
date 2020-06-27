import java.awt.Graphics;
public class fish extends FishActor{
	public fish(int cx,int cy,int sx,int sy,int state,String imageNameyou,String imageNamezuo){
		super(cx,cy, sx, sy, state,imageNameyou,imageNamezuo);
		this.w=120;
		this.h=60;
	}
	public void update() {
    	cx += sx;
    	cy += sy;
    	zx=cx+100;
    	zy=cy+25;
	}
	public void update_speed(ActorAnimation game) {
		if(this.direction==0) {
		sx=(int) (Math.random()*10)+5;
    	sy=(int) (Math.random()*15)-5;
		}
		if(this.direction==1) {
			sx=-(int) (Math.random()*10)-5;
	    	sy=(int) (Math.random()*15)-5;
			}
    	if(this.direction==0)//you
		{
			if(cx>game.n.zx-300&&cx<game.n.zx+300&&cy<game.n.zy+200&&cy>game.n.zy-200) {
				sx=-(int) (Math.random()*10);
				sy=(int) (Math.random()*10);
			}
		}
		if(this.direction==1)//you
		{
			if(cx<game.n.zx+300&&cx>game.n.zx-300&&cy<game.n.zy+200&&cy>game.n.zy-200) {
			sx=((int) (Math.random()*10));
			sy=-(int) (Math.random()*10);
			}
		}
	}
	public void draw(Graphics g) {
		if(this.direction==0)
		g.drawImage(image_y, cx,cy, null);
		if(this.direction==1)
		g.drawImage(image_z, cx,cy, null);
		g.drawRect(zx-10, zy+10, 20, 20);
	}
}
