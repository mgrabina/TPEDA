package controller;

import java.util.HashMap;

import back.*;
import java.util.Map;

import back.Jugador;
import back.Tablero;

public class Go {

	private Jugador maquina = new Jugador("Maquina", true,true);
	private Jugador persona;
	private Jugador next;
	private Tablero tablero = new Tablero();
	
	public Jugador getNext() {
		return next;
	}

	public void setNext(Jugador next) {
		this.next = next;
	}

	public Jugador getMaquina() {
		return maquina;
	}

	public Jugador getPersona() {
		return persona;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public Go(String persona){
		this.persona = new Jugador(persona, false, false);
		next=this.persona;
	}
	
	public boolean mover(int fila, int columna, Jugador j){
		if(validarMovimiento(fila, columna, j)){
			tablero.agregarFicha(j, columna, fila);
			return true;
		}
		
		return false;
	}
	
	public boolean validarMovimiento(int fila, int columna, Jugador j){
		return esSuicidio(fila, columna, j) && esKo(fila, columna, j);
	}

	private boolean esKo(int fila, int columna, Jugador j) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean esSuicidio(int fila, int columna, Jugador j) {
		Map<Integer, Integer> marcados = new HashMap<Integer, Integer>();	//i == key ; j == value;
		
		boolean r = tieneLibertad(fila, columna, j.getColor(), marcados);
		
		//desmarcar();
		
		return r;
	}

	private boolean tieneLibertad(int fila, int columna, boolean color, Map<Integer, Integer> marcados) {
		if(fila == 0 && columna == 0){
			Ficha f1 = tablero.getFicha(fila + 1, columna);
			Ficha f2 = tablero.getFicha(fila, columna + 1);
			boolean r = true;
			
			if(f1 == null || f2 == null)
				return true;
			
			if(f1 != null && f2 != null && f1.getColor() != color && f2.getColor() != color)
				return false;
			
			marcados.put(fila, columna);
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = r && tieneLibertad(fila + 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = r && tieneLibertad(fila, columna + 1, color, marcados);
			
			return r;			
		}
		
		return true;
		
	}
}
