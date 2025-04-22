package clases_personaje;

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
	private int idFaccion;
	
	@ManyToOne
	@JoinColumn(name = "idReino", nullable = false)
	private Reino reino;
	
	@Column (name = "nombre")
	private String nombre;
	
	public Ideologia(){}

	public int getIdFaccion() {
		return idFaccion;
	}

	public void setIdFaccion(int idFaccion) {
		this.idFaccion = idFaccion;
	}

	public Reino getReino() {
		return reino;
	}

	public void setReino(Reino reino) {
		this.reino = reino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
