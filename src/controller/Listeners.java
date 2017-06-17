package controller;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import front.GUI;

public class Listeners {
	
	
	private static Go game;
	public Listeners(Go game) {
		this.game = game;
	}

	public static void generarDotClicked(){
		GUI.loading(true);
		game.MINIMAX(game.getNext(), true);
		GUI.loading(false);
	}
	public static void pasarClicked(){
		turnoMaquina();
		game.setNext(game.getPersona());
	}
	private static void turnoMaquina(){
		int mov[] = new int[2];
		//Turno Maquina
		game.setNext(game.getMaquina());
		GUI.loading(true);
		mov=game.MINIMAX(game.getNext(), false);	//Obtiene el movimiento a hacer
		GUI.loading(false);
		game.mover(mov[0], mov[1], game.getNext(),true);
		agregarFicha(mov[0],mov[1]);
	}
	public static void llamadaDeCasillero(int fil, int col){
		int mov[]=new int[2];
		if(!game.getNext().esMaquina())	//Si le toca a la persona
			if(game.mover(fil, col, game.getNext(),true)){	//Si se puede hacer el movimiento que quiere
				agregarFicha(fil, col);
				turnoMaquina();
				//Vuelve a persona
				game.setNext(game.getPersona());
			}else{
				GUI.alertarGanador("Movimiento no valido.");
			}
	}
	
	private static void agregarFicha(int fil, int col){
		GUI.ponerFicha(((game.getNext().getColor()==true)?true:false), fil, col);
		game.getTablero().agregarFicha(game.getNext(), fil, col);
		game.getNext().agregarPuntos(1);
		GUI.actualizarPuntajes(game.getPersona().getNombre(), game.getPersona().getPuntos(), game.getMaquina().getPuntos());
	}
	
	
	
	
	
}
