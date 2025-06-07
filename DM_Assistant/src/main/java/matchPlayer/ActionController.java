package matchPlayer;

import clases_estadisticas.EstadoPjJson;
import clases_habilidades.Hechizo;
import clases_objetos.Arma;
import clases_partida.Mundo;
import clases_personaje.Dado;
import clases_personaje.EquipoPersonaje;
import clases_personaje.Personaje;
import dbhandlerCRUD.PersonajeCRUD;

public class ActionController {
	
	private Mundo mundo;
	private PersonajeCRUD pjCrud = new PersonajeCRUD();

	public ActionController(Mundo mundo) {
		this.mundo = mundo;
	}
	
	public boolean exitoAtaqueRango(Personaje atacante, Personaje objetivo) {
		Dado _1d20 = new Dado("1d20");
		
		int precision = _1d20.totalThrow() + EstadoPjJson.desdeJson(atacante.getEstadoJson()).getStatOnline().getStatDestreza().getPrecision();
		int evasion = 5 + EstadoPjJson.desdeJson(objetivo.getEstadoJson()).getStatOnline().getStatDestreza().getAgilidad();
		
		if(precision >= evasion) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean exitoAtaque(Personaje atacante, Personaje objetivo) {
		Dado _1d20 = new Dado("1d20");
		
		int combate = _1d20.totalThrow() + EstadoPjJson.desdeJson(atacante.getEstadoJson()).getStatOnline().getStatDestreza().getCombate();
		int evasion = 5 + EstadoPjJson.desdeJson(objetivo.getEstadoJson()).getStatOnline().getStatDestreza().getAgilidad();
		
		if(combate >= evasion) {
			return true;
		} else {
			return false;
		}
	}

	public int calcularDanioHechizo(Personaje atacante, Personaje objetivo, Hechizo hechizo) {
		int danio = 0;
		Dado dadoDanio = new Dado(hechizo.getDado());
		int comArcana = EstadoPjJson.desdeJson(atacante.getEstadoJson()).getStatOnline().getStatInteligencia().getComArcana();
		
		danio = dadoDanio.totalThrow() + comArcana;
		
		return danio;
	}
	
	public int calcularDanioAtaque(Personaje atacante, Personaje objetivo) {
		Arma arma = null;
		int danio = 0;
		for(EquipoPersonaje ep : atacante.getEquipacion()) {
			if(ep.getRanura() == 6) {
				arma = (Arma) ep.getObjeto();
			}
		}

		Dado dadoClase = new Dado(atacante.getClase().getDado());
		
		if(arma != null) {
			Dado dadoArma = new Dado(arma.getDado());
			danio = dadoClase.totalThrow() + dadoArma.totalThrow() + EstadoPjJson.desdeJson(atacante.getEstadoJson()).getStatOnline().getStatFuerza().getImpacto();
			
		} else {
			danio = dadoClase.totalThrow() + EstadoPjJson.desdeJson(atacante.getEstadoJson()).getStatOnline().getStatFuerza().getImpacto();
			
		}
		
		return danio;
		
		// TODO calcular resistencia de armadura
	}
	
	public void modificarSalud(Personaje personaje, int salud, boolean suma) {
	    EstadoPjJson estado = EstadoPjJson.desdeJson(personaje.getEstadoJson());
	    int saludMax = estado.getStatBase().getStatGeneral().getSalud();
	    int saludActual = estado.getStatOnline().getStatGeneral().getSalud();
	    int nuevaSalud = suma ? saludActual + salud : saludActual - salud;

	    nuevaSalud = Math.max(0, Math.min(nuevaSalud, saludMax));

	    if (nuevaSalud != saludActual) {
	        estado.getStatOnline().getStatGeneral().setSalud(nuevaSalud);
	        personaje.setEstadoJson(estado.generarJson());
	        pjCrud.savePersonaje(personaje);
	    }
	}
	
	public void modificarAura(Personaje personaje, int aura, boolean suma) {
		EstadoPjJson estado = EstadoPjJson.desdeJson(personaje.getEstadoJson());
		int auraMax = estado.getStatBase().getStatGeneral().getAura();
	    int auraActual = estado.getStatOnline().getStatGeneral().getAura();
	    int nuevaAura = suma ? auraActual + aura : auraActual - aura;
	    
	    nuevaAura = Math.max(0, Math.min(nuevaAura, auraMax));
	    
	    if (nuevaAura != auraActual) {
	        estado.getStatOnline().getStatGeneral().setAura(nuevaAura);
	        personaje.setEstadoJson(estado.generarJson());
	        pjCrud.savePersonaje(personaje);
	    }
	}
	
	public boolean superaDesafio(Personaje personaje, String tipo, int dificultad, int tirada) {
		boolean resultado = false;
		int modificador = personaje.getStatConcreto(tipo);
		
		if(dificultad <= modificador + tirada) {
			resultado = true;
		}
		
		return resultado;
	}
	
}
