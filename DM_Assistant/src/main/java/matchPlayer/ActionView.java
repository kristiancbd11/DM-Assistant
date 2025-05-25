package matchPlayer;

import clases_estadisticas.EstadoPjJson;
import clases_partida.Criatura;
import clases_personaje.Personaje;
import designerView.ElementoVisual;
import designerView.TableroView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ActionView {

	private TableroView tableroView;
	private Object ultimoObjetoMostrado = null;
	private ActionController actionController;
	private final MatchInfoView matchInfoView;

	public ActionView(TableroView tableroView, MatchInfoView matchInfoView, ActionController actionController) {
		this.tableroView = tableroView;
		this.matchInfoView = matchInfoView;
		this.actionController = actionController;
	}

	public void iniciar(Stage stage) {
		VBox layout = new VBox(15);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(20));

		Label turnoLabel = new Label("No seleccionado");
		turnoLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

		// Iconos con etiquetas
		Image iconoAccion = new Image(getClass().getResource("/icons/accion.png").toExternalForm(), 24, 24, true, true);
		Image iconoReaccion = new Image(getClass().getResource("/icons/reaccion.png").toExternalForm(), 24, 24, true,
				true);

		ImageView iconoAccionView = new ImageView(iconoAccion);
		ImageView iconoReaccionView = new ImageView(iconoReaccion);

		Label labelSalud = new Label();
		labelSalud.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

		Label labelAura = new Label();
		labelAura.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

		
		Label labelAccion = new Label("Acción");
		labelAccion.setStyle("-fx-font-size: 14;");
		Label labelReaccion = new Label("Reacción");
		labelReaccion.setStyle("-fx-font-size: 14;");

		HBox iconosBox = new HBox(20);
		iconosBox.setAlignment(Pos.CENTER);
		iconosBox.getChildren().addAll(new HBox(5, iconoAccionView, labelAccion),
				new HBox(5, iconoReaccionView, labelReaccion));

		// Barras de progreso
		ProgressBar barraSalud = new ProgressBar(0);
		barraSalud.setStyle("-fx-accent: a2ff8b;");
		barraSalud.setPrefWidth(200);

		ProgressBar barraAura = new ProgressBar(0);
		barraAura.setStyle("-fx-accent: aefff8;");
		barraAura.setPrefWidth(200);

		// Botones de acción
		Button atacar = new Button("Atacar");
		Button hechizos = new Button("Hechizos");
		Button dones = new Button("Dones");
		Button consumibles = new Button("Consumibles");
		Button acabarTurno = new Button("Acabar turno");

		atacar.setPrefWidth(200);
		hechizos.setPrefWidth(200);
		dones.setPrefWidth(200);
		consumibles.setPrefWidth(200);
		acabarTurno.setPrefWidth(200);

		atacar.setDisable(true);
		hechizos.setDisable(true);
		dones.setDisable(true);
		consumibles.setDisable(true);
		acabarTurno.setDisable(true);

		StackPane stackSalud = new StackPane(barraSalud, labelSalud);
		StackPane stackAura = new StackPane(barraAura, labelAura);
		stackSalud.setPrefWidth(200);
		stackAura.setPrefWidth(200);

		layout.getChildren().addAll(turnoLabel, iconosBox, stackSalud, stackAura,
			atacar, hechizos, dones, consumibles, acabarTurno);


		Scene scene = new Scene(layout, 300, 768);
		stage.setTitle("Acciones");
		stage.setScene(scene);
		stage.show();

		// Actualización dinámica
		Timeline refresco = new Timeline(new KeyFrame(Duration.millis(200), e -> {
			try {
				var elemento = tableroView.getElementoResaltado();
				if (elemento != null) {
					Object nuevoObjeto = elemento.getObj();
					if (nuevoObjeto != null) {
						String nombre = "Desconocido";
						boolean esValido = false;

						if (nuevoObjeto instanceof Personaje personaje) {
							nombre = personaje.getNombre();
							esValido = true;

							// Obtén los valores necesarios para la barra verde (salud)
							EstadoPjJson estado = EstadoPjJson.desdeJson(personaje.getEstadoJson());

							if (estado != null) {
								var statBase = estado.getStatBase();
								var statOnline = estado.getStatOnline();

								if (statBase != null && statOnline != null) {
									var statGenBase = statBase.getStatGeneral();
									var statGenOnline = statOnline.getStatGeneral();

									if (statGenBase != null && statGenOnline != null) {
										int saludMax = statGenBase.getSalud();
										int saludActual = statGenOnline.getSalud();

										double progresoSalud = (saludMax > 0) ? ((double) saludActual / saludMax) : 0;
										barraSalud.setProgress(progresoSalud);
										labelSalud.setText(saludActual + " / " + saludMax);
										StackPane.setAlignment(labelSalud, Pos.CENTER);
										
										// Igual para aura (barra azul)
										int auraMax = statGenBase.getAura();
										int auraActual = statGenOnline.getAura();

										double progresoAura = (auraMax > 0) ? ((double) auraActual / auraMax) : 0;
										barraAura.setProgress(progresoAura);
										
										labelAura.setText(auraActual + " / " + auraMax);
										StackPane.setAlignment(labelAura, Pos.CENTER);
									}
								}
							}

						} else if (nuevoObjeto instanceof Criatura criatura) {
							nombre = criatura.getNombre();
							esValido = true;

							// Aquí puedes decidir si quieres hacer algo con barras para Criatura
							// Por ahora dejamos barras a 0
							barraSalud.setProgress(0);
							barraAura.setProgress(0);
						}

						turnoLabel.setText("Turno de \"" + nombre + "\"");

						atacar.setDisable(!esValido);
						hechizos.setDisable(!esValido);
						dones.setDisable(!esValido);
						consumibles.setDisable(!esValido);
						acabarTurno.setDisable(!esValido);

						if (nuevoObjeto != ultimoObjetoMostrado) {
							ultimoObjetoMostrado = nuevoObjeto;
						}

						return;
					}
				}

				// Si no hay objeto válido
				turnoLabel.setText("No seleccionado");
				atacar.setDisable(true);
				hechizos.setDisable(true);
				dones.setDisable(true);
				consumibles.setDisable(true);
				acabarTurno.setDisable(true);

				barraSalud.setProgress(0);
				barraAura.setProgress(0);

				ultimoObjetoMostrado = null;

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}));

		// Estado para modo ataque
		final boolean[] enModoAtaque = { false };

		// Referencia al nodo del tablero
		Pane tableroPane = tableroView.getContenido();
		
		atacar.setOnAction(event -> {
			enModoAtaque[0] = true;

			Stage ventanaEmergente = new Stage();
			ventanaEmergente.setTitle("Selecciona el objetivo del ataque");

			GridPane gridBotones = new GridPane();
			gridBotones.setPadding(new Insets(10));
			gridBotones.setHgap(10);
			gridBotones.setVgap(10);
			gridBotones.setAlignment(Pos.CENTER);

			int col = 0, row = 0;
			Personaje atacante = (ultimoObjetoMostrado instanceof Personaje) ? (Personaje) ultimoObjetoMostrado : null;
			
			for (ElementoVisual ev : tableroView.getElementosColocados().values().stream()
					.map(data -> new ElementoVisual(data, tableroView)).toList()) {

				Object obj = ev.getObj();

				if ((obj instanceof Personaje || obj instanceof Criatura) && obj != atacante) {
					String nombre = (obj instanceof Personaje p) ? p.getNombre() : ((Criatura) obj).getNombre();

					Button boton = new Button(nombre);
					boton.setPrefWidth(100);
					boton.setOnAction(e -> {
						if (atacante != null && obj instanceof Personaje objetivo) {
							if(actionController.exitoAtaque(atacante, objetivo)) {
								int danio = actionController.cacularDanioAtaque(atacante, objetivo);
								actionController.modificarSalud(objetivo, danio, false);
								
								tableroView.refrescarTablero();
								
								matchInfoView.mostrarMensajeAtaque(atacante, objetivo, danio);
								actualizarEstado(turnoLabel, barraSalud, barraAura, atacar, hechizos, dones, consumibles, acabarTurno);

							}
							
						}

						ventanaEmergente.close();
						enModoAtaque[0] = false;
					});

					gridBotones.add(boton, col, row);
					col++;
					if (col >= 5) {
						col = 0;
						row++;
					}
				}
			}

			// ScrollPane para botones
			ScrollPane scroll = new ScrollPane(gridBotones);
			scroll.setFitToWidth(true);
			scroll.setPrefViewportHeight(250);
			scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

			// Botón cancelar
			Button cancelar = new Button("Cancelar");
			cancelar.setStyle("-fx-background-color: red; -fx-text-fill: white;");
			cancelar.setPrefWidth(200);
			cancelar.setOnAction(e -> {
				ventanaEmergente.close();
				enModoAtaque[0] = false;
			});

			HBox contenedorCancelar = new HBox(cancelar);
			contenedorCancelar.setAlignment(Pos.CENTER);
			contenedorCancelar.setPadding(new Insets(10, 0, 0, 0));

			VBox layoutPopup = new VBox(scroll, contenedorCancelar);
			layoutPopup.setAlignment(Pos.CENTER);
			layoutPopup.setPadding(new Insets(10));

			Scene escenaPopup = new Scene(layoutPopup, 600, 350);
			ventanaEmergente.setScene(escenaPopup);

			// Restaurar estado si se cierra la ventana con [X]
			ventanaEmergente.setOnCloseRequest(e -> {
				enModoAtaque[0] = false;
			});

			ventanaEmergente.show();
		});
		
		acabarTurno.setOnAction(event -> {
		    if (!(ultimoObjetoMostrado instanceof Personaje atacante)) {
		        return; // Si no es personaje, no hacemos nada
		    }

		    Personaje actualEnTurno = (Personaje) matchInfoView.getPersonajeEnTurno(); // Suponiendo que matchInfoView está accesible

		    if (atacante.getId() != actualEnTurno.getId()) {
		        Alert alerta = new Alert(Alert.AlertType.WARNING);
		        alerta.setTitle("Turno inválido");
		        alerta.setHeaderText("No es el turno de este personaje");
		        alerta.setContentText("Solo el personaje activo puede terminar su turno.");
		        alerta.showAndWait();
		        return;
		    }

		    // Si es su turno, terminar
		    matchInfoView.terminarTurno();
		});
;

		refresco.setCycleCount(Timeline.INDEFINITE);
		refresco.play();
	}
	
	private void actualizarEstado(Label turnoLabel, ProgressBar barraAccion, ProgressBar barraReaccion, Button atacar, Button hechizos, Button dones, Button consumibles, Button acabarTurno) {
		try {
			var elemento = tableroView.getElementoResaltado();
			if (elemento != null) {
				Object nuevoObjeto = elemento.getObj();
				if (nuevoObjeto != null) {
					String nombre = "Desconocido";
					boolean esValido = false;

					if (nuevoObjeto instanceof Personaje personaje) {
						nombre = personaje.getNombre();
						esValido = true;

						EstadoPjJson estado = EstadoPjJson.desdeJson(personaje.getEstadoJson());

						if (estado != null) {
							var statBase = estado.getStatBase();
							var statOnline = estado.getStatOnline();

							if (statBase != null && statOnline != null) {
								var statGenBase = statBase.getStatGeneral();
								var statGenOnline = statOnline.getStatGeneral();

								if (statGenBase != null && statGenOnline != null) {
									int saludMax = statGenBase.getSalud();
									int saludActual = statGenOnline.getSalud();
									double progresoSalud = (saludMax > 0) ? ((double) saludActual / saludMax) : 0;
									barraAccion.setProgress(progresoSalud);

									int auraMax = statGenBase.getAura();
									int auraActual = statGenOnline.getAura();
									double progresoAura = (auraMax > 0) ? ((double) auraActual / auraMax) : 0;
									barraReaccion.setProgress(progresoAura);
								}
							}
						}

					} else if (nuevoObjeto instanceof Criatura criatura) {
						nombre = criatura.getNombre();
						esValido = true;
						barraAccion.setProgress(0);
						barraReaccion.setProgress(0);
					}

					turnoLabel.setText("Turno de \"" + nombre + "\"");

					atacar.setDisable(!esValido);
					hechizos.setDisable(!esValido);
					dones.setDisable(!esValido);
					consumibles.setDisable(!esValido);
					acabarTurno.setDisable(!esValido);

					if (nuevoObjeto != ultimoObjetoMostrado) {
						ultimoObjetoMostrado = nuevoObjeto;
					}
					return;
				}
			}

			turnoLabel.setText("No seleccionado");
			atacar.setDisable(true);
			hechizos.setDisable(true);
			dones.setDisable(true);
			consumibles.setDisable(true);
			acabarTurno.setDisable(true);
			barraAccion.setProgress(0);
			barraReaccion.setProgress(0);
			ultimoObjetoMostrado = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}