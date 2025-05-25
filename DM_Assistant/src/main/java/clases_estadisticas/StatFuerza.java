package clases_estadisticas;

public class StatFuerza {
	
	private int impacto;
	private int destrozo;
	private int carga;
	private int levantamiento;
	private int vigor;
	private int atletismo;
	
	public StatFuerza() {}
	
	public StatFuerza(int impacto, int destrozo, int carga, int levantamiento, int vigor, int atletismo) {
		super();
		this.impacto = impacto;
		this.destrozo = destrozo;
		this.carga = carga;
		this.levantamiento = levantamiento;
		this.vigor = vigor;
		this.atletismo = atletismo;
	}
	public int getImpacto() {
		return impacto;
	}
	public void setImpacto(int fuerza) {
		this.impacto = fuerza;
	}
	public int getDestrozo() {
		return destrozo;
	}
	public void setDestrozo(int destrozo) {
		this.destrozo = destrozo;
	}
	public int getCarga() {
		return carga;
	}
	public void setCarga(int carga) {
		this.carga = carga;
	}
	public int getLevantamiento() {
		return levantamiento;
	}
	public void setLevantamiento(int levantamiento) {
		this.levantamiento = levantamiento;
	}
	public int getVigor() {
		return vigor;
	}
	public void setVigor(int vigor) {
		this.vigor = vigor;
	}
	public int getAtletismo() {
		return atletismo;
	}
	public void setAtletismo(int atletismo) {
		this.atletismo = atletismo;
	}

}
