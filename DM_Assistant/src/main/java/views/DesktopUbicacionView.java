package views;

import clases_partida.Escena;
import clases_partida.Tienda;
import clases_partida.TiendaObjeto;
import clases_partida.Ubicacion;
import dbhandlerCRUD.ObjetoCRUD;
import dbhandlerCRUD.PersonajeCRUD;
import dbhandlerCRUD.TiendaObjetoCRUD;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DesktopUbicacionView {

	private Ubicacion ubicacion;
	private TiendaObjetoCRUD tienObjCrud = new TiendaObjetoCRUD();
	private PersonajeCRUD personajeCrud = new PersonajeCRUD();
	private ObjetoCRUD objetoCrud = new ObjetoCRUD();
	private VBox tiendaVista; // Asegurémonos de tener una referencia a la vista de la tienda

	public DesktopUbicacionView(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public StackPane getVista() {

		if (ubicacion instanceof Tienda tienda) {
			tiendaVista = new VBox(15); // Ahora inicializamos la vista de la tienda aquí
			tiendaVista.setPadding(new Insets(15));
			tiendaVista.setAlignment(Pos.TOP_CENTER);

			Label titulo = new Label(tienda.getNombre() + " | Fondos: " + tienda.getFondos());
			titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
			tiendaVista.getChildren().add(titulo);

			GridPane grid = new GridPane();
			grid.setHgap(20);
			grid.setVgap(20);
			grid.setAlignment(Pos.CENTER);

			int columnas = 3;
			int row = 0;
			int col = 0;

			// El cambio importante aquí es la referencia de cantidadLabel fuera del loop
			for (TiendaObjeto tiendaObjeto : tienda.getObjetosConCantidad()) {
				VBox objetoBox = new VBox(5);
				objetoBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
				objetoBox.setAlignment(Pos.CENTER);

				// Nombre del objeto
				Label nombreLabel = new Label(tiendaObjeto.getObjeto().getNombre());
				nombreLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

				// Imagen del objeto
				ImageView imagenObjeto;
				try {
					Image img = new Image(getClass().getResourceAsStream("/icons/default_object.png"));
					imagenObjeto = new ImageView(img);
					imagenObjeto.setFitWidth(64);
					imagenObjeto.setFitHeight(64);
				} catch (Exception e) {
					imagenObjeto = new ImageView(); // fallback vacío
				}

				// Detalles: peso - rareza - valor - cantidad
				HBox detallesBox = new HBox(20);
				detallesBox.setAlignment(Pos.CENTER);

				// Aquí usamos una etiqueta de cantidad final o efectivamente final.
				final Label cantidadLabel = new Label("Cantidad: " + tiendaObjeto.getCantidad());

				detallesBox.getChildren().addAll(new Label("Peso: " + tiendaObjeto.getObjeto().getPeso()),
						new Label("Rareza: " + tiendaObjeto.getObjeto().getRareza()),
						new Label("Valor: " + tiendaObjeto.getObjeto().getValor()), cantidadLabel // Mostrar la cantidad
																									// disponible
				);

				// Botones: Modificar - Comprar - Eliminar
				HBox botonesBox = new HBox(10);
				botonesBox.setAlignment(Pos.CENTER);

				// Botón Modificar
				Button modificarBtn = new Button("Modificar");
				modificarBtn.setOnAction(e -> {
					// Crear ventana emergente
					Stage popup = new Stage();
					popup.initModality(Modality.APPLICATION_MODAL);
					popup.setTitle("Modificar cantidad");

					VBox popupLayout = new VBox(10);
					popupLayout.setPadding(new Insets(15));
					popupLayout.setAlignment(Pos.CENTER);

					Label instruccion = new Label("Ingrese la nueva cantidad:");
					TextField cantidadField = new TextField();
					cantidadField.setPromptText("Cantidad");

					HBox botonesPopup = new HBox(10);
					botonesPopup.setAlignment(Pos.CENTER);

					Button confirmarBtn = new Button("Confirmar");
					Button cancelarBtn = new Button("Cancelar");

					confirmarBtn.setOnAction(event -> {
						try {
							int nuevaCantidad = Integer.parseInt(cantidadField.getText());
							tiendaObjeto.setCantidad(nuevaCantidad);
							tienObjCrud.guardarOActualizarTiendaObjeto(tiendaObjeto); // Actualiza la base de datos
							cantidadLabel.setText("Cantidad: " + nuevaCantidad); // Actualizamos directamente la
																					// etiqueta
							popup.close();
						} catch (NumberFormatException ex) {
							cantidadField.setText("");
							cantidadField.setPromptText("Ingrese un número válido");
						}
					});

					cancelarBtn.setOnAction(event -> popup.close());

					botonesPopup.getChildren().addAll(confirmarBtn, cancelarBtn);
					popupLayout.getChildren().addAll(instruccion, cantidadField, botonesPopup);

					Scene popupScene = new Scene(popupLayout, 300, 150);
					popup.setScene(popupScene);
					popup.showAndWait();
				});

				// Botón Comprar
				Button comprarBtn = new Button("Comprar");
				comprarBtn.setOnAction(e -> {
					Stage popup = new Stage();
					popup.initModality(Modality.APPLICATION_MODAL);
					popup.setTitle("Compra de objeto");

					VBox layout = new VBox(10);
					layout.setPadding(new Insets(15));
					layout.setAlignment(Pos.CENTER);

					Label seleccionLabel = new Label("Selecciona un personaje:");
					javafx.scene.control.ComboBox<String> personajeCombo = new javafx.scene.control.ComboBox<>();

					// Rellenar el ComboBox con los personajes del mundo actual
					ubicacion.getReino().getNacion().getMundo().getPersonajes()
							.forEach(p -> personajeCombo.getItems().add(p.getNombre()));

					TextField cantidadField = new TextField();
					cantidadField.setPromptText("Cantidad a comprar");

					Button aceptarBtn = new Button("Aceptar");
					Button cancelarBtn = new Button("Cancelar");

					HBox botones = new HBox(10);
					botones.setAlignment(Pos.CENTER);
					botones.getChildren().addAll(aceptarBtn, cancelarBtn);

					aceptarBtn.setOnAction(event -> {
						String nombreSeleccionado = personajeCombo.getValue();
						if (nombreSeleccionado == null || cantidadField.getText().isEmpty()) {
							cantidadField.setPromptText("Faltan datos");
							return;
						}

						try {
							int cantidadComprar = Integer.parseInt(cantidadField.getText());

							if (cantidadComprar <= 0 || cantidadComprar > tiendaObjeto.getCantidad()) {
								cantidadField.setText("");
								cantidadField.setPromptText("Cantidad inválida");
								return;
							}

							var personaje = ubicacion.getReino().getNacion().getMundo().getPersonajes().stream()
									.filter(p -> p.getNombre().equals(nombreSeleccionado)).findFirst().orElse(null);

							if (personaje == null)
								return;

							int costoTotal = cantidadComprar * tiendaObjeto.getObjeto().getValor();

							if (personaje.getOro() >= costoTotal) {
								personaje.setOro(personaje.getOro() - costoTotal);
								tienda.setFondos(tienda.getFondos() + costoTotal);
								for (int i = 0; i < cantidadComprar; i++) {
									personaje.aniadirObjeto(tiendaObjeto.getObjeto());
								}

								personajeCrud.savePersonaje(personaje);

								tiendaObjeto.setCantidad(tiendaObjeto.getCantidad() - cantidadComprar);
								cantidadLabel.setText("Cantidad: " + tiendaObjeto.getCantidad());

								if (tiendaObjeto.getCantidad() <= 0) {
									tiendaObjeto.getTienda().removeTiendaObjeto(tiendaObjeto);
									tienObjCrud.eliminarTiendaObjeto(tiendaObjeto);
								} else {
									tienObjCrud.guardarOActualizarTiendaObjeto(tiendaObjeto);
								}

								// Refrescar la vista
								tiendaVista.getChildren().clear();
								tiendaVista.getChildren().add(getVista());
								popup.close();
							} else {
								cantidadField.setText("");
								cantidadField.setPromptText("Oro insuficiente");
							}

						} catch (NumberFormatException ex) {
							cantidadField.setText("");
							cantidadField.setPromptText("Ingrese un número válido");
						}
					});

					cancelarBtn.setOnAction(event -> popup.close());

					layout.getChildren().addAll(seleccionLabel, personajeCombo, cantidadField, botones);
					Scene scene = new Scene(layout, 350, 200);
					popup.setScene(scene);
					popup.showAndWait();
				});

				// Botón Eliminar con confirmación
				Button eliminarBtn = new Button("Eliminar");
				eliminarBtn.setOnAction(e -> {
					Stage confirmPopup = new Stage();
					confirmPopup.initModality(Modality.APPLICATION_MODAL);
					confirmPopup.setTitle("Confirmar eliminación");

					VBox layout = new VBox(10);
					layout.setPadding(new Insets(15));
					layout.setAlignment(Pos.CENTER);

					Label mensaje = new Label("¿Estás seguro de que deseas eliminar este objeto?");
					Button aceptarBtn = new Button("Aceptar");
					Button cancelarBtn = new Button("Cancelar");

					HBox botones = new HBox(10);
					botones.setAlignment(Pos.CENTER);
					botones.getChildren().addAll(aceptarBtn, cancelarBtn);

					aceptarBtn.setOnAction(event -> {
						tiendaObjeto.getTienda().removeTiendaObjeto(tiendaObjeto); // Lo elimina del modelo
						tienObjCrud.eliminarTiendaObjeto(tiendaObjeto); // Lo elimina de la base de datos

						// Refrescar la vista de la tienda (igual que el botón Modificar)
						tiendaVista.getChildren().clear();
						tiendaVista.getChildren().add(getVista());

						confirmPopup.close();
					});

					cancelarBtn.setOnAction(event -> confirmPopup.close());

					layout.getChildren().addAll(mensaje, botones);
					Scene scene = new Scene(layout, 300, 150);
					confirmPopup.setScene(scene);
					confirmPopup.showAndWait();
				});

				botonesBox.getChildren().addAll(modificarBtn, comprarBtn, eliminarBtn);

				// Agregar todo al contenedor del objeto
				objetoBox.getChildren().addAll(nombreLabel, imagenObjeto, detallesBox, botonesBox);

				grid.add(objetoBox, col, row);

				col++;
				if (col >= columnas) {
					col = 0;
					row++;
				}
			}

			// Añadir un único botón al final para "Añadir objeto"
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
				imagenAnadir = new ImageView(); // fallback vacío
			}

			Button anadirBtn = new Button();
			anadirBtn.setGraphic(imagenAnadir);
			anadirBtn.setStyle("-fx-background-color: transparent;");
			anadirBtn.setOnAction(e -> {
				Stage popup = new Stage();
				popup.initModality(Modality.APPLICATION_MODAL);
				popup.setTitle("Añadir nuevo objeto a la tienda");

				VBox layout = new VBox(10);
				layout.setPadding(new Insets(15));
				layout.setAlignment(Pos.CENTER);

				Label instruccionLabel = new Label("Selecciona un objeto y su cantidad:");

				javafx.scene.control.ComboBox<String> objetoCombo = new javafx.scene.control.ComboBox<>();
				var todosLosObjetos = objetoCrud.fetchAllObjetos();
				todosLosObjetos.forEach(o -> objetoCombo.getItems().add(o.getNombre()));

				TextField cantidadField = new TextField();
				cantidadField.setPromptText("Cantidad");

				Button aceptarBtn = new Button("Aceptar");
				Button cancelarBtn = new Button("Cancelar");

				HBox botones = new HBox(10);
				botones.setAlignment(Pos.CENTER);
				botones.getChildren().addAll(aceptarBtn, cancelarBtn);

				aceptarBtn.setOnAction(event -> {
					String nombreSeleccionado = objetoCombo.getValue();
					String cantidadTexto = cantidadField.getText();

					if (nombreSeleccionado == null || cantidadTexto.isEmpty()) {
						cantidadField.setPromptText("Faltan datos");
						return;
					}

					try {
						int cantidad = Integer.parseInt(cantidadTexto);
						if (cantidad <= 0) {
							cantidadField.setText("");
							cantidadField.setPromptText("Cantidad inválida");
							return;
						}

						var objetoSeleccionado = todosLosObjetos.stream()
								.filter(o -> o.getNombre().equals(nombreSeleccionado)).findFirst().orElse(null);

						if (objetoSeleccionado == null)
							return;

						// Buscar si el objeto ya existe en la tienda
						var existente = tienda.getObjetosConCantidad().stream()
								.filter(to -> to.getObjeto().getNombre().equals(objetoSeleccionado.getNombre()))
								.findFirst().orElse(null);

						if (existente != null) {
							// Ya existe: sumar cantidad
							int nuevaCantidad = existente.getCantidad() + cantidad;
							existente.setCantidad(nuevaCantidad);
							tienObjCrud.guardarOActualizarTiendaObjeto(existente);
						} else {
							// No existe: crear nuevo
							TiendaObjeto nuevoTiendaObjeto = new TiendaObjeto(tienda, objetoSeleccionado, cantidad);
							tienda.addTiendaObjeto(nuevoTiendaObjeto);
							tienObjCrud.guardarOActualizarTiendaObjeto(nuevoTiendaObjeto);
						}

						// Refrescar la vista
						tiendaVista.getChildren().clear();
						tiendaVista.getChildren().add(getVista());

						popup.close();
					} catch (NumberFormatException ex) {
						cantidadField.setText("");
						cantidadField.setPromptText("Ingrese un número válido");
					}
				});

				cancelarBtn.setOnAction(event -> popup.close());

				layout.getChildren().addAll(instruccionLabel, objetoCombo, cantidadField, botones);
				Scene scene = new Scene(layout, 350, 200);
				popup.setScene(scene);
				popup.showAndWait();
			});

			anadirBox.getChildren().add(anadirBtn);
			grid.add(anadirBox, col, row);

			tiendaVista.getChildren().add(grid);
			return new StackPane(tiendaVista);
		} else {
			Escena escena = (Escena) ubicacion;
			StackPane pane = new StackPane(new Label("Vista de Escena: " + escena.getNombre()));
			return pane;
		}
	}
}
