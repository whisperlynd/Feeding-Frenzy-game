import java.awt.Graphics;
public class bigfish extends FishActor{
	
	public bigfish(int cx,int cy,int sx,int sy,int state,String imageNameyou,String imageNamezuo){
		super(cx,cy, sx, sy, state,imageNameyou,imageNamezuo);
		this.w=300;
		this.h=150;
	}
	public void update() {
    	cx += sx;
    	cy += sy;
    	if(this.direction==0) {
	    	zx=cx+200;
	    	zy=cy+75;}
    	if(this.direction==1) {
    		zx=cx+20;
	    	zy=cy+75;
    	}
    	
	}
	public void update_speed(ActorAnimation game) {
		if(this.direction==0) {
			sx=(int) (Math.random()*10)+5;
	    	sy=(int) (Math.random()*6)-3;
			}
			if(this.direction==1) {
				sx=-(int) (Math.random()*10)-5;
		    	sy=(int) (Math.random()*6)-3;
				}
    	if(this.direction==0)//you
		{
			if(sx<0&&cx>game.n.zx-200&&cx<game.n.zx+200&&cy<game.n.zy+100&&cy>game.n.zy-100) {
				sx=(int) (Math.random()*8+4);
				sy=(int) (Math.random()*6-3);
			}
		}
		if(this.direction==1)//you
		{
			if(sx>0&&cx<game.n.zx+200&&cx>game.n.zx-200&&cy<game.n.zy+100&&cy>game.n.zy-100) {
			sx=-((int) (Math.random()*5)+10);
			sy=-((int) (Math.random()*6)-3);
			}
		}
	}
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		if(this.direction==0)
			g.drawImage(image_y, cx,cy, null);
		if(this.direction==1)
			g.drawImage(image_z, cx,cy, null);
		g.drawRect(zx-10, zy+10, 20, 20);
	}
	
	public void collision(ActorAnimation game) {
		// TODO Auto-generated method stub
		//检测与其他演员相撞
		/*
		for( int i = 0; i < game.actors.size(); i++ ){    //碰撞检测
    		Actor s = game.actors.get(i);    	
    		if(s instanceof ball) {
    			if(s.collideActor(this)==this) {
    			game.actors.remove(this);
    			game.score++;
    			}
    		}
    	} 
    	*/
	}
}
