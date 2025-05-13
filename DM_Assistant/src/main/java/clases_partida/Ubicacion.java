package clases_partida;

import java.util.List;

import clases_personaje.Personaje;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "UBICACIONES")
public class Ubicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ubicacion_gen")
	@SequenceGenerator(name = "ubicacion_gen", sequenceName = "ubicacion_sec", allocationSize = 1)
	@Column(name = "idUbicacion")
	private int idUbicacion;

	@ManyToOne
	@JoinColumn(name = "idReino", nullable = true)
	private Reino reino;

	@Column(name = "nombre")	
	private String nombre;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private UbicacionType tipo;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "UBICACION_PERSONAJE", joinColumns = @JoinColumn(name = "idUbicacion"), inverseJoinColumns = @JoinColumn(name = "idPersonaje"))
	private List<Personaje> personajes;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "UBICACION_NPC", joinColumns = @JoinColumn(name = "idUbicacion"), inverseJoinColumns = @JoinColumn(name = "idNpc"))
	private List<Personaje> npcs;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "UBICACION_CRIATURA", joinColumns = @JoinColumn(name = "idUbicacion"), inverseJoinColumns = @JoinColumn(name = "idCriatura"))
	private List<Criatura> criaturas;

	public Ubicacion(Reino reino, String nombre, UbicacionType tipo) {
		super();
		this.reino = reino;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public Ubicacion() {
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
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

	public UbicacionType getTipo() {
		return tipo;
	}

	public void setTipo(UbicacionType tipo) {
		this.tipo = tipo;
	}

	public List<Personaje> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<Personaje> personajes) {
		this.personajes = personajes;
	}

	public List<Personaje> getNpcs() {
		return npcs;
	}

	public void setNpcs(List<Personaje> npcs) {
		this.npcs = npcs;
	}

	public List<Criatura> getCriaturas() {
		return criaturas;
	}

	public void setCriaturas(List<Criatura> criaturas) {
		this.criaturas = criaturas;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	

}
