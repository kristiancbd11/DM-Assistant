package designerView;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.UUID;

public class TableroView {

    private PaletaView paleta;
    private Tablero tablero;
    private VBox root;
    private HashMap<UUID, ElementoVisualData> elementosColocados;
    private String rutaFondoActual;
    private ElementoVisual elementoResaltado;

    public TableroView(Tablero tablero) {
        this.tablero = tablero;
        this.elementosColocados = tablero.getElementosColocados();
        this.rutaFondoActual = tablero.getFondo();
    }

    
    public void inicializar() {
        root = new VBox(10);
        root.setPrefSize(1024, 768);

        Pane contenido = new Pane();
        contenido.setPrefSize(1024, 768);
        root.getChildren().add(contenido);

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
}
