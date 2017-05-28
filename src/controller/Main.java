package controller;

import front.*;
import back.*;
public class Main {
	
	public static void main(String[] args){
		
		Go game = new Go("Juan");
		Listeners listener= new Listeners(game);
		GUI.iniciar();
		
	}
	
}
