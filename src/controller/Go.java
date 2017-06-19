package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import back.*;
import front.GUI;
import javafx.util.Pair;
import sun.misc.Queue;

public class Go {

	private static final int DEPTHDEFAULT = 2;
	private Jugador maquina = new Jugador("Maquina", true,true);
	private Jugador persona;
	private Jugador next;
	private Long time = null;
	private Integer depth = null;
	private Tablero tablero;
	boolean ko;
	LinkedList<Ficha> fichasacomer=new LinkedList<Ficha>();
	LinkedList<Ficha> fichascomidas=new LinkedList<Ficha>();
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
	
	public void setTime(Long time){
		this.time = time;
	}
	
	public void setDepth(Integer depth){
		this.depth = depth;
	}

	public Go(String persona){
		this.persona = new Jugador(persona, false, false);
		ko=false;
		next=this.persona;
		tablero = new Tablero();
	}
	
	public Go(String username, long n) {
		this.persona = new Jugador(username, false, false);
		ko=false;
		next=this.persona;
		time = n;
		tablero = new Tablero();
	}
	
	public Go(String username, int n) {
		this.persona = new Jugador(username, false, false);
		ko=false;
		next=this.persona;
		depth = n;
		tablero = new Tablero();
	}

	public Go(int player, long n, Tablero t) {
		this.persona = new Jugador("Jugador", false, false);
		ko=false;
		next=this.persona;
		time = n;
		tablero = t;
	}
	
	public Go(int player, int n, Tablero t, boolean crearDot) {
		this.persona = new Jugador("Jugador", false, false);
		ko=false;
		next=this.persona;
		depth = n;
		tablero = t;
	}


