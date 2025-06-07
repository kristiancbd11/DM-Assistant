package clases_habilidades;

import clases_personaje.Clase;
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

@Entity
@Table (name = "TALENTOS")
public class Talento {

	@Id
	@OneToOne
	@JoinColumn (name = "idHabilidad", nullable = false)
	private Habilidad habilidad;
	
	@ManyToOne
	@JoinColumn (name = "idClase", nullable = false)
	private Clase clase;
	
	@Column (name = "tipoLanzamiento", nullable = false)
	private int tipoLanzamiento;
	
	@Column (name = "preparacion")
	private int preparacion;
	
	@Column (name = "recuperacion", nullable = false)
	private int recuperacion;
	
	@Column (name = "usos", nullable = false)
	private int usos;
	
	//Constructores:
	
	public Talento(){}

	public Habilidad getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
	}
}
