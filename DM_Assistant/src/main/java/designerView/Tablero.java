package designerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.UUID;

import javafx.scene.layout.VBox;

public class Tablero {
    private String fondo;
    private HashMap<UUID, ElementoVisualData> elementosColocados = new HashMap<UUID, ElementoVisualData>();

    // Constructor vacío necesario para Jackson
    public Tablero() {}

    // Método estático para crear un Tablero desde un JSON
    public static Tablero desdeJson(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.addMixIn(VBox.class, IgnoreJavaFXProperties.class);

            return mapper.readValue(json, Tablero.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No se pudo parsear el JSON en un objeto Tablero.");
        }
    }

    public String getFondo() {
        return fondo;
    }

    public void setFondo(String fondo) {
        this.fondo = fondo;
    }

    public HashMap<UUID, ElementoVisualData> getElementosColocados() {
        return elementosColocados;
    }

    public void setElementosColocados(HashMap<UUID, ElementoVisualData> elementosColocados) {
        this.elementosColocados = elementosColocados;
    }

    public String generarJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.addMixIn(VBox.class, IgnoreJavaFXProperties.class);

        try {
            if (this.fondo == null || this.elementosColocados == null) {
                throw new IllegalStateException("El objeto Tablero no está correctamente inicializado.");
            }

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Mixin para ignorar propiedades de JavaFX
    @JsonIgnoreProperties({"parent", "scene", "antiAliasing"})
    private static abstract class IgnoreJavaFXProperties {
    }

}