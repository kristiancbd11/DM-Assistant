package clases_objetos;

import clases_estadisticas.EstadoPjJson;
import clases_personaje.Dado;
import clases_personaje.InventarioPersonaje;
import clases_personaje.Personaje;
import jakarta.persistence.*;
import matchPlayer.ActionController;

@Entity
@Table(name = "CONSUMIBLES")
public class Consumible extends Objeto {

	@Id
	@OneToOne
	@JoinColumn (name = "idObjeto", nullable = false)
	private Objeto objeto;
	
	@Column (name = "dado")
	private String dado;
	
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
    
	public int consumir(Personaje personaje, InventarioPersonaje inp, ActionController actionController) {
		EstadoPjJson estado = EstadoPjJson.desdeJson(personaje.getEstadoJson());
		Dado dado = new Dado(this.dado);
		int resultado = dado.totalThrow();
		if(inp.getCantidad() - 1 <= 0) {
			personaje.getObjetosConCantidad().remove(inp);
		} else {
			inp.setCantidad(inp.getCantidad() - 1);
		}
		
		if(tipo.equals("Cura")) {
			actionController.modificarSalud(personaje, resultado, true);
		} else if (tipo.equals("Elixir")) {
			actionController.modificarAura(personaje, resultado, true);
		} else if (tipo.equals("Buff")) {
			
		} else if (tipo.equals("Veneno")) {
			
		}
		return resultado;
	}
  
}

