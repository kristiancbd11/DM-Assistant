package clases_partida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "O_REINOS")
public class O_Reino {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oreinos_gen")
    @SequenceGenerator(name = "oreinos_gen", sequenceName = "oreinos_seq", allocationSize = 1)
	@Column (name = "idReino")
	private int idReino;
	
	@Column (name = "nombre")
	private String nombre;

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

	@Override
	public String toString() {
		return nombre;
	}

}
