package back;

import controller.Main;

public class Tablero {
	private Ficha[][] tablero = new Ficha[13][13];
	private int fichast = 0;

	public Tablero() {

	}

	public boolean agregarFicha(Jugador jug, int fil, int col) {
		if (tablero[fil][col] == null) {
			tablero[fil][col] = new Ficha(jug.getColor(), fil, col);
			fichast++;
			return true;
		}

		return false;
	}

	public int getFichast() {
		return fichast;
	}

	public void sacarFicha(int fil, int col) {
		tablero[fil][col] = null;
		fichast--;
		return;
	}

	public Ficha getFicha(int fila, int columna) {
		if (tablero[fila][columna] == null)
			return null;

		return tablero[fila][columna];
	}

	public Tablero clone() {
		Tablero t = new Tablero();
		Jugador j;
		
		for (int x = 0; x < 13; x++)
			for (int y = 0; y < 13; y++) {
				if (tablero[x][y] != null) {
					if (tablero[x][y].getColor())
						j = Main.obtenerGo().getMaquina();
					else
						j = Main.obtenerGo().getPersona();
					t.agregarFicha(j, x, y);
				}
			}
		
		return t;
	}

}