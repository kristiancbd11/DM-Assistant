package views;

import clases_partida.*;
import designerView.Tablero;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;

public class DesktopReinoView {

    private Reino reino;

    public DesktopReinoView(Reino reino) {
        this.reino = reino;
    }

    public StackPane crearView() {
        // === HEADER ===
        HBox header = new HBox();
        header.setStyle("-fx-background-color: #c3dfb0;");
        header.setPadding(new Insets(15, 20, 15, 20));
        header.setAlignment(Pos.CENTER_LEFT);

        Label titulo = new Label(reino.getNombre());
        try {
            Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Dungeon_Depths.ttf"), 26);
            if (customFont != null) {
                titulo.setFont(customFont);
            } else {
                titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
            }
        } catch (Exception e) {
            titulo.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");
            System.err.println("No se pudo cargar la fuente personalizada: " + e.getMessage());
        }

        titulo.setStyle(titulo.getStyle() + "; -fx-text-fill: black;");
        header.getChildren().add(titulo);

        // === GRID DE UBICACIONES ===
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));

        List<Ubicacion> ubicaciones = reino.getUbicaciones();
        int columnas = 4;

        for (int i = 0; i < ubicaciones.size(); i++) {
            Ubicacion ubicacion = ubicaciones.get(i);

            Label nombre = new Label(ubicacion.getNombre());
            nombre.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

            StackPane recuadro = new StackPane(nombre);
            recuadro.setPrefSize(160, 120);

            BackgroundImage bgImage = null;
            try {
                if (ubicacion instanceof Tienda) {
                    Image imagen = new Image(getClass().getResource("/images/tienda.jpg").toExternalForm());
                    bgImage = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                            new BackgroundSize(160, 120, false, false, false, false));
                } else if (ubicacion instanceof Escena escena) {
                    Tablero tablero = Tablero.desdeJson(escena.getTableroJson());
                    String fondoRuta = tablero.getFondo();
                    Image imagen = new Image(getClass().getResource("/" + fondoRuta).toExternalForm());
                    bgImage = new BackgroundImage(imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                            new BackgroundSize(160, 120, false, false, false, false));
                }
            } catch (Exception e) {
                System.err.println("Error al cargar imagen de fondo: " + e.getMessage());
            }

            if (bgImage != null) {
                recuadro.setBackground(new Background(bgImage));
            } else {
                recuadro.setStyle("-fx-background-color: gray;");
            }

            StackPane marco = new StackPane(recuadro);
            marco.setStyle("-fx-border-color: black; -fx-border-width: 3; -fx-background-color: white;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 8, 0.5, 2, 2);");
            marco.setMaxSize(160, 120);

            int fila = i / columnas;
            int columna = i % columnas;
            grid.add(marco, columna, fila);
        }

        // === RECUADRO "AÑADIR" ===
        StackPane recuadroAniadir = new StackPane();
        recuadroAniadir.setPrefSize(160, 120);

        try {
            Image icono = new Image(getClass().getResource("/images/nueva_ubicacion.png").toExternalForm());
            BackgroundImage bgIcono = new BackgroundImage(icono, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(160, 120, false, false, false, false));
            recuadroAniadir.setBackground(new Background(bgIcono));
        } catch (Exception e) {
            System.err.println("No se pudo cargar icono_aniadir.png: " + e.getMessage());
            recuadroAniadir.setStyle("-fx-background-color: lightgray;");
        }

        StackPane marcoAniadir = new StackPane(recuadroAniadir);
        marcoAniadir.setStyle("-fx-border-color: black; -fx-border-width: 3; -fx-background-color: white;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 8, 0.5, 2, 2);");
        marcoAniadir.setMaxSize(160, 120);

        int total = ubicaciones.size();
        int filaAniadir = total / columnas;
        int columnaAniadir = total % columnas;
        grid.add(marcoAniadir, columnaAniadir, filaAniadir);

        // === COMBINAR HEADER Y GRID ===
        VBox layout = new VBox();
        layout.getChildren().addAll(header, grid);

        return new StackPane(layout);
    }
}
