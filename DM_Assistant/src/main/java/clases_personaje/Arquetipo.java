package clases_personaje;

import clases_habilidades.Rasgo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "ARQUETIPOS")
public class Arquetipo {
	
	@Id
	@Column (name = "idArquetipo")
	private int idArquetipo;
	
	@Column (name = "fuerza")
	private int fuerza;
	
	@Column (name = "constitucion")
	private int constitucion;
	
	@Column (name = "destreza")
	private int destreza;

	@Column (name = "inteligencia")
	private int inteligencia;

	@Column (name = "sabiduria")
	private int sabiduria;

	@Column (name = "carisma")
	private int carisma;

	@Column (name = "iniciativa")
	private int iniciatva;

	@Column (name = "aura")
	private int aura;
	
	@Column (name = "movimiento")
	private int movimiento;
	
	public Arquetipo(){}

	public int getIdArquetipo() {
		return idArquetipo;
	}

	public void setIdArquetipo(int idArquetipo) {
		this.idArquetipo = idArquetipo;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getConstitucion() {
		return constitucion;
	}

	public void setConstitucion(int constitucion) {
		this.constitucion = constitucion;
	}

	public int getDestreza() {
		return destreza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getSabiduria() {
		return sabiduria;
	}

	public void setSabiduria(int sabiduria) {
		this.sabiduria = sabiduria;
	}

	public int getCarisma() {
		return carisma;
	}

	public void setCarisma(int carisma) {
		this.carisma = carisma;
	}

	public int getIniciatva() {
		return iniciatva;
	}

	public void setIniciatva(int iniciatva) {
		this.iniciatva = iniciatva;
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

}
