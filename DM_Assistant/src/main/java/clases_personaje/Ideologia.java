package clases_personaje;

import clases_partida.Nacion;
import clases_partida.O_Nacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "IDEOLOGIAS")
public class Ideologia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ideologias_gen")
    @SequenceGenerator(name = "ideologias_gen", sequenceName ="ideologias_sec", allocationSize = 1)
	@Column (name = "idIdeologia")
	private int idIdeologia;
	
	@ManyToOne
	@JoinColumn(name = "idNacion", nullable = false)
	private O_Nacion nacion;
	
	@Column (name = "nombre")
	private String nombre;
	
	public Ideologia(){}

	public int getIdIdeologia() {
		return idIdeologia;
	}

	public void setIdIdeologia(int idFaccion) {
		this.idIdeologia = idFaccion;
	}

	public O_Nacion getNacion() {
		return nacion;
	}

	public void setNacion(O_Nacion nacion) {
		this.nacion = nacion;
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
