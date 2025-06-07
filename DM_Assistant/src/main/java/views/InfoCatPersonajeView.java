package views;

import clases_partida.Mundo;
import clases_personaje.Personaje;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoCatPersonajeView {
	private Mundo mundo;

    public InfoCatPersonajeView(Mundo mundo) {
        this.mundo = mundo;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(100); 
        pane.setPadding(new Insets(100));

        // Datos del mundo
        int nPersonajes = 0;
        
        for(Personaje personajes : mundo.getPersonajes()) {
        	nPersonajes++;
        }

        // Crear etiquetas
        Label lblInfo = new Label("Número de personajes en " + mundo.getNombre() + ": " + nPersonajes);

        // Agregar etiquetas al VBox
        pane.getChildren().addAll(lblInfo);

        return pane;
    }
}
