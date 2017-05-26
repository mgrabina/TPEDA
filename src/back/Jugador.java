package back;

public abstract class Jugador  {
	
	Boolean ismaquina;
	String name;
	Integer puntos;
	
	public Jugador(Boolean ismaquina, String name) {
		super();
		this.ismaquina = ismaquina;
		this.name = name;
		this.puntos = 0;
	}
	
	public Boolean getIsmaquina() {
		return ismaquina;
	}
	public String getName() {
		return name;
	}
	public Integer getPuntos() {
		return puntos;
	}
	
	
	

}
