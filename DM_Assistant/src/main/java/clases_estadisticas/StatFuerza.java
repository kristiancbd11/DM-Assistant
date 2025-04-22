package clases_estadisticas;

public class StatFuerza {
	
	private int fuerza;
	private int destrozo;
	private int carga;
	private int levantamiento;
	private int vigor;
	private int atletismo;
	
	public StatFuerza(int fuerza, int destrozo, int carga, int levantamiento, int vigor, int atletismo) {
		super();
		this.fuerza = fuerza;
		this.destrozo = destrozo;
		this.carga = carga;
		this.levantamiento = levantamiento;
		this.vigor = vigor;
		this.atletismo = atletismo;
	}
	public int getFuerza() {
		return fuerza;
	}
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
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
