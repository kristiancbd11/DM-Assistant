package clases_habilidades;

import clases_roles.Clase;
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

//	@Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talentos_gen")
//    @SequenceGenerator(name = "talentos_gen", sequenceName = "talentos_seq", allocationSize = 1)
//	@Column (name = "idTalento")
//	private int idTalento;
//	
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
}
