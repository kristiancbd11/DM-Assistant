package views;

import java.util.List;

import clases_partida.Mundo;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DesignerView {

    private static final int FILAS = 15;
    private static final int COLUMNAS = 25;
    private static final int CASILLA_TAM = 40; // tamaño en píxeles
    private Mundo mundo;

    public DesignerView(Mundo mundo) {
        this.mundo = mundo;
    }

    public void mostrar() {
    	Platform.runLater(() -> {
    		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    		double gridWidth = COLUMNAS * CASILLA_TAM;
    		double gridHeight = FILAS * CASILLA_TAM;
    		double paletteWidth = 200;
    		double margen = 10;

    		double gridX = (screenBounds.getWidth() - (gridWidth + margen + paletteWidth)) / 2;
    		double gridY = (screenBounds.getHeight() - gridHeight) / 2;

    		Stage[] referencias = new Stage[2];
    		Stage cuadrícula = crearVentanaCuadricula(gridX, gridY);
    		Stage paleta = crearVentanaPaleta(gridX + gridWidth + margen, gridY);

    		referencias[0] = cuadrícula;
    		referencias[1] = paleta;

    		cuadrícula.setOnCloseRequest(e -> referencias[1].close());
    		paleta.setOnCloseRequest(e -> referencias[0].close());
    	});
    }

    private StackPane crearCasilla() {
    	StackPane casilla = new StackPane();
    	casilla.setPrefSize(CASILLA_TAM, CASILLA_TAM);
    	casilla.setStyle("-fx-border-color: lightgray; -fx-background-color: white;");
    	return casilla;
    }

    private Stage crearVentanaCuadricula(double x, double y) {
    	Stage stage = new Stage();
    	stage.setTitle("Cuadrícula " + COLUMNAS + "x" + FILAS);
    	stage.setResizable(false);

    	GridPane grid = new GridPane();
    	for (int fila = 0; fila < FILAS; fila++) {
    		for (int col = 0; col < COLUMNAS; col++) {
    			grid.add(crearCasilla(), col, fila);
    		}
    	}

    	Scene scene = new Scene(grid);
    	stage.setScene(scene);
    	stage.setX(x);
    	stage.setY(y);
    	stage.show();
    	return stage;
    }

    private Stage crearVentanaPaleta(double x, double y) {
    	Stage stage = new Stage();
    	stage.setTitle("Paleta de Elementos");
    	stage.setResizable(false);

    	VBox contenido = new VBox(10);
    	contenido.setPadding(new Insets(10));
    	contenido.setAlignment(Pos.TOP_CENTER);

    	TitledPane personajesPane = crearCategoria("Personajes", mundo.getPersonajes());
    	TitledPane npcsPane = crearCategoria("NPCs", mundo.getNpcs());
    	TitledPane criaturasPane = crearCategoria("Criaturas", mundo.getCriaturas());

    	GridPane paletaColores = crearPaletaColores();

    	contenido.getChildren().addAll(new Accordion(personajesPane, npcsPane, criaturasPane), paletaColores);

    	Scene scene = new Scene(contenido, 200, FILAS * CASILLA_TAM);
    	stage.setScene(scene);
    	stage.setX(x);
    	stage.setY(y);
    	stage.show();
    	return stage;
    }

    private TitledPane crearCategoria(String titulo, List<?> elementos) {
    	VBox contenedor = new VBox(5);
    	contenedor.setPadding(new Insets(5));
    	contenedor.setAlignment(Pos.TOP_CENTER);

    	for (Object obj : elementos) {
    		contenedor.getChildren().add(new Label(obj.toString()));
    	}

    	return new TitledPane(titulo, contenedor);
    }

    private GridPane crearPaletaColores() {
    	GridPane grid = new GridPane();
    	grid.setHgap(5);
    	grid.setVgap(5);
    	grid.setPadding(new Insets(10));
    	Color[] colores = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PURPLE, Color.BROWN, Color.GRAY };

    	for (int i = 0; i < colores.length; i++) {
    		Rectangle rect = new Rectangle(40, 40, colores[i]);
    		grid.add(rect, i % 4, i / 4);
    	}

    	return grid;
    }
}
