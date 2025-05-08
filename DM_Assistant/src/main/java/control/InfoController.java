package control;

import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import views.EscenaView;
import views.InfoView;
import views.ReinoView;
import views.UbicacionView;
import clases_partida.Criatura;
import clases_partida.Mundo;
import clases_partida.Nacion;
import clases_personaje.Personaje;

public class InfoController {

    private final InfoView infoView;
    
    public void cambiarVista(InfoViewType vistaTipo, TreeItem<Object> item) {
        VBox nuevaVista = switch (vistaTipo) {
            case MUNDO -> {
            	InfoMundoController mundoController = new InfoMundoController((Mundo) item.getValue());
            	yield mundoController.getVista();
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
}
