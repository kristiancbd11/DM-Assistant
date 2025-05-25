package clases_estadisticas;

public class ConjuntoEstadisticas {
	
	StatGeneral statGeneral;
	StatFuerza statFuerza;
	StatConstitucion statConstitucion;
	StatDestreza statDestreza;
	StatInteligencia statInteligencia;
	StatSabiduria statSabiduria ;
	StatCarisma statCarisma;
	
	public ConjuntoEstadisticas () {}
	
	public ConjuntoEstadisticas(StatGeneral statGeneral, StatFuerza statFuerza, StatConstitucion statConstitucion,
			StatDestreza statDestreza, StatInteligencia statInteligencia, StatSabiduria statSabiduria,
			StatCarisma statCarisma) {
		super();
		this.statGeneral = statGeneral;
		this.statFuerza = statFuerza;
		this.statConstitucion = statConstitucion;
		this.statDestreza = statDestreza;
		this.statInteligencia = statInteligencia;
		this.statSabiduria = statSabiduria;
		this.statCarisma = statCarisma;
	}

	public StatGeneral getStatGeneral() {
		return statGeneral;
	}

	public void setStatGeneral(StatGeneral statGeneral) {
		this.statGeneral = statGeneral;
	}

	public StatFuerza getStatFuerza() {
		return statFuerza;
	}

	public void setStatFuerza(StatFuerza statFuerza) {
		this.statFuerza = statFuerza;
	}

	public StatConstitucion getStatConstitucion() {
		return statConstitucion;
	}

	public void setStatConstitucion(StatConstitucion statConstitucion) {
		this.statConstitucion = statConstitucion;
	}

	public StatDestreza getStatDestreza() {
		return statDestreza;
	}

	public void setStatDestreza(StatDestreza statDestreza) {
		this.statDestreza = statDestreza;
	}

	public StatInteligencia getStatInteligencia() {
		return statInteligencia;
	}

	public void setStatInteligencia(StatInteligencia statInteligencia) {
		this.statInteligencia = statInteligencia;
	}

	public StatSabiduria getStatSabiduria() {
		return statSabiduria;
	}

	public void setStatSabiduria(StatSabiduria statSabiduria) {
		this.statSabiduria = statSabiduria;
	}

	public StatCarisma getStatCarisma() {
		return statCarisma;
	}

	public void setStatCarisma(StatCarisma statCarisma) {
		this.statCarisma = statCarisma;
	}
	
}
