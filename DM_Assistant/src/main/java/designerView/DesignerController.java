package designerView;

import control.ExplorerController;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import clases_partida.Escena;
import clases_partida.Mundo;
import dbhandlerCRUD.UbicacionCRUD;
import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;

public class DesignerController {

	private TableroView tableroView;
	private PaletaView paletaView;
	private Tablero tablero;
	private Mundo mundo;
	private Escena escena;
	private ExplorerController explorerController;

	private Stage principalStage;
	private Stage paletaStage; // Nuevo stage para PaletaView

	public DesignerController(Mundo mundo, Escena escena, ExplorerController explorerController) {
		this.mundo = mundo;
		this.escena = escena;
		this.explorerController = explorerController;

		if (escena.getTableroJson() == null) {
			this.tablero = new Tablero();
		} else {
			this.tablero = Tablero.desdeJson(escena.getTableroJson());
		}
	}

	public Stage iniciar() {
		if (principalStage != null && principalStage.isShowing()) {
			principalStage.toFront();
			if (paletaStage != null && paletaStage.isShowing()) {
				paletaStage.toFront();
			}
			return principalStage;
		}

		tableroView = new TableroView(tablero);
		paletaView = new PaletaView(mundo);

		paletaView.setTableroView(tableroView);
		tableroView.setPaleta(paletaView);

		paletaView.inicializar();
		tableroView.inicializar();

		// --- Ventana principal solo con TableroView ---
		principalStage = new Stage();
		Scene sceneTablero = new Scene(tableroView.getRoot());
		principalStage.setTitle("Diseñador de Escena - Tablero");
		principalStage.setScene(sceneTablero);
		principalStage.setWidth(1024);
		principalStage.setHeight(768);
		principalStage.show();

		// --- Nueva ventana para PaletaView ---
		paletaStage = new Stage();
		Scene scenePaleta = new Scene(paletaView.getRoot());
		paletaStage.setTitle("Diseñador de Escena - Paleta");
		paletaStage.setScene(scenePaleta);
		paletaStage.setWidth(300);
		paletaStage.setHeight(768);

		// Posicionar la ventana de Paleta a la derecha de la principal (10px de
		// separación)
		paletaStage.setX(principalStage.getX() + principalStage.getWidth() + 10);
		paletaStage.setY(principalStage.getY());
		paletaStage.show();

		// --- Vincular cierre entre ventanas ---
		principalStage.setOnCloseRequest(event -> {
			if (paletaStage != null) {
				paletaStage.close();
			}
		});

		paletaStage.setOnCloseRequest(event -> {
			if (principalStage != null) {
				principalStage.close();
			}
		});

		// --- Acción del botón Guardar ---
		paletaView.setOnGuardarAction(() -> {
			guardarTablero();
			if (principalStage != null)
				principalStage.close();
			if (paletaStage != null)
				paletaStage.close();
		});

		return principalStage;
	}

	private void guardarTablero() {
		if (tablero == null) {
			tablero = new Tablero();
		}

		String rutaFondo = tableroView.getRutaFondoActual();
		if (rutaFondo == null || rutaFondo.isEmpty()) {
			rutaFondo = "tablero/grounds/Default.png";
		}

		tablero.setElementosColocados(tableroView.getElementosColocados());
		tablero.setFondo(rutaFondo);
		escena.setTableroJson(tablero.generarJson());

		UbicacionCRUD ubiCrud = new UbicacionCRUD();
		ubiCrud.saveUbicacion(escena);
		explorerController.refreshTreeView();
	}
}
