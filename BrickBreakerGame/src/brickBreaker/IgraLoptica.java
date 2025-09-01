package brickBreaker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IgraLoptica extends Frame {
	

	private Scena scena = new Scena();
	private int ciglaNo = 15;
	private int rows = 3;
	
	
	//polja da se unese spolja koliko cigli treba da bude
	
	public IgraLoptica() {
		setBounds(500, 200, 300, 400);
		setResizable(false);
		setTitle("Loptica");
		addDialog();
		populateWindow();
		
		
		pack();
		
		
		
		addListeners();
		setVisible(true);
	}
	
	private void addDialog() {
		Dialog dialog = new Dialog(this, ModalityType.APPLICATION_MODAL);
		dialog.setTitle("ReadMe");
		dialog.add(new Label("Moguce je koristiti komande a i d"
				+ " zajedno sa levom i desnom strelicom." + " ||| You can use a-d"
				+ " along with left and right arrow. "
				, Label.CENTER));
				
		dialog.setBounds(700,200,300,500);
		dialog.setResizable(true);
		
		dialog.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dialog.dispose();
			}
		});
		dialog.pack();
		dialog.setVisible(true);
	}

	private void populateWindow() {
		
		scena.setSize(new Dimension(300,400));
		//scena.setBackground(Color.yellow);
		
		addCigle();
		
		new Igrac(scena, scena.getWidth()/2, scena.getHeight()-20, 30, 10);
		add(scena, BorderLayout.CENTER);
		
	}

	private void addCigle() {
		int columns, rest;
		if(ciglaNo%rows==0) {
			columns = ciglaNo/rows;
			rest = 0;
		} else {
			columns = ciglaNo/rows;
			rest = ciglaNo%rows;
			rows++;
		}
				
		int cw = scena.getWidth()/columns-2;
		int ch = 20;
		
		for(int i=0; i<rows; i++) {
			int tmp = ((ch+2)/2)*(2*i+1)+((i==0)?1:i);
			for (int j = 0; j < columns; j++) {
				new Cigla(scena, (scena.getWidth()/(columns*2))*(2*j+1), tmp , 100, cw, ch);
			}
		}
	}

	private void addListeners() {
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				scena.finishScenaAktivneFigure();
				dispose();
			}
		});
		
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_A || e.getKeyCode()==KeyEvent.VK_LEFT) {
					scena.getIgracFigura().updatePosition(-10, 0);
					scena.repaint();
				}
				else if(e.getKeyCode()==KeyEvent.VK_D || e.getKeyCode()==KeyEvent.VK_RIGHT) {
					scena.getIgracFigura().updatePosition(10, 0);
					scena.repaint();
				}
			}
		
		});
	}

	public static void main(String[] args) {
		new IgraLoptica();
	}
}
