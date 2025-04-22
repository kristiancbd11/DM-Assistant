package clases_habilidades;

import clases_personaje.Personaje;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "RASGOS")
@PrimaryKeyJoinColumn(name = "idHabilidad") // Relaciona con la clave primaria de Habilidad
public class Rasgo extends Habilidad {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rasgos_gen")
//    @SequenceGenerator(name = "rasgos_gen", sequenceName = "rasgos_seq", allocationSize = 1)
//	@Column (name = "idRasgo")
//	private int idRasgo;
//	
//	@Id
//	@OneToOne
//	@JoinColumn (name = "idHabilidad", nullable = false)
//	private int idHabilidad;
	
	@Column (name = "unlockLvl")
	private int unlockLvl;
	
	//Constructores:
	
	public Rasgo(){}
	
	@Override
	public void ejecutar(Personaje personaje, Personaje objetivo) {
		// TODO Auto-generated method stub
		
	}
}
