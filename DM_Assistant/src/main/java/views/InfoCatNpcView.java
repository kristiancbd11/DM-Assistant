package views;

import clases_partida.Mundo;
import clases_personaje.Personaje;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoCatNpcView {
	private Mundo mundo;

    public InfoCatNpcView(Mundo mundo) {
        this.mundo = mundo;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(100); 
        pane.setPadding(new Insets(100));

        // Datos del mundo
        int nNpc = 0;
        
        for(Personaje npc : mundo.getNpcs()) {
        	nNpc++;
        }

        // Crear etiquetas
        Label lblInfo = new Label("Número de npcs en " + mundo.getNombre() + ": " + nNpc);

        // Agregar etiquetas al VBox
        pane.getChildren().addAll(lblInfo);

        return pane;
    }
}
