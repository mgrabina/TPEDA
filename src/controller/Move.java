package controller;

import java.util.ArrayList;

import back.Ficha;
import back.Jugador;
import back.Tablero;
import javafx.util.Pair;

public class Move {
	private int heuristica;
	private ArrayList<Ficha> movimientos;
	
	public Move(ArrayList<Ficha> movimientosAnteriores, Ficha f) {
		movimientos = (ArrayList<Ficha>) movimientosAnteriores.clone();
		movimientos.add(f);
		
	}
	public int getHeuristica(){
		return heuristica();
	}
	public void addMovementToHistory(Ficha f){
		movimientos.add(f);
	}
	public void setHeuristica(int h){
		heuristica = h;
	}
	private int heuristica(){
		//Heuristica base: Diferencia de puntaje actual, respecto del jugador que realiza el movimiento.
		if(movimientos.get(movimientos.size()-1).getColor() == true)
			heuristica = Math.abs(Main.obtenerGo().obtenerPuntajes());
		else
			heuristica = Main.obtenerGo().obtenerPuntajes();
		return heuristica;
	}
	public ArrayList<Ficha> getMovimientos() {
		return movimientos;
	}
//	@Override
//	public String toString() {
//		return "("+movimientos.get(movimientos.size()-1).getKey()+","+movimientos.get(movimientos.size()-1).getValue()+") "+heuristica;
//	}

	
	
}
