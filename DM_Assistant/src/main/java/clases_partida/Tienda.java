package clases_partida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import clases_objetos.Objeto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "TIENDAS")
public class Tienda extends Ubicacion {
	
	@Id
	@OneToOne
	@JoinColumn (name = "idUbicacion", nullable = false)
	private Ubicacion ubicacion;
	
	@Column (name = "fondos")
	private int fondos;
	
	@OneToMany(mappedBy = "tienda", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<TiendaObjeto> objetosConCantidad = new ArrayList<>();
	
	public Tienda(Reino reino, String nombre, UbicacionType tipo) {
		super(reino, nombre, tipo);
	}
	
	public List<TiendaObjeto> getObjetosConCantidad() {
	    return objetosConCantidad;
	}

	public Tienda() {}
	
	public void setObjetosConCantidad(List<TiendaObjeto> objetosConCantidad) {
	    this.objetosConCantidad = objetosConCantidad;
	}
	
	public void removeTiendaObjeto(TiendaObjeto tiendaObjeto) {
		this.objetosConCantidad.remove(tiendaObjeto);
	}
	
	public void addTiendaObjeto(TiendaObjeto tiendaObjeto) {
		this.objetosConCantidad.add(tiendaObjeto);
	}

	public Ubicacion getZona() {
		return ubicacion;
	}

	public void setZona(Ubicacion zona) {
		this.ubicacion = zona;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getFondos() {
		return fondos;
	}

	public void setFondos(int fondos) {
		this.fondos = fondos;
	}
	
	@Override
	public String toString() {
		return getNombre();
	}
	
	
	
}
