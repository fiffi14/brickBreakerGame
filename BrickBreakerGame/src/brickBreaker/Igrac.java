package brickBreaker;

import java.awt.Color;
import java.awt.Graphics;

public class Igrac extends Figura {

	private int igracWidth, igracHeigth;
	
	public Igrac(Scena s, double x, double y, int w, int h) {
		super(s, x, y, Color.blue);
		this.igracWidth=w;
		this.igracHeigth=h;
	}

	public int getIgracWidth() {
		return igracWidth;
	}

	public int getIgracHeigth() {
		return igracHeigth;
	}

	public void setIgracWidth(int igracWidth) {
		this.igracWidth = igracWidth;
	}

	public void setIgracHeigth(int igracHeigth) {
		this.igracHeigth = igracHeigth;
	}

	@Override
	public void drawFigura(Graphics g) {
		
		g.setColor(this.getColor());
		int x[] = {(int)getX()-igracWidth/2, (int)getX()+igracWidth/2, (int)getX()+igracWidth/2, (int)getX()-igracWidth/2};
		int y[] = {(int)getY()-igracHeigth/2, (int)getY()-igracHeigth/2, (int)getY()+igracHeigth/2,(int)getY()+igracHeigth/2};
		g.fillPolygon(x, y, 4);
	}

	@Override
	public char getSign() {
		return 'I';
	}

	
	@Override
	public String toString() {
		return "Igrac ";
	}

	@Override
	public void updatePosition(double byX, double byY) {
		double x = getX();
		double y = getY();
		if(x-getIgracWidth()/2+byX>=0 && x+getIgracWidth()/2+byX<=scene.getWidth()) setX(x+byX);
		
		setY(y+byY);
	}
	

	
}
