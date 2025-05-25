package designerView;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import clases_partida.Mundo;

public class PaletaView {

	private TableroView tableroView;
	private VBox root;
	private Mundo mundo;
	private Button btnGuardar;
	private ElementoVisual seleccionado;
	private List<ElementoVisual> personajesDisponibles;
	private TitledPane panelPersonajes;

	public PaletaView(Mundo mundo) {
		this.mundo = mundo;
		root = new VBox(10);
		root.setPrefSize(200, 768);
		root.setAlignment(Pos.TOP_CENTER);
	}

	public void inicializar() {
	    ComboBox<String> selectorFondos = crearSelectorFondos();

	    personajesDisponibles = mundo.getPersonajes().stream()
	        .map(obj -> new ElementoVisual(new ElementoVisualData(UUID.randomUUID(), obj, 0, 0, ElementoVisualType.PERSONAJE), tableroView))
	        .collect(Collectors.toList());

	    List<ElementoVisual> npcs = mundo.getNpcs().stream()
	        .map(obj -> new ElementoVisual(new ElementoVisualData(UUID.randomUUID(), obj, 0, 0, ElementoVisualType.NPC), tableroView))
	        .toList();

	    List<ElementoVisual> criaturas = mundo.getCriaturas().stream()
	        .map(obj -> new ElementoVisual(new ElementoVisualData(UUID.randomUUID(), obj, 0, 0, ElementoVisualType.CRIATURA), tableroView))
	        .toList();

		panelPersonajes = crearPanelDeElementos("Personajes", personajesDisponibles, 2);
		TitledPane panelNpcs = crearPanelDeElementos("NPCs", npcs, 2);
		TitledPane panelCriaturas = crearPanelDeElementos("Criaturas", criaturas, 2);

		List<ElementoVisual> decoradoresCave = cargarDecoradores("tablero/cave");
		List<ElementoVisual> decoradoresForest = cargarDecoradores("tablero/forest");
		List<ElementoVisual> decoradoresDecoration = cargarDecoradores("tablero/decoration");

		TitledPane panelDecoradoresCave = crearPanelDeElementos("Decoradores Cave", decoradoresCave, 3);
		TitledPane panelDecoradoresForest = crearPanelDeElementos("Decoradores Forest", decoradoresForest, 3);
		TitledPane panelDecoradoresDecoration = crearPanelDeElementos("Decoradores Decoration", decoradoresDecoration,3);

		root.getChildren().addAll(selectorFondos, panelPersonajes, panelNpcs, panelCriaturas, panelDecoradoresCave,
				panelDecoradoresForest, panelDecoradoresDecoration);

		btnGuardar = new Button("Guardar");
		root.getChildren().add(btnGuardar);
	}

	private ComboBox<String> crearSelectorFondos() {
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setPromptText("Selecciona fondo");

		URL carpetaURL = getClass().getResource("/tablero/grounds");
		if (carpetaURL != null) {
			File carpeta = new File(carpetaURL.getFile());
			if (carpeta.exists() && carpeta.isDirectory()) {
				List<String> archivos = Arrays.stream(carpeta.listFiles()).filter(File::isFile).map(File::getName)
						.collect(Collectors.toList());
				comboBox.getItems().addAll(archivos);
			} else {
				System.err.println("La ruta no es un directorio: " + carpeta.getAbsolutePath());
			}
		} else {
			System.err.println("No se encontró el recurso /tablero/grounds en resources.");
		}

		comboBox.setOnAction(e -> {
			String fondoSeleccionado = comboBox.getValue();
			if (fondoSeleccionado != null && tableroView != null) {
				String rutaFondo = "tablero/grounds/" + fondoSeleccionado;
				tableroView.actualizarFondo(rutaFondo);
			}
		});

		return comboBox;
	}

