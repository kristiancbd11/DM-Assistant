package views;

import clases_partida.Criatura;
import clases_partida.Mundo;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoCatCriaturasView {
	private Mundo mundo;

    public InfoCatCriaturasView(Mundo mundo) {
        this.mundo = mundo;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(100); 
        pane.setPadding(new Insets(100));

        // Datos del mundo
        int nCriaturas = 0;
        
        for(Criatura criatura : mundo.getCriaturas()) {
        	nCriaturas++;
        }

        // Crear etiquetas
        Label lblInfo = new Label("Número de criaturas en " + mundo.getNombre() + ": " + nCriaturas);

        // Agregar etiquetas al VBox
        pane.getChildren().addAll(lblInfo);

        return pane;
    }
}
