package views;

import clases_partida.Escena;
import clases_partida.Reino;
import clases_partida.Ubicacion;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoReinoView {

    private Reino reino;

    public InfoReinoView(Reino reino) {
        this.reino = reino;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(20); 
        pane.setPadding(new Insets(20));

        // Header: DM Assistant
        Label headerLabel = new Label("DM Assistant - Resumen:");
        headerLabel.setStyle("-fx-background-color: #c3dfb0; -fx-font-family: 'Copperplate Gothic Bold'; -fx-font-size: 18px;");
        headerLabel.setPadding(new Insets(10));
        headerLabel.setMaxWidth(Double.MAX_VALUE);

        // Datos del reino
        String nombre = reino.getNombre();
        int nUbicaciones = reino.getUbicaciones().size();
        int nEscenas = 0;
        int nTiendas = 0;
        
        for (Ubicacion ubicacion : reino.getUbicaciones()) {
            if (ubicacion instanceof Escena) {
                nEscenas++;
            } else {
                nTiendas++;
            }
        }

        // Crear etiquetas principales
        Label lblNombre = new Label("Nombre del reino: " + nombre);
        Label lblCantidad = new Label("Número de ubicaciones: " + nUbicaciones);
        Label lblCantidadEscenas = new Label("- escenas: " + nEscenas);
        Label lblCantidadTiendas = new Label("- tiendas: " + nTiendas);
        Label lblUbicacionesTitulo = new Label("Ubicaciones:");

        // Agregar elementos al VBox
        pane.getChildren().addAll(
            headerLabel,
            lblNombre,
            lblCantidad,
            lblCantidadEscenas,
            lblCantidadTiendas,
            lblUbicacionesTitulo
        );

        // Agregar un label por cada ubicación
        for (Ubicacion ubicacion : reino.getUbicaciones()) {
            Label lblUbicacion = new Label("- " + ubicacion.getNombre());
            pane.getChildren().add(lblUbicacion);
        }

        return pane;
    }
}
