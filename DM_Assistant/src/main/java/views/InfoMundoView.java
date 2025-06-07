package views;

import clases_partida.Escena;
import clases_partida.Mundo;
import clases_partida.Nacion;
import clases_partida.Reino;
import clases_partida.Ubicacion;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoMundoView {

    private Mundo mundo;

    public InfoMundoView(Mundo mundo) {
        this.mundo = mundo;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(100); 
        pane.setPadding(new Insets(100));

        // Datos del mundo
        String nombre = mundo.getNombre();
        int nPj = mundo.getPersonajes().size();
        int nNpc = mundo.getNpcs().size();
        int nCriaturas = mundo.getCriaturas().size();
        int nUbicaciones = 0;
        int nEscenas = 0;
        int nTiendas = 0;

        for (Nacion nacion : mundo.getNaciones()) {
            for (Reino reino : nacion.getReinos()) {
                nUbicaciones += reino.getUbicaciones().size();
                for (Ubicacion ubicacion : reino.getUbicaciones()) {
                    if (ubicacion instanceof Escena) {
                        nEscenas++;
                    } else {
                        nTiendas++;
                    }
                }
            }
        }

        // Crear etiquetas
        Label lblNombre = new Label("Nombre del mundo: " + nombre);
        Label lblPj = new Label("Número de personajes: " + nPj);
        Label lblNpc = new Label("Número de npc: " + nNpc);
        Label lblCriaturas = new Label("Número de criaturas: " + nCriaturas);
        Label lblUbicaciones = new Label("Número de ubicaciones: " + nUbicaciones);
        Label lblEscenas = new Label("Número de escenas: " + nEscenas);
        Label lblTiendas = new Label("Número de tiendas: " + nTiendas);

        // Agregar etiquetas al VBox
        pane.getChildren().addAll(lblNombre, lblPj, lblNpc, lblCriaturas, lblUbicaciones, lblEscenas, lblTiendas);

        return pane;
    }
}
