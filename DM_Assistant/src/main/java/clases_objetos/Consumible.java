package clases_objetos;

import jakarta.persistence.*;

@Entity
@Table(name = "CONSUMIBLES")
public class Consumible extends Objeto {

	@Id
	@OneToOne
	@JoinColumn (name = "idObjeto", nullable = false)
	private Objeto objeto;
	
    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;

    public Consumible() {}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
    
  
}