	private List<ElementoVisual> cargarDecoradores(String ruta) {
		try {
			URL carpetaURL = getClass().getClassLoader().getResource(ruta);
			if (carpetaURL != null) {
				if (carpetaURL.getProtocol().equals("file")) {
					File carpeta = new File(carpetaURL.toURI());
					if (carpeta.exists() && carpeta.isDirectory()) {
						File[] archivos = carpeta.listFiles((dir, nombre) -> {
							String lower = nombre.toLowerCase();
							return lower.endsWith(".png") || lower.endsWith(".jpg") || lower.endsWith(".jpeg")
									|| lower.endsWith(".gif");
						});
						if (archivos != null) {
							return Arrays.stream(archivos).map(archivo -> {
								String pathCompleto = ruta + "/" + archivo.getName();
								ElementoAux aux = new ElementoAux(pathCompleto);
								ElementoVisualData evData = new ElementoVisualData(UUID.randomUUID(), aux, 0, 0, ElementoVisualType.AUX);
								return new ElementoVisual(evData, tableroView);
							}).collect(Collectors.toList());
						}
					} else {
						System.err.println("La ruta no es un directorio: " + carpeta.getAbsolutePath());
					}
				} else {
					System.err.println("Protocolo no soportado para la ruta: " + carpetaURL.getProtocol());
				}
			} else {
				System.err.println("No se encontró el recurso: " + ruta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List.of();
	}

	private TitledPane crearPanelDeElementos(String titulo, List<ElementoVisual> elementos, int columnas) {
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		int col = 0;
		int row = 0;

		for (ElementoVisual ev : elementos) {
			VBox contenedor = ev.getContenedor();

			// Forzar tamaño fijo del contenedor
			contenedor.setPrefSize(90, 90);
			contenedor.setMinSize(90, 90);
			contenedor.setMaxSize(90, 90);

			// Escalar cualquier imagen dentro del contenedor a 30x30
			contenedor.getChildren().forEach(node -> {
				if (node instanceof javafx.scene.image.ImageView imageView) {
					imageView.setFitWidth(30);
					imageView.setFitHeight(30);
					imageView.setPreserveRatio(false); // Para que se ajuste estrictamente a 30x30
				}
			});

			contenedor.setOnMouseClicked(e -> {
				if (seleccionado != null) {
					seleccionado.getContenedor().setStyle("");
				}
				seleccionado = ev;
				contenedor.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");
				e.consume();
			});

			grid.add(contenedor, col, row);
			col++;
			if (col >= columnas) {
				col = 0;
				row++;
			}
		}

		ScrollPane scroll = new ScrollPane(grid);
		scroll.setFitToWidth(true);
		scroll.setPrefHeight(150);

		TitledPane panel = new TitledPane(titulo, scroll);
		panel.setExpanded(false);
		return panel;
	}

	public void mostrarElemento(ElementoVisual ev) {
		if (personajesDisponibles.stream().noneMatch(e -> e.equals(ev))) {
		    personajesDisponibles.add(ev);
		    actualizarPanelPersonajes();
		}
		actualizarPanelPersonajes();
	}

	private void actualizarPanelPersonajes() {
		int index = root.getChildren().indexOf(panelPersonajes);
		if (index >= 0) {
			TitledPane nuevoPanel = crearPanelDeElementos("Personajes", personajesDisponibles, 2);
			panelPersonajes = nuevoPanel;
			root.getChildren().set(index, nuevoPanel);
		}
	}

	public ElementoVisual getSeleccionado() {
		return seleccionado;
	}

	public void setOnGuardarAction(Runnable action) {
		btnGuardar.setOnAction(e -> action.run());
	}

	public void setTableroView(TableroView tableroView) {
		this.tableroView = tableroView;
	}

	public VBox getRoot() {
		return root;
	}

	public void setRoot(VBox root) {
		this.root = root;
	}

	public TableroView getTableroView() {
		return tableroView;
	}

	public void setSeleccionado(ElementoVisual seleccionado) {
		this.seleccionado = seleccionado;
	}
	
	
}
