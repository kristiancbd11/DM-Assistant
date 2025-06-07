package clases_habilidades;

import clases_personaje.Personaje;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table (name = "HABILIDADES")
public abstract class Habilidad {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "habilidades_gen")
    @SequenceGenerator(name = "habilidades_gen", sequenceName = "habilidades_seq", allocationSize = 1)
	@Column (name = "idHabilidad")
	private int idHabilidad;
	
	@Column (name = "nombre")
	private String nombre;
	
	@Column (name = "descripcion")
	private String descripcion;
	
	@Column (name = "activa")
	private boolean activa;
	
	@Transient
	private short cooldown;
	
	public abstract void ejecutar(Personaje personaje, Personaje objetivo);

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdHabilidad() {
		return idHabilidad;
	}	
	
}
