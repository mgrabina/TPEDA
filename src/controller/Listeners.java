package controller;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import front.GUI;

public class Listeners {
	
	
	private static Go game;
	public Listeners(Go game) {
		this.game = game;
	}


	public static void llamadaDeCasillero(int fil, int col){
		int mov[]=new int[2];
		if(!game.getNext().esMaquina())
			if(game.mover(fil, col, game.getNext())){
				agregarFicha(fil, col);
				game.setNext(game.getMaquina());
				mov=game.MINIMAX(game.getNext(),2);
				agregarFicha(mov[0],mov[1]);
			}
		else {

			}
	}
	
	private static void agregarFicha(int fil, int col){
		GUI.ponerFicha(((game.getNext().getColor()==true)?true:false), fil, col);
		game.getTablero().agregarFicha(game.getNext(), fil, col);
	}
	
	
	
	
	
}
