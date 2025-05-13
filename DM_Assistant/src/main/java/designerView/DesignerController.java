package designerView;

import clases_partida.Escena;
import clases_partida.Mundo;
import control.ExplorerController;
import dbhandlerCRUD.UbicacionCRUD;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DesignerController {

    private final Mundo mundo;
    private ExplorerController explorerController;
    private TableroView tableroView;
    private PaletaView paletaView;
    private Tablero tablero;
    private Escena nuevaZona;
    private UbicacionCRUD ubicacionCrud;

    // Referencias a los stages
    private Stage stageTablero;
    private Stage stagePaleta;

    public DesignerController(Mundo mundo, Escena nuevaZona, ExplorerController explorerController) {
        this.mundo = mundo;
        this.nuevaZona = nuevaZona;
        this.explorerController = explorerController;
    }

    public void iniciar() {
        Platform.runLater(() -> {
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            double gridWidth = 1024;
            double gridHeight = 768;
            double paletteWidth = 200;
            double margen = 10;

            double gridX = (screenBounds.getWidth() - (gridWidth + margen + paletteWidth)) / 2;
            double gridY = (screenBounds.getHeight() - gridHeight) / 2;

            tableroView = new TableroView(gridWidth, gridHeight, 0);
            paletaView = new PaletaView(mundo, tableroView);
            paletaView.getGuardarButton().setOnAction(event -> {
                guardarTablero();
            });
            tableroView.setPaletaView(paletaView);

            // Guardar referencias a los stages
            stageTablero = tableroView.crearVentana(gridX, gridY);
            stagePaleta = paletaView.crearVentana(gridX + gridWidth + margen, gridY);

            stageTablero.setOnCloseRequest(e -> stagePaleta.close());
            stagePaleta.setOnCloseRequest(e -> stageTablero.close());
        });
    }

    public void guardarTablero() {
        String fondo = tableroView.getPanelVisual().getStyle();
        Tablero tablero = new Tablero(fondo, tableroView.getElementosColocados());

        try {
            nuevaZona.setTableroJson(tablero.generarJson());
            ubicacionCrud = new UbicacionCRUD();

            boolean exito = ubicacionCrud.saveUbicacion(nuevaZona);
            if (exito) {
                explorerController.refreshTreeView();

                // ✅ Cerrar las ventanas tras guardar
                if (stageTablero != null) stageTablero.close();
                if (stagePaleta != null) stagePaleta.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
