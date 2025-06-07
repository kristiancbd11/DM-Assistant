package control;

import clases_partida.Criatura;
import clases_partida.Mundo;
import clases_partida.Nacion;
import clases_partida.Reino;
import clases_partida.Ubicacion;
import clases_personaje.Personaje;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import views.*;

public class DesktopController {

    private final DesktopView desktopView;
    private final DesktopNacionController nacionController;
    private ExplorerController explorerController;

    public DesktopController(DesktopView vista) {
        this.desktopView = vista;
        this.nacionController = new DesktopNacionController(desktopView);
    }

    public StackPane getVista() {
        return desktopView.getDesktop();
    }

    public void cambiarVista(DesktopViewType vistaTipo, TreeItem<Object> item, Mundo mundo) {
        StackPane nuevaVista = switch (vistaTipo) {
            case PERSONAJE -> {
            	nacionController.setNacion(null);
                DesktopPersonajeController personajeController = new DesktopPersonajeController((Personaje) item.getValue(), this, mundo);
                yield personajeController.getVista();
            }
            case CRIATURA -> {
            	nacionController.setNacion(null);
            	DesktopCriaturaController criaturaController = new DesktopCriaturaController((Criatura) item.getValue());
            	yield criaturaController.getVista();
            }
            case NACION -> {
            	nacionController.setNacion((Nacion) item.getValue());
            	yield nacionController.getVista();
            }
            case MUNDO -> {
            	nacionController.setNacion(null);
            	DesktopMundoController mundoController = new DesktopMundoController((Mundo) item.getValue());
            	yield mundoController.getVista();
            }
            case REINO -> {
            	nacionController.setNacion(null);
            	DesktopReinoView reinoView = new DesktopReinoView((Reino) item.getValue());
            	yield reinoView.crearView();
            }
            case UBICACION -> {
            	nacionController.setNacion(null);
            	DesktopUbicacionView ubicacionView = new DesktopUbicacionView((Ubicacion) item.getValue(), explorerController);
            	yield ubicacionView.getVista();
            }
            case ESCENA -> new EscenaView().crearView();
            default -> null;
        };

        if (nuevaVista != null) {
            desktopView.setContenido(nuevaVista);
        } else {
            System.out.println("Vista desconocida o nula: " + vistaTipo);
        }
    }
    
    public void vistaInicio() {
    	desktopView.setContenido(desktopView.generarVistaInicio());
    }

	public DesktopNacionController getDesktopNacionController() {
		return nacionController;
	}

	public ExplorerController getExplorerController() {
		return explorerController;
	}

	public void setExplorerController(ExplorerController explorerController) {
		this.explorerController = explorerController;
	}
	
	
}

