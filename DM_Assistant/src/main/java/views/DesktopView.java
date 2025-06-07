package views;

import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class DesktopView {

	private StackPane desktop;

	public DesktopView() {
		desktop = new StackPane();
		desktop.setMinWidth(800);
		desktop.setStyle("-fx-background-color: #FFFFFF;");
		setContenido(generarVistaInicio());
	}

	public StackPane generarVistaInicio() {
		// Construye contenido como antes, pero no reasignes desktop
		Image image = new Image(getClass().getResourceAsStream("/images/DM Assistant Logo 2.png"));
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(120);

		HBox header = new HBox(imageView);
		header.setAlignment(Pos.CENTER_LEFT);
		header.setStyle("-fx-background-color: #c3dfb0; -fx-padding: 20;");
		HBox.setMargin(imageView, new Insets(0, 20, 0, 0));

		Label infoLabel = new Label("DM Assistan v1.0\n" + "Guía rápida de uso:\n\n" + "¿Qué hace esta aplicación?\n"
				+ "Esta herramienta está diseñada para ayudarte a organizar y dirigir tus partidas de rol creando y gestionando mundos, personajes, criaturas y escenarios de manera estructurada y visual.\n\n"
				+ "Pasos Básicos de Uso\n" + "1. Crear un Mundo\n" + "El mundo es el núcleo de tu partida.\n"
				+ "Todos los demás elementos deben estar vinculados a un mundo.\n"
				+ "Primero debes crear un mundo antes de añadir cualquier otro elemento.\n\n"
				+ "2. Añadir Elementos al Mundo\n"
				+ "Una vez creado un mundo, puedes agregar los siguientes elementos:\n" + "- Personajes\n"
				+ "  Representan a los jugadores.\n"
				+ "  Se asocian directamente al mundo y pueden interactuar con NPCs, criaturas y ubicaciones.\n"
				+ "- NPCs (Personajes no Jugables)\n" + "  Figuras del mundo que no controlan los jugadores.\n"
				+ "  Pueden ser aliados, enemigos o parte del entorno.\n" + "- Criaturas\n"
				+ "  Seres del mundo, útiles para desafíos, encuentros o ambientación.\n" + "- Ubicaciones\n"
				+ "  Lugares dentro del mundo que pueden tener funciones específicas:\n"
				+ "  * Tiendas: Lugares donde los personajes pueden comprar objetos.\n"
				+ "  * Escenas: Representaciones gráficas donde puedes:\n"
				+ "    - Emplazar personajes, NPCs y criaturas.\n" + "    - Añadir elementos decorativos.\n"
				+ "    - Simular entornos, desafíos, situaciones narrativas o combates.\n\n"
				+ "Developer: Cristian Biergüete Domínguez\n" + "Compatibilidades:\n" + "ROG Archive 1.1\n"
				+ "Firebase\n\n" + "Asistente de mesa para RUINS OF GARTEA®\n\n" + "Para más información:");

		Hyperlink link = new Hyperlink("https://www.notion.so/Mundo-12dbcf7519ea80438c6ce1c8165f2847");
		link.setOnAction(e -> {
			try {
				Desktop.getDesktop().browse(new URI(link.getText()));
			} catch (IOException | URISyntaxException ex) {
				ex.printStackTrace();
			}
		});

		VBox body = new VBox(infoLabel, link);
		body.setSpacing(10);
		body.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20;");

		VBox mainContainer = new VBox(header, body);
		mainContainer.setMinWidth(100);

		StackPane contenido = new StackPane(mainContainer);
		contenido.setMinWidth(800);
		contenido.setStyle("-fx-background-color: #FFFFFF;");

		return contenido;
	}

	public StackPane getDesktop() {
		return desktop;
	}

	public void setContenido(StackPane nuevoContenido) {
		desktop.getChildren().clear();
		desktop.getChildren().add(nuevoContenido);
	}
}
