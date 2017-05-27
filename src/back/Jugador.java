package back;

public class Jugador {

	private String nombre;
	private int puntos;
	private boolean maquina;
	
	public Jugador(String nombre, boolean maquina){
		this.nombre = nombre;
		this.maquina = maquina;
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
