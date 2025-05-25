package views;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import clases_objetos.Arma;
import clases_objetos.Armadura;
import clases_objetos.Objeto;
import clases_partida.Mundo;
import clases_personaje.EquipoPersonaje;
import clases_personaje.InventarioPersonaje;
import clases_personaje.Personaje;
import control.DesktopPersonajeController;
import dbhandlerCRUD.EquipoPersonajeCRUD;
import dbhandlerCRUD.InventarioPersonajeCRUD;
import dbhandlerCRUD.ObjetoCRUD;
import dbhandlerCRUD.PersonajeCRUD;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DesktopPersonajeView {

	private TextField nombreField;
	private TextField experienciaField;
	private TextField oroField;
	private Label nivelLabel;
	private Button guardarBtn;
	private Button cancelarBtn;
	private Personaje personaje;
	private DesktopPersonajeController controller;
	private PersonajeCRUD pjCrud = new PersonajeCRUD();
	private InventarioPersonajeCRUD inpCrud = new InventarioPersonajeCRUD();
	private EquipoPersonajeCRUD epCrud = new EquipoPersonajeCRUD();
	private final java.util.Map<String, VBox> equipoBoxes = new java.util.HashMap<>();
	private Mundo mundo;

	private StackPane contentPane; // Panel central que cambiará con las pestañas

	public DesktopPersonajeView(Personaje personaje, DesktopPersonajeController controller, Mundo mundo) {
		this.personaje = personaje;
		this.controller = controller;
		this.mundo = mundo;
	}

	public StackPane crearView(Runnable onGuardar, Runnable onCancelar) {

		BorderPane root = new BorderPane();
		contentPane = new StackPane();

		// Tab Pane en la parte superior
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		// Crear cada pestaña (solo General implementado por ahora)
		Tab generalTab = new Tab("General");
		generalTab.setOnSelectionChanged(e -> {
			if (generalTab.isSelected()) {
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearGeneralPane());
			}
		});

		// Las demás pestañas vacías por ahora
		Tab inventarioTab = new Tab("Inventario");
		inventarioTab.setOnSelectionChanged(e -> {
			if (inventarioTab.isSelected()) {
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearInventarioPane());
			}
		});

		Tab equipoTab = new Tab("Equipo");
		equipoTab.setOnSelectionChanged(e -> {
			if (equipoTab.isSelected()) {
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearInventarioEquipoPane());
			}
		});

		Tab habilidadesTab = new Tab("Habilidades");
		habilidadesTab.setOnSelectionChanged(e -> {
			if (habilidadesTab.isSelected()) {
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearGeneralPane());
			}
		});

		Tab rasgosTab = new Tab("Rasgos");
		rasgosTab.setOnSelectionChanged(e -> {
			if (rasgosTab.isSelected()) {
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearGeneralPane());
			}
		});

		Tab ventajasTab = new Tab("Ventajas");
		ventajasTab.setOnSelectionChanged(e -> {
			if (ventajasTab.isSelected()) {
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearGeneralPane());
			}
		});

		Tab donesTab = new Tab("Dones");
		donesTab.setOnSelectionChanged(e -> {
			if (donesTab.isSelected()) {
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearGeneralPane());
			}
		});

		tabPane.getTabs().addAll(generalTab, inventarioTab, equipoTab, habilidadesTab, rasgosTab, ventajasTab,
				donesTab);

		// Botones
		guardarBtn = new Button("Guardar");
		cancelarBtn = new Button("Cancelar");

		guardarBtn.setOnAction(e -> onGuardar.run());
		cancelarBtn.setOnAction(e -> onCancelar.run());

		HBox botones = new HBox(10, guardarBtn, cancelarBtn);
		botones.setPadding(new Insets(10));
		botones.setStyle("-fx-alignment: center;");

		root.setTop(tabPane);
		root.setCenter(contentPane);
		root.setBottom(botones);

		// Iniciar con la pestaña General activa
		this.personaje = pjCrud.fetchPersonaje(personaje.getId());
		contentPane.getChildren().add(crearGeneralPane());

		return new StackPane(root);
	}

	private VBox crearGeneralPane() {
		VBox contenedor = new VBox(10);
		contenedor.setPadding(new Insets(20));

		Label titulo = new Label("General");
		titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		nombreField = new TextField(personaje.getNombre());
		experienciaField = new TextField(String.valueOf(personaje.getExperiencia()));
		oroField = new TextField(String.valueOf(personaje.getOro()));

		nivelLabel = new Label("Nivel: " + personaje.getNivel());
		Label claseLabel = new Label("Clase: " + personaje.getClase());
		Label subclaseLabel = new Label(
				"Subclase: " + (personaje.getSubclase() != null ? personaje.getSubclase() : "No asignada"));
		Label razaLabel = new Label("Raza: " + personaje.getRaza());
		Label sexoLabel = new Label("Sexo: " + personaje.getSexo());
		Label religionLabel = new Label("Religión: " + personaje.getReligion());
		Label nacionLabel = new Label("Nación: " + personaje.getNacion());
		Label ideologiaLabel = new Label("Ideología: " + personaje.getIdeologia());

		contenedor.getChildren().addAll(titulo, new Label("Nombre:"), nombreField, claseLabel, subclaseLabel,
				nivelLabel, new Label("Experiencia:"), experienciaField, new Label("Oro:"), oroField, razaLabel,
				sexoLabel, religionLabel, nacionLabel, ideologiaLabel);

		return contenedor;
	}

	private VBox crearInventarioPane() {
		VBox contenedor = new VBox(15);
		contenedor.setPadding(new Insets(15));
		contenedor.setAlignment(Pos.TOP_CENTER);

		Label titulo = new Label("Inventario de " + personaje.getNombre());
		titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
		contenedor.getChildren().add(titulo);

		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setAlignment(Pos.CENTER);

		int columnas = 3;
		int row = 0;
		int col = 0;

		for (InventarioPersonaje inp : personaje.getObjetosConCantidad()) {
			Objeto objeto = inp.getObjeto();
			VBox objetoBox = new VBox(5);
			objetoBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
			objetoBox.setAlignment(Pos.CENTER);

			Label nombreLabel = new Label(objeto.getNombre());
			nombreLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

			ImageView imagenObjeto;
			try {
				String tokenPath = objeto.getToken(); // Ruta del token
				InputStream imageStream;

				if (tokenPath != null) {
					imageStream = getClass().getResourceAsStream(tokenPath);
					if (imageStream == null) {
						// Si la ruta del token no existe, usar imagen por defecto
						imageStream = getClass().getResourceAsStream("/icons/default_object.png");
					}
				} else {
					// Si el token es null, usar imagen por defecto
					imageStream = getClass().getResourceAsStream("/icons/default_object.png");
				}

				Image img = new Image(imageStream);
				imagenObjeto = new ImageView(img);
				imagenObjeto.setFitWidth(64);
				imagenObjeto.setFitHeight(64);

			} catch (Exception e) {
				imagenObjeto = new ImageView(); // fallback en caso de error grave
			}

			HBox detallesBox = new HBox(20);
			detallesBox.setAlignment(Pos.CENTER);

			detallesBox.getChildren().addAll(new Label("Peso: " + objeto.getPeso()),
					new Label("Rareza: " + objeto.getRareza()), new Label("Valor: " + objeto.getValor()),
					new Label("Cantidad: " + inp.getCantidad()));

			HBox botonesBox = new HBox(10);
			botonesBox.setAlignment(Pos.CENTER);

			Button modificarBtn = new Button("Modificar");
			modificarBtn.setOnAction(e -> {
				Stage popup = new Stage();
				popup.initModality(Modality.APPLICATION_MODAL);
				popup.setTitle("Modificar cantidad");

				VBox popupLayout = new VBox(10);
				popupLayout.setPadding(new Insets(15));
				popupLayout.setAlignment(Pos.CENTER);

				TextField cantidadField = new TextField(String.valueOf(inp.getCantidad()));
				cantidadField.setPromptText("Nueva cantidad");

				Button confirmar = new Button("Confirmar");
				Button cancelar = new Button("Cancelar");

				confirmar.setOnAction(ev -> {
					String nuevaCantidadStr = cantidadField.getText().trim();
					if (nuevaCantidadStr.matches("\\d+")) {
						int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);

						if (nuevaCantidad == 0) {
							inpCrud.eliminarInventarioPersonaje(inp);
						} else {
							inp.setCantidad(nuevaCantidad);
							inpCrud.guardarOActualizarPersonajeIventario(inp);
						}

						// Recargar desde la BD para asegurarse de que el visor esté actualizado
						this.personaje = pjCrud.fetchPersonaje(personaje.getId());
						contentPane.getChildren().setAll(crearInventarioPane());
						popup.close();
					}
				});

				cancelar.setOnAction(ev -> popup.close());

				popupLayout.getChildren().addAll(new Label("Ingrese nueva cantidad para " + objeto.getNombre() + ":"),
						cantidadField, new HBox(10, confirmar, cancelar));

				popup.setScene(new Scene(popupLayout, 300, 150));
				popup.showAndWait();
			});

			// Botón Vender
			Button venderBtn = new Button("Vender");
			venderBtn.setOnAction(e -> {
				Stage popup = new Stage();
				popup.initModality(Modality.APPLICATION_MODAL);
				popup.setTitle("Vender objeto");

				VBox layout = new VBox(10);
				layout.setPadding(new Insets(15));
				layout.setAlignment(Pos.CENTER);

				TextField cantidadField = new TextField();
				cantidadField.setPromptText("Cantidad a vender");

				Button aceptarBtn = new Button("Aceptar");
				Button cancelarBtn = new Button("Cancelar");

				aceptarBtn.setOnAction(event -> {
					try {
						int cantidadVender = Integer.parseInt(cantidadField.getText());

						if (cantidadVender <= 0 || cantidadVender > inp.getCantidad()) {
							cantidadField.clear();
							cantidadField.setPromptText("Cantidad inválida");
							return;
						}

						// Verificar restricción: objeto equipado y solo queda uno
						boolean equipado = estaEquipado(objeto);
						if (equipado && inp.getCantidad() - cantidadVender < 1) {
							Alert alerta = new Alert(Alert.AlertType.WARNING);
							alerta.setTitle("Objeto equipado");
							alerta.setHeaderText(null);
							alerta.setContentText("No puedes vender este objeto porque está equipado y solo queda uno.");
							alerta.showAndWait();
							return;
						}

						personaje.venderObjeto(inp, cantidadVender);
						pjCrud.savePersonaje(personaje);
						controller.setOriginal(personaje);
						mundo.removePersonaje(personaje);
						mundo.addPersonaje(pjCrud.fetchPersonaje(personaje.getId()));

						this.personaje = pjCrud.fetchPersonaje(personaje.getId());
						oroField.setText(String.valueOf(this.personaje.getOro()));
						contentPane.getChildren().setAll(crearInventarioPane());

						popup.close();

					} catch (NumberFormatException ex) {
						cantidadField.clear();
						cantidadField.setPromptText("Número inválido");
					}
				});

				cancelarBtn.setOnAction(event -> popup.close());

				layout.getChildren().addAll(new Label("¿Cuántos '" + objeto.getNombre() + "' quieres vender?"),
						cantidadField, new HBox(10, aceptarBtn, cancelarBtn));

				Scene scene = new Scene(layout, 300, 150);
				popup.setScene(scene);
				popup.showAndWait();
			});

			Button eliminarBtn = new Button("Eliminar");
			eliminarBtn.setOnAction(e -> {
				if (estaEquipado(objeto)) {
					Alert alerta = new Alert(Alert.AlertType.WARNING);
					alerta.setTitle("Objeto equipado");
					alerta.setHeaderText(null);
					alerta.setContentText("No puedes eliminar este objeto porque está equipado.");
					alerta.showAndWait();
					return;
				}

				personaje.removeObjetoConCantidad(inp);
				inpCrud.eliminarInventarioPersonaje(inp);
				this.personaje = pjCrud.fetchPersonaje(personaje.getId());
				contentPane.getChildren().setAll(crearInventarioPane());
			});


			botonesBox.getChildren().addAll(modificarBtn, venderBtn, eliminarBtn);

			objetoBox.getChildren().addAll(nombreLabel, imagenObjeto, detallesBox, botonesBox);

			grid.add(objetoBox, col, row);
			col++;
			if (col >= columnas) {
				col = 0;
				row++;
			}
		}

		// Botón para añadir objetos
		VBox anadirBox = new VBox(5);
		anadirBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
		anadirBox.setAlignment(Pos.CENTER);

		ImageView imagenAnadir;
		try {
			Image img = new Image(getClass().getResourceAsStream("/icons/icono_aniadir.png"));
			imagenAnadir = new ImageView(img);
			imagenAnadir.setFitWidth(64);
			imagenAnadir.setFitHeight(64);
		} catch (Exception e) {
			imagenAnadir = new ImageView();
		}

		Button anadirBtn = new Button();
		anadirBtn.setGraphic(imagenAnadir);
		anadirBtn.setStyle("-fx-background-color: transparent;");
		anadirBtn.setOnAction(e -> {
			Stage popup = new Stage();
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setTitle("Añadir objeto al inventario");

			VBox layout = new VBox(10);
			layout.setPadding(new Insets(15));
			layout.setAlignment(Pos.CENTER);

			// Obtener objetos desde la base de datos
			ObjetoCRUD objetoDAO = new ObjetoCRUD();
			ArrayList<Objeto> objetosDisponibles = new ArrayList<>();

			objetosDisponibles.addAll(objetoDAO.fetchAllObjetosArmadura());
			objetosDisponibles.addAll(objetoDAO.fetchAllObjetosArma());
			objetosDisponibles.addAll(objetoDAO.fetchAllObjetosConsumible());

			// ComboBox para selección
			ComboBox<Objeto> comboBox = new ComboBox<>();
			comboBox.getItems().addAll(objetosDisponibles);
			comboBox.setPromptText("Selecciona un objeto");

			comboBox.setCellFactory(lv -> new ListCell<>() {
				@Override
				protected void updateItem(Objeto obj, boolean empty) {
					super.updateItem(obj, empty);
					setText(empty || obj == null ? null : obj.getNombre());
				}
			});
			comboBox.setButtonCell(new ListCell<>() {
				@Override
				protected void updateItem(Objeto obj, boolean empty) {
					super.updateItem(obj, empty);
					setText(empty || obj == null ? null : obj.getNombre());
				}
			});

			// Campo para ingresar cantidad
			TextField cantidadField = new TextField();
			cantidadField.setPromptText("Cantidad");

			Button aceptar = new Button("Aceptar");
			Button cancelar = new Button("Cancelar");

			aceptar.setOnAction(ev -> {
				Objeto seleccionado = comboBox.getValue();
				String cantidadTexto = cantidadField.getText();
				if (seleccionado != null && cantidadTexto.matches("\\d+")) {
					int cantidad = Integer.parseInt(cantidadTexto);
					if (cantidad <= 0) {
						return; // No se permite añadir cantidades negativas o cero
					}

					boolean existe = false;
					for (InventarioPersonaje inp : personaje.getObjetosConCantidad()) {
						if (seleccionado.getIdObjeto() == inp.getObjeto().getIdObjeto()) {
							inp.setCantidad(inp.getCantidad() + cantidad);
							inpCrud.guardarOActualizarPersonajeIventario(inp);
							existe = true;
							break;
						}
					}

					if (!existe) {
						InventarioPersonaje nuevo = new InventarioPersonaje(seleccionado, personaje, cantidad);
						personaje.getObjetosConCantidad().add(nuevo);
						inpCrud.guardarOActualizarPersonajeIventario(nuevo);
					}

					this.personaje = pjCrud.fetchPersonaje(personaje.getId());
					contentPane.getChildren().setAll(crearInventarioPane());

					popup.close();
				}
			});

			cancelar.setOnAction(ev -> popup.close());

			layout.getChildren().addAll(new Label("Elige un objeto existente:"), comboBox,
					new Label("Cantidad a añadir:"), cantidadField, new HBox(10, aceptar, cancelar));

			popup.setScene(new Scene(layout, 300, 250));
			popup.showAndWait();
		});

		anadirBox.getChildren().add(anadirBtn);
		grid.add(anadirBox, col, row);

		contenedor.getChildren().add(grid);
		return contenedor;
	}

	private VBox crearInventarioEquipoPane() {
		VBox contenedor = new VBox(10);
		contenedor.setPadding(new Insets(20));
		contenedor.setAlignment(Pos.TOP_CENTER);

		String[] etiquetas = { "Casco", "Coraza", "Guantes", "Grebas", "Botas", "Arma" };
		equipoBoxes.clear();

		// Mapeo entre índice de ranura y etiqueta
		java.util.Map<Integer, String> ranuraEtiqueta = new java.util.HashMap<>();
		ranuraEtiqueta.put(1, "Casco");
		ranuraEtiqueta.put(2, "Coraza");
		ranuraEtiqueta.put(3, "Guantes");
		ranuraEtiqueta.put(4, "Grebas");
		ranuraEtiqueta.put(5, "Botas");
		ranuraEtiqueta.put(6, "Arma");

		// Mapa inverso: etiqueta -> ranura
		java.util.Map<String, Integer> etiquetaRanura = ranuraEtiqueta.entrySet().stream()
				.collect(Collectors.toMap(java.util.Map.Entry::getValue, java.util.Map.Entry::getKey));

		// Inicializar boxes vacíos con etiquetas
		for (String etiqueta : etiquetas) {
			VBox itemBox = new VBox(5);
			itemBox.setPrefSize(200, 100);
			itemBox.setAlignment(Pos.CENTER);
			itemBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f0f0f0;");

			Label label = new Label(etiqueta);
			label.setStyle("-fx-font-weight: bold;");
			itemBox.getChildren().add(label);
			equipoBoxes.put(etiqueta, itemBox);
		}

		// Si hay equipo asignado al personaje, muéstralo
		List<EquipoPersonaje> equipados = personaje.getEquipacion();
		if (equipados != null && !equipados.isEmpty()) {
			for (EquipoPersonaje eq : equipados) {
				Objeto obj = eq.getObjeto();
				int ranura = eq.getRanura();
				String etiqueta = ranuraEtiqueta.get(ranura);

				VBox itemBox = equipoBoxes.get(etiqueta);
				if (itemBox != null) {
					// Eliminar contenido anterior, mantener la etiqueta
					Label title = (Label) itemBox.getChildren().get(0);
					itemBox.getChildren().clear();
					itemBox.getChildren().add(title);

					// Añadir imagen
					ImageView imagen;
					try {
						InputStream is = getClass().getResourceAsStream(
								obj.getToken() != null ? obj.getToken() : "/icons/default_object.png");
						if (is == null) {
							is = getClass().getResourceAsStream("/icons/default_object.png");
						}
						imagen = new ImageView(new Image(is));
						imagen.setFitWidth(48);
						imagen.setFitHeight(48);
					} catch (Exception ex) {
						imagen = new ImageView();
					}

					Label nombre = new Label(obj.getNombre());
					nombre.setStyle("-fx-font-size: 12px;");

					itemBox.getChildren().addAll(imagen, nombre);
				}
			}
		}

		// Comportamiento al hacer clic para seleccionar objeto del inventario
		for (String etiqueta : etiquetas) {
			VBox itemBox = equipoBoxes.get(etiqueta);
			itemBox.setOnMouseClicked(event -> {
				List<Objeto> objetosFiltrados = personaje.getObjetosConCantidad().stream()
						.map(InventarioPersonaje::getObjeto).filter(obj -> {
							if ("Arma".equals(etiqueta)) {
								return obj instanceof Arma;
							} else if (obj instanceof Armadura) {
								Armadura arm = (Armadura) obj;
								switch (etiqueta) {
								case "Casco":
									return arm.getTipo().equalsIgnoreCase("Casco");
								case "Coraza":
									return arm.getTipo().equalsIgnoreCase("Coraza");
								case "Guantes":
									return arm.getTipo().equalsIgnoreCase("Guantes");
								case "Grebas":
									return arm.getTipo().equalsIgnoreCase("Grebas");
								case "Botas":
									return arm.getTipo().equalsIgnoreCase("Botas");
								default:
									return false;
								}
							}
							return false;
						}).collect(Collectors.toList());

				mostrarVentanaSeleccion(objetosFiltrados, etiqueta);
			});
		}

		// Añadir boxes al contenedor en orden
		for (String etiqueta : etiquetas) {
			contenedor.getChildren().add(equipoBoxes.get(etiqueta));
		}

		return contenedor;
	}

	private void mostrarVentanaSeleccion(List<Objeto> objetos, String ranura) {
		Stage popup = new Stage();
		popup.initModality(Modality.APPLICATION_MODAL);
		popup.setTitle("Seleccionar " + ranura);

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(15));
		layout.setAlignment(Pos.CENTER);

		ComboBox<Objeto> comboBox = new ComboBox<>();
		comboBox.getItems().addAll(objetos);
		comboBox.setPromptText("Selecciona un objeto");

		comboBox.setCellFactory(lv -> new ListCell<>() {
			@Override
			protected void updateItem(Objeto obj, boolean empty) {
				super.updateItem(obj, empty);
				setText(empty || obj == null ? null : obj.getNombre());
			}
		});

		comboBox.setButtonCell(new ListCell<>() {
			@Override
			protected void updateItem(Objeto obj, boolean empty) {
				super.updateItem(obj, empty);
				setText(empty || obj == null ? null : obj.getNombre());
			}
		});

		Button confirmar = new Button("Equipar");
		confirmar.setOnAction(e -> {
			Objeto seleccionado = comboBox.getValue();
			if (seleccionado != null) {
				actualizarRecuadroEquipo(ranura, seleccionado);
				popup.close();
			}
		});

		Button desequipar = new Button("Desequipar");
		desequipar.setOnAction(e -> {
			int ranuraId = valorRanura(ranura);

			// Buscar el equipo correspondiente a la ranura
			EquipoPersonaje equipoAEliminar = personaje.getEquipacion().stream()
					.filter(eq -> eq.getRanura() == ranuraId).findFirst().orElse(null);

			if (equipoAEliminar != null) {
				// Eliminar de la lista de equipación
				personaje.getEquipacion().remove(equipoAEliminar);

				// Actualizar persistencia
				epCrud.eliminarEquipoPersonaje(equipoAEliminar);
				pjCrud.savePersonaje(personaje);

				// Actualizar mundo
				mundo.removePersonaje(personaje);
				mundo.addPersonaje(pjCrud.fetchPersonaje(personaje.getId()));

				// Limpiar visualmente el recuadro
				VBox itemBox = equipoBoxes.get(ranura);
				if (itemBox != null) {
					itemBox.getChildren().clear();
					Label label = new Label(ranura);
					label.setStyle("-fx-font-weight: bold;");
					itemBox.getChildren().add(label);
				}
			}

			popup.close();
		});

		layout.getChildren().addAll(comboBox, confirmar, desequipar);
		popup.setScene(new Scene(layout, 300, 200));
		popup.showAndWait();
	}

	private void actualizarRecuadroEquipo(String ranura, Objeto objeto) {
	int ranuraValor = valorRanura(ranura);

	// Buscar si ya hay un objeto equipado en esa ranura
	EquipoPersonaje equipadoExistente = personaje.getEquipacion().stream()
		.filter(eq -> eq.getRanura() == ranuraValor)
		.findFirst()
		.orElse(null);

	// Si hay uno, lo eliminamos de la lista y de la BD
	if (equipadoExistente != null) {
		personaje.getEquipacion().remove(equipadoExistente);
		epCrud.eliminarEquipoPersonaje(equipadoExistente); // <-- asegúrate de que exista este método
	}

	// Equipar nuevo objeto
	EquipoPersonaje nuevoEquipo = new EquipoPersonaje(objeto, personaje, ranuraValor);
	personaje.getEquipacion().add(nuevoEquipo);
	epCrud.guardarOActualizarEquipoPersonaje(nuevoEquipo);

	// Actualizar personaje
	pjCrud.savePersonaje(personaje);
	mundo.removePersonaje(personaje);
	mundo.addPersonaje(pjCrud.fetchPersonaje(personaje.getId()));

	// Actualizar visualmente el recuadro
	VBox itemBox = equipoBoxes.get(ranura);
	if (itemBox == null)
		return;

	itemBox.getChildren().clear();

	Label label = new Label(ranura);
	label.setStyle("-fx-font-weight: bold;");
	itemBox.getChildren().add(label);

	ImageView icono;
	try {
		InputStream imgStream = objeto.getToken() != null
			? getClass().getResourceAsStream(objeto.getToken())
			: getClass().getResourceAsStream("/icons/default_object.png");

		if (imgStream == null) {
			imgStream = getClass().getResourceAsStream("/icons/default_object.png");
		}

		icono = new ImageView(new Image(imgStream));
		icono.setFitWidth(32);
		icono.setFitHeight(32);
	} catch (Exception e) {
		icono = new ImageView();
	}

	Label nombreLabel = new Label(objeto.getNombre());
	itemBox.getChildren().addAll(icono, nombreLabel);

	if (objeto instanceof Arma arma) {
		Label dadoLabel = new Label("Dado: " + arma.getDado());
		itemBox.getChildren().add(dadoLabel);
	}
}


	private int valorRanura(String etiqueta) {
		return switch (etiqueta.toLowerCase()) {
		case "casco" -> 1;
		case "coraza" -> 2;
		case "guantes" -> 3;
		case "grebas" -> 4;
		case "botas" -> 5;
		case "arma" -> 6;
		default -> -1; // Valor inválido
		};
	}
	
	private boolean estaEquipado(Objeto objeto) {
		for (EquipoPersonaje eq : personaje.getEquipacion()) {
			if (eq.getObjeto().getIdObjeto() == objeto.getIdObjeto()) {
				return true;
			}
		}
		return false;
	}

	// Métodos públicos de acceso

	public String getNombre() {
		return nombreField.getText();
	}

	public String getExperiencia() {
		return experienciaField.getText();
	}

	public String getOro() {
		return oroField.getText();
	}

	public void setNombre(String nombre) {
		nombreField.setText(nombre);
	}

	public void setExperiencia(int experiencia) {
		experienciaField.setText(String.valueOf(experiencia));
	}

	public void setOro(int oro) {
		oroField.setText(String.valueOf(oro));
	}

	public void actualizarNivel(int nivel) {
		nivelLabel.setText("Nivel: " + nivel);
	}
}
