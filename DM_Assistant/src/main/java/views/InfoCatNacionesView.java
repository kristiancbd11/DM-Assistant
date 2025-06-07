package views;

import clases_partida.Mundo;
import clases_partida.Nacion;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoCatNacionesView {
	private Mundo mundo;

    public InfoCatNacionesView(Mundo mundo) {
        this.mundo = mundo;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(100); 
        pane.setPadding(new Insets(100));

        // Datos del mundo
        int nNaciones = 0;
        
        for(Nacion nacion : mundo.getNaciones()) {
        	nNaciones++;
        }

        // Crear etiquetas
        Label lblInfo = new Label("Número de naciones en " + mundo.getNombre() + ": " + nNaciones);

        // Agregar etiquetas al VBox
        pane.getChildren().addAll(lblInfo);

        return pane;
    }
}
