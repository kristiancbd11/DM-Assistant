package clases_objetos;

import clases_personaje.Dado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table (name = "ARMAS")
public class Arma extends Objeto {
	//Datos de ataque
	
	@Id
	@OneToOne
	@JoinColumn (name = "idObjeto", nullable = false)
	private Objeto objeto;
	
	@Column (name = "dado")
	private String dado;
	
	@Transient
	private Dado danio;
	
	@Column (name = "tipoDanio")
	private String tipoDanio; //Lacerante, Perforante, Contundente
	
	//Pasivas adicionales por tratar:
	//private String consagracion;
	//private String encantamiento;
	//private String engarce;
	
	//Constructores:
	
	public Arma(){}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
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

	public String getTipoDanio() {
		return tipoDanio;
	}

	public void setTipoDanio(String tipoDanio) {
		this.tipoDanio = tipoDanio;
	}
	

}
