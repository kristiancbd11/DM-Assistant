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
}
