package clases_estadisticas;

public class StatConstitucion {

	private int vitalidad;
	private int corpulencia;
	private int regeneracion;
	private int tenacidad;
	private int resistencia;
	private int motricidad;
	
	//Constructor de la clase	
	public StatConstitucion(int vitalidad, int corpulencia, int regeneracion, int tenacidad, int resistencia,
			int motricidad) {
		super();
		this.vitalidad = vitalidad;
		this.corpulencia = corpulencia;
		this.regeneracion = regeneracion;
		this.tenacidad = tenacidad;
		this.resistencia = resistencia;
		this.motricidad = motricidad;
	}
	
	//Getters y setters
	public int getVitalidad() {
		return vitalidad;
	}
	public void setVitalidad(int vitalidad) {
		this.vitalidad = vitalidad;
	}
	public int getCorpulencia() {
		return corpulencia;
	}
	public void setCorpulencia(int corpulencia) {
		this.corpulencia = corpulencia;
	}
	public int getRegeneracion() {
		return regeneracion;
	}
	public void setRegeneracion(int regeneracion) {
		this.regeneracion = regeneracion;
	}
	public int getTenacidad() {
		return tenacidad;
	}
	public void setTenacidad(int tenacidad) {
		this.tenacidad = tenacidad;
	}
	public int getResistencia() {
		return resistencia;
	}
	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}
	public int getMotricidad() {
		return motricidad;
	}
	public void setMotricidad(int motricidad) {
		this.motricidad = motricidad;
	}
	
}
