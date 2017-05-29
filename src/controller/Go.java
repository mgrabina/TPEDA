package controller;

import java.util.LinkedList;
import java.util.List;

import back.*;

import back.Jugador;
import back.Tablero;

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
		
		boolean r = tieneLibertad(fila, columna, j.getColor(), marcados);
		
		for(Ficha f : marcados)
			tablero.getFicha(f.getFila(), f.getColumna()).setColor(!f.getColor());
		
		return r;
	}

	private boolean tieneLibertad(int fila, int columna, boolean color, List<Ficha> marcados) {
		if(fila == 0 && columna == 0){
			Ficha f1 = tablero.getFicha(fila + 1, columna);
			Ficha f2 = tablero.getFicha(fila, columna + 1);
			boolean r = true;
			
			if(f1 == null || f2 == null)
				return true;
			
			if(f1 != null && f2 != null && f1.getColor() != color && f2.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila + 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila, columna + 1, color, marcados);
			
			return r;			
		}
		
		if(fila == 12 && columna == 0){
			Ficha f1 = tablero.getFicha(fila - 1, columna);
			Ficha f2 = tablero.getFicha(fila, columna + 1);
			boolean r = true;
			
			if(f1 == null || f2 == null)
				return true;
			
			if(f1 != null && f2 != null && f1.getColor() != color && f2.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila - 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila, columna + 1, color, marcados);
			
			return r;			
		}
		
		if(fila == 12 && columna == 12){
			Ficha f1 = tablero.getFicha(fila - 1, columna);
			Ficha f2 = tablero.getFicha(fila, columna - 1);
			boolean r = true;
			
			if(f1 == null || f2 == null)
				return true;
			
			if(f1 != null && f2 != null && f1.getColor() != color && f2.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila - 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila, columna - 1, color, marcados);
			
			return r;			
		}
		
		if(fila == 0 && columna == 12){
			Ficha f1 = tablero.getFicha(fila + 1, columna);
			Ficha f2 = tablero.getFicha(fila, columna - 1);
			boolean r = true;
			
			if(f1 == null || f2 == null)
				return true;
			
			if(f1 != null && f2 != null && f1.getColor() != color && f2.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila + 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila, columna - 1, color, marcados);
			
			return r;			
		}
		
		if(fila == 0){
			Ficha f1 = tablero.getFicha(fila + 1, columna);
			Ficha f2 = tablero.getFicha(fila, columna - 1);
			Ficha f3 = tablero.getFicha(fila, columna + 1);
			boolean r = true;
			
			if(f1 == null || f2 == null || f3 == null)
				return true;
			
			if(f1 != null && f2 != null && f3 != null && f1.getColor() != color && f2.getColor() != color && f3.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila + 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila, columna - 1, color, marcados);
			
			if(r)
				return true;
			
			if(f3 != null && f3.getColor() == color)
				r = tieneLibertad(fila, columna + 1, color, marcados);
			
			return r;			
		}
		
		if(fila == 12){
			Ficha f1 = tablero.getFicha(fila - 1, columna);
			Ficha f2 = tablero.getFicha(fila, columna - 1);
			Ficha f3 = tablero.getFicha(fila, columna + 1);
			boolean r = true;
			
			if(f1 == null || f2 == null || f3 == null)
				return true;
			
			if(f1 != null && f2 != null && f3 != null && f1.getColor() != color && f2.getColor() != color && f3.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila - 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila, columna - 1, color, marcados);
			
			if(r)
				return true;
			
			if(f3 != null && f3.getColor() == color)
				r = tieneLibertad(fila, columna + 1, color, marcados);
			
			return r;			
		}
		
		if(columna == 0){
			Ficha f1 = tablero.getFicha(fila, columna + 1);
			Ficha f2 = tablero.getFicha(fila - 1, columna);
			Ficha f3 = tablero.getFicha(fila + 1, columna);
			boolean r = true;
			
			if(f1 == null || f2 == null || f3 == null)
				return true;
			
			if(f1 != null && f2 != null && f3 != null && f1.getColor() != color && f2.getColor() != color && f3.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila, columna + 1, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila - 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f3 != null && f3.getColor() == color)
				r = tieneLibertad(fila + 1, columna, color, marcados);
			
			return r;			
		}
		
		if(columna == 12){
			Ficha f1 = tablero.getFicha(fila, columna - 1);
			Ficha f2 = tablero.getFicha(fila - 1, columna);
			Ficha f3 = tablero.getFicha(fila + 1, columna);
			boolean r = true;
			
			if(f1 == null || f2 == null || f3 == null)
				return true;
			
			if(f1 != null && f2 != null && f3 != null && f1.getColor() != color && f2.getColor() != color && f3.getColor() != color)
				return false;
			
			marcados.add(new Ficha(color, fila, columna));
			tablero.getFicha(fila, columna).setColor(!color);
			
			if(f1 != null && f1.getColor() == color)
				r = tieneLibertad(fila, columna - 1, color, marcados);
			
			if(r)
				return true;
			
			if(f2 != null && f2.getColor() == color)
				r = tieneLibertad(fila - 1, columna, color, marcados);
			
			if(r)
				return true;
			
			if(f3 != null && f3.getColor() == color)
				r = tieneLibertad(fila + 1, columna, color, marcados);
			
			return r;			
		}
		
		Ficha f1 = tablero.getFicha(fila + 1, columna);
		Ficha f2 = tablero.getFicha(fila - 1, columna);
		Ficha f3 = tablero.getFicha(fila, columna + 1);
		Ficha f4 = tablero.getFicha(fila, columna - 1);
		boolean r = true;
		
		if(f1 == null || f2 == null || f3 == null || f4 == null)
			return true;
		
		if(f1 != null && f2 != null && f3 != null && f4 != null && f1.getColor() != color && f2.getColor() != color && f3.getColor() != color && f4.getColor() != color)
			return false;
		
		marcados.add(new Ficha(color, fila, columna));
		tablero.getFicha(fila, columna).setColor(!color);
		
		if(f1 != null && f1.getColor() == color)
			r = tieneLibertad(fila + 1, columna, color, marcados);
		
		if(r)
			return true;
		
		if(f2 != null && f2.getColor() == color)
			r = tieneLibertad(fila - 1, columna, color, marcados);
		
		if(r)
			return true;
		
		if(f3 != null && f3.getColor() == color)
			r = tieneLibertad(fila, columna + 1, color, marcados);
		
		if(r)
			return true;
		
		if(f4 != null && f4.getColor() == color)
			r = tieneLibertad(fila, columna - 1, color, marcados);
		
		return r;	
	}
}