	public boolean mover(int fila, int columna, Jugador j,boolean real){
		boolean a;
		boolean b;
		if(tablero.getFicha(fila, columna) != null)
			return false;
		fichasacomer.clear();
		fichascomidas.clear();
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
				comer(fic.getFila(),fic.getColumna(),fic.getColor(),real);
			}
			//System.out.println("PuntosCP:" + maquina.getPuntos() + ", puntosPlayer:" + persona.getPuntos());
			return true;
		}
		if(b){
			tablero.agregarFicha(j, fila, columna);
			//System.out.println("PuntosCP:" + maquina.getPuntos() + ", puntosPlayer:" + persona.getPuntos());
			return true;
		}
		return false;
	}
		
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
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

	private void comer(int fila, int columna, boolean color,boolean real) {
		int izq, der, arr, abj;
		Ficha f;
		
		izq = (columna == 0) ? 0 : 1;
		der = (12 == columna) ? 0 : 1;
		arr = (fila == 0) ? 0 : 1;
		abj = (12 == fila) ? 0 : 1;
		
		if(color)
			persona.agregarPuntos(1);
		else
			maquina.agregarPuntos(1);
		
		tablero.sacarFicha(fila, columna);
		fichascomidas.add(new Ficha(color, fila, columna));
		if(real)
			GUI.sacarFicha(fila, columna);
		
		for (int l = columna - izq; l <= columna + der; l++) {
			f = tablero.getFicha(fila, l);
			if (f != null && f.getColor() == color) {
				comer(fila, l, color,real);
			}
		}
		
		if (arr == 1) {
			f = tablero.getFicha(fila - 1, columna);
			if (f != null && f.getColor() == color) {
				comer(fila - 1, columna, color,real);
			}
		}
		
		if (abj == 1) {
			f = tablero.getFicha(fila + 1, columna);
			if (f != null && f.getColor() == color) {
				comer(fila + 1, columna, color,real);
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
	
	//devuelve la cantidad de casilleros en un territorio; si devuelve 0 entonces no es territorio
	
	private int esTerritorioWR(int fila, int columna, Jugador j, boolean real){
		List<Pair<Integer, Integer>> marcados = new LinkedList<Pair<Integer, Integer>>();
		int resp = esTerritorio(fila, columna, marcados);
		
		if(resp == 1 || resp == 2){
			if(real){
				if(j.esMaquina())
					maquina.agregarPuntos(marcados.size());
				else
					persona.agregarPuntos(marcados.size());
			}
		}
		
		return marcados.size();
		
	}
	
	private int esTerritorio(int fila, int columna, List<Pair<Integer, Integer>> marcados){
		boolean izqB, derB, arrB, abjB;
		Pair<Integer, Integer> pair = new Pair<Integer, Integer>(fila, columna);
		marcados.add(pair);
		
		izqB = !(columna == 0);
		derB = !(columna == 12);
		arrB = !(fila == 0);
		abjB = !(fila == 12);
		int izqR = 0, derR = 0, arrR = 0, abjR = 0;
		
		if(izqB){
			Ficha izq = tablero.getFicha(fila, columna - 1);
			Pair<Integer, Integer> izqP = new Pair<Integer, Integer>(fila, columna - 1);
			if(!marcados.contains(izqP)){
				if(izq != null){
					if(izq.getColor() == true)
						izqR = 1;
					else
						izqR = 2;
				}
				else
					izqR = esTerritorio(fila, columna - 1, marcados);				
			}
		}
		
		if(derB){
			Ficha der = tablero.getFicha(fila, columna + 1);
			Pair<Integer, Integer> derP = new Pair<Integer, Integer>(fila, columna + 1);
			if(!marcados.contains(derP)){
				if(der != null){
					if(der.getColor() == true)
						derR = 1;
					else
						derR = 2;
				}
				else
					derR = esTerritorio(fila, columna + 1, marcados);
			}
		}
		
		if(arrB){
			Ficha arr = tablero.getFicha(fila - 1, columna);
			Pair<Integer, Integer> arrP = new Pair<Integer, Integer>(fila - 1, columna);
			if(!marcados.contains(arrP)){
				if(arr != null){
					if(arr.getColor() == true)
						arrR = 1;
					else
						arrR = 2;
				}
				else
					arrR = esTerritorio(fila - 1, columna, marcados);
			}
		}
		
		if(abjB){
			Ficha abj = tablero.getFicha(fila + 1, columna);
			Pair<Integer, Integer> abjP = new Pair<Integer, Integer>(fila + 1, columna);
			if(!marcados.contains(abjP)){	
				if(abj != null){
					if(abj.getColor() == true)
						abjR = 1;
					else
						abjR = 2;
				}
				else
					abjR = esTerritorio(fila + 1, columna, marcados);
			}
		}
		
		if(izqR + derR == 3 || izqR + arrR == 3 || izqR + abjR == 3 || derR + arrR == 3 || derR + abjR == 3 || arrR + abjR == 3 || izqR == 3 || derR == 3 || arrR == 3 || abjR == 3)
			return 3;
		else
			return Math.max(Math.max(izqR, derR), Math.max(arrR, abjR));
		
	}
	
	public int obtenerPuntajes(){
		return maquina.getPuntos() - persona.getPuntos();
	}
	
	int[] MINIMAX(Jugador j, boolean crearDot){
		//Hacer arbol de movimientos, usando la clase tree y move que cuando se crea se asigna su heuristica.
		Tree<ArrayList<Move>> t = new Tree<ArrayList<Move>>(new ArrayList<Move>());
		Integer index;
		
		//Long start = System.currentTimeMillis();
		
		if(depth != null){
			index = minimax(t, depth, 0, j.getColor());
		}else{
			index = minimax(t,DEPTHDEFAULT, 0, j.getColor(), time);
		}
		
		//System.out.println(System.currentTimeMillis() - start);
		
		
		if(index == null)
			return null;
		
		ArrayList<Move> movimientosHijo = (ArrayList<Move>)t.getChildren().get(index).getValue();
		Move movimientoHijo = movimientosHijo.get(((ArrayList<Move>)t.getChildren().get(index).getValue()).size() - 1);
		if(crearDot){
		try {
			t.generarDOT();
		} catch (FileNotFoundException e) {
			System.out.println("No se encontró el archivo");
		} catch (InterruptedException e) {
			System.out.println("Se interrumpió la creación del .dot");
		}
		}
		Ficha f = movimientoHijo.getFicha();
		int a[]= new int[2];
		a[0]=f.getFila();
		a[1]=f.getColumna();
		return a;
	}

	//Agregar funciones getMovPosibles(), getMax() y getMin().
	
	public Integer minimax(Tree<ArrayList<Move>> tree, int depth, int currentLevel, boolean color){
		//aplicar heuristica
		if(currentLevel == depth){
			int heuristicaDeLaHoja = ((ArrayList<Move>)tree.getValue()).get(((ArrayList<Move>) tree.getValue()).size() - 1).heuristica(tree.getValue());
			
			((ArrayList<Move>)tree.getValue()).get(((ArrayList<Move>) tree.getValue()).size() - 1).setHeuristica(heuristicaDeLaHoja);

			return -1;
		}
		
		for(int i=0;i<13;i++){
			for(int j=0;j<13;j++){
			//Obtengo los movimientos posibles
				Ficha f;
				f = intentoPonerFicha(new Ficha(color, i, j));
				if(f != null && !((ArrayList<Move>)tree.getValue()).contains(new Move(f))){
					//Por cada movimiento posible creo un hijo con los movimientos viejos + el movimiento posible
					ArrayList<Move> movimientosAnteriores = new ArrayList<Move> (((ArrayList<Move>)tree.getValue()));
					movimientosAnteriores.add(new Move(f));
					
					tree.getChildren().add(new Tree<ArrayList<Move>> (movimientosAnteriores));	
						
				}
			}
		}
		for(Tree<ArrayList<Move>> t : tree.getChildren()) //Por cada hijo hay una recursiva
			minimax(t, depth, currentLevel+1, !color);
		
		if(tree.getChildren().size() == 0)
			return null;
		
		//Checkeo Minimax
		if(currentLevel%2==0) //Nivel par -> max
		{	
			int indexMax = tree.getMaxHeuristica();
			
			if(((ArrayList<Move>)tree.getValue()).size() != 0){
				
				ArrayList<Move> movimientos = tree.getValue();
	
				int sizeMovimientos = movimientos.size();
					
				Move movimientoActual = movimientos.get(sizeMovimientos - 1);
					
				ArrayList<Move> movimientosHijo = (ArrayList<Move>)tree.getChildren().get(indexMax).getValue();
					
				int sizeMovimientosHijo = ((ArrayList<Move>)tree.getChildren().get(indexMax).getValue()).size();
					
				Move movimientoHijo = movimientosHijo.get(sizeMovimientosHijo - 1);
	
				movimientoActual.setHeuristica(movimientoHijo.getHeuristica());
			}
				
			return indexMax;
				
		}else{					//Nivel impar -> min
			int indexMin = tree.getMinHeuristica();
			
			if(((ArrayList<Move>)tree.getValue()).size() != 0){
				
				ArrayList<Move> movimientos = tree.getValue();
				
				int sizeMovimientos = movimientos.size();
				
				Move movimientoActual = movimientos.get(sizeMovimientos - 1);
				
				ArrayList<Move> movimientosHijo = (ArrayList<Move>)tree.getChildren().get(indexMin).getValue();
				
				int sizeMovimientosHijo = ((ArrayList<Move>)tree.getChildren().get(indexMin).getValue()).size();
				
				Move movimientoHijo = movimientosHijo.get(sizeMovimientosHijo - 1);

				movimientoActual.setHeuristica(movimientoHijo.getHeuristica());
			}
				 
			return indexMin;
		}
	}
	
	public int minimax(Tree<ArrayList<Move>> tree, int depth, int currentLevel, boolean color, Long time){
		Double start = (double) (System.currentTimeMillis()/1000);
		//aplicar heuristica
		if(currentLevel == depth){
			int heuristicaDeLaHoja = ((ArrayList<Move>)tree.getValue()).get(((ArrayList<Move>) tree.getValue()).size() - 1).heuristica(tree.getValue());
					
			((ArrayList<Move>)tree.getValue()).get(((ArrayList<Move>) tree.getValue()).size() - 1).setHeuristica(heuristicaDeLaHoja);

			return -1;
		}
				
		for(int i=0;i<13;i++){
			for(int j=0;j<13;j++){
			//Obtengo los movimientos posibles
				Ficha f;
				f = intentoPonerFicha(new Ficha(color, i, j));
				if(f != null && !((ArrayList<Move>)tree.getValue()).contains(new Move(f))){
					//Por cada movimiento posible creo un hijo con los movimientos viejos + el movimiento posible
					ArrayList<Move> movimientosAnteriores = new ArrayList<Move> (((ArrayList<Move>)tree.getValue()));
					movimientosAnteriores.add(new Move(f));
							
					tree.getChildren().add(new Tree<ArrayList<Move>> (movimientosAnteriores));	
								
				}
			}
		}
		
		while(System.currentTimeMillis()/1000 - start < time){
			for(Tree<ArrayList<Move>> t : tree.getChildren()) //Por cada hijo hay una recursiva
				minimax(t, depth, currentLevel+1, !color);
		}
				
				
		//Checkeo Minimax
		if(currentLevel%2==0) //Nivel par -> max
		{
			int indexMax = tree.getMaxHeuristica();
					
			if(((ArrayList<Move>)tree.getValue()).size() != 0){
						
				ArrayList<Move> movimientos = tree.getValue();
			
				int sizeMovimientos = movimientos.size();
							
				Move movimientoActual = movimientos.get(sizeMovimientos - 1);
							
				ArrayList<Move> movimientosHijo = (ArrayList<Move>)tree.getChildren().get(indexMax).getValue();
							
				int sizeMovimientosHijo = ((ArrayList<Move>)tree.getChildren().get(indexMax).getValue()).size();
							
				Move movimientoHijo = movimientosHijo.get(sizeMovimientosHijo - 1);
			
				movimientoActual.setHeuristica(movimientoHijo.getHeuristica());
			}
						
			return indexMax;
						
		}else{					//Nivel impar -> min
			int indexMin = tree.getMinHeuristica();
					
			if(((ArrayList<Move>)tree.getValue()).size() != 0){
						
				ArrayList<Move> movimientos = tree.getValue();
						
				int sizeMovimientos = movimientos.size();
						
				Move movimientoActual = movimientos.get(sizeMovimientos - 1);
						
				ArrayList<Move> movimientosHijo = (ArrayList<Move>)tree.getChildren().get(indexMin).getValue();
						
				int sizeMovimientosHijo = ((ArrayList<Move>)tree.getChildren().get(indexMin).getValue()).size();
						
				Move movimientoHijo = movimientosHijo.get(sizeMovimientosHijo - 1);

				movimientoActual.setHeuristica(movimientoHijo.getHeuristica());
			}
						 
			return indexMin;
		}
	}
	
	private Ficha intentoPonerFicha(Ficha f){
		if(moverSinComer(f.getFila(), f.getColumna(), (f.getColor() ? maquina : persona)))
			return f;
		
		return null;
	}
	
	private boolean moverSinComer(int fila, int columna, Jugador j){
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
		if(a)
			return true;
		if(b)
			return true;
		
		return false;
	}
	
	
	
}