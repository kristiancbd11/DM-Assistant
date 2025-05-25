package clases_estadisticas;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import clases_estados.Estado;
import clases_estados.Buff;

public class EstadoPjJson {
	ConjuntoEstadisticas statBase;
	ConjuntoEstadisticas statOnline;
	ArrayList<Estado> listaEstados = new ArrayList<Estado>();
	ArrayList<Buff> listaBuff = new ArrayList<Buff>();
	
	public EstadoPjJson() {}
	
	public EstadoPjJson(ConjuntoEstadisticas statBase) {
		super();
		this.statBase = statBase;
		this.statOnline = statBase;
	}

	public String generarJson() {
		String json = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	public static EstadoPjJson desdeJson(String estadoJson) {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        return mapper.readValue(estadoJson, EstadoPjJson.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public ConjuntoEstadisticas getStatBase() {
		return statBase;
	}
	public void setStatBase(ConjuntoEstadisticas statBase) {
		this.statBase = statBase;
	}
	public ConjuntoEstadisticas getStatOnline() {
		return statOnline;
	}
	public void setStatOnline(ConjuntoEstadisticas statOnline) {
		this.statOnline = statOnline;
	}
	public ArrayList<Estado> getListaEstados() {
		return listaEstados;
	}
	public void setListaEstados(ArrayList<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}
	public ArrayList<Buff> getListaBuff() {
		return listaBuff;
	}
	public void setListaBuff(ArrayList<Buff> listaBuff) {
		this.listaBuff = listaBuff;
	}
		
}

