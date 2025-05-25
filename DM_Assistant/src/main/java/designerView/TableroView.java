package designerView;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import clases_personaje.Personaje;

public class TableroView {

	private Pane contenido;
    private PaletaView paleta;
    private Tablero tablero;
    private VBox root;
    private HashMap<UUID, ElementoVisualData> elementosColocados;
    private String rutaFondoActual;
    private ElementoVisual elementoResaltado;
    private HashMap<UUID, ElementoVisual> elementosVisuales = new HashMap<>();

    public TableroView(Tablero tablero) {
        this.tablero = tablero;
        this.elementosColocados = tablero.getElementosColocados();
        this.rutaFondoActual = tablero.getFondo();
    }

    
    public void inicializar() {
        root = new VBox(10);
        root.setPrefSize(1024, 768);

        this.contenido = new Pane();
        contenido.setPrefSize(1024, 768);
        root.getChildren().add(contenido);
        contenido.setFocusTraversable(true);
        contenido.requestFocus();

        // Se recorre por los valores del HashMap
        for (ElementoVisualData evData : elementosColocados.values()) {
            ElementoVisual ev = new ElementoVisual(evData, this);
            VBox contenedor = ev.getContenedor();
            contenedor.setLayoutX(evData.getEjeX());
            contenedor.setLayoutY(evData.getEjeY());
            contenido.getChildren().add(contenedor);
        }

        if (rutaFondoActual != null && !rutaFondoActual.isEmpty()) {
            actualizarFondo(rutaFondoActual);
        }

        contenido.setOnMouseClicked(event -> {
            if (paleta == null) return;

            // Solo colocar si se hace clic sobre el Pane directamente
            if (event.getTarget() != contenido) return;

            ElementoVisual seleccionado = paleta.getSeleccionado();
            if (seleccionado == null) return;

            ElementoVisual copia = new ElementoVisual(seleccionado.getData().clonar(), this);
            VBox contenedor = copia.getContenedor();

            double x = event.getX();
            double y = event.getY();

            copia.getData().setEjeX((int) x);
            copia.getData().setEjeY((int) y);
            contenedor.setLayoutX(x);
            contenedor.setLayoutY(y);

            contenido.getChildren().add(contenedor);

            UUID clave = UUID.randomUUID();
            copia.getData().setClave(clave);
            elementosColocados.put(clave, copia.getData());
        });

        final List<Line> lineasTemporales = new ArrayList<>();
        final Label[] etiquetaLongitud = {null};
        final Line[] lineaCreada = {null};
        final double[] startX = new double[1];
        final double[] startY = new double[1];
        final double[] currentMouseX = new double[1];
        final double[] currentMouseY = new double[1];
        final boolean[] controlPresionado = {false};

        // Asegurar que el pane pueda recibir eventos de teclado
        contenido.setFocusTraversable(true);
        contenido.requestFocus();

        contenido.setOnMouseMoved(event -> {
            currentMouseX[0] = event.getX();
            currentMouseY[0] = event.getY();

            if (controlPresionado[0] && lineaCreada[0] != null) {
                lineaCreada[0].setEndX(currentMouseX[0]);
                lineaCreada[0].setEndY(currentMouseY[0]);

                double dx = currentMouseX[0] - startX[0];
                double dy = currentMouseY[0] - startY[0];
                double distancia = Math.sqrt(dx * dx + dy * dy);
                int valor = (int)(distancia / 40);

                if (etiquetaLongitud[0] == null) {
                    Label label = new Label();
                    label.setTextFill(Color.YELLOW);
                    label.setFont(new Font(14));
                    contenido.getChildren().add(label);
                    etiquetaLongitud[0] = label;
                }

                etiquetaLongitud[0].setText(String.valueOf(valor));
                etiquetaLongitud[0].setLayoutX(currentMouseX[0] + 10);
                etiquetaLongitud[0].setLayoutY(currentMouseY[0] - 10);
            }
        });

        // Al presionar Ctrl: iniciar primera línea desde posición actual
        contenido.setOnKeyPressed(event -> {
            switch(event.getCode()) {
                case CONTROL -> {
                    if (!controlPresionado[0]) {
                        controlPresionado[0] = true;
                        startX[0] = currentMouseX[0];
                        startY[0] = currentMouseY[0];

                        Line linea = new Line(startX[0], startY[0], startX[0], startY[0]);
                        linea.setStroke(Color.YELLOW);
                        linea.setStrokeWidth(2);
                        contenido.getChildren().add(linea);

                        lineaCreada[0] = linea;
                        lineasTemporales.add(linea);
                    }
                }
            }
            event.consume();
        });

        // Al hacer clic izquierdo mientras Ctrl está presionado: fijar línea actual y comenzar otra nueva
        contenido.setOnMousePressed(event -> {
            if (controlPresionado[0] && event.getButton() == MouseButton.PRIMARY) {
                if (lineaCreada[0] != null) {
                    // Ya está en la lista, solo "cerramos" la línea actual
                    lineaCreada[0] = null;
                }

                // Crear nueva línea desde clic
                startX[0] = event.getX();
                startY[0] = event.getY();

                Line nueva = new Line(startX[0], startY[0], startX[0], startY[0]);
                nueva.setStroke(Color.YELLOW);
                nueva.setStrokeWidth(2);
                contenido.getChildren().add(nueva);

                lineaCreada[0] = nueva;
                lineasTemporales.add(nueva);
            }
            event.consume();
        });

        // Al soltar Ctrl: eliminar todas las líneas y etiqueta
        contenido.setOnKeyReleased(event -> {
            switch(event.getCode()) {
                case CONTROL -> {
                    controlPresionado[0] = false;

                    for (Line l : lineasTemporales) {
                        contenido.getChildren().remove(l);
                    }
                    lineasTemporales.clear();
                    lineaCreada[0] = null;

                    if (etiquetaLongitud[0] != null) {
                        contenido.getChildren().remove(etiquetaLongitud[0]);
                        etiquetaLongitud[0] = null;
                    }
                }
            }
            event.consume();
        });


    }

