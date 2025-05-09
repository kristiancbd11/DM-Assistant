package designerView;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TableroView {

    private final int filas;
    private final int columnas;
    private final int tamano;

    private Canvas canvas;
    private final List<ElementoVisual> elementosColocados = new ArrayList<>();

    public TableroView(int filas, int columnas, int tamano) {
        this.filas = filas;
        this.columnas = columnas;
        this.tamano = tamano;
    }

    public Stage crearVentana(double x, double y) {
        double width = columnas * tamano;
        double height = filas * tamano;

        canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        redibujar(gc);

        canvas.setOnDragOver(this::manejarDragOver);
        canvas.setOnDragDropped(this::manejarDragDropped);

        StackPane root = new StackPane(canvas);
        Stage stage = new Stage();
        stage.setTitle("Lienzo de Diseño");
        stage.setScene(new Scene(root));
        stage.setX(x);
        stage.setY(y);
        stage.setResizable(false);
        stage.show();
        return stage;
    }

    private void manejarDragOver(DragEvent event) {
        if (event.getGestureSource() != canvas && event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    private void manejarDragDropped(DragEvent event) {
        String nombreElemento = event.getDragboard().getString();
        double x = event.getX();
        double y = event.getY();

        // Snap a la grilla
        int columna = (int) (x / tamano);
        int fila = (int) (y / tamano);

        elementosColocados.add(new ElementoVisual(nombreElemento, fila, columna));

        redibujar(canvas.getGraphicsContext2D());

        event.setDropCompleted(true);
        event.consume();
    }

    private void redibujar(GraphicsContext gc) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        dibujarGrid(gc);

        for (ElementoVisual ev : elementosColocados) {
            double drawX = ev.getColumna() * tamano + tamano * 0.1;
            double drawY = ev.getFila() * tamano + tamano * 0.6;
            gc.setFill(Color.BLACK);
            gc.fillText(ev.getNombre(), drawX, drawY);
        }
    }

    private void dibujarGrid(GraphicsContext gc) {
        gc.setStroke(Color.LIGHTGRAY);
        for (int i = 0; i <= columnas; i++) {
            gc.strokeLine(i * tamano, 0, i * tamano, filas * tamano);
        }
        for (int j = 0; j <= filas; j++) {
            gc.strokeLine(0, j * tamano, columnas * tamano, j * tamano);
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public List<ElementoVisual> getElementosColocados() {
        return elementosColocados;
    }
}
