package views;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DesktopView {

    private final StackPane desktop;

    public DesktopView() {
        Image image = new Image(getClass().getResourceAsStream("/images/ROG_logo.png"));
        ImageView imageView = new ImageView(image);
        Label infoLabel = new Label(
        		"DM Assistan v0.3\n"
        		+ "Developer: Cristian Biergüete Domínguez\n"
        		+ "Compatibilidades:\n"
        		+ "ROG Archive 1.1\n"
        		+ "Firebase");
        
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400);

        VBox container = new VBox(imageView, infoLabel);
        container.setMinWidth(100);
        container.setStyle("-fx-padding: 10; -fx-background-color: #FFFFFF;");
        
        desktop = new StackPane(container);
        desktop.setMinWidth(800);
        desktop.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10;");
    }

    public StackPane getDesktop() {
        return desktop;
    }

    public void setContenido(StackPane nuevoContenido) {
        desktop.getChildren().clear();
        desktop.getChildren().add(nuevoContenido);
    }
}
