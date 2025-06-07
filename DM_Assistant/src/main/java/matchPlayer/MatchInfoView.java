package matchPlayer;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

import clases_habilidades.Hechizo;
import clases_objetos.Consumible;
import clases_personaje.Personaje;

public class MatchInfoView {

	private List<Object> ordenTurnosList; // Lista ordenada y secuencial
	private VBox opcionesContainer;
	private VBox turnoContainer; // Referencia guardada
	private ScrollPane scrollPaneMensajes;

	public MatchInfoView(List<Object> ordenTurnosList) {
		this.ordenTurnosList = new LinkedList<>(ordenTurnosList); // LinkedList para rotar fácilmente
		this.opcionesContainer = new VBox(5);
		this.turnoContainer = new VBox(10);
	}

	public void iniciar(Stage stage) {
		VBox contenedorPrincipal = new VBox();
		contenedorPrincipal.setStyle("-fx-padding: 10;");

		turnoContainer.setStyle("-fx-padding: 10; -fx-alignment: top-center; -fx-background-color: #f0f0f0;");
		turnoContainer.setFillWidth(true);

		actualizarVistaTurnos(); // Inicializar lista visual

		opcionesContainer.setStyle("-fx-background-color: #ffffff; -fx-padding: 5;");
		scrollPaneMensajes = new ScrollPane(opcionesContainer);
		scrollPaneMensajes.setFitToWidth(true);
		scrollPaneMensajes.setPrefHeight(300);
		scrollPaneMensajes.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

		VBox.setVgrow(turnoContainer, Priority.ALWAYS);
		contenedorPrincipal.getChildren().addAll(turnoContainer, scrollPaneMensajes);

		Scene scene = new Scene(contenedorPrincipal, 300, 768);
		stage.setTitle("Información de la Partida");
		stage.setScene(scene);
		stage.show();
	}

	private void actualizarVistaTurnos() {
		Platform.runLater(() -> {
			turnoContainer.getChildren().clear();

			for (int i = 0; i < ordenTurnosList.size(); i++) {
				Object entidad = ordenTurnosList.get(i);
				String nombre;
				int iniciativa = 0;
				try {
					nombre = (String) entidad.getClass().getMethod("getNombre").invoke(entidad);
					try {
						Object iniObj = entidad.getClass().getMethod("getIniciativa").invoke(entidad);
						if (iniObj instanceof Integer) {
							iniciativa = (Integer) iniObj;
						}
					} catch (Exception e) {
						// No tiene iniciativa, dejar 0
					}
				} catch (Exception e) {
					nombre = "Desconocido";
					e.printStackTrace();
				}

				Label label = new Label(nombre);
				label.setMaxWidth(Double.MAX_VALUE); // 🔹 Hace que la Label use todo el ancho posible
				label.setStyle("-fx-font-size: 14; -fx-padding: 5;");

				// Aplicar estilo especial al primero (turno actual)
				if (i == 0) {
					label.setStyle(
							"-fx-font-size: 14; -fx-padding: 5; -fx-background-color: #a2ff8b; -fx-border-color: black; -fx-border-width: 2;");
				}

				// Permitir que VBox lo estire horizontalmente si es necesario
				VBox.setVgrow(label, Priority.NEVER);

				turnoContainer.getChildren().add(label);
			}
		});
	}

