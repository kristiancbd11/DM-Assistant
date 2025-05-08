package clases_partida;

import java.util.ArrayList;
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
@Table(name = "REINOS")
public class Reino {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reino_gen")
	@SequenceGenerator(name = "reino_gen", sequenceName = "reino_sec", allocationSize = 1)
	@Column(name = "idReino")
	private int idReino;

	@ManyToOne
	@JoinColumn(name = "idNacion", nullable = true)
	private Nacion nacion;

	@Column(name = "nombre")
	private String nombre;

	@OneToMany(mappedBy = "reino", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();

	public Reino() {
	}

	@Override
	public String toString() {
		return nombre;
	}

	public int getIdReino() {
		return idReino;
	}

	public void setIdReino(int idReino) {
		this.idReino = idReino;
	}

	public Nacion getNacion() {
		return nacion;
	}

	public void setNacion(Nacion nacion) {
		this.nacion = nacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public void addUbicacion(Ubicacion ubicacion) {
		this.ubicaciones.add(ubicacion);
	}
	
	public void removeUbicacion(Ubicacion ubicacion) {
		this.ubicaciones.remove(ubicacion);
		ubicacion.setReino(null);
	}
}

