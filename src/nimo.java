import java.awt.Graphics;

public class nimo extends FishActor{
	public nimo(int cx,int cy,int sx,int sy,int state,String imageNameyou,String imageNamezuo){
		super(cx,cy, sx, sy,state,imageNameyou,imageNamezuo);
	}
	public void update() {
    	cx += sx;
    	cy += sy;
    	if(this.direction==1) {
    		zx=cx;
    		zy=cy+15;}
    	if(this.direction==0) {
    		zx=cx+100;
    		zy=cy+15;
    	}
	}
	public void collision(ActorAnimation game) {
		// TODO Auto-generated method stub
		System.out.println("collide");
		for( int i = 0; i < game.f.actors.size(); i++ ){    //Åö×²¼ì²â
    		FishActor s = game.f.actors.get(i);
    		System.out.println(game.f.actors.size());
    		System.out.println(this.state);
    		System.out.println(s.state);
    		if(s.state<=this.state) {
    			if(this.collideActor(s)==s) {
        			game.f.actors.remove(s);
        			game.score_now.score++;
        			game.audio2.play();
        		}
    		}
    		if(s.state>this.state) {
        		if(this.collideActor(s)==this) {
        				System.out.println("lose");
        				game.screen_flag=-1;
            		}
    		}
		}
    	
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		  //this.px=mx-this.w/2;
		  /*
		  this.py=h-this.h;
		  */
		System.out.println("fish");
		if(this.direction==0)
			g.drawImage(image_y, cx,cy, null);
		if(this.direction==1)
			g.drawImage(image_z, cx,cy, null);
		g.drawRect(zx-10, zy+10, 20, 20);
	}
	
	@Override
	protected void update_speed(ActorAnimation actorAnimation) {
		// TODO Auto-generated method stub
		
	}
}