	public void mostrarMensajeAtaque(Personaje atacante, Personaje objetivo, int danio) {
		TextFlow mensajeFlujo = new TextFlow();

		Text atacanteNombre = new Text(atacante.getNombre());
		atacanteNombre.setFill(Color.GREEN);
		atacanteNombre.setStyle("-fx-font-weight: bold;");

		Text texto1 = new Text(" ha realizado un ataque sobre ");

		Text objetivoNombre = new Text(objetivo.getNombre());
		objetivoNombre.setFill(Color.GREEN);
		objetivoNombre.setStyle("-fx-font-weight: bold;");

		Text texto2 = new Text(".\n");

		Text texto3 = new Text(objetivo.getNombre());
		texto3.setFill(Color.GREEN);
		texto3.setStyle("-fx-font-weight: bold;");

		Text texto4 = new Text(" ha sufrido ");

		Text textoDanio = new Text(String.valueOf(danio));
		textoDanio.setFill(Color.RED);
		textoDanio.setStyle("-fx-font-weight: bold;");

		Text texto5 = new Text(" de daño.");

		mensajeFlujo.getChildren().addAll(atacanteNombre, texto1, objetivoNombre, texto2, texto3, texto4, textoDanio,
				texto5);

		mensajeFlujo.setMaxWidth(opcionesContainer.getWidth());
		mensajeFlujo.maxWidthProperty().bind(opcionesContainer.widthProperty());

		opcionesContainer.getChildren().add(mensajeFlujo);

		if (opcionesContainer.getChildren().size() > 40) {
			opcionesContainer.getChildren().remove(0);
		}

		Platform.runLater(() -> scrollPaneMensajes.setVvalue(1.0));
	}

	public void mostarMensajeAtaqueFallido(Personaje atacante, Personaje objetivo) {
		TextFlow mensajeFlujo = new TextFlow();

		Text atacanteNombre = new Text(atacante.getNombre());
		atacanteNombre.setFill(Color.GREEN);
		atacanteNombre.setStyle("-fx-font-weight: bold;");

		Text texto1 = new Text(" ha realizado un ataque sobre ");

		Text objetivoNombre = new Text(objetivo.getNombre());
		objetivoNombre.setFill(Color.GREEN);
		objetivoNombre.setStyle("-fx-font-weight: bold;");

		Text texto2 = new Text(".\nEl ataque ha ");

		Text fallado = new Text("fallado");
		fallado.setStyle("-fx-font-weight: bold;");

		Text punto = new Text(".");

		mensajeFlujo.getChildren().addAll(atacanteNombre, texto1, objetivoNombre, texto2, fallado, punto);

		mensajeFlujo.setMaxWidth(opcionesContainer.getWidth());
		mensajeFlujo.maxWidthProperty().bind(opcionesContainer.widthProperty());

		opcionesContainer.getChildren().add(mensajeFlujo);

		if (opcionesContainer.getChildren().size() > 40) {
			opcionesContainer.getChildren().remove(0);
		}

		Platform.runLater(() -> scrollPaneMensajes.setVvalue(1.0));
	}

	public void mostrarMensajeHechizo(Personaje lanzador, Personaje objetivo, Hechizo hechizo, int danio) {
		TextFlow mensajeFlujo = new TextFlow();

		Text lanzadorNombre = new Text(lanzador.getNombre());
		lanzadorNombre.setFill(Color.PURPLE);
		lanzadorNombre.setStyle("-fx-font-weight: bold;");

		Text texto1 = new Text(" ha lanzado el hechizo ");

		Text nombreHechizo = new Text("'" + hechizo.getNombre() + "'");
		nombreHechizo.setFill(Color.BLUE);
		nombreHechizo.setStyle("-fx-font-weight: bold;");

		Text texto2 = new Text(" sobre ");

		Text objetivoNombre = new Text(objetivo.getNombre());
		objetivoNombre.setFill(Color.GREEN);
		objetivoNombre.setStyle("-fx-font-weight: bold;");

		Text texto3 = new Text(", causando ");

		Text cantidadDanio = new Text(danio + " puntos de daño");
		cantidadDanio.setFill(Color.RED);
		cantidadDanio.setStyle("-fx-font-weight: bold;");

		Text punto = new Text(".");

		mensajeFlujo.getChildren().addAll(lanzadorNombre, texto1, nombreHechizo, texto2, objetivoNombre, texto3,
				cantidadDanio, punto);

		mensajeFlujo.setMaxWidth(opcionesContainer.getWidth());
		mensajeFlujo.maxWidthProperty().bind(opcionesContainer.widthProperty());

		opcionesContainer.getChildren().add(mensajeFlujo);

		if (opcionesContainer.getChildren().size() > 40) {
			opcionesContainer.getChildren().remove(0);
		}

		Platform.runLater(() -> scrollPaneMensajes.setVvalue(1.0));
	}
	
