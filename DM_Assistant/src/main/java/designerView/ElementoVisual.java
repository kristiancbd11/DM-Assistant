package designerView;

import java.io.InputStream;

import clases_partida.Criatura;
import clases_personaje.Personaje;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ElementoVisual {
    private final String nombre;
    private int fila;
    private int columna;
    private final Object obj;
    private final String token;
    private final VBox nodoVisual;

    private double posInicialX;
    private double posInicialY;

    public ElementoVisual(String nombreImagen, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.obj = null;
        this.nombre = nombreImagen.contains(".") ? nombreImagen.substring(0, nombreImagen.lastIndexOf('.')) : nombreImagen;
        this.token = nombreImagen;

        Label nombreLabel = new Label(this.nombre);
        nombreLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: black;");

        InputStream is = getClass().getResourceAsStream(nombreImagen);
        if (is == null) {
            System.err.println("No se encontró la imagen: " + nombreImagen);
        }


        if (is == null) {
            System.err.println("No se encontró la imagen: " + this.token);
        }

        ImageView imageView = new ImageView(new Image(is, 32, 32, true, true));


        this.nodoVisual = new VBox(2, nombreLabel, imageView);
        nodoVisual.setAlignment(Pos.CENTER);
        nodoVisual.setStyle("-fx-background-color: transparent;");
    }

    
    public ElementoVisual(Object obj, int fila, int columna) {
        if (obj instanceof Personaje personaje) {
            nombre = personaje.getNombre();
        } else {
            Criatura criatura = (Criatura) obj;
            nombre = criatura.getNombre();
        }
        this.fila = fila;
        this.columna = columna;
        this.obj = obj;
        if (obj instanceof Personaje personaje) {
            token = personaje.getToken();
        } else {
            token = "268894-Giant Spider Black.png";
        }

        // Crear el VBox en el constructor de ElementoVisual
        Label nombreLabel = new Label(this.nombre);
        nombreLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: black;");

        ImageView imageView = new ImageView(new Image(
                getClass().getResourceAsStream("/tablero/tokens/" + this.token), 32, 32, true, true));

        this.nodoVisual = new VBox(2, nombreLabel, imageView); // Imagen abajo, nombre arriba
        nodoVisual.setAlignment(Pos.CENTER);
        nodoVisual.setStyle("-fx-background-color: transparent;");
    }

    // Getter y setter para obtener y actualizar las posiciones
    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    // Método para actualizar la posición visual del nodo
    public void actualizarPosicion(double x, double y) {
        nodoVisual.setLayoutX(x);
        nodoVisual.setLayoutY(y);
    }

    public String getNombre() {
        return nombre;
    }

    public Object getObj() {
        return obj;
    }

    public String getToken() {
        return token;
    }

    public VBox getNodoVisual() {
        return nodoVisual;  // Devolvemos el VBox que contiene la imagen y el nombre
    }

    // Métodos para almacenar las posiciones iniciales del mouse
    public double getPosInicialX() {
        return posInicialX;
    }

    public void setPosInicialX(double posInicialX) {
        this.posInicialX = posInicialX;
    }

    public double getPosInicialY() {
        return posInicialY;
    }

    public void setPosInicialY(double posInicialY) {
        this.posInicialY = posInicialY;
    }
}
