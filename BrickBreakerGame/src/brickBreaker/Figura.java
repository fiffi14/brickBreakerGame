package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {
	
	protected Scena scene;
	
	private double x,y;
	private Color color;
	
	public Figura(Scena s, double x, double y, Color c) {
		this.x=x;
		this.y=y;
		this.scene=s;
		this.setCol(c);
		scene.addFigura(this);
		
	}
	
	public void evictFigura() {
		scene.removeFigura(this);
		scene.repaint();
	}
	
	public abstract void drawFigura(Graphics g);
	public abstract char getSign();
	public abstract void updatePosition(double dx, double dy);

	public Color getColor() {
		return color;
	}

	public void setCol(Color color) {
		this.color = color;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
