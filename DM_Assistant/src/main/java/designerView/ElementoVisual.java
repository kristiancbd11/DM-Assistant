package designerView;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.UUID;

import clases_partida.Criatura;
import clases_personaje.Personaje;
import javafx.geometry.Pos;

public class ElementoVisual {

	private VBox contenedor;
	private ElementoVisualData data;
	private TableroView tableroView;
	private Object obj;

	public ElementoVisual(ElementoVisualData data, TableroView tableroView) {
		this.data = data;
		this.contenedor = new VBox();
		this.tableroView = tableroView;
		this.obj = data.BuscarObjeto(data.getTipo(), data.getId());
		configurarVista();
	}
	
	

	private void configurarVista() {
		// Crear etiqueta con el nombre
		Label nombreLabel = new Label(data.getNombre());

		// Crear ImageView e intentar cargar el recurso
		ImageView imageView = new ImageView();
		String tokenPath = data.getToken();

		try {
			var imageUrl = getClass().getResource(tokenPath);
			if (imageUrl != null) {
				Image imagen = new Image(imageUrl.toExternalForm());
				imageView.setImage(imagen);
				imageView.setPreserveRatio(true);

				if (obj instanceof Criatura || obj instanceof Personaje) {
					imageView.setFitWidth(40);
					imageView.setFitHeight(40);
				} else if (obj instanceof ElementoAux) {
					imageView.setFitWidth(imagen.getWidth() / 6);
					imageView.setFitHeight(imagen.getHeight() / 6);
				} else {
					// Comportamiento por defecto, por si quieres definirlo
					imageView.setFitWidth(imagen.getWidth() / 6);
					imageView.setFitHeight(imagen.getHeight() / 6);
				}
			} else {
				System.err.println("No se encontró la imagen en recursos: " + tokenPath);
			}
		} catch (Exception e) {
			System.err.println("Error cargando imagen del recurso: " + tokenPath);
			e.printStackTrace();
		}

		// Configurar VBox
		contenedor.setAlignment(Pos.CENTER);
		contenedor.setSpacing(5);
		contenedor.getChildren().addAll(nombreLabel, imageView);

		contenedor.setUserData(this);

		// Variables para almacenar la posición al presionar
		final Delta dragDelta = new Delta();

		contenedor.setOnMouseClicked(event -> {
			if (tableroView != null) {
				ElementoVisual anterior = tableroView.getElementoResaltado();
				if (anterior != null) {
					anterior.getContenedor().setStyle("");
				}
				tableroView.setElementoResaltado(this);
			}
			contenedor.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
			event.consume();
		});

		contenedor.setOnMousePressed(event -> {
			dragDelta.x = event.getSceneX() - contenedor.getLayoutX();
			dragDelta.y = event.getSceneY() - contenedor.getLayoutY();
			contenedor.toFront();
			event.consume();
		});

		contenedor.setOnMouseDragged(event -> {
			double newX = event.getSceneX() - dragDelta.x;
			double newY = event.getSceneY() - dragDelta.y;

			contenedor.setLayoutX(newX);
			contenedor.setLayoutY(newY);

			event.consume();
		});

		contenedor.setOnMouseReleased(event -> {
		    if (tableroView != null) {
		        double newX = contenedor.getLayoutX();
		        double newY = contenedor.getLayoutY();

		        data.setEjeX(newX);
		        data.setEjeY(newY);

		        UUID clave = data.getClave();

		        // Siempre actualiza la posición del mismo objeto
		        tableroView.getElementosColocados().put(clave, data);

		        event.consume();
		    }
		});

		// Menú contextual para eliminar el elemento del tablero
		ContextMenu menuContextual = new ContextMenu();
		MenuItem eliminarItem = new MenuItem("Eliminar");
		menuContextual.getItems().add(eliminarItem);

		// Evento para clic derecho
		contenedor.setOnMousePressed(event -> {
		    if (event.getButton() == MouseButton.SECONDARY) {
		        menuContextual.show(contenedor, event.getScreenX(), event.getScreenY());
		        event.consume();
		    } else {
		        menuContextual.hide(); // Ocultar si no es clic derecho
		    }
		});

		// Acción de eliminar
		eliminarItem.setOnAction(e -> {
		    if (tableroView != null) {
		        Pane contenido = (Pane) tableroView.getRoot().getChildren().get(0);
		        contenido.getChildren().remove(contenedor);
		        tableroView.getElementosColocados().remove(data.getClave());
		    }
		});
	}

	public VBox getContenedor() {
		return contenedor;
	}

	public ElementoVisualData getData() {
		return data;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	private static class Delta {
		double x, y;
	}

}