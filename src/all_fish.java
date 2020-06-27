import java.util.Vector;

public class all_fish {
	public Vector<FishActor> actors = new Vector<FishActor>();//鱼群的数组
	private int count=0;
	public void add_fish(ActorAnimation zhu) {
		int wt,ht;
		count++;
		if(count%100==99) {
			count=0;
    		wt=(int) (Math.random()*zhu.getWidth());
    		ht=(int) (Math.random()*zhu.getHeight());
    		FishActor b1=new fish(-40, ht,10, 0,zhu.n.state-1,"1.png","2.png");
			zhu.f.actors.add(b1);
			wt=(int) (Math.random()*zhu.getWidth());
    		ht=(int) (Math.random()*zhu.getHeight());
			FishActor b3=new bigfish(-40, ht,5, 0,zhu.n.state+1,"3.png","4.png");
			actors.add(b3);
			wt=(int) (Math.random()*zhu.getWidth());
    		ht=(int) (Math.random()*zhu.getHeight());
			FishActor b4=new bigfish(1200, ht,-5, 0,zhu.n.state+1,"3.png","4.png");
			actors.add(b4);
    	}
		if(count%100==30) {
			wt=(int) (Math.random()*zhu.getWidth());
    		ht=(int) (Math.random()*zhu.getHeight());
			FishActor b5=new fish(1200, ht,-10, 0,1,"1.png","2.png");
			zhu.f.actors.add(b5);
			wt=(int) (Math.random()*zhu.getWidth());
    		ht=(int) (Math.random()*zhu.getHeight());
    		
			FishActor b2=new fish(1200, ht,-10, 0,zhu.n.state-1,"1.png","2.png");
			zhu.f.actors.add(b2);
		}
	}
	public void update_fish(ActorAnimation zhu) {
		for( int i = 0; i < zhu.f.actors.size(); i++ )
		{
			FishActor a = zhu.f.actors.get(i);
    		a.update();	   
    		if(count%40==0) {
    		a.update_speed(zhu);}
    	} 
		//出框检测
		for( int i = 0; i < zhu.f.actors.size(); i++ ){   
    		FishActor s = zhu.f.actors.get(i);  
    		if(s.cx>1200||s.cy>720||s.cx<-40||s.cy<-40) {
    		zhu.f.actors.remove(s);}
    	} 
	}
}
