package clases_personaje;

import java.util.Random;

public class Dado {
	
	//Característicasd del dado
	private int cantidad;
	private int caras;
	
	private Dado dadoBack;
	public void setDadoBack(Dado dado) {
		this.dadoBack = dado;
	}
	public Dado getDadoBack() {
		return dadoBack;
	}
	
	
	//Declaración de recursos
	private Random rd = new Random();
	
	//Funciones:
	
	//Constructor
	public Dado(String formato) {
		String [] valores = formato.split("d");
		this.cantidad = Integer.parseInt(valores[0]);
		this.caras = Integer.parseInt(valores[1]);
	}
	
	//Función para lanzar el dado
	public int totalThrow() {
		int resultado = 0;
		
		for (int i = 0; i<cantidad; i++) {
			resultado += rd.nextInt(caras)+1;
		}
		
		return resultado;
	}
	
	public int[] splitThrow() {
		int[] resultado = new int[cantidad];
		
		for (int i = 0; i<cantidad; i++) {
			resultado[i] = rd.nextInt(caras)+1;
		}
		
		return resultado;
	}
	
	@Override
	public String toString() {
		return "Dado [cantidad=" + cantidad + ", caras=" + caras;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCaras() {
		return caras;
	}
	public void setCaras(int caras) {
		this.caras = caras;
	}
	
	
	
}
