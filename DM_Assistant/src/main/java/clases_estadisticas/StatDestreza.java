package clases_estadisticas;

public class StatDestreza {

	private int agilidad;
	private int presteza;
	private int reflejos;
	private int combate;
	private int sigilo;
	private int precision;
	
	public StatDestreza() {}
	
	public StatDestreza(int agilidad, int presteza, int reflejos, int combate, int sigilo, int precision) {
		super();
		this.agilidad = agilidad;
		this.presteza = presteza;
		this.reflejos = reflejos;
		this.combate = combate;
		this.sigilo = sigilo;
		this.precision = precision;
	}
	public int getAgilidad() {
		return agilidad;
	}
	public void setAgilidad(int agilidad) {
		this.agilidad = agilidad;
	}
	public int getPresteza() {
		return presteza;
	}
	public void setPresteza(int presteza) {
		this.presteza = presteza;
	}
	public int getReflejos() {
		return reflejos;
	}
	public void setReflejos(int reflejos) {
		this.reflejos = reflejos;
	}
	public int getCombate() {
		return combate;
	}
	public void setCombate(int combate) {
		this.combate = combate;
	}
	public int getSigilo() {
		return sigilo;
	}
	public void setSigilo(int sigilo) {
		this.sigilo = sigilo;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}	
}
