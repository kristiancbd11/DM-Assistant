package clases_habilidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table (name = "VENTAJAS")
public class Ventaja {
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ventajas_gen")
//    @SequenceGenerator(name = "ventajas_gen", sequenceName = "ventajas_seq", allocationSize = 1)
//	@Column (name = "idVentaja")
//	private int idVentaja;
	
	@Id
	@OneToOne
	@JoinColumn (name = "idHabilidad", nullable = false)
	private Habilidad habilidad;
	
	@Column (name = "requisito")
	private int requisito;
	
	@Column (name = "raiz")
	private int raiz;
	
	//Constructores:
	
	public Ventaja(){}

	public Habilidad getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
	}
	
	
	
}
