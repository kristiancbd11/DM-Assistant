package views;

import clases_partida.Nacion;
import clases_partida.Reino;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoNacionView {

    private Nacion nacion;

    public InfoNacionView(Nacion nacion) {
        this.nacion = nacion;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(20); 
        pane.setPadding(new Insets(20)); // márgenes alrededor del VBox

        // Datos de la nación
        String nombre = nacion.getNombre();
        int nReino = nacion.getReinos().size();

        // Crear etiquetas principales
        Label lblNombre = new Label("Nombre de la nación: " + nombre);
        Label lblCantidad = new Label("Número de reinos de la nación: " + nReino);
        Label lblReinosTitulo = new Label("Reinos:");

        // Agregar etiquetas al VBox
        pane.getChildren().addAll(lblNombre, lblCantidad, lblReinosTitulo);

        // Agregar un label por cada reino
        for (Reino reino : nacion.getReinos()) {
            Label lblReino = new Label("- " + reino.getNombre());
            pane.getChildren().add(lblReino);
        }

        return pane;
    }
}
