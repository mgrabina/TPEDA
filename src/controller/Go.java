package controller;

import back.Jugador;
import back.Tablero;

public class Go {

	private Jugador maquina = new Jugador("Maquina", true,1);
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
		this.persona = new Jugador(persona, false,0);
		next=this.persona;
	}
	
	public boolean mover(int fila, int columna, int color){
		if(validarMovimiento(fila, columna, color)){
			tablero.agregarFicha(color, columna, fila);
			return true;
		}
		
		return false;
	}
	
	public boolean validarMovimiento(int fila, int columna, int color){
		return esSuicidio(fila, columna, color) && esKo(fila, columna, color);
	}

	private boolean esKo(int fila, int columna, int color) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean esSuicidio(int fila, int columna, int color) {
		// TODO Auto-generated method stub
		return true;
	}
}
