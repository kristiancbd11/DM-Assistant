package clases_estadisticas;

public class StatCarisma {
	
	private int conviccion;
	private int enganio;
	private int intimidacion;
	private int liderazgo;
	private int jovialidad;
	private int seduccion;
	
	public StatCarisma() {}
	
	//Contructro de la clase
	public StatCarisma(int conviccion, int enganio, int intimidacion, int liderazgo, int jovialidad, int seduccion) {
		super();
		this.conviccion = conviccion;
		this.enganio = enganio;
		this.intimidacion = intimidacion;
		this.liderazgo = liderazgo;
		this.jovialidad = jovialidad;
		this.seduccion = seduccion;
	}

	public int getConviccion() {
		return conviccion;
	}

	public void setConviccion(int conviccion) {
		this.conviccion = conviccion;
	}

	public int getEnganio() {
		return enganio;
	}

	public void setEnganio(int enganio) {
		this.enganio = enganio;
	}

	public int getIntimidacion() {
		return intimidacion;
	}

	public void setIntimidacion(int intimidacion) {
		this.intimidacion = intimidacion;
	}

	public int getLiderazgo() {
		return liderazgo;
	}

	public void setLiderazgo(int liderazgo) {
		this.liderazgo = liderazgo;
	}

	public int getJovialidad() {
		return jovialidad;
	}

	public void setJovialidad(int jovialidad) {
		this.jovialidad = jovialidad;
	}

	public int getSeduccion() {
		return seduccion;
	}

	public void setSeduccion(int seduccion) {
		this.seduccion = seduccion;
	}

}
