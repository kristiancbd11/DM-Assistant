package clases_objetos;

import clases_habilidades.Habilidad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ARMADURAS")
public class Armadura extends Objeto {

	@Id
	@OneToOne
	@JoinColumn (name = "idObjeto", nullable = false)
	private Objeto objeto;
	
	// 0 -> Contundente
	// 1 -> Lacerante
	// 2 -> Perforante
	// 3 -> Igneo
	// 4 -> Eléctrico
	// 5 -> Gélido
	// 6 -> Psíquico
	// 7 -> Radiante
	// 8 -> Espectral
	// 9 -> Necrótico
	// 10 -> Sónico
	@Column(name = "resistencia")
	private byte resistencia;

	@Column(name = "tipo")
	private String tipo; // Casco, coraza, botas, guantes, grebas
	
	//Constructores:
	
	public Armadura(){}
}
