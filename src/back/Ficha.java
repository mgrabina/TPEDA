package back;

public class Ficha {

	private boolean color;
	private int fila;
	private int columna;
	
	public Ficha (boolean color, int fila, int columna){
		this. color = color;
		this.fila = fila;
		this.columna = columna;
	}
	
	public boolean getColor(){
		return color;
	}
	
	public int getFila(){
		return fila;
	}
	
	public int getColumna(){
		return columna;
	}
	
	public void setColor(boolean color){
		this.color = color;
	}
	
}
