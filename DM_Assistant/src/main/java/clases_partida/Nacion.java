package clases_partida;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "NACIONES")
public class Nacion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nacion_gen")
	@SequenceGenerator(name = "nacion_gen", sequenceName = "nacion_sec", allocationSize = 1)
	@Column(name = "idNacion")
	private int idNacion;

	@ManyToOne
	@JoinColumn(name = "idMundo", nullable = false)
	private Mundo mundo;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "nacion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Reino> reinos;

	public Nacion() {
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

	public Mundo getMundo() {
		return mundo;
	}

	public void setMundo(Mundo mundo) {
		this.mundo = mundo;
	}

	public List<Reino> getReinos() {
		return reinos;
	}

	public void setReinos(List<Reino> reinos) {
		this.reinos = reinos;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
