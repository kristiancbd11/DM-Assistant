package control;

import clases_partida.Escena;
import clases_partida.Mundo;
import clases_partida.Reino;
import clases_partida.Tienda;
import clases_partida.Ubicacion;
import clases_partida.UbicacionType;
import dbhandlerCRUD.UbicacionCRUD;
import designerView.DesignerController;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import views.CreationUbicacionView;

public class CreationUbicacionController {

	private final Stage window;
	private final CreationUbicacionView view;
	private final ExplorerController explorerController;
	private final Mundo mundo;
	private final TreeItem<Object> item;
	private final UbicacionCRUD ubicacionCRUD = new UbicacionCRUD();

	public CreationUbicacionController(Stage window, CreationUbicacionView view, ExplorerController explorerController,
			Mundo mundo, TreeItem<Object> item) {
		this.window = window;
		this.view = view;
		this.explorerController = explorerController;
		this.mundo = mundo;
		this.item = item;

		inicializarEventos();

		if (item != null && item.getValue() instanceof Reino reinoSeleccionado) {
			view.getComboReino().setValue(reinoSeleccionado);
		}
	}

	private void inicializarEventos() {
		view.getCrearButton().setOnAction(e -> crearZona());
		view.getCancelarButton().setOnAction(e -> window.close());
	}

	private void crearZona() {
		String nombre = view.getNombreField().getText().trim();
		UbicacionType tipo = view.getTipoComboBox().getValue();
		Reino reino = view.getComboReino().getValue();

		if (nombre.isEmpty() || tipo == null || reino == null) {
			mostrarAlerta("Por favor, completa todos los campos.");
			return;
		}

		if (tipo == UbicacionType.TIENDA) {
			Tienda nuevaZona = new Tienda(reino, nombre, tipo);
			reino.addUbicacion(nuevaZona);
			boolean exito = ubicacionCRUD.saveUbicacion(nuevaZona);

			if (exito) {
				explorerController.refreshTreeView();
				window.close();
			} else {
				mostrarAlerta("No se pudo guardar la ubicación.");
			}
		} else if (tipo == UbicacionType.ESCENA) {
			Escena nuevaZona = new Escena(reino, nombre, tipo);
			reino.addUbicacion(nuevaZona);
			DesignerController dc = new DesignerController(mundo, nuevaZona, explorerController);
			dc.iniciar();
			window.close();
		}

	}

	private void mostrarAlerta(String mensaje) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}
}
