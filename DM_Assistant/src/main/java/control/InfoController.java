package control;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import views.EscenaView;
import views.InfoCatCriaturasView;
import views.InfoCatNacionesView;
import views.InfoCatNpcView;
import views.InfoCatPersonajeView;
import views.InfoMundoView;
import views.InfoNacionView;
import views.InfoPersonajeView;
import views.InfoReinoView;
import views.InfoView;
import views.InfoViewType;
import views.DesktopReinoView;
import clases_partida.Criatura;
import clases_partida.Mundo;
import clases_partida.Nacion;
import clases_partida.Reino;
import clases_personaje.Personaje;

public class InfoController {

    private final InfoView infoView;
    private ExplorerController explorer;
    
    public void cambiarVista(InfoViewType vistaTipo, TreeItem<Object> item) {
        VBox nuevaVista = switch (vistaTipo) {
            case MUNDO -> {
            	InfoMundoView view = new InfoMundoView((Mundo) item.getValue());
            	yield view.crearView();
            }
            case CAT_CRIATURAS -> {
            	InfoCatCriaturasView view = new InfoCatCriaturasView(explorer.fetchTreeRoot(item));
            	yield view.crearView();
            }
            case CAT_NACIONES -> {
            	InfoCatNacionesView view = new InfoCatNacionesView(explorer.fetchTreeRoot(item));
            	yield view.crearView();
            }
            case CAT_NPCS -> {
            	InfoCatNpcView view = new InfoCatNpcView(explorer.fetchTreeRoot(item));
            	yield view.crearView();
            }
            case CAT_PERSONAJES -> {
            	InfoCatPersonajeView view = new InfoCatPersonajeView(explorer.fetchTreeRoot(item));
            	yield view.crearView();
            }
            case NACION -> {
            	InfoNacionView view = new InfoNacionView((Nacion) item.getValue());
            	yield view.crearView();
            }
            case REINO -> {
            	InfoReinoView view = new InfoReinoView((Reino) item.getValue());
            	yield view.crearView();
            }
            case PERSONAJE ->{
            	InfoPersonajeView view = new InfoPersonajeView((Personaje) item.getValue());
            	yield view.crearView();
            }
            default -> null;
        };

        if (nuevaVista != null) {
        	infoView.setContenido(nuevaVista);
        } else {
            System.out.println("Vista desconocida o nula: " + vistaTipo);
        }
    }
    
    public InfoController(InfoView infoView) {
        this.infoView = infoView;
    }

    public void limpiarInformacion() {
        infoView.getInfoLabel().setText("Sin información.");
    }

    public InfoView getView() {
        return infoView;
    }

	public void setExplorer(ExplorerController explorer) {
		this.explorer = explorer;
	}

}
