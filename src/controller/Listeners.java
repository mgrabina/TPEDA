package controller;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import front.GUI;

public class Listeners {
	
	
	private static Go game;
	public Listeners(Go game) {
		this.game = game;
	}


	public static void llamadaDeCasillero(int fil, int col){
		if(game.validarMovimiento(fil, col, game.getNext().getColor())){
			GUI.ponerFicha(((game.getNext().getColor()==1)?true:false), fil, col);
			game.setNext((game.getNext().esMaquina()==true)?game.getPersona():game.getMaquina());
		}
	}
}
