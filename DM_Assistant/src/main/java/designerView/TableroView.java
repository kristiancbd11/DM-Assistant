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

	private Pane panelVisual;
	private final List<ElementoVisual> elementosColocados = new ArrayList<>();
	private PaletaView paletaView;

	public TableroView(double width, double height, int casillaTam) {
		this.width = width;
		this.height = height;
	}

	public Stage crearVentana(double x, double y) {
		panelVisual = new Pane();
		panelVisual.setPrefSize(width, height);

		// Fondo con imagen
		panelVisual.setStyle("""
				-fx-background-image: url('/tablero/grounds/stone.png');
				-fx-background-size: 1024px 768px;
				""");

		// Eventos de DnD
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

		Object obj = paletaView.getObjetoPorNombre(nombreElemento);
		if (obj != null) {
			ElementoVisual ev;

			// Verificamos si ya es un ElementoVisual (por ejemplo, para imágenes de
			// decoración, cueva, etc.)
			if (obj instanceof ElementoVisual elementoExistente) {
				ev = new ElementoVisual(elementoExistente.getNombre(), 0, 0); // recreamos usando ruta como string
			} else {
				ev = new ElementoVisual(obj, 0, 0); // para personajes, NPCs, criaturas
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
        ev.setDragging(true); // Establecer que se está arrastrando
    });

    nodoVisual.setOnMouseDragged((MouseEvent event) -> {
        double deltaX = event.getSceneX() - ev.getPosInicialX();
        double deltaY = event.getSceneY() - ev.getPosInicialY();

        double nuevaX = nodoVisual.getLayoutX() + deltaX;
        double nuevaY = nodoVisual.getLayoutY() + deltaY;

        // Limitar dentro del tablero
        double maxX = width - nodoVisual.getWidth();
        double maxY = height - nodoVisual.getHeight();

        // Clamp (restringe) la posición dentro de los límites
        nuevaX = Math.max(0, Math.min(nuevaX, maxX));
        nuevaY = Math.max(0, Math.min(nuevaY, maxY));

        nodoVisual.setLayoutX(nuevaX);
        nodoVisual.setLayoutY(nuevaY);

        ev.setPosInicialX(event.getSceneX());
        ev.setPosInicialY(event.getSceneY());
    });

    nodoVisual.setOnMouseReleased(event -> {
        ev.setDragging(false); // Establecer que ya no se está arrastrando
    });

    ContextMenu contextMenu = new ContextMenu();
    MenuItem eliminarItem = new MenuItem("Eliminar");
    contextMenu.getItems().add(eliminarItem);

    nodoVisual.setOnContextMenuRequested(e -> {
        contextMenu.show(nodoVisual, e.getScreenX(), e.getScreenY());
    });

    eliminarItem.setOnAction(e -> {
        panelVisual.getChildren().remove(nodoVisual);
        elementosColocados.remove(ev);

        // Volver a mostrarlo en la paleta si corresponde
        if (ev.getObj() instanceof clases_personaje.Personaje) {
            paletaView.reinsertarElemento(ev.getObj());
        }
    });
}


	private int obtenerIdDesdeObjeto(Object obj) {
		try {
			var method = obj.getClass().getMethod("getId");
			return (int) method.invoke(obj);
		} catch (Exception e) {
			System.err.println("Error al obtener ID: " + e.getMessage());
			return -1;
		}
	}

	public void actualizarFondo(String estiloCSS) {
		if (panelVisual != null) {
			panelVisual.setStyle(estiloCSS);
		}
	}

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
