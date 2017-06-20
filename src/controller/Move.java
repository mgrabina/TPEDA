package controller;

import java.util.*;

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

	public int getHeuristica() {
		return heuristica;
	}

	public Ficha getFicha() {
		return f;
	}

	public void setHeuristica(int h) {
		heuristica = h;
	}

	public int heuristica(ArrayList<Move> movimientosAnteriores) {
		Jugador j;
		int puntm = Main.obtenerGo().getMaquina().getPuntos();
		int puntj = Main.obtenerGo().getPersona().getPuntos();
		int punt = -puntj + puntm;
		List<List<Ficha>> set = new LinkedList<>();

		// realizamos los movimientos
		for (Move m : movimientosAnteriores) {
			if (m.getFicha().getColor())
				j = Main.obtenerGo().getMaquina();
			else
				j = Main.obtenerGo().getPersona();
			Main.obtenerGo().mover(m.getFicha().getFila(), m.getFicha().getColumna(), j, false);
			if (!Main.obtenerGo().fichascomidas.isEmpty())
				set.add((List<Ficha>) Main.obtenerGo().fichascomidas.clone());
		}
		
		if (f.getColor())
			j = Main.obtenerGo().getMaquina();
		else
			j = Main.obtenerGo().getPersona();
		Main.obtenerGo().mover(f.getFila(), f.getColumna(), j, false);
		
		// calculamos la heuristica
		heuristica = (Main.obtenerGo().obtenerPuntajes() - punt) * 50;

		if (f.getColor())
			heuristica += (Main.obtenerGo().cadena(f.getFila(), f.getColumna(), true) > 1) ? 10 : 0;
		else {
			Ficha fich = movimientosAnteriores.get(movimientosAnteriores.size() - 2).f;
			heuristica += (Main.obtenerGo().cadena(fich.getFila(), fich.getColumna(), true) > 1) ? 10 : 0;
		}
		
		// if(Main.obtenerGo().getTablero().getFichast()>=40){
		Main.obtenerGo().esTerritorioWR();
		heuristica += (Main.obtenerGo().getMaquina().getPuntosT() - Main.obtenerGo().getPersona().getPuntosT()) * 20;
		// }

		// seteamos el tablero original
		for (List<Ficha> l : set) {
			for (Ficha f : l) {
				if (f.getColor())
					j = Main.obtenerGo().getMaquina();
				else
					j = Main.obtenerGo().getPersona();
				Main.obtenerGo().getTablero().agregarFicha(j, f.getFila(), f.getColumna());
			}
		}
		
		for (Move m : movimientosAnteriores) {
			Main.obtenerGo().getTablero().sacarFicha(m.getFicha().getFila(), m.getFicha().getColumna());
		}
		
		Main.obtenerGo().getMaquina().setPuntos(puntm);
		Main.obtenerGo().getPersona().setPuntos(puntj);

		return heuristica;
	}

	@Override
	public String toString() {
		return "\"(" + f.getFila() + "," + f.getColumna() + ") " + heuristica + '\"';
	}

}
