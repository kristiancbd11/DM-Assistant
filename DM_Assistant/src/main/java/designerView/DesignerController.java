package designerView;

import java.util.ArrayList;

import clases_partida.Escena;
import clases_partida.Mundo;
import control.ExplorerController;
import dbhandlerCRUD.UbicacionCRUD;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DesignerController {

	private double gridWidth = 1024;
	private double gridHeight = 768;
	private double paletteWidth = 200;
	private double margen = 10;

	private final Mundo mundo;
	private ExplorerController explorerController;
	private TableroView tableroView = new TableroView(gridWidth, gridHeight, 0);
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
			// Crear ventana
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			double gridX = (screenBounds.getWidth() - (gridWidth + margen + paletteWidth)) / 2;
			double gridY = (screenBounds.getHeight() - gridHeight) / 2;

			paletaView = new PaletaView(mundo, tableroView);
			paletaView.getGuardarButton().setOnAction(event -> {
				guardarTablero();
			});
			tableroView.setPaletaView(paletaView);

			// Establecer el tablero de la escena en el tableroView
			String tableroJson = nuevaZona.getTableroJson();
			Tablero tablero;
			if (tableroJson != null) {
				try {
					tablero = Tablero.desdeJson(tableroJson);
				} catch (Exception e) {
					System.err.println("Error al cargar el tablero desde JSON, se creará uno en blanco.");
					e.printStackTrace();
					tablero = new Tablero("", new ArrayList<>());
				}
			} else {
				tablero = new Tablero("", new ArrayList<>()); // fondo vacío y sin elementos
			}

			tableroView.cargarDesdeTablero(tablero);

			// Crear ventanas
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
				if (stageTablero != null)
					stageTablero.close();
				if (stagePaleta != null)
					stagePaleta.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TableroView getTableroView() {
		return tableroView;
	}

}
