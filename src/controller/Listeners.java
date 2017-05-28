package controller;

import com.sun.java.swing.plaf.windows.resources.windows_zh_TW;

import front.GUI;

public class Listeners {

	public static void llamadaDeCasillero(int i, int j){
		//Cuando apreta el casillero en la posicion i-j se ejecuta
		GUI.ponerFicha(true, i, j);
	}
}
