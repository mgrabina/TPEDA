package back;

public class Tablero {
	
	Casillero[][] tablero;

	public Tablero(Casillero[][] tablero) {
		this.tablero= new Casillero[13][13];
		for(int x=0;x<13;x++)
			for(int y=0;y<13;y++)
				tablero[x][y]=new Casillero(x,y);
	}

	public Casillero[][] getTablero() {
		return tablero;
	} 
	
	
	
	
	
	
	
	
}