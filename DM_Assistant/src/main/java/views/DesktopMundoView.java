package views;

import clases_partida.Mundo;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DesktopMundoView {

    public StackPane crearView(Mundo mundo) {
        Image imagen = new Image(getClass().getResourceAsStream("/images/Gartea_Ciudades.png"));

        BackgroundImage fondo = new BackgroundImage(
            imagen,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        Label titulo = new Label(mundo.getNombre());
        titulo.setStyle("-fx-background-color: white; -fx-padding: 6px 12px; -fx-font-size: 16px; -fx-font-weight: bold;");
        titulo.getStyleClass().add("titulo-mundo");

        VBox overlay = new VBox(titulo);
        overlay.setAlignment(Pos.TOP_CENTER);
        overlay.setPickOnBounds(false);

        StackPane stackPane = new StackPane(overlay);
        stackPane.setBackground(new Background(fondo));

        // Aplica el CSS (opcional)
        stackPane.getStylesheets().add(getClass().getResource("/styles/desktop_mundo.css").toExternalForm());

        return stackPane;
    }
}
