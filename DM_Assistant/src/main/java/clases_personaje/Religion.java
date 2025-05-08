package clases_personaje;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "RELIGIONES")
public class Religion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "religiones_gen")
    @SequenceGenerator(name = "religiones_gen", sequenceName ="religiones_sec", allocationSize = 1)
	@Column (name = "idReligion")	
	private int idReligion;
	
	@Column(name = "nombre")
	private String nombre;
	
	public Religion(){}

	public int getIdReligion() {
		return idReligion;
	}

	public void setIdReligion(int idReligion) {
		this.idReligion = idReligion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
