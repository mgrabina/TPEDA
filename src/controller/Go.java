package controller;

import back.Jugador;
import back.Tablero;

public class Go {

	Jugador maquina = new Jugador("Maquina", true);
	Jugador persona;
	Jugador next;
	Tablero tablero = new Tablero();
	
	public Go(String persona){
		this.persona = new Jugador(persona, false);
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
		return false;
	}

	private boolean esSuicidio(int fila, int columna, int color) {
		// TODO Auto-generated method stub
		return false;
	}
}
