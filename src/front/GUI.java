package front;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.Border;

import controller.Go;
import controller.Listeners;

public class GUI {
	static JFrame ventana;
	static JFrame controles;
	static JLabel puntajes;
	public static final Integer ANCHO = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	public static final Integer ALTO = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	static GridLayout tablero;
	static JPanel[][] casilleros;
	public static final Integer TAMANIO=13;
	public static final Integer TAMANIOCASILLERO=ALTO/TAMANIO;
	public static final Color BACKGROUNDCOLOR = Color.yellow;
	public static final String PATH = new File("").getAbsolutePath();
	public static void iniciar() {
		Integer i,j;
		ventana = new JFrame("Tablero");
		ventana.setSize(1000, 1000);
		ventana.setLocation(0,0);
		ventana.setLayout(new BorderLayout());
		ventana.setResizable(false);
		ventana.setContentPane(new JLabel(new ImageIcon(PATH+"/src/img/background.jpg")));
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
		
		controles = new JFrame("Controles");
		controles.setSize(500, 1000);
		controles.setLocation(1000, 0);
		controles.setLayout(new BorderLayout());
		controles.setResizable(false);
		controles.setContentPane(new JLabel(new ImageIcon(PATH+"/src/img/backControles.jpg")));
		controles.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
		
		JLabel titulo = new JLabel("The Go Game!");
		titulo.setBounds(0, 0, 500, 100);
		titulo.setHorizontalAlignment((int) titulo.CENTER_ALIGNMENT);
		titulo.setForeground(Color.white);
		titulo.setFont(new Font("Verdana", Font.BOLD, 50));
		
		JLabel pie = new JLabel("Varela - Tay - Grethe - Grabina - Mosquera");
		pie.setBounds(0, 900, 500, 100);
		pie.setHorizontalAlignment((int) pie.CENTER_ALIGNMENT);
		pie.setForeground(Color.black);
		pie.setFont(new Font("Verdana", Font.BOLD, 15));
		
		JButton generarDotButton = new JButton("GenerarDot");
		generarDotButton.setBounds(0,800, 500, 100);
		generarDotButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listeners.generarDotClicked();
				
			}
		});
		
		JButton pasar = new JButton("Pasar");
		pasar.setBounds(0, 700, 500, 100);
		pasar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listeners.pasarClicked();
				
			}
		});
		
		puntajes = new JLabel("Jugador 0 - 0 Maquina");
		puntajes.setBounds(0, 300, 500, 100);
		puntajes.setHorizontalAlignment((int) puntajes.CENTER_ALIGNMENT);
		puntajes.setForeground(Color.black);
		puntajes.setFont(new Font("Verdana", Font.PLAIN, 40));
		
		controles.add(titulo);
		controles.add(generarDotButton);
		controles.add(pasar);
		controles.add(puntajes);
		controles.add(pie);
		
		tablero = new GridLayout(TAMANIO, TAMANIO);
		ventana.setLayout(tablero);
		
		casilleros = new JPanel[TAMANIO][TAMANIO];
		
		for(i=0;i<TAMANIO;i++)
			for(j=0;j<TAMANIO;j++){
				casilleros[i][j] = new JPanel();	
				casilleros[i][j].setSize(TAMANIOCASILLERO,TAMANIOCASILLERO);
				casilleros[i][j].setLocation(new Point(i*TAMANIOCASILLERO, j*TAMANIOCASILLERO));
				casilleros[i][j].setOpaque(false);
				casilleros[i][j].addMouseListener(new MyListener(i,j));
				ventana.add(casilleros[i][j]);
			}
		ventana.setVisible(true);
		controles.setVisible(true);
	}
	public static void ponerFicha(Boolean color, int i, int j){
		casilleros[i][j].setOpaque(true);
		casilleros[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
		if(color)
			casilleros[i][j].setBackground(Color.white);
		else
			casilleros[i][j].setBackground(Color.BLACK);
	}
	public static void sacarFicha(int i, int j){
		casilleros[i][j].setOpaque(false);

		casilleros[i][j].setBorder(null);
		casilleros[i][j].setBackground(BACKGROUNDCOLOR);
	}
	public static void actualizarPuntajes(int puntosJugador, int puntosMaquina){
		puntajes.setText("Jugador "+ puntosJugador + " - " + puntosMaquina + " Maquina");
	}
	public static void alertarGanador(String texto){
		JDialog alerta = new JDialog(controles, texto);
	}
	
}
