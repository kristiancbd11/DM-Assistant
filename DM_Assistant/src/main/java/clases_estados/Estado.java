package clases_estados;

import java.util.Random;

import clases_personaje.Personaje;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class Estado {
	//Declaración de recursos:
	private Random rd = new Random();
	
	//Identificador
	private String nombre;
	
	//Referencia a la duración en turnos del estado
	private int duracion;
	
	//Referencia a la probabilidad en % de que el estado se aplique al objetivo
	// min 1
	// max 100
	private int probabilidad;
	
	//Globalidad del estado, especifíca si el estado afecta al personaje solo durante su turno
	//o también durante los turnos de los demás personajes
	private boolean global;
	
	//Este valor determina el número que se deverá superar para evitar el estado
	private int salvacion;
	
	//Determina el tipo de valor que debe superar la salvacion
	private String tipoSalvacion;
	
	//Constructores:
	
	public Estado(){}
	
	public boolean isEfective(Personaje personaje) {
		boolean resultado = false;
		return resultado;
	}
	
	public void efecto(Personaje personaje) {
		
	}
	
	public boolean isOvercomed(Personaje personaje) {
		// La salida de esta función debe ser manejada y llevadas a cabo las siguientes
		// acciones en función del resultado
		// resultado = 0 (Sin modificaciones)
		// resultado = 1 (Se retira el estado)
		boolean resultado = false;
		int duracion = this.duracion;
		if(duracion == 0) {
			resultado = true;
		} 
		return resultado;
	}
	
	public boolean estadoEfectivo(int control, int reduccion) {
		boolean resultado = false;
		//Generamos un número aleatorio de 1 a 100 
		//para representar la probabilidad en %
		int requerido = rd.nextInt(100) + 1;
		
		//Control representa el porcentaje necesario para acertar
		//Reducción es la resistencia  en % del perosnaje a sufrir el estado
		control = control - reduccion;
		
		//Control debe ser igual o mayor que requerido para que el estado afecte
		if(control>=requerido) {
			resultado = true;
		}
		
		return resultado;
	}

	public Random getRd() {
		return rd;
	}

	public void setRd(Random rd) {
		this.rd = rd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(int probabilidad) {
		this.probabilidad = probabilidad;
	}

	public boolean isGlobal() {
		return global;
	}

	public void setGlobal(boolean global) {
		this.global = global;
	}

	public int getSalvacion() {
		return salvacion;
	}

	public void setSalvacion(int salvacion) {
		this.salvacion = salvacion;
	}

	public String getTipoSalvacion() {
		return tipoSalvacion;
	}

	public void setTipoSalvacion(String tipoSalvacion) {
		this.tipoSalvacion = tipoSalvacion;
	}
	
}
