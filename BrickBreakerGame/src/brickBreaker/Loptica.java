package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Loptica extends AktivnaFigura{

	private int R;
	private Random random = new Random();
	private double dx =1, dy=0;
	private int periodNo = 0;
	
	 { random.setSeed(System.currentTimeMillis()); }
	public Loptica(Scena s, double x, double y, int p, int rr) {
		super(s, x, y, Color.GREEN, p);
		this.R = rr;
		getSpeed();
		this.threadFigure.start();
		//System.out.println("Kreirana loptica");
	}
	//ISCRTAJ PRVO CIGLU PA LOPTICU DA BI SE NASLA IZNAD NJE

	
	@Override
	public void drawFigura(Graphics graphics) {
		//System.out.println("Boji lopticu");
		graphics.setColor(this.getColor());
		graphics.fillOval((int)this.getX()-R/2,(int)this.getY()-R/2, R, R);
		
	}

	@Override
	public char getSign() {
		return 'L';
	}
	
	@Override
	public void run() {
		//System.out.println("Usao u run");
		try {
			while(!Thread.interrupted()) {
				
				synchronized (this) {
					while(!active) wait();
				}
				updatePosition(dx,dy);
				scene.repaint();
				Thread.sleep(getPeriod());
			}
			
		} catch (InterruptedException e) {}
	}
	

	@Override
	public void updatePosition(double byx, double byy) {
		periodNo++;
		if(periodNo==100) moreSpeed();
		double newX = getX(); double newY = getY();
		newX += byx; newY+=byy;
		Igrac igrac=scene.getIgracFigura();
		Cigla collisionCigla;
		for(int i=0; i<scene.getSz();i++) {
			if(scene.getFigura(i).getSign()=='L') continue;
			else if(scene.getFigura(i).getSign()=='C') {
				collisionCigla = (Cigla)scene.getFigura(i);
				if(collisionCigla.hitIndicator()) {collisionCigla=null; continue;}
				//gornja i donja ivica
				if(
					(newX>=collisionCigla.getX()-collisionCigla.getCiglaWidth()/2 && newX<=collisionCigla.getX()+collisionCigla.getCiglaWidth()/2 &&
					newY+R/2>=collisionCigla.getY()-collisionCigla.getCiglaHeigth()/2 && newY+R/2<=collisionCigla.getY()+collisionCigla.getCiglaHeigth()/2)
					||
					(newX>=collisionCigla.getX()-collisionCigla.getCiglaWidth()/2 && newX<=collisionCigla.getX()+collisionCigla.getCiglaWidth()/2 &&
					newY-R/2<=collisionCigla.getY()+collisionCigla.getCiglaHeigth()/2 && newY-R/2>=collisionCigla.getY()-collisionCigla.getCiglaHeigth()/2)
				) {
					collisionCigla.setHitIndicator(); dy *= -1; break;
				}
				
				if( //leva i desna ivica
					(newX+R/2<=collisionCigla.getX()+collisionCigla.getCiglaWidth()/2 && newX+R/2>=collisionCigla.getX()-collisionCigla.getCiglaWidth()/2 &&
					 newY>=collisionCigla.getY()-collisionCigla.getCiglaHeigth()/2 && newY<=collisionCigla.getY()+collisionCigla.getCiglaHeigth()/2)
					||
					(newX-R/2>=collisionCigla.getX()-collisionCigla.getCiglaWidth()/2 && newX-R/2<=collisionCigla.getX()+collisionCigla.getCiglaWidth() &&
					newY>=collisionCigla.getY()-collisionCigla.getCiglaHeigth()/2 && newY<=collisionCigla.getY()+collisionCigla.getCiglaHeigth()/2)
				) {
					collisionCigla.setHitIndicator(); dx *= -1; break;
				}
			}
			else if(scene.getFigura(i).getSign()=='I') {
				/*if(newX+R/2==igrac.getX()-igrac.getIgracWidth()/2 && newY+R/2==igrac.getY()-igrac.getIgracHeigth()/2) {
					dx *= -1; dy*= -1; break;
				}
				
				if(newX-R/2==igrac.getX()+igrac.getIgracWidth()/2 && newY+R/2==igrac.getY()-igrac.getIgracHeigth()/2) {
					dx *= -1; dy*= -1; break;
				}
				*/
				if(
					(newX>=igrac.getX()-igrac.getIgracWidth()/2 && newX<= igrac.getX()+igrac.getIgracWidth()/2 &&
					newY+R/2<=igrac.getY()+igrac.getIgracHeigth()/2 && newY+R/2>=igrac.getY()-igrac.getIgracHeigth()/2)
					||
					(newX>=igrac.getX()-igrac.getIgracWidth()/2 && newX<= igrac.getX()+igrac.getIgracWidth()/2 &&
					 newY-R/2<=igrac.getY()+igrac.getIgracHeigth()/2 && newY-R/2>=igrac.getY()-igrac.getIgracHeigth()/2)
				) {
					dy *= -1; break;
				}
				if(
					(newX+R/2<=igrac.getX()+igrac.getIgracWidth()/2 && newX+R/2>=igrac.getX()-igrac.getIgracWidth()/2 &&
					 newY>=igrac.getY()-igrac.getIgracHeigth()/2 && newY<= igrac.getY()+igrac.getIgracHeigth()/2)
					||
					(newX-R/2>=igrac.getX()-igrac.getIgracWidth()/2 && newX-R/2<=igrac.getX()+igrac.getIgracWidth()/2 &&
					 newY>=igrac.getY()-igrac.getIgracHeigth()/2 && newY<= igrac.getY()+igrac.getIgracHeigth()/2)
				) {
					dx *= -1; break;
				}
					
			}
			
		}
		
		
		
		
		//gornja ivica frejma
		if(newY-R/2<=0) dy *= -1;
		
		//leva ivica frejma
		if(newX-R/2<=0) dx *= -1;
		
		//desna ivica frejma
		if(newX+R/2>=scene.getWidth()) dx *= -1;
		
		//donja ivica frejma
		if(newY+R/2 >= scene.getHeight()) {
			//System.out.println("Brisem lopticu");
			this.evictFigura();
		}
		setX(newX);
		setY(newY);
	}
	
	//POCETNI UGAO BICE [45,60] ILI [-60,-45]
	private void getSpeed() { //PROMENI KAD BUDE SVE RADILO NA ==0
		while(Math.abs(Math.tan(dy/dx))<1||Math.abs(Math.tan(dy/dx))>1.74) {
			dx = -1+Math.random()*2; // [-1,1)
			if(dx==1) continue; 
			break;
			
		}
		while(Math.abs(Math.tan(dy/dx))<1||Math.abs(Math.tan(dy/dx))>1.74) {
			dy = Math.random()-1; // (-1,0]
			if(dy==-1) continue;
			break;
		}
		//System.out.println("dx= "+dx+", dy= "+dy);
	}
	
	private void moreSpeed() {
		//System.out.println("**POVECANA BRZINA**");
		//System.out.println(getX()+" "+getY()+" u updatePosition za lopticu");
		dx += dx*0.1;
		dy += dy*0.1;
		periodNo = 0;
	}
	
	@Override
	public String toString() {
		return "Loptica ";
	}
	
}
