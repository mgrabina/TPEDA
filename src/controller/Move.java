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
		heuristica = -1;
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
		int punt=puntj-puntm;
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
			heuristica = -Main.obtenerGo().obtenerPuntajes()+punt;
		else
			heuristica = Main.obtenerGo().obtenerPuntajes()-punt;
		Main.obtenerGo().setTablero(original);
		Main.obtenerGo().getMaquina().setPuntos(puntm);
		Main.obtenerGo().getPersona().setPuntos(puntj);
		//System.out.println(heuristica);
		
		
		return heuristica;
	}
	

	@Override
	public String toString() {
		return "("+f.getFila()+","+f.getColumna()+") "+heuristica;
	}

	
	
}
