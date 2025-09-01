package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class Cigla extends AktivnaFigura {

	private final int moveY = 5;
	private boolean hit = false;
	private int ciglaWidth, ciglaHeigth;
	
	//PREDEFINISATI KOORDINATE DA SE STVARA NA TACNO Y ALI PROIZV X npr
	
	public Cigla(Scena s, double x, double y, int p, int w, int h) {
		super(s, x, y, Color.red, p);
		this.ciglaHeigth = h;
		this.ciglaWidth = w;
		this.threadFigure.start();
	}
	

	@Override
	public void drawFigura(Graphics g) {
		
		g.setColor(this.getColor());
		int x[] = {(int)getX()-ciglaWidth/2, (int)getX()+ciglaWidth/2, (int)getX()+ciglaWidth/2, (int)getX()-ciglaWidth/2};
		int y[] = {(int)getY()-ciglaHeigth/2, (int)getY()-ciglaHeigth/2, (int)getY()+ciglaHeigth/2, (int)getY()+ciglaHeigth/2};
		//g.fillRect((int)(getX())-ciglaWidth/2, (int)getY()-ciglaHeigth/2, ciglaWidth, ciglaHeigth);
		g.fillPolygon(x, y, 4);
	}

	@Override
	public char getSign() {
		return 'C';
	}
	
	
	public int getCiglaWidth() {
		return ciglaWidth;
	}


	public int getCiglaHeigth() {
		return ciglaHeigth;
	}


	public void setCiglaWidth(int ciglaWidth) {
		this.ciglaWidth = ciglaWidth;
	}


	public void setCiglaHeigth(int ciglaHeigth) {
		this.ciglaHeigth = ciglaHeigth;
	}

	
	public boolean hitIndicator() {
		return hit;
	}
	public synchronized void setHitIndicator() {
		this.hit = true;
		this.setCol(Color.gray);
		this.commence();
		scene.repaint();
	}

	@Override
	public void updatePosition(double dx, double dy) {
		if(hitIndicator()==true) {
			int endY = scene.getHeight();
			if(this.getY()-this.ciglaHeigth/2>=endY) {
				this.evictFigura();
			}
			else {
				double x = getX(); double y = getY();
				setX(x+dx);
				setY(y+moveY);
			}
		}
	}
	
	@Override
	public void run() {
		//System.out.println("Usao u run cigle");
		try {
			while(!Thread.interrupted()) {
				
				synchronized (this) {
					while(!active) wait();
				}
				updatePosition(0,moveY);
				scene.repaint();
				Thread.sleep(getPeriod());
			}
			
		} catch (InterruptedException e) {}
	}
	
	
	@Override
	public String toString() {
		return "Cigla ";
	}
	public static void main(String[] args) {
		Cigla cigla = new Cigla(null, 0, 0, 4,4,5);
		System.out.println(cigla.getColor());
	}
	
}
