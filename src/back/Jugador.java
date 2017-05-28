package back;

public class Jugador {

	private String nombre;
	private int puntos;
	private boolean maquina;
	private boolean color;
	
	public Jugador(String nombre, boolean maquina, boolean color){
		this.nombre = nombre;
		this.maquina = maquina;
		this.color=color;
	}
	
	public boolean getColor() {
		return color;
	}

	public String getNombre(){
		return nombre;
	}
	
	public int getPuntos(){
		return puntos;
	}
	
	public int agregarPuntos(int puntos){
		this.puntos += puntos;
		return this.puntos;
	}
	
	public int borrarPuntos(int puntos){
		this.puntos -= puntos;
		return this.puntos;
	}
	
	public boolean esMaquina(){
		return maquina;
	}
}
