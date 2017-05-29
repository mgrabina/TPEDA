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
import sun.misc.Queue;

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
		if(tablero.getFicha(fila, columna) != null)
			return false;
					
		if(noEsKo(fila, columna, j)){
			if(puedoComerFichas(fila, columna, j)){
				tablero.agregarFicha(j, fila, columna); //habria que cambiar el orden de los parametros de este metodo para que esten todos igualitos :)
				comer(fila, columna, j);
				return true;
			}

			if(noEsSuicidio(fila, columna, j)){
				tablero.agregarFicha(j, fila, columna);
				return true;
			}
		}
		return false;
	}
		
/*		if(validarMovimiento(fila, columna, j)){
			tablero.agregarFicha(j, fila, columna);
			return true;
		}
		
		return false;*/
	

/*	public boolean validarMovimiento(int fila, int columna, Jugador j){
		//if(puedoComerFicha)
		//		if(!esKo)
		//			return true;
		//		return false;
		
		return noEsSuicidio(fila, columna, j) && noEsKo(fila, columna, j);
	}*/
	
	private void comer(int fila, int columna, Jugador j) {
		// TODO Auto-generated method stub
		
	}


	private boolean puedoComerFichas(int fila, int columna, Jugador j) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean noEsKo(int fila, int columna, Jugador j) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean noEsSuicidio(int fila, int columna, Jugador j) {
		List<Ficha> marcados = new LinkedList<Ficha>();
		tablero.agregarFicha(j, fila, columna);
		boolean a=tieneLibertad(fila, columna, j.getColor(), marcados);
		tablero.comerFicha(fila, columna);
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
					System.out.println("llego");
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
					System.out.println("llego");
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
					System.out.println("llego");
					return true;
				}
		}
		
		
		
		return false;
	}
	void MINIMAX(){
		
	}
	
}

