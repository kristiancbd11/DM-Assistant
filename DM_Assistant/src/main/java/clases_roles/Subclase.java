package clases_roles;

import java.util.List;

import clases_habilidades.Rasgo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table (name = "SUBCLASES")
public class Subclase {
	
	//Identificador
	@Id
	@Column (name = "idSubclase")
	private int idSubclase;
	
	@Column (name = "idClase")
	private int idClase;
	
	//Datos temáticos
	@Column (name = "nombre")
	private String nombre;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
    		name = "RASGOS_SUBCLASES",
    		joinColumns = @JoinColumn(name = "idSubclase"),
    		inverseJoinColumns = @JoinColumn(name = "idRasgo")
    )
	private List<Rasgo> rasgosSubclase;
	
	//Constructor:
	
	public Subclase(){}

}
