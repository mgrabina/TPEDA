package controller;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import front.GUI;

public class Listeners {
	private static boolean paso = false;
	
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
		if(paso){
			GUI.alertarGanador("Fin del juego.");
			System.exit(1);
			return;
		}
		
		if(game.getMaquina().getPuntos()<=game.getPersona().getPuntos()){
			turnoMaquina();
			game.setNext(game.getPersona());
		}else{
			GUI.alertarGanador("Fin del juego.");
			System.exit(1);
		}
	}
	
	private static void turnoMaquina(){
		int mov[] = new int[2];
		//Turno Maquina
		game.setNext(game.getMaquina());
		mov=game.MINIMAX(game.getNext(), false);	//Obtiene el movimiento a hacer
		
		if(mov == null){
			paso = true;
			return;
		}
		
		paso = false;
		
		game.mover(mov[0], mov[1], game.getNext(),true);
		agregarFicha(mov[0],mov[1]);
	}
	
	public static void llamadaDeCasillero(int fil, int col){
		if(!game.getNext().esMaquina())	//Si le toca a la persona
			if(game.mover(fil, col, game.getNext(),true)){	//Si se puede hacer el movimiento que quiere
				agregarFicha(fil, col);
				long a= System.currentTimeMillis();
				turnoMaquina();
				System.out.println(System.currentTimeMillis()-a);
				//Vuelve a persona
				
				game.setNext(game.getPersona());
			}else{
				GUI.alertarGanador("Movimiento no valido.");
			}
	}
	
	private static void agregarFicha(int fil, int col){
		GUI.ponerFicha(((game.getNext().getColor()==true)?true:false), fil, col);
		game.getTablero().agregarFicha(game.getNext(), fil, col);
		GUI.actualizarPuntajes(game.getPersona().getNombre(), game.getPersona().getPuntos(), game.getMaquina().getPuntos());
	}
	
	
	
	
	
}
