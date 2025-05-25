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
