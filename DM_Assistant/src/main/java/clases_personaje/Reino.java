package clases_personaje;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "REINOS")
public class Reino {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reinos_gen")
    @SequenceGenerator(name = "reinos_gen", sequenceName = "reinos_sec", allocationSize = 1)
	@Column (name = "idReino")
	private int idReino;
	
	@Column (name = "nombre")
	private String nombre;
	
	public Reino(){}

	public int getIdReino() {
		return idReino;
	}

	public void setIdReino(int idReino) {
		this.idReino = idReino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
