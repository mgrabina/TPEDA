package controller;

import front.*;

import java.io.FileNotFoundException;

import back.*;
public class Main {
	private static Go game;
	
	public static void main(String[] args){
//		Argumentos
//		java -jar tpe.jar (-visual | -file archivo -player n) (-maxtime n | -depth n) [-prune] [-tree]
//		
//		
		game = new Go("Jugador");
		Listeners listener= new Listeners(game);
		
		//if(args[0]=="visual")
			GUI.iniciar();
		//else{
			//Por consola con parametros file y player
		//}
		
		
		
	}
	
	public static Go obtenerGo(){
		return game;
	}
}
