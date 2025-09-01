package brickBreaker;

import java.awt.Color;

public abstract class AktivnaFigura extends Figura implements Runnable{

	private int period;
	
	
	public AktivnaFigura(Scena s, double x, double y, Color c, int p) {
		super(s, x, y, c);
		this.period = p;
		
		
	}
	
	protected boolean active = false;
	
	protected Thread threadFigure = new Thread(this);
	
	
	
	
	
	



	public synchronized void go() {
		//System.out.println("Usao u go za start");
		if(threadFigure==null) {
			threadFigure.start();
		}
	}
	
	public synchronized void commence() {
		//System.out.println("Usao u commence");
		active = true;
		notify();
	}
	
	public synchronized void pause() {
		active=false;
	}
	public synchronized void finish() {
		if(threadFigure!=null) {
			active=false;
			threadFigure.interrupt();
		}
			
	}
	public int getPeriod() {
		return period;
	}
	
	@Override
	public void evictFigura() {
		super.evictFigura();
		this.finish();
	}
	
	
	
}
