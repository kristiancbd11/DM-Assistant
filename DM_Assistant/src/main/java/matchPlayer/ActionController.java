package matchPlayer;

import clases_estadisticas.EstadoPjJson;
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
	
	public int cacularDanioAtaque(Personaje atacante, Personaje objetivo) {
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
	    int saludActual = estado.getStatOnline().getStatGeneral().getSalud();
	    int nuevaSalud = suma ? saludActual + salud : saludActual - salud;
	    nuevaSalud = Math.max(nuevaSalud, 0);

	    if (nuevaSalud != saludActual) {
	        estado.getStatOnline().getStatGeneral().setSalud(nuevaSalud);
	        personaje.setEstadoJson(estado.generarJson());
	        pjCrud.savePersonaje(personaje);
	    }
	}
	
}
