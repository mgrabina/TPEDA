package front;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import controller.Listeners;

public class GUI {
	static JFrame ventana;
	public static final Integer ANCHO = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	public static final Integer ALTO = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	static GridLayout tablero;
	static JPanel[][] casilleros;
	public static final Integer TAMANIO=13;
	public static final Integer TAMANIOCASILLERO=ALTO/TAMANIO;
	public static final Color BACKGROUNDCOLOR = Color.CYAN;
	
	public static void iniciar() {
		Integer i,j;
		ventana = new JFrame("GO EDA!");
		ventana.setSize(ALTO, ALTO);
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout());
		ventana.setResizable(false);
		ventana.setForeground(new Color(144, 238, 144));
		
		tablero = new GridLayout(TAMANIO, TAMANIO);
		ventana.setLayout(tablero);
		
		casilleros = new JPanel[TAMANIO][TAMANIO];
		
		for(i=0;i<TAMANIO;i++)
			for(j=0;j<TAMANIO;j++){
				casilleros[i][j] = new JPanel();
				casilleros[i][j].add(new JLabel(i+" "+j));				
				casilleros[i][j].setSize(TAMANIOCASILLERO,TAMANIOCASILLERO);
				casilleros[i][j].setLocation(new Point(i*TAMANIOCASILLERO, j*TAMANIOCASILLERO));
				casilleros[i][j].setBackground(BACKGROUNDCOLOR);
				casilleros[i][j].addMouseListener(new MyListener(i,j));
				ventana.add(casilleros[i][j]);
			}
		ventana.setVisible(true);
	}
	public static void ponerFicha(Boolean color, int i, int j){
		if(color)
			casilleros[i][j].setBackground(Color.white);
		else
			casilleros[i][j].setBackground(Color.RED);
	}
	public static void sacarFicha(int i, int j){
		casilleros[i][j].setBackground(BACKGROUNDCOLOR);
	}
	
	
}