	public void mostrarMensajeHechizoFallido(Personaje lanzador, Personaje objetivo, Hechizo hechizo) {
	    TextFlow mensajeFlujo = new TextFlow();

	    Text lanzadorNombre = new Text(lanzador.getNombre());
	    lanzadorNombre.setFill(Color.PURPLE);
	    lanzadorNombre.setStyle("-fx-font-weight: bold;");

	    Text texto1 = new Text(" intentó lanzar el hechizo ");

	    Text nombreHechizo = new Text("'" + hechizo.getNombre() + "'");
	    nombreHechizo.setFill(Color.BLUE);
	    nombreHechizo.setStyle("-fx-font-weight: bold;");

	    Text texto2 = new Text(" sobre ");

	    Text objetivoNombre = new Text(objetivo.getNombre());
	    objetivoNombre.setFill(Color.GREEN);
	    objetivoNombre.setStyle("-fx-font-weight: bold;");

	    Text texto3 = new Text(", pero ");

	    Text fallo = new Text("falló");
	    fallo.setFill(Color.GRAY);
	    fallo.setStyle("-fx-font-weight: bold;");

	    Text punto = new Text(".");

	    mensajeFlujo.getChildren().addAll(
	        lanzadorNombre, texto1, nombreHechizo, texto2,
	        objetivoNombre, texto3, fallo, punto
	    );

	    mensajeFlujo.setMaxWidth(opcionesContainer.getWidth());
	    mensajeFlujo.maxWidthProperty().bind(opcionesContainer.widthProperty());

	    opcionesContainer.getChildren().add(mensajeFlujo);

	    if (opcionesContainer.getChildren().size() > 40) {
	        opcionesContainer.getChildren().remove(0);
	    }

	    Platform.runLater(() -> scrollPaneMensajes.setVvalue(1.0));
	}
	
	public void mostrarMensajeConsumibleCurativo(Personaje personaje, int cura, Consumible consumible) {
	    TextFlow mensajeFlujo = new TextFlow();

	    Text nombrePersonaje = new Text(personaje.getNombre());
	    nombrePersonaje.setFill(Color.GREEN);
	    nombrePersonaje.setStyle("-fx-font-weight: bold;");

	    Text texto1 = new Text(" ha consumido ");

	    Text nombreConsumible = new Text(consumible.getNombre());
	    nombreConsumible.setFill(Color.ORANGE);
	    nombreConsumible.setStyle("-fx-font-weight: bold;");

	    Text texto2 = new Text(" y ha recuperado ");

	    Text cantidadCurada = new Text(cura + " de salud");
	    cantidadCurada.setFill(Color.RED);
	    cantidadCurada.setStyle("-fx-font-weight: bold;");

	    Text punto = new Text(".");

	    mensajeFlujo.getChildren().addAll(
	        nombrePersonaje, texto1, nombreConsumible, texto2, cantidadCurada, punto
	    );

	    mensajeFlujo.setMaxWidth(opcionesContainer.getWidth());
	    mensajeFlujo.maxWidthProperty().bind(opcionesContainer.widthProperty());

	    opcionesContainer.getChildren().add(mensajeFlujo);

	    if (opcionesContainer.getChildren().size() > 40) {
	        opcionesContainer.getChildren().remove(0);
	    }

	    Platform.runLater(() -> scrollPaneMensajes.setVvalue(1.0));
	}

	public void terminarTurno() {
		if (!ordenTurnosList.isEmpty()) {
			Object primero = ordenTurnosList.remove(0);
			ordenTurnosList.add(primero);
		}
		actualizarVistaTurnos();
	}

	public Object getPersonajeEnTurno() {
		if (!ordenTurnosList.isEmpty()) {
			return ordenTurnosList.get(0);
		}
		return null;
	}

}
