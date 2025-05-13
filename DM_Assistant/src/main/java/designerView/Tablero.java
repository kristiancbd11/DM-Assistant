package designerView;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javafx.scene.layout.VBox;

public class Tablero {
    private String fondo;
    private List<ElementoVisual> elementosColocados;

    public Tablero(String fondo, List<ElementoVisual> elementosColocados) {
        this.fondo = fondo;
        this.elementosColocados = elementosColocados;
    }

    public String getFondo() {
        return fondo;
    }

    public List<ElementoVisual> getElementosColocados() {
        return elementosColocados;
    }

    public String generarJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.addMixIn(VBox.class, IgnoreJavaFXProperties.class);

        try {
            // Verificar que el objeto esté correctamente inicializado
            if (this.fondo == null || this.elementosColocados == null) {
                throw new IllegalStateException("El objeto Tablero no está correctamente inicializado.");
            }

            // Serializar el objeto a JSON
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // O maneja el error de otra manera
        }
    }

    // Mixin para ignorar propiedades de JavaFX
    @JsonIgnoreProperties({"parent", "scene", "antiAliasing"})
    private static abstract class IgnoreJavaFXProperties {
        // Esta clase se usa solo para anotaciones
    }
}
