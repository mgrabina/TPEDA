package controller;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import front.GUI;

public class Listeners {
	
	
	private static Go game;
	public Listeners(Go game) {
		this.game = game;
	}

	public static void generarDotClicked(){
		
	}
	public static void pasarClicked(){
		
	}
	
	public static void llamadaDeCasillero(int fil, int col){
		int mov[]=new int[2];
		if(!game.getNext().esMaquina())	//Si le toca a la persona
			if(game.mover(fil, col, game.getNext(),true)){	//Si se puede hacer el movimiento que quiere
				agregarFicha(fil, col);
				
				//Turno Maquina
				game.setNext(game.getMaquina());
				mov=game.MINIMAX(game.getNext());	//Obtiene el movimiento a hacer
				game.mover(mov[0], mov[1], game.getNext(),true);
				agregarFicha(mov[0],mov[1]);
				game.setNext(game.getPersona());
				System.out.println();
			}
	}
	
	private static void agregarFicha(int fil, int col){
		GUI.ponerFicha(((game.getNext().getColor()==true)?true:false), fil, col);
		game.getTablero().agregarFicha(game.getNext(), fil, col);
	}
	
	
	
	
	
}
