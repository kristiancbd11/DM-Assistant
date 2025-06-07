package matchPlayer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import clases_estadisticas.EstadoPjJson;
import clases_habilidades.Hechizo;
import clases_objetos.Consumible;
import clases_partida.Criatura;
import clases_personaje.Dado;
import clases_personaje.InventarioPersonaje;
import clases_personaje.Personaje;
import designerView.ElementoVisual;
import designerView.TableroView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
		Button desafio = new Button ("Desafío");
		Button atacar = new Button("Atacar");
		Button hechizos = new Button("Hechizos");
		Button dones = new Button("Dones");
		Button consumibles = new Button("Consumibles");
		Button botonEstado = new Button("Estado");
		Button acabarTurno = new Button("Acabar turno");

		desafio.setPrefWidth(200);
		atacar.setPrefWidth(200);
		hechizos.setPrefWidth(200);
		dones.setPrefWidth(200);
		consumibles.setPrefWidth(200);
		botonEstado.setPrefWidth(200);
		acabarTurno.setPrefWidth(200);

		desafio.setDisable(true);
		atacar.setDisable(true);
		hechizos.setDisable(true);
		dones.setDisable(true);
		consumibles.setDisable(true);
		botonEstado.setDisable(true);
		acabarTurno.setDisable(true);

		StackPane stackSalud = new StackPane(barraSalud, labelSalud);
		StackPane stackAura = new StackPane(barraAura, labelAura);
		stackSalud.setPrefWidth(200);
		stackAura.setPrefWidth(200);

		layout.getChildren().addAll(turnoLabel, iconosBox, stackSalud, stackAura,
			desafio, atacar, hechizos, dones, botonEstado, consumibles, acabarTurno);


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

						desafio.setDisable(!esValido);
						atacar.setDisable(!esValido);
						hechizos.setDisable(!esValido);
						dones.setDisable(!esValido);
						consumibles.setDisable(!esValido);
						botonEstado.setDisable(!esValido);
						acabarTurno.setDisable(!esValido);

						if (nuevoObjeto != ultimoObjetoMostrado) {
							ultimoObjetoMostrado = nuevoObjeto;
						}

						return;
					}
				}

				// Si no hay objeto válido
				turnoLabel.setText("No seleccionado");
				desafio.setDisable(true);
				atacar.setDisable(true);
				hechizos.setDisable(true);
				dones.setDisable(true);
				consumibles.setDisable(true);
				botonEstado.setDisable(true);
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
		
		desafio.setOnAction(event -> {
			Personaje personaje = (ultimoObjetoMostrado instanceof Personaje) ? (Personaje) ultimoObjetoMostrado : null;
		    Stage ventanaDesafio = new Stage();
		    ventanaDesafio.setTitle("Seleccionar Atributo y Subatributo");

		    // ComboBox de atributos principales
		    ComboBox<String> comboAtributo = new ComboBox<>();
		    comboAtributo.getItems().addAll(
		        "Fuerza", "Constitución", "Destreza", 
		        "Inteligencia", "Carisma", "Sabiduría"
		    );
		    comboAtributo.setPromptText("Selecciona un atributo");

		    // ComboBox de subatributos
		    ComboBox<String> comboSubatributo = new ComboBox<>();
		    comboSubatributo.setPromptText("Selecciona un subatributo");

		    // Campo de entrada para dificultad
		    TextField campoDificultad = new TextField();
		    campoDificultad.setPromptText("Introduce dificultad (número)");

		    // Mapeo de atributos a subatributos
		    Map<String, List<String>> mapaSubatributos = new HashMap<>();
		    mapaSubatributos.put("Fuerza", Arrays.asList("Impacto", "Destrozo", "Carga", "Levantamiento", "Vigor", "Atletismo"));
		    mapaSubatributos.put("Constitución", Arrays.asList("Vitalidad", "Corpulencia", "Regeneración", "Tenacidad", "Resistencia", "Motricidad"));
		    mapaSubatributos.put("Destreza", Arrays.asList("Agilidad", "Presteza", "Reflejos", "Combate", "Sigilo", "Precisión"));
		    mapaSubatributos.put("Inteligencia", Arrays.asList("Perspicacia", "Intuición", "Investigación", "Estrategia", "Memoria", "comArcana"));
		    mapaSubatributos.put("Carisma", Arrays.asList("Convicción", "Engaño", "Intimidación", "Liderazgo", "Jovialidad", "Seducción"));
		    mapaSubatributos.put("Sabiduría", Arrays.asList("Conocimiento", "Medicina", "Percepción", "Empatía", "Voluntad", "Supervivencia"));

		    comboAtributo.setOnAction(e -> {
		        String seleccionado = comboAtributo.getValue();
		        comboSubatributo.getItems().clear();
		        if (seleccionado != null && mapaSubatributos.containsKey(seleccionado)) {
		            comboSubatributo.getItems().addAll(mapaSubatributos.get(seleccionado));
		        }
		    });

		    // Botón de aceptar
		    Button aceptar = new Button("Aceptar");
		    aceptar.setPrefWidth(150);
		    aceptar.setOnAction(e -> {
		        String tipo = comboSubatributo.getValue();
		        String dificultadTexto = campoDificultad.getText();
		        if (tipo != null && dificultadTexto != null && !dificultadTexto.trim().isEmpty()) {
		            try {
		                int dificultad = Integer.parseInt(dificultadTexto);
		                Dado dado = new Dado("1d20");
		                int tirada = dado.totalThrow();
		                boolean resultado = actionController.superaDesafio(personaje, tipo, dificultad, tirada);

		                String mensaje = "Modificador de " + tipo + " de " + personaje.getNombre() + ": " + personaje.getStatConcreto(tipo) + "\n"
		                        + "Tirada de dado: " + tirada + "\n"
		                        + "Dificultad del desafío: " + dificultad + "\n"
		                        + "Desafío " + (resultado ? "SUPERADO" : "FALLIDO");

		                Alert alertaResultado = new Alert(resultado ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
		                alertaResultado.setTitle("Resultado del desafío");
		                alertaResultado.setHeaderText("Resultado para " + personaje.getNombre());
		                alertaResultado.setContentText(mensaje);
		                alertaResultado.showAndWait();

		                ventanaDesafio.close();
		            } catch (NumberFormatException ex) {
		                Alert alerta = new Alert(Alert.AlertType.ERROR);
		                alerta.setTitle("Valor inválido");
		                alerta.setHeaderText("La dificultad debe ser un número entero.");
		                alerta.showAndWait();
		            }
		        } else {
		            Alert alerta = new Alert(Alert.AlertType.WARNING);
		            alerta.setTitle("Campos incompletos");
		            alerta.setHeaderText("Debes seleccionar un subatributo y una dificultad.");
		            alerta.showAndWait();
		        }
		    });


		    // Botón de cancelar
		    Button cancelar = new Button("Cancelar");
		    cancelar.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		    cancelar.setPrefWidth(150);
		    cancelar.setOnAction(e -> ventanaDesafio.close());

		    HBox botones = new HBox(10, aceptar, cancelar);
		    botones.setAlignment(Pos.CENTER);

		    VBox layoutDesafios = new VBox(15, comboAtributo, comboSubatributo, campoDificultad, botones);
		    layoutDesafios.setAlignment(Pos.CENTER);
		    layoutDesafios.setPadding(new Insets(20));

		    Scene escena = new Scene(layoutDesafios, 400, 300);
		    ventanaDesafio.setScene(escena);
		    ventanaDesafio.show();
		});
		
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
								int danio = actionController.calcularDanioAtaque(atacante, objetivo);
								actionController.modificarSalud(objetivo, danio, false);
								
								tableroView.refrescarTablero();
								
								matchInfoView.mostrarMensajeAtaque(atacante, objetivo, danio);
								actualizarEstado(turnoLabel, barraSalud, barraAura, desafio, atacar, hechizos, dones, consumibles, botonEstado, acabarTurno);

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
		
		hechizos.setOnAction(event -> {
		    Personaje lanzador = (ultimoObjetoMostrado instanceof Personaje) ? (Personaje) ultimoObjetoMostrado : null;

		    if (lanzador == null || lanzador.getListaHechizos().isEmpty()) {
		        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		        alerta.setTitle("Sin hechizos");
		        alerta.setHeaderText("Este personaje aún no conoce ningún hechizo");
		        alerta.setContentText(null);
		        alerta.showAndWait();
		        return;
		    }

		    Stage ventanaHechizo = new Stage();
		    ventanaHechizo.setTitle("Lanzar hechizo");

		    ComboBox<Hechizo> comboHechizos = new ComboBox<>();
		    comboHechizos.getItems().addAll(lanzador.getListaHechizos());
		    comboHechizos.setPromptText("Selecciona un hechizo");

		    GridPane gridObjetivos = new GridPane();
		    gridObjetivos.setPadding(new Insets(10));
		    gridObjetivos.setHgap(10);
		    gridObjetivos.setVgap(10);
		    gridObjetivos.setAlignment(Pos.CENTER);

		    int col = 0, row = 0;

		    for (ElementoVisual ev : tableroView.getElementosColocados().values().stream()
		            .map(data -> new ElementoVisual(data, tableroView)).toList()) {

		        Object obj = ev.getObj();

		        if ((obj instanceof Personaje || obj instanceof Criatura) && obj != lanzador) {
		            String nombre = (obj instanceof Personaje p) ? p.getNombre() : ((Criatura) obj).getNombre();

		            Button botonObjetivo = new Button(nombre);
		            botonObjetivo.setPrefWidth(100);
		            botonObjetivo.setOnAction(e -> {
		                Hechizo hechizoSeleccionado = comboHechizos.getValue();
		                if (hechizoSeleccionado != null && obj instanceof Personaje objetivo) {

		                    System.out.println("Lanzando hechizo '" + hechizoSeleccionado.getNombre() +
		                            "' de " + lanzador.getNombre() + " sobre " + objetivo.getNombre());

		                    if (actionController.exitoAtaqueRango(lanzador, objetivo)) {
		                        int danio = actionController.calcularDanioHechizo(lanzador, objetivo, hechizoSeleccionado);
		                        actionController.modificarSalud(objetivo, danio, false);

		                        tableroView.refrescarTablero();

		                        // Muestra un mensaje informativo (si tienes este método)
		                        matchInfoView.mostrarMensajeHechizo(lanzador, objetivo, hechizoSeleccionado, danio);

		                        actualizarEstado(turnoLabel, barraSalud, barraAura, desafio, atacar, hechizos, dones, consumibles, botonEstado, acabarTurno);
		                    } else {
		                        matchInfoView.mostrarMensajeHechizoFallido(lanzador, objetivo, hechizoSeleccionado);
		                    }
		                    
		                    actionController.modificarAura(lanzador, hechizoSeleccionado.getCosteAura(), false);
		                    ventanaHechizo.close();
		                }
		            });

		            gridObjetivos.add(botonObjetivo, col, row);
		            col++;
		            if (col >= 5) {
		                col = 0;
		                row++;
		            }
		        }
		    }

		    ScrollPane scrollObjetivos = new ScrollPane(gridObjetivos);
		    scrollObjetivos.setFitToWidth(true);
		    scrollObjetivos.setPrefViewportHeight(250);
		    scrollObjetivos.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		    Button cancelar = new Button("Cancelar");
		    cancelar.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		    cancelar.setPrefWidth(200);
		    cancelar.setOnAction(e -> ventanaHechizo.close());

		    HBox contenedorCancelar = new HBox(cancelar);
		    contenedorCancelar.setAlignment(Pos.CENTER);
		    contenedorCancelar.setPadding(new Insets(10, 0, 0, 0));

		    VBox layoutPopup = new VBox(10, comboHechizos, scrollObjetivos, contenedorCancelar);
		    layoutPopup.setAlignment(Pos.CENTER);
		    layoutPopup.setPadding(new Insets(10));

		    Scene escenaPopup = new Scene(layoutPopup, 600, 400);
		    ventanaHechizo.setScene(escenaPopup);
		    ventanaHechizo.show();
		});
		
		botonEstado.setOnAction(event -> {
		    Personaje pj = (ultimoObjetoMostrado instanceof Personaje) ? (Personaje) ultimoObjetoMostrado : null;

		    if (pj == null) {
		        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		        alerta.setTitle("Sin personaje");
		        alerta.setHeaderText("No hay personaje seleccionado.");
		        alerta.setContentText(null);
		        alerta.showAndWait();
		        return;
		    }

		    // Crear ventana emergente
		    Stage ventanaEstado = new Stage();
		    ventanaEstado.setTitle("Estado de " + pj.getNombre());

		    // Contenido del panel con estadísticas
		    VBox contenedorEstado = crearEstadoPane(pj); // pasamos el personaje como parámetro

		    // Botón de cerrar
		    Button cerrar = new Button("Cerrar");
		    cerrar.setPrefWidth(200);
		    cerrar.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		    cerrar.setOnAction(e -> ventanaEstado.close());

		    HBox contenedorCerrar = new HBox(cerrar);
		    contenedorCerrar.setAlignment(Pos.CENTER);
		    contenedorCerrar.setPadding(new Insets(10, 0, 0, 0));

		    VBox layoutPopup = new VBox(10, contenedorEstado, contenedorCerrar);
		    layoutPopup.setAlignment(Pos.TOP_CENTER);
		    layoutPopup.setPadding(new Insets(15));

		    ScrollPane scroll = new ScrollPane(layoutPopup);
		    scroll.setFitToWidth(true);
		    scroll.setPrefViewportHeight(500);

		    Scene escenaPopup = new Scene(scroll, 800, 600);
		    ventanaEstado.setScene(escenaPopup);
		    ventanaEstado.show();
		});
		
		consumibles.setOnAction(event -> {
		    Personaje personaje = (ultimoObjetoMostrado instanceof Personaje) ? (Personaje) ultimoObjetoMostrado : null;
		    if (personaje == null) return;

		    Stage ventanaConsumibles = new Stage();
		    ventanaConsumibles.setTitle("Usar Consumible");
		    
		    ComboBox<InventarioPersonaje> comboConsumibles = new ComboBox<>();
		    comboConsumibles.setPromptText("Selecciona un consumible");
		    
		    // Agrega al combo los objetos que sean instancia de Consumible
		    for (InventarioPersonaje inp : personaje.getObjetosConCantidad()) {
		        if (inp.getObjeto() instanceof Consumible) {
		            comboConsumibles.getItems().add(inp);
		        }
		    }

		    if (comboConsumibles.getItems().isEmpty()) {
		        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		        alerta.setTitle("Sin consumibles");
		        alerta.setHeaderText("Este personaje no tiene consumibles disponibles.");
		        alerta.setContentText(null);
		        alerta.showAndWait();
		        return;
		    }

		    // Renderizado amigable
		    comboConsumibles.setCellFactory(lv -> new ListCell<>() {
		        @Override
		        protected void updateItem(InventarioPersonaje item, boolean empty) {
		            super.updateItem(item, empty);
		            if (empty || item == null) {
		                setText(null);
		            } else {
		                setText(item.getObjeto().getNombre() + " (x" + item.getCantidad() + ")");
		            }
		        }
		    });
		    comboConsumibles.setButtonCell(comboConsumibles.getCellFactory().call(null));

		    Button aceptar = new Button("Aceptar");
		    aceptar.setPrefWidth(150);
		    aceptar.setOnAction(e -> {
		        InventarioPersonaje seleccion = comboConsumibles.getValue();
		        if (seleccion != null && seleccion.getObjeto() instanceof Consumible consumible) {
		            matchInfoView.mostrarMensajeConsumibleCurativo(personaje, consumible.consumir(personaje, seleccion, actionController), (Consumible) seleccion.getObjeto());
		            
		            ventanaConsumibles.close();
		            actualizarEstado(turnoLabel, barraSalud, barraAura, desafio, atacar, hechizos, dones, consumibles, botonEstado, acabarTurno);
		        }
		    });

		    Button cancelar = new Button("Cancelar");
		    cancelar.setStyle("-fx-background-color: red; -fx-text-fill: white;");
		    cancelar.setPrefWidth(150);
		    cancelar.setOnAction(e -> ventanaConsumibles.close());

		    HBox botones = new HBox(10, aceptar, cancelar);
		    botones.setAlignment(Pos.CENTER);

		    VBox layoutConsumibles = new VBox(15, comboConsumibles, botones);
		    layoutConsumibles.setAlignment(Pos.CENTER);
		    layoutConsumibles.setPadding(new Insets(20));

		    Scene escena = new Scene(layoutConsumibles, 400, 200);
		    ventanaConsumibles.setScene(escena);
		    ventanaConsumibles.show();
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
	
	private void actualizarEstado(Label turnoLabel, ProgressBar barraAccion, ProgressBar barraReaccion, Button desafio, Button atacar, Button hechizos, Button dones, Button consumibles, Button botonEstado, Button acabarTurno) {
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

					desafio.setDisable(!esValido);
					atacar.setDisable(!esValido);
					hechizos.setDisable(!esValido);
					dones.setDisable(!esValido);
					botonEstado.setDisable(!esValido);
					consumibles.setDisable(!esValido);
					acabarTurno.setDisable(!esValido);

					if (nuevoObjeto != ultimoObjetoMostrado) {
						ultimoObjetoMostrado = nuevoObjeto;
					}
					return;
				}
			}

			turnoLabel.setText("No seleccionado");
			desafio.setDisable(true);
			atacar.setDisable(true);
			hechizos.setDisable(true);
			dones.setDisable(true);
			consumibles.setDisable(true);
			botonEstado.setDisable(true);
			acabarTurno.setDisable(true);
			barraAccion.setProgress(0);
			barraReaccion.setProgress(0);
			ultimoObjetoMostrado = null;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private VBox crearEstadoPane(Personaje personaje) {
	    VBox contenedor = new VBox(10);
	    contenedor.setPadding(new Insets(10));

	    Label titulo = new Label(personaje.getNombre());
	    titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    contenedor.getChildren().add(titulo);

	    agregarSeccion(contenedor, "Estadísticas generales",
	            EstadoPjJson.desdeJson(personaje.getEstadoJson()).getStatBase().getStatGeneral().getAll(),
	            List.of("Salud", "Iniciativa", "Aura", "Movimiento"));

	    agregarSeccion(contenedor, "Estadísticas de fuerza",
	            EstadoPjJson.desdeJson(personaje.getEstadoJson()).getStatBase().getStatFuerza().getAll(),
	            List.of("Impacto", "Destrozo", "Carga", "Levantamiento", "Vigor", "Atletismo"));

	    agregarSeccion(contenedor, "Estadísticas de constitución",
	            EstadoPjJson.desdeJson(personaje.getEstadoJson()).getStatBase().getStatConstitucion().getAll(),
	            List.of("Vitalidad", "Corpulencia", "Regeneración", "Tenacidad", "Resistencia", "Motricidad"));

	    agregarSeccion(contenedor, "Estadísticas de destreza",
	            EstadoPjJson.desdeJson(personaje.getEstadoJson()).getStatBase().getStatDestreza().getAll(),
	            List.of("Agilidad", "Presteza", "Reflejos", "Combate", "Sigilo", "Precisión"));

	    agregarSeccion(contenedor, "Estadísticas de inteligencia",
	            EstadoPjJson.desdeJson(personaje.getEstadoJson()).getStatBase().getStatInteligencia().getAll(),
	            List.of("Perspicacia", "Intuición", "Investigación", "Estrategia", "Memoria", "comArcana"));

	    agregarSeccion(contenedor, "Estadísticas de carisma",
	            EstadoPjJson.desdeJson(personaje.getEstadoJson()).getStatBase().getStatCarisma().getAll(),
	            List.of("Convicción", "Engaño", "Intimidación", "Liderazgo", "Jovialidad", "Seducción"));

	    agregarSeccion(contenedor, "Estadísticas de sabiduría",
	            EstadoPjJson.desdeJson(personaje.getEstadoJson()).getStatBase().getStatSabiduria().getAll(),
	            List.of("Conocimiento", "Medicina", "Percepción", "Empatía", "Voluntad", "Supervivencia"));

	    return contenedor;
	}
	
	private void agregarSeccion(VBox contenedor, String tituloSeccion, List<Integer> valores,
			List<String> encabezados) {
		Label seccionLabel = new Label(tituloSeccion);
		seccionLabel.setStyle("-fx-font-weight: bold;");

		GridPane tabla = new GridPane();
		tabla.setHgap(0);
		tabla.setVgap(0);
		tabla.setPadding(new Insets(5));
		tabla.setStyle("-fx-grid-lines-visible: true; -fx-border-color: black;");

		int columnas = encabezados.size();

		// Fila 1: encabezados personalizados
		for (int i = 0; i < columnas; i++) {
			Label encabezado = new Label(encabezados.get(i));
			encabezado.setStyle(
					"-fx-alignment: center; -fx-border-color: black; -fx-padding: 5px; -fx-pref-width: 100px; -fx-text-alignment: center;");
			GridPane.setHalignment(encabezado, HPos.CENTER);
			tabla.add(encabezado, i, 0);
		}

		// Fila 2: valores reales
		for (int i = 0; i < columnas && i < valores.size(); i++) {
			Label valor = new Label(String.valueOf(valores.get(i)));
			valor.setStyle(
					"-fx-alignment: center; -fx-border-color: black; -fx-padding: 5px; -fx-pref-width: 100px; -fx-text-alignment: center;");
			GridPane.setHalignment(valor, HPos.CENTER);
			tabla.add(valor, i, 1);
		}

		contenedor.getChildren().addAll(seccionLabel, tabla);
	}

}