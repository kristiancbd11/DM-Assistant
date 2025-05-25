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
	
	@Column (name = "token")
	private String token;
	
	//Constructores:
	
	public Objeto(){}

	public int getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getRareza() {
		return rareza;
	}

	public void setRareza(String rareza) {
		this.rareza = rareza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}
}
