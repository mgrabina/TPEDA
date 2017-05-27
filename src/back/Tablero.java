package back;

public class Tablero {
	private Ficha[][] tablero = new Ficha[13][13];
	
	public Tablero(){
		
	}
	
	public boolean agregarFicha(int color, int columna, int fila){
		if(tablero[fila][columna] == null){
			tablero[fila][columna] = new Ficha(color);
			return true;
		}
		
		return false;
	}
	
	public boolean comerFicha(int color, int columna, int fila){
		if(tablero[fila][columna] == null || tablero[fila][columna].getColor() == color)
			return false;
		
		tablero[fila][columna].setColor(color);
		return true;
	}
}