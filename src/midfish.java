import java.awt.Graphics;
public class midfish extends FishActor{
	public midfish(int cx,int cy,int sx,int sy,int state,String imageNameyou,String imageNamezuo){
		super(cx,cy, sx, sy, 3,imageNameyou,imageNamezuo);
		this.w=120;
		this.h=100;
		this.state=3;
	}
	public void update() {
    	cx += sx;
    	cy += sy;
    	zx=cx+100;
    	zy=cy+50;
	}
	public void update_speed(ActorAnimation game) {
		sx=(int) (Math.random()*6)+3;
    	sy=(int) (Math.random()*4)-2;
    	
    	if(this.direction==0)//you
		{
			if(sx<0&&cx>game.n.zx-200&&cx<game.n.zx+200&&cy<game.n.zy+100&&cy>game.n.zy-100) {
				sx=(int) (Math.random()*5+10);
				sy=(int) (Math.random()*20);
			}
		}
		if(this.direction==1)//you
		{
			if(sx>0&&cx<game.n.zx+200&&cx>game.n.zx-200&&cy<game.n.zy+100&&cy>game.n.zy-100) {
			sx=-((int) (Math.random()*5)+10);
			sy=-(int) (Math.random()*20);
			}
		}
	}
	public void draw(Graphics g) {
		if(this.direction==0)
			g.drawImage(image_y, cx,cy, null);
		if(this.direction==1)
			g.drawImage(image_z, cx,cy, null);
	}
}
