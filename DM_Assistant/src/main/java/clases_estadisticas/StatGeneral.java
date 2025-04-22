package clases_estadisticas;

public class StatGeneral {
	
	private boolean accion = true;
	private boolean reaccion = true;
	private int salud;
	private int iniciativa;
	private int aura;
	private int movimiento;
	private String dado;
	
	public StatGeneral(int salud, int iniciativa, int aura, int movimiento,
			String dado) {
		super();
		this.salud = salud;
		this.iniciativa = iniciativa;
		this.aura = aura;
		this.movimiento = movimiento;
		this.dado = dado;
	}
	public boolean isAccion() {
		return accion;
	}
	public void setAccion(boolean accion) {
		this.accion = accion;
	}
	public boolean isReaccion() {
		return reaccion;
	}
	public void setReaccion(boolean reaccion) {
		this.reaccion = reaccion;
	}
	public int getSalud() {
		return salud;
	}
	public void setSalud(int salud) {
		this.salud = salud;
	}
	public int getIniciativa() {
		return iniciativa;
	}
	public void setIniciativa(int iniciativa) {
		this.iniciativa = iniciativa;
	}
	public int getAura() {
		return aura;
	}
	public void setAura(int aura) {
		this.aura = aura;
	}
	public int getMovimiento() {
		return movimiento;
	}
	public void setMovimiento(int movimiento) {
		this.movimiento = movimiento;
	}
	public String getDado() {
		return dado;
	}
	public void setDado(String dado) {
		this.dado = dado;
	}
}
