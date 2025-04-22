package clases_estadisticas;

public class StatInteligencia {

	private int perspicacia;
	private int intuicion;
	private int investigacion;
	private int estrategia;
	private int memoria;
	private int comArcana;
	
	public StatInteligencia(int perspicacia, int intuicion, int investigacion, int estrategia, int memoria,
			int comArcana) {
		super();
		this.perspicacia = perspicacia;
		this.intuicion = intuicion;
		this.investigacion = investigacion;
		this.estrategia = estrategia;
		this.memoria = memoria;
		this.comArcana = comArcana;
	}
	
	public int getPerspicacia() {
		return perspicacia;
	}
	public void setPerspicacia(int perspicacia) {
		this.perspicacia = perspicacia;
	}
	public int getIntuicion() {
		return intuicion;
	}
	public void setIntuicion(int intuicion) {
		this.intuicion = intuicion;
	}
	public int getInvestigacion() {
		return investigacion;
	}
	public void setInvestigacion(int investigacion) {
		this.investigacion = investigacion;
	}
	public int getEstrategia() {
		return estrategia;
	}
	public void setEstrategia(int estrategia) {
		this.estrategia = estrategia;
	}
	public int getMemoria() {
		return memoria;
	}
	public void setMemoria(int memoria) {
		this.memoria = memoria;
	}
	public int getComArcana() {
		return comArcana;
	}
	public void setComArcana(int comArcana) {
		this.comArcana = comArcana;
	}
	
}
