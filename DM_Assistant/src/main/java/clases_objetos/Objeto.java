package clases_objetos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table (name = "OBJETOS")
public class Objeto {
	
	//Identificador
	@Id
	@Column (name = "idObjeto")
	private int idObjeto;
	
	//Datos temáticos
	@Column (name = "rareza")
	private String rareza;
	
	@Column (name = "nombre")
	private String nombre;
	
	//Datos estadísticos
	@Column (name = "valor")
	private int valor;
	
	@Column (name = "peso")
	private int peso;
	
	//Constructores:
	
	public Objeto(){}
}
