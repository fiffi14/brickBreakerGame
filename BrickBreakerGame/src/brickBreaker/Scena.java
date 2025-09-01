package brickBreaker;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Scena extends Canvas{

	private ArrayList<Figura> listFigura = new ArrayList<Figura>();
	private Igrac igracFigura;
	private int clicked=0;
	Rectangle rectangleFrame;
	
	
	public Scena() {
		rectangleFrame = this.getBounds();
		
		
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getParent().requestFocus();
				if(MouseEvent.BUTTON1==e.getButton()) {
					
					Loptica loptica = new Loptica(Scena.this, igracFigura.getX(), igracFigura.getY()-igracFigura.getIgracHeigth(), 10, igracFigura.getIgracHeigth());
					loptica.go();
					repaint();
					loptica.commence();
					repaint();
				}
				
			}
		});
		
		
	}
	
	public void addFigura(Figura figura) {
		//System.out.println("dodato: "+figura);
		if(figura.getSign()=='I') {
			for (Figura f : listFigura) {
				if(f.getSign()=='I') return;
			}
			listFigura.add(figura);
			igracFigura=(Igrac) figura;
		}
		else {
			listFigura.add(figura);
		}
		
	}
	
	public Figura getFigura(int i) {
		if(listFigura.size()>0 && i>=0 && i<listFigura.size()) return listFigura.get(i);
		return null;
	}
	
	public void removeFigura(Figura figura) {
		listFigura.remove(figura);
	}
	
	public int getSz() {
		return listFigura.size();
	}
	 
	
	
	
	@Override
	public void paint(Graphics g) {
		for (Figura figura : listFigura) {
			figura.drawFigura(g);
		}
	}
	
	public synchronized void goScenaAktivneFigure() {
		System.out.println("Startovane niti iz scene");
		for (Figura figura : listFigura) {
			if(figura instanceof AktivnaFigura) 
				((AktivnaFigura) figura).go();
		}
	}
	public synchronized void finishScenaAktivneFigure() {
		
		for (Figura aktivnaFigura : listFigura) {
			if(aktivnaFigura instanceof AktivnaFigura) 
				((AktivnaFigura) aktivnaFigura).finish();
		}
		
	}
	
	

	public Igrac getIgracFigura() {
		return igracFigura;
	}
	
	public int getCiglaNo() {
		int br=0;
		for (Figura figura : listFigura) {
			if(figura.getSign()=='C') br++;
		}
		return br;
	}
	
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Lista: ");
		for (Figura figura : listFigura) {
			sBuilder.append(figura.toString());
		}
		return sBuilder.toString();
	}
	
	public static void main(String[] args) {
		Scena s = new Scena();
		Figura niz[] = {
			/*new Igrac(s, 5, 5, 10, 4),
			new Loptica(s, 4, 4, 5, 5),
			new Cigla(s, 40, 40, 20, 10, 10)*/
		};
		/*niz[1].evictFigura();
		System.out.println(s);
		System.out.println(s.getIgracFigura());
		System.out.println(s.getFigura(1));
		System.out.println(s.rectangleFrame);*/
		System.out.println(s);
	}
	
	
}
