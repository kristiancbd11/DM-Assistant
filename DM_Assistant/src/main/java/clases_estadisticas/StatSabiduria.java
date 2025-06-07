package clases_estadisticas;

import java.util.ArrayList;

public class StatSabiduria {
	
	private int conocimiento;
	private int medicina;
	private int percepcion;
	private int empatia;
	private int voluntad;
	private int supervivencia;
	
	public StatSabiduria() {}
	
	public StatSabiduria(int conocimiento, int medicina, int percepcion, int empatia, int voluntad, int supervivencia) {
		super();
		this.conocimiento = conocimiento;
		this.medicina = medicina;
		this.percepcion = percepcion;
		this.empatia = empatia;
		this.voluntad = voluntad;
		this.supervivencia = supervivencia;
	}
	
	public ArrayList<Integer> getAll() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		lista.add(conocimiento);
		lista.add(medicina);
		lista.add(percepcion);
		lista.add(empatia);
		lista.add(voluntad);
		lista.add(supervivencia);
		return lista;
	}
	
	public int getConocimiento() {
		return conocimiento;
	}
	public void setConocimiento(int conocimiento) {
		this.conocimiento = conocimiento;
	}
	public int getMedicina() {
		return medicina;
	}
	public void setMedicina(int medicina) {
		this.medicina = medicina;
	}
	public int getPercepcion() {
		return percepcion;
	}
	public void setPercepcion(int percepcion) {
		this.percepcion = percepcion;
	}
	public int getEmpatia() {
		return empatia;
	}
	public void setEmpatia(int empatia) {
		this.empatia = empatia;
	}
	public int getVoluntad() {
		return voluntad;
	}
	public void setVoluntad(int voluntad) {
		this.voluntad = voluntad;
	}
	public int getSupervivencia() {
		return supervivencia;
	}
	public void setSupervivencia(int supervivencia) {
		this.supervivencia = supervivencia;
	}
}
