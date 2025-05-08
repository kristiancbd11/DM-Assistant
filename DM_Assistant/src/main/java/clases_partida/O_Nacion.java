package clases_partida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "O_NACIONES")
public class O_Nacion {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "onaciones_gen")
    @SequenceGenerator(name = "onaciones_gen", sequenceName = "onaciones_seq", allocationSize = 1)
	@Column (name = "idNacion")
	private int idNacion;
	
	@Column (name = "nombre")
	private String nombre;
	
	public O_Nacion() {
		
	}

	public int getIdNacion() {
		return idNacion;
	}

	public void setIdNacion(int idNacion) {
		this.idNacion = idNacion;
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
