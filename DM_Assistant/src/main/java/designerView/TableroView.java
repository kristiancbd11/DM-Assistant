package designerView;

import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TableroView {

	private final double width;
	private final double height;

	private Pane panelVisual = new Pane();
	private final List<ElementoVisual> elementosColocados = new ArrayList<>();
	private PaletaView paletaView;

	public TableroView(double width, double height, int casillaTam) {
		this.width = width;
		this.height = height;
	}

	public Stage crearVentana(double x, double y) {
		panelVisual.setPrefSize(width, height);

		// Estilo por defecto
		panelVisual.setStyle(String.format(
				"-fx-background-image: url('/tablero/grounds/stone.png'); -fx-background-size: %fpx %fpx;",
				width, height));

		// Eventos DnD
		panelVisual.setOnDragOver(this::manejarDragOver);
		panelVisual.setOnDragDropped(this::manejarDragDropped);

		StackPane root = new StackPane(panelVisual);
		Stage stage = new Stage();
		stage.setTitle("Lienzo de Diseño Libre");
		stage.setScene(new Scene(root));
		stage.setX(x);
		stage.setY(y);
		stage.setResizable(false);
		stage.show();

		return stage;
	}

	private void manejarDragOver(DragEvent event) {
		if (event.getGestureSource() != panelVisual && event.getDragboard().hasString()) {
			event.acceptTransferModes(TransferMode.COPY);
		}
		event.consume();
	}

	private void manejarDragDropped(DragEvent event) {
		String nombreElemento = event.getDragboard().getString();
		double x = event.getX();
		double y = event.getY();

		if (paletaView == null) {
			System.err.println("PaletaView no ha sido establecida.");
			return;
		}

		Object obj = paletaView.getObjetoPorNombre(nombreElemento);
		if (obj != null) {
			ElementoVisual ev;

			if (obj instanceof ElementoVisual elementoExistente) {
				ev = new ElementoVisual(elementoExistente.getNombre(), 0, 0);
			} else {
				ev = new ElementoVisual(obj, 0, 0);
			}

			elementosColocados.add(ev);

			VBox nodoVisual = ElementoVisualBuilder.construirNodoVisual(ev);
			nodoVisual.setLayoutX(x);
			nodoVisual.setLayoutY(y);

			agregarEventoArrastre(nodoVisual, ev);
			panelVisual.getChildren().add(nodoVisual);

			if (obj instanceof clases_personaje.Personaje) {
				paletaView.removerElemento(nombreElemento);

				int id = obtenerIdDesdeObjeto(obj);
				for (CategoriaPaleta categoria : paletaView.getCategorias()) {
					if (categoria.getObjetosPorNombre().containsKey(nombreElemento)) {
						categoria.getEstadoColocados().put(id, true);
						break;
					}
				}
			}
		}

		event.setDropCompleted(true);
		event.consume();
	}

	private void agregarEventoArrastre(VBox nodoVisual, ElementoVisual ev) {
		nodoVisual.setOnMousePressed((MouseEvent event) -> {
			ev.setPosInicialX(event.getSceneX());
			ev.setPosInicialY(event.getSceneY());
			ev.setDragging(true);
		});

		nodoVisual.setOnMouseDragged((MouseEvent event) -> {
			double deltaX = event.getSceneX() - ev.getPosInicialX();
			double deltaY = event.getSceneY() - ev.getPosInicialY();

			double nuevaX = nodoVisual.getLayoutX() + deltaX;
			double nuevaY = nodoVisual.getLayoutY() + deltaY;

			double maxX = width - nodoVisual.getWidth();
			double maxY = height - nodoVisual.getHeight();

			nuevaX = Math.max(0, Math.min(nuevaX, maxX));
			nuevaY = Math.max(0, Math.min(nuevaY, maxY));

			nodoVisual.setLayoutX(nuevaX);
			nodoVisual.setLayoutY(nuevaY);

			ev.setPosInicialX(event.getSceneX());
			ev.setPosInicialY(event.getSceneY());
		});

		nodoVisual.setOnMouseReleased(event -> ev.setDragging(false));

		ContextMenu contextMenu = new ContextMenu();
		MenuItem eliminarItem = new MenuItem("Eliminar");
		contextMenu.getItems().add(eliminarItem);

		nodoVisual.setOnContextMenuRequested(e ->
			contextMenu.show(nodoVisual, e.getScreenX(), e.getScreenY())
		);

		eliminarItem.setOnAction(e -> {
			panelVisual.getChildren().remove(nodoVisual);
			elementosColocados.remove(ev);

			if (ev.getObj() instanceof clases_personaje.Personaje) {
				paletaView.reinsertarElemento(ev.getObj());
			}
		});
	}

	/**
	 * Carga todos los elementos del objeto Tablero al panel visual
	 * y refresca el fondo con el estilo especificado.
	 */
	public void cargarDesdeTablero(Tablero tablero) {
	    this.elementosColocados.clear();
	    this.panelVisual.getChildren().clear();

	    // Aquí puedes agregar lógica para construir los elementos visuales a partir del Tablero
	    for (ElementoVisual ev : tablero.getElementosColocados()) {
	        VBox nodo = ElementoVisualBuilder.construirNodoVisual(ev);
	        nodo.setLayoutX(ev.getPosInicialX());
	        nodo.setLayoutY(ev.getPosInicialY());
	        panelVisual.getChildren().add(nodo);
	    }

	    // Si es necesario, también puedes agregar lógica para cambiar el fondo del tablero
		this.panelVisual.setOnDragOver(this::manejarDragOver);
		this.panelVisual.setOnDragDropped(this::manejarDragDropped);
	    this.panelVisual.setStyle(tablero.getFondo());
	}


	public void actualizarFondo(String estiloCSS) {
		if (panelVisual != null && estiloCSS != null && !estiloCSS.isBlank()) {
			panelVisual.setStyle(estiloCSS);
		} else {
			System.err.println("No se pudo actualizar el fondo: estiloCSS es null o vacío.");
		}
	}

	private int obtenerIdDesdeObjeto(Object obj) {
		try {
			var method = obj.getClass().getMethod("getId");
			return (int) method.invoke(obj);
		} catch (Exception e) {
			System.err.println("Error al obtener ID del objeto: " + e.getMessage());
			return -1;
		}
	}

	// Getters y Setters

	public void setPaletaView(PaletaView paletaView) {
		this.paletaView = paletaView;
	}

	public Pane getPanelVisual() {
		return panelVisual;
	}

	public void setPanelVisual(Pane panelVisual) {
		this.panelVisual = panelVisual;
	}

	public List<ElementoVisual> getElementosColocados() {
		return elementosColocados;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
}
