package back;

public class Tablero {
	private Ficha[][] tablero = new Ficha[13][13];
	
	public Tablero(){
		
	}
	
	public boolean agregarFicha(Jugador jug, int fil, int col){
		if(tablero[fil][col] == null){
			tablero[fil][col] = new Ficha(jug.getColor());
			return true;
		}
		
		return false;
	}
	
	public boolean comerFicha(boolean color, int fil, int col){
		if(tablero[fil][col] == null || tablero[fil][col].getColor() == color)
			return false;
		
		tablero[fil][col].setColor(color);
		return true;
	}
	
	public Ficha getFicha(int fila, int columna){
		if(tablero[fila][columna] == null)
			return null;
		
		return tablero[fila][columna];
	}
}