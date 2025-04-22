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
}
