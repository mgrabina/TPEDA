package controller;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import back.*;

import back.Jugador;
import back.Tablero;
import front.GUI;
import sun.misc.Queue;

public class Go {

	private Jugador maquina = new Jugador("Maquina", true,true);
	private Jugador persona;
	private Jugador next;
	private Tablero tablero = new Tablero();
	boolean ko;
	List<Ficha> fichasacomer=new LinkedList<Ficha>();
	Ficha knock;
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
		ko=false;
		next=this.persona;
	}
	
	public boolean mover(int fila, int columna, Jugador j){
		boolean a;
		boolean b;
		if(tablero.getFicha(fila, columna) != null)
			return false;
		fichasacomer.clear();
		a=puedoComerFichas(fila, columna, j);
		b=noEsSuicidio(fila, columna, j);
		if(a==true && b==false && ko==true && knock.getFila()==fila && knock.getColumna()==columna ){
			return false;
		}	
		if(a==true && b==false){
			ko=true;
			knock=fichasacomer.get(0);
		}else{
			ko=false;
			knock=null;
		}	
		if(a){
			tablero.agregarFicha(j, fila, columna);
			for(Ficha fic: fichasacomer){
				comer(fic.getFila(),fic.getColumna(),fic.getColor());
			}
			System.out.println("PuntosCP:" + maquina.getPuntos() + ", puntosPlayer:" + persona.getPuntos());
			return true;
		}
		if(b){
			tablero.agregarFicha(j, fila, columna);
			return true;
		}
			
	
		
		return false;
	}
		
	private boolean puedoComerFichas(int fila, int columna, Jugador j) {
		tablero.agregarFicha(j, fila, columna);
		
		Ficha f;
		
		int izq = (columna == 0) ? 0 : 1;
		int der = (12 == columna) ? 0 : 1;
		int arr = (fila == 0) ? 0 : 1;
		int abj = (12 == fila) ? 0 : 1;
		boolean izqB = false, derB = false, arrB = false, abjB = false;
		
		if(izq != 0){
			f = tablero.getFicha(fila, columna - 1);
			if(f != null && f.getColor() != j.getColor())
				izqB = intentarComer(fila, columna - 1, !j.getColor());
		}
		
		if(der != 0){
			f = tablero.getFicha(fila, columna + 1);
			if(f != null && f.getColor() != j.getColor())
				derB = intentarComer(fila, columna + 1, !j.getColor());
		}
		
		if(arr != 0){
			f = tablero.getFicha(fila - 1, columna);
			if(f != null && f.getColor() != j.getColor())
				arrB = intentarComer(fila - 1, columna, !j.getColor());
		}
		
		if(abj != 0){
			f = tablero.getFicha(fila + 1, columna);
			if(f != null && f.getColor() != j.getColor())
				abjB = intentarComer(fila + 1, columna, !j.getColor());
		}
		
		tablero.sacarFicha(fila, columna);
		
		return izqB || derB || arrB || abjB;
		
	}

	private boolean intentarComer(int fila, int columna, boolean color) {
		if(!tieneLibertad(fila, columna, color, new LinkedList<Ficha>())){
				fichasacomer.add(new Ficha(color, fila, columna));
				return true;
		}
		
		return false;
	}

	private void comer(int fila, int columna, boolean color) {
		int izq, der, arr, abj;
		Ficha f;
		
		izq = (columna == 0) ? 0 : 1;
		der = (12 == columna) ? 0 : 1;
		arr = (fila == 0) ? 0 : 1;
		abj = (12 == fila) ? 0 : 1;
		
		if(color)
			maquina.agregarPuntos();
		else
			persona.agregarPuntos();
		
		tablero.sacarFicha(fila, columna);
		GUI.sacarFicha(fila, columna);
		
		for (int l = columna - izq; l <= columna + der; l++) {
			f = tablero.getFicha(fila, l);
			if (f != null && f.getColor() == color) {
				comer(fila, l, color);
			}
		}
		
		if (arr == 1) {
			f = tablero.getFicha(fila - 1, columna);
			if (f != null && f.getColor() == color) {
				comer(fila - 1, columna, color);
			}
		}
		
		if (abj == 1) {
			f = tablero.getFicha(fila + 1, columna);
			if (f != null && f.getColor() == color) {
				comer(fila + 1, columna, color);
			}
		}
	}

	private boolean noEsSuicidio(int fila, int columna, Jugador j) {
		List<Ficha> marcados = new LinkedList<Ficha>();
		tablero.agregarFicha(j, fila, columna);
		boolean a=tieneLibertad(fila, columna, j.getColor(), marcados);
		tablero.sacarFicha(fila, columna);
		return a;
	}

	private boolean tieneLibertad(int fil, int col, boolean color, List<Ficha> marcados) {
	
		int izq, der, arr, abj;
		Ficha f;
		marcados.add(tablero.getFicha(fil, col));
		izq = (col == 0)? 0 : 1;
		der = (12 == col)? 0 : 1;
		arr = (fil == 0)? 0 : 1;
		abj = (12 == fil)? 0 : 1;
		boolean flag;
		for(int l=col - izq;l <= col + der ;l++){
			f=tablero.getFicha(fil, l);
			if(f!=null && f.getColor()==color && !marcados.contains(f)){
				flag=tieneLibertad(fil, l, color, marcados);
				if(flag)
					return true;
			}else
				if(f==null){
					return true;
				}
		}
		if(arr==1){
			f=tablero.getFicha(fil-1, col);
			if(f!=null && f.getColor()==color && !marcados.contains(f)){
				flag=tieneLibertad(fil-1,col, color, marcados);
				if(flag)
					return true;
			}else
				if(f==null){
					return true;
				}
		}
		if(abj==1){
			f=tablero.getFicha(fil+1, col);
			if(f!=null && f.getColor()==color && !marcados.contains(f)){
				flag=tieneLibertad(fil+1,col, color, marcados);
				if(flag)
					return true;
			}else
				if(f==null){
					return true;
				}
		}
		
		
		
		return false;
	}
	void MINIMAX(){
		//Hacer arbol de movimientos, usando la clase tree y move que cuando se crea se asigna su heuristica.
		Move m = new Move(tablero, 0, 0, maquina);
	}
	
}

