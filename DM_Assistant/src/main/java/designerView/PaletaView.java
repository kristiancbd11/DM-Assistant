package designerView;

import clases_partida.Mundo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaletaView {

	private final Mundo mundo;
	private final TableroView tableroView;
	private final Map<String, Object> objetosPorNombre = new HashMap<>();
	private List<CategoriaPaleta> categorias = new ArrayList<>();

	public PaletaView(Mundo mundo, TableroView tableroView) {
		this.mundo = mundo;
		this.tableroView = tableroView;
	}

	public Stage crearVentana(double x, double y) {
	    VBox contenido = new VBox(10);
	    contenido.setPadding(new Insets(10));
	    contenido.setAlignment(Pos.TOP_CENTER);

	    TitledPane vegetacionPane = crearCategoriaDesdeImagenes("Vegetación", "/tablero/forest");
	    TitledPane cuevaPane = crearCategoriaDesdeImagenes("Cueva", "/tablero/cave");
	    TitledPane decoracionPane = crearCategoriaDesdeImagenes("Decoración", "/tablero/decoration");

	    TitledPane personajesPane = crearCategoria("Personajes", mundo.getPersonajes());
	    TitledPane npcsPane = crearCategoria("NPCs", mundo.getNpcs());
	    TitledPane criaturasPane = crearCategoria("Criaturas", mundo.getCriaturas());
	    TitledPane tablerosPane = crearPaneTableros();

	    Accordion accordion = new Accordion(
	        vegetacionPane, cuevaPane, decoracionPane,
	        personajesPane, npcsPane, criaturasPane, tablerosPane
	    );

	    contenido.getChildren().add(accordion);

	    Stage stage = new Stage();
	    stage.setTitle("Paleta de Elementos");
	    stage.setScene(new Scene(contenido, 200, 600));
	    stage.setX(x);
	    stage.setY(y);
	    stage.setResizable(false);
	    stage.show();
	    return stage;
	}


	private TitledPane crearCategoria(String titulo, List<?> elementos) {
		CategoriaPaleta categoria = new CategoriaPaleta(titulo, elementos);
		categorias.add(categoria);
		objetosPorNombre.putAll(categoria.getObjetosPorNombre());
		return new TitledPane(titulo, categoria.getContenedor());
	}

	private GridPane crearPaletaColores() {
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(10));
		Color[] colores = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.BROWN,
				Color.GRAY };

		for (int i = 0; i < colores.length; i++) {
			Rectangle rect = new Rectangle(40, 40, colores[i]);
			grid.add(rect, i % 4, i / 4);
		}

		return grid;
	}

	public Object getObjetoPorNombre(String nombre) {
		return objetosPorNombre.get(nombre);
	}

	public void removerElemento(String nombre) {
		for (CategoriaPaleta categoria : categorias) {
			Object obj = categoria.getObjetosPorNombre().get(nombre);
			if (obj != null && obj instanceof clases_personaje.Personaje) {
				// Buscar el nodo en el GridPane y eliminarlo
				categoria.getContenedor().getChildren().removeIf(node -> {
					if (node instanceof VBox vbox) {
						Label label = (Label) vbox.getChildren().get(0);
						return label.getText().equals(nombre);
					}
					return false;
				});
				break;
			}
		}
	}

	public void reinsertarElemento(Object obj) {
		for (CategoriaPaleta categoria : categorias) {
			if (categoria.getObjetosPorNombre().containsValue(obj)) {
				categoria.reinsertarObjeto(obj);
				break;
			}
		}
	}

	private TitledPane crearPaneTableros() {
		VBox contenedor = new VBox(5);
		contenedor.setPadding(new Insets(10));

		try {
			var path = getClass().getResource("/tablero/grounds/");
			if (path != null) {
				var folder = new java.io.File(path.toURI());
				var archivos = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));

				if (archivos != null) {
					for (var archivo : archivos) {
						String nombre = archivo.getName();
						Label label = new Label(nombre);
						label.setOnMouseClicked(e -> {
							String rutaCss = String.format(
									"-fx-background-image: url('/tablero/grounds/%s'); -fx-background-size: %fpx %fpx;",
									nombre, tableroView.getWidth(), tableroView.getHeight());
							tableroView.actualizarFondo(rutaCss);
						});
						contenedor.getChildren().add(label);
					}
				}
			}
		} catch (Exception e) {
			contenedor.getChildren().add(new Label("Error al cargar tableros."));
			e.printStackTrace();
		}

		return new TitledPane("Tableros", contenedor);
	}

	private TitledPane crearCategoriaDesdeImagenes(String titulo, String carpetaRelativa) {
		VBox contenedor = new VBox(5);
		contenedor.setPadding(new Insets(5));
		contenedor.setAlignment(Pos.TOP_CENTER);

		try {
			var path = getClass().getResource(carpetaRelativa);
			if (path != null) {
				var folder = new java.io.File(path.toURI());
				var archivos = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));

				if (archivos != null) {
					int fila = 0, columna = 0;
					GridPane grid = new GridPane();
					grid.setHgap(10);
					grid.setVgap(10);
					grid.setAlignment(Pos.TOP_CENTER);

					for (var archivo : archivos) {
						String nombreArchivo = archivo.getName();
						String rutaCompleta = carpetaRelativa + "/" + nombreArchivo;

						ElementoVisual ev = new ElementoVisual(rutaCompleta, 0, 0);
						objetosPorNombre.put(ev.getNombre(), ev);

						VBox nodo = ev.getNodoVisual();
						nodo.setOnDragDetected(event -> {
							var db = nodo.startDragAndDrop(javafx.scene.input.TransferMode.COPY);
							var content = new javafx.scene.input.ClipboardContent();
							content.putString(ev.getNombre());
							db.setContent(content);
							db.setDragView(nodo.snapshot(null, null));
							event.consume();
						});

						grid.add(nodo, columna, fila);
						columna++;
						if (columna >= 4) {
							columna = 0;
							fila++;
						}
					}

					contenedor.getChildren().add(grid);
				}
			}
		} catch (Exception e) {
			contenedor.getChildren().add(new Label("Error al cargar " + titulo));
			e.printStackTrace();
		}

		return new TitledPane(titulo, contenedor);
	}

	public List<CategoriaPaleta> getCategorias() {
		return categorias;
	}
}
