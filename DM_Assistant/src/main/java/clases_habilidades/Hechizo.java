package clases_habilidades;

import clases_estados.Estado;
import clases_personaje.Dado;
import clases_personaje.Personaje;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table (name = "HECHIZOS")
public class Hechizo extends Habilidad {
	
	@Id
	@OneToOne
	@JoinColumn (name = "idHabilidad", nullable = false)
	private Habilidad habilidad;
	
	@Column (name = "costeAura")
	private int costeAura;
	
	@Column (name = "tipoLanzamiento")
	private int tipoLanzamiento;
	// -1: Descanso | 0: Reacción | 1: Acción | 2: Completo | 3+: Turno Extra
	
	@Column (name = "preparacion")
	private int preparacion;
	
	@Column (name = "recuperacion")
	private int recuperacion;
	
	@Column (name = "usos")
	private int usos;
	
	@Column (name = "nivel")
	private int nivel;
	
	@Column (name = "dado")
	private String dado;
	
	@Transient
	private Dado danio;
	
	@Column (name = "rango")
	private int rango;
	
	//Constructor:
	
	public Hechizo(){}

	@Override
	public void ejecutar(Personaje personaje, Personaje objetivo) {
		// TODO Auto-generated method stub
		
	}

	public Habilidad getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
	}

	public int getCosteAura() {
		return costeAura;
	}

	public void setCosteAura(int costeAura) {
		this.costeAura = costeAura;
	}

	public int getTipoLanzamiento() {
		return tipoLanzamiento;
	}

	public void setTipoLanzamiento(int tipoLanzamiento) {
		this.tipoLanzamiento = tipoLanzamiento;
	}

	public int getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(int preparacion) {
		this.preparacion = preparacion;
	}

	public int getRecuperacion() {
		return recuperacion;
	}

	public void setRecuperacion(int recuperacion) {
		this.recuperacion = recuperacion;
	}

	public int getUsos() {
		return usos;
	}

	public void setUsos(int usos) {
		this.usos = usos;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}

	public Dado getDanio() {
		return danio;
	}

	public void setDanio(Dado danio) {
		this.danio = danio;
	}

	public int getRango() {
		return rango;
	}

	public void setRango(int rango) {
		this.rango = rango;
	}

	@Override
	public String toString() {
		return super.getNombre();
	}
	
	
	
}
