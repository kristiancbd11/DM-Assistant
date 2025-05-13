package designerView;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class ElementoVisualBuilder {

    public static VBox construirNodoVisual(ElementoVisual ev) {
        Label nombreLabel = new Label(ev.getNombre());
        nombreLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: black;");

        ImageView imageView = new ImageView(new Image(
                ElementoVisualBuilder.class.getResourceAsStream(ev.getToken()), 32, 32, true, true));

        // Clic izquierdo: rotar
        imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && !ev.isDragging()) {
                double angle = ev.getRotateAngle() + 45;
                if (angle >= 360) angle = 0;
                ev.setRotateAngle(angle);
                imageView.setRotate(angle);
            }
        });

        VBox nodoVisual = new VBox(2, nombreLabel, imageView);
        nodoVisual.setAlignment(Pos.CENTER);
        nodoVisual.setStyle("-fx-background-color: transparent;");
        return nodoVisual;
    }
}
