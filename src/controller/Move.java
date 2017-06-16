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
	
	public int getHeuristica(ArrayList<Move> movimientosAnteriores){
		return heuristica(movimientosAnteriores);
	}
	
	public Ficha getFicha(){
		return f;
	}
	
	public void setHeuristica(int h){
		heuristica = h;
	}
	
	private int heuristica(ArrayList<Move> movimientosAnteriores){
		//Heuristica base: Diferencia de puntaje actual, respecto del jugador que realiza el movimiento.
		Jugador j;
		Tablero original=Main.obtenerGo().getTablero().clone();
		int puntm=Main.obtenerGo().getMaquina().getPuntos();
		int puntj=Main.obtenerGo().getPersona().getPuntos();
		for(Move m: movimientosAnteriores){
			if(m.getFicha().getColor())
				j= Main.obtenerGo().getMaquina();
			else
				j=Main.obtenerGo().getPersona();
			Main.obtenerGo().mover(m.getFicha().getFila(), m.getFicha().getColumna(), j,false);
		}
		
		if(f.getColor())
			j= Main.obtenerGo().getMaquina();
		else
			j=Main.obtenerGo().getPersona();
		Main.obtenerGo().mover(f.getFila(), f.getColumna(), j,false);

		
		if(f.getColor())
			heuristica = Math.abs(Main.obtenerGo().obtenerPuntajes());
		else
			heuristica = Main.obtenerGo().obtenerPuntajes();
		Main.obtenerGo().setTablero(original);
		Main.obtenerGo().getMaquina().setPuntos(puntm);
		Main.obtenerGo().getPersona().setPuntos(puntj);
		System.out.println(heuristica);
		
		
		return heuristica;
	}
	

//	@Override
//	public String toString() {
//		return "("+movimientos.get(movimientos.size()-1).getKey()+","+movimientos.get(movimientos.size()-1).getValue()+") "+heuristica;
//	}

	
	
}
