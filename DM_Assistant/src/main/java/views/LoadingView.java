package views;

import java.util.Random;

import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class LoadingView extends VBox {

    public LoadingView() {
        super(15);
        setAlignment(Pos.CENTER);

        // Elegir una imagen de fondo aleatoria
        Random random = new Random();
        int numero = random.nextInt(4) + 1; // Número entre 1 y 4
        String rutaImagen = "/images/loading_screen" + numero + ".png";
        Image backgroundImage = new Image(getClass().getResourceAsStream(rutaImagen));

        // Crear un BackgroundImage con la imagen cargada
        BackgroundImage backgroundImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
        );

        // Aplicar el BackgroundImage al VBox
        setBackground(new Background(backgroundImg));

        // Imagen de carga (logo central)
        Image image = new Image(getClass().getResourceAsStream("/images/loading_logo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100); // Tamaño deseado
        imageView.setPreserveRatio(true);

        // Animación de rotación
        RotateTransition rotate = new RotateTransition(Duration.seconds(2), imageView);
        rotate.setByAngle(360);
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setInterpolator(javafx.animation.Interpolator.LINEAR);
        rotate.play();

        // Texto
        Label label = new Label("Cargando datos...");

        // Agregar nodos
        getChildren().addAll(imageView, label);
    }
}
