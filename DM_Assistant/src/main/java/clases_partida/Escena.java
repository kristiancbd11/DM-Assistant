package clases_partida;

import java.util.List;

import clases_personaje.Personaje;
import designerView.Tablero;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "ESCENAS")
public class Escena extends Ubicacion {
	
	@Id
	@OneToOne
	@JoinColumn (name = "idUbicacion", nullable = false)
	private Ubicacion ubicacion;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "ESCENA_PERSONAJE", joinColumns = @JoinColumn(name = "idEscena"), inverseJoinColumns = @JoinColumn(name = "idPersonaje"))
	private List<Personaje> personajes;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "ESCENA_NPC", joinColumns = @JoinColumn(name = "idEscena"), inverseJoinColumns = @JoinColumn(name = "idNpc"))
	private List<Personaje> npcs;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "ESCENA_CRIATURA", joinColumns = @JoinColumn(name = "idEscena"), inverseJoinColumns = @JoinColumn(name = "idCriatura"))
	private List<Criatura> criaturas;
	
	private String tableroJson;
	
	public Escena(Reino reino, String nombre, UbicacionType tipo) {
		super(reino, nombre, tipo);
	}

	public Escena() {
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTableroJson() {
		return tableroJson;
	}

	public void setTableroJson(String tableroJson) {
		this.tableroJson = tableroJson;
	}

	@Override
	public String toString() {
		return getNombre();
	};

}
