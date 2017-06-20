package back;

public class Jugador {

	private String nombre;
	private int puntos = 0;
	private boolean maquina;
	private boolean color;
	private int puntosT = 0;

	public Jugador(String nombre, boolean maquina, boolean color) {
		this.nombre = nombre;
		this.maquina = maquina;
		this.color = color;
	}

	public int getPuntosT() {
		return puntosT;
	}

	public void setPuntosT(int puntosT) {
		this.puntosT = puntosT;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public boolean getColor() {
		return color;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public int agregarPuntos(int puntos) {
		this.puntos += puntos;
		return this.puntos;
	}

	public int borrarPuntos(int puntos) {
		this.puntos -= puntos;
		return this.puntos;
	}

	public boolean esMaquina() {
		return maquina;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		if (color != other.color)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

}
