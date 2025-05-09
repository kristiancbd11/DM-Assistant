package designerView;

import clases_partida.Mundo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaletaView {

    private final Mundo mundo;
    private final TableroView tableroView;
    private final Map<String, Object> objetosPorNombre = new HashMap<>();

    public PaletaView(Mundo mundo, TableroView tableroView) {
        this.mundo = mundo;
        this.tableroView = tableroView;
    }

    public Stage crearVentana(double x, double y) {
        VBox contenido = new VBox(10);
        contenido.setPadding(new Insets(10));
        contenido.setAlignment(Pos.TOP_CENTER);

        TitledPane personajesPane = crearCategoria("Personajes", mundo.getPersonajes());
        TitledPane npcsPane = crearCategoria("NPCs", mundo.getNpcs());
        TitledPane criaturasPane = crearCategoria("Criaturas", mundo.getCriaturas());

        contenido.getChildren().addAll(new Accordion(personajesPane, npcsPane, criaturasPane), crearPaletaColores());

        Stage stage = new Stage();
        stage.setTitle("Paleta de Elementos");
        stage.setScene(new Scene(contenido, 200, 600));
        stage.setX(x);
        stage.setY(y);
        stage.setResizable(false);
        stage.show();
        return stage;
    }

    private TitledPane crearCategoria(String titulo, List<?> elementos) {
        VBox contenedor = new VBox(5);
        contenedor.setPadding(new Insets(5));
        contenedor.setAlignment(Pos.TOP_CENTER);

        for (Object obj : elementos) {
            String nombre = obj.toString();
            objetosPorNombre.put(nombre, obj);

            Label label = new Label(nombre);
            label.setStyle("-fx-background-color: lightblue; -fx-padding: 5px; -fx-border-color: gray;");
            label.setUserData(Map.of("nombre", nombre, "categoria", titulo));

            label.setOnDragDetected(event -> {
                var db = label.startDragAndDrop(javafx.scene.input.TransferMode.MOVE);
                var content = new javafx.scene.input.ClipboardContent();
                content.putString(nombre);
                db.setContent(content);
                db.setDragView(label.snapshot(null, null));
                event.consume();
            });

            contenedor.getChildren().add(label);
        }

        return new TitledPane(titulo, contenedor);
    }

    private GridPane crearPaletaColores() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));
        Color[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.BROWN, Color.GRAY};

        for (int i = 0; i < colores.length; i++) {
            Rectangle rect = new Rectangle(40, 40, colores[i]);
            grid.add(rect, i % 4, i / 4);
        }

        return grid;
    }
}
