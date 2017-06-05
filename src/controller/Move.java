package controller;

import back.Jugador;
import back.Tablero;
import javafx.util.Pair;

public class Move {
	private int heuristica;
	private Tablero actual;
	private Tablero siguiente;
	private Pair<Integer, Integer> nuevaFicha;
	private Jugador j;
	
	public Move(Tablero actual, int x, int y, Jugador j) {
		this.actual = actual;
		nuevaFicha = new Pair<Integer, Integer>(x, y);
		siguiente = actual;
		siguiente.agregarFicha(j, x, y);
		heuristica();
	}
	public int getHeuristica(){
		return heuristica;
	}
	
	private void heuristica(){
		//Heuristica base: Diferencia de puntaje actual, respecto del jugador que realiza el movimiento.
		if(j.esMaquina())
			heuristica = Math.abs(Main.obtenerGo().obtenerPuntajes());
		else
			heuristica = Main.obtenerGo().obtenerPuntajes();
	}
	@Override
	public String toString() {
		return "("+nuevaFicha.getKey()+","+nuevaFicha.getValue()+") "+heuristica;
	}
}
