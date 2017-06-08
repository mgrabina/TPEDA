package controller;

import java.util.ArrayList;

import back.Ficha;
import back.Jugador;
import back.Tablero;
import javafx.util.Pair;

public class Move {
	private int heuristica;
	private Ficha f;
	
	public Move(Ficha f) {
		this.f = f;
		
	}
	
	public int getHeuristica(){
		return heuristica();
	}
	
	public Ficha getFicha(){
		return f;
	}
	
	public void setHeuristica(int h){
		heuristica = h;
	}
	
	private int heuristica(){
		//Heuristica base: Diferencia de puntaje actual, respecto del jugador que realiza el movimiento.
		if(f.getColor() == true)
			heuristica = Math.abs(Main.obtenerGo().obtenerPuntajes());
		else
			heuristica = Main.obtenerGo().obtenerPuntajes();
		return heuristica;
	}
	

//	@Override
//	public String toString() {
//		return "("+movimientos.get(movimientos.size()-1).getKey()+","+movimientos.get(movimientos.size()-1).getValue()+") "+heuristica;
//	}

	
	
}
