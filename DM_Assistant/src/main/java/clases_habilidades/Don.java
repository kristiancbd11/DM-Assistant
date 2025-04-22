package clases_habilidades;

import clases_personaje.Personaje;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "DONES")
public class Don extends Habilidad {

//	@Id
//	@Column (name = "idDon")
//	private int idDon;
//
	@Id
	@OneToOne
	@JoinColumn (name = "idHabilidad", nullable = false)
	private Habilidad habilidad;
	
	@Column (name = "preparacion")
	private int preparacion;
	
	@Column (name = "usos")
	private int usos;
	
	//Constructores:
	
	public Don(){}
	
	@Override
	public void ejecutar(Personaje personaje, Personaje objetivo) {
		// TODO Auto-generated method stub
		
	}

}
