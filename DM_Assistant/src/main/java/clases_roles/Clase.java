package clases_roles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import clases_habilidades.Hechizo;
import clases_habilidades.Rasgo;
import clases_personaje.Arquetipo;
import clases_personaje.Dado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table (name = "CLASES")
public class Clase {
	
	//Identificador
	@Id
	@Column (name = "idClase")
	private int idClase;
	
	//Datos temáticos
	@Column (name = "nombre")
	private String nombre;
	
	//Datos estadísticos
	@Column (name = "dado")
	private String dado;
	
	@Transient
	private Dado dadoImpacto;
	
    // 0: No competente || 1: Competente
    // Bit 1: Armas diestras
    // Bit 2: Armas de duelo
    // Bit 3: Armas de guerra
    // Bit 4: Armas a dos manos
    // Bit 5: Armas a distancia
	// Bit 6: Escudos
	// Bit 7: Puños
	// Bit 8: Guadañas
	@Column (name = "armas")
	private byte[] competenciaArma;
	
	// 0: No competente || 1: Competente
	// Bit 1: Armaduras ligeras
	// Bit 2: Armaduras medias
	// Bit 3: Armaduras pesadas
	@Column (name = "armaduras")
	private byte[] competenciaArmadura;
	
    @ManyToOne
    @JoinColumn(name = "idArquetipo", nullable = true)
	private Arquetipo arquetipo;
	
	//Datos de capacidades
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
    		name = "RASGOS_CLASES",
    		joinColumns = @JoinColumn(name = "idClase"),
    		inverseJoinColumns = @JoinColumn(name = "idRasgo")
    )
    private List<Rasgo> listaRasgos;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
    		name = "HECHIZOS_CLASES",
    		joinColumns = @JoinColumn(name = "idClase"),
    		inverseJoinColumns = @JoinColumn(name = "idHechizo")
    )
	private List<Hechizo> listaHechizo;

    //Constructor:
    
    public Clase(){}
    
	//Funciones:
    
    public void getCompetence() {
    	for(int i = 7; i > 0; i--) {
    		boolean control = (this.competenciaArma[0] & (1 << i)) != 0;
    		if(control) {
    			System.out.println("Competencia " + i);
    		}
    	}
    	
//    	if((competenciaArma[0] & 0x01) != 0) {
//    		System.out.println("Armas diestras");
//    	}
//    	if((competenciaArma[0] & 0x02) != 0) {
//    		System.out.println("Armas de duelo");
//    	}
//    	if((competenciaArma[0] & 0x04) != 0) {
//    		System.out.println("Armas de guerra");
//    	}
//    	if((competenciaArma[0] & 0x08) != 0) {
//    		System.out.println("Armas a dos manos");
//    	}
//    	if((competenciaArma[0] & 0x10) != 0) {
//    		System.out.println("Armas a distancia");
//    	}
//    	if((competenciaArma[0] & 0x20) != 0) {
//    		System.out.println("Escudos");
//    	}
//    	if((competenciaArma[0] & 0x40) != 0) {
//    		System.out.println("Combate con puños");
//    	}
//    	if((competenciaArma[0] & 0x80) != 0) {
//    		System.out.println("Guadañas");
//    	}
    }
    
    //Getters y Setters:

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}

	public Dado getDadoImpacto() {
		return dadoImpacto;
	}

	public void setDadoImpacto(Dado dadoImpacto) {
		this.dadoImpacto = dadoImpacto;
	}

	public byte[] getCompetenciaArma() {
		return competenciaArma;
	}

	public void setCompetenciaArma(byte competenciaArma[]) {
		this.competenciaArma = competenciaArma;
	}

	public byte[] getCompetenciaArmadura() {
		return competenciaArmadura;
	}

	public void setCompetenciaArmadura(byte competenciaArmadura[]) {
		this.competenciaArmadura = competenciaArmadura;
	}

	public Arquetipo getArquetipo() {
		return arquetipo;
	}

	public void setArquetipo(Arquetipo arquetipo) {
		this.arquetipo = arquetipo;
	}

	public List<Rasgo> getListaRasgos() {
		return listaRasgos;
	}

	public void setListaRasgos(List<Rasgo> listaRasgos) {
		this.listaRasgos = listaRasgos;
	}

	public List<Hechizo> getListaHechizo() {
		return listaHechizo;
	}

	public void setListaHechizo(List<Hechizo> listaHechizo) {
		this.listaHechizo = listaHechizo;
	}

	@Override
	public String toString() {
		return nombre;
	}
    
}


