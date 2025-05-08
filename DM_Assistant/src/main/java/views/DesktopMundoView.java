package views;

import clases_partida.Mundo;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DesktopMundoView {

    public StackPane crearView(Mundo mundo) {
        Image imagen = new Image(getClass().getResourceAsStream("/images/Gartea_Ciudades.png"));
        ImageView imageView = new ImageView(imagen);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600); // Ajusta el tamaño según lo necesites

        Label titulo = new Label(mundo.getNombre());
        titulo.getStyleClass().add("titulo-mundo");

        VBox overlay = new VBox(titulo);
        overlay.setAlignment(Pos.TOP_CENTER);
        overlay.setPickOnBounds(false);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, overlay);

        // Aplica el CSS
        stackPane.getStylesheets().add(getClass().getResource("/styles/desktop_mundo.css").toExternalForm());

        return stackPane;
    }
}