    public void actualizarFondo(String rutaFondo) {
        try (InputStream stream = getClass().getResourceAsStream("/" + rutaFondo)) {
            if (stream == null) {
                System.err.println("No se encontró el recurso de fondo: " + rutaFondo);
                return;
            }
            Image image = new Image(stream);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(1024, 768, false, false, false, false));

            if (!root.getChildren().isEmpty() && root.getChildren().get(0) instanceof Pane contenido) {
                contenido.setBackground(new Background(backgroundImage));
            }

            rutaFondoActual = rutaFondo;
        } catch (Exception e) {
            System.err.println("Error cargando imagen fondo: " + e.getMessage());
        }
    }
    
    public void refrescarTablero() {
        elementosVisuales.clear();
        contenido.getChildren().clear();

        for (ElementoVisualData evData : elementosColocados.values()) {
            ElementoVisual ev = new ElementoVisual(evData, this);
            elementosVisuales.put(evData.getClave(), ev);

            VBox contenedor = ev.getContenedor();
            contenedor.setLayoutX(evData.getEjeX());
            contenedor.setLayoutY(evData.getEjeY());

            contenido.getChildren().add(contenedor);
        }

        if (rutaFondoActual != null && !rutaFondoActual.isEmpty()) {
            actualizarFondo(rutaFondoActual);
        }
    }


    public String getRutaFondoActual() {
        return rutaFondoActual;
    }

    public PaletaView getPaleta() {
        return paleta;
    }

    public void setPaleta(PaletaView paleta) {
        this.paleta = paleta;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
        this.rutaFondoActual = tablero.getFondo();
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }

    public HashMap<UUID, ElementoVisualData> getElementosColocados() {
        return elementosColocados;
    }

    public void setElementosColocados(HashMap<UUID, ElementoVisualData> elementosColocados) {
        this.elementosColocados = elementosColocados;
    }

    public void setRutaFondoActual(String rutaFondoActual) {
        this.rutaFondoActual = rutaFondoActual;
    }

    public void setElementoResaltado(ElementoVisual ev) {
        this.elementoResaltado = ev;
    }

    public ElementoVisual getElementoResaltado() {
        return this.elementoResaltado;
    }
    
    public Pane getContenido() {
        return this.contenido;
    }


	public HashMap<UUID, ElementoVisual> getElementosVisuales() {
		return elementosVisuales;
	}


	public void setElementosVisuales(HashMap<UUID, ElementoVisual> elementosVisuales) {
		this.elementosVisuales = elementosVisuales;
	}


	public void setContenido(Pane contenido) {
		this.contenido = contenido;
	}
    
    

}
