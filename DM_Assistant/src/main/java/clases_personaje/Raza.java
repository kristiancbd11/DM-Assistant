package clases_personaje;

import clases_habilidades.Rasgo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "RAZAS")
public class Raza {
		
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "razas_gen")
    @SequenceGenerator(name = "razas_gen", sequenceName = "razas_sec", allocationSize = 1)
	@Column (name = "idRaza")
	private int idRaza;
	
	@Column (name = "nombre")
	private String nombre;

	@OneToOne
	@JoinColumn (name = "idArquetipo", nullable = false)
	private Arquetipo arquetipo;
	
	@OneToOne
	@JoinColumn (name = "idRasgo", nullable = false)
	private Rasgo rasgo;
	
	public Raza(){}

	public int getIdRaza() {
		return idRaza;
	}

	public void setIdRaza(int idRaza) {
		this.idRaza = idRaza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Arquetipo getArquetipo() {
		return arquetipo;
	}

	public void setArquetipo(Arquetipo arquetipo) {
		this.arquetipo = arquetipo;
	}

	public Rasgo getRasgo() {
		return rasgo;
	}

	public void setRasgo(Rasgo rasgo) {
		this.rasgo = rasgo;
	}

	
	
}
