package views;

import clases_personaje.Personaje;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DesktopPersonajeView {

    private TextField nombreField;
    private TextField experienciaField;
    private Label nivelLabel;
    private Button guardarBtn;
    private Button cancelarBtn;

    public StackPane crearView(Personaje personaje, Runnable onGuardar, Runnable onCancelar) {
        VBox contenedor = new VBox(10);
        contenedor.setPadding(new Insets(20));

        Label titulo = new Label("Editar Personaje");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        nombreField = new TextField(personaje.getNombre());
        experienciaField = new TextField(String.valueOf(personaje.getExperiencia()));
        nivelLabel = new Label("Nivel: " + personaje.getNivel());

        Label claseLabel = new Label("Clase: " + personaje.getClase());
        Label subclaseLabel = new Label("Subclase: " + 
            (personaje.getSubclase() != null ? personaje.getSubclase() : "No asignada"));
        Label razaLabel = new Label("Raza: " + personaje.getRaza());
        Label sexoLabel = new Label("Sexo: " + personaje.getSexo());
        Label religionLabel = new Label("Religión: " + personaje.getReligion());
        Label nacionLabel = new Label("Nación: " + personaje.getNacion());
        Label ideologiaLabel = new Label("Ideología: " + personaje.getIdeologia());

        guardarBtn = new Button("Guardar");
        cancelarBtn = new Button("Cancelar");

        guardarBtn.setOnAction(e -> onGuardar.run());
        cancelarBtn.setOnAction(e -> onCancelar.run());

        HBox botones = new HBox(10, guardarBtn, cancelarBtn);
        botones.setPadding(new Insets(10, 0, 0, 0));

        contenedor.getChildren().addAll(
            titulo,
            new Label("Nombre:"), nombreField,
            claseLabel, subclaseLabel, nivelLabel,
            new Label("Experiencia:"), experienciaField,
            razaLabel, sexoLabel, religionLabel, nacionLabel, ideologiaLabel,
            botones
        );

        return new StackPane(contenedor);
    }

    public String getNombre() {
        return nombreField.getText();
    }

    public String getExperiencia() {
        return experienciaField.getText();
    }

    public void setNombre(String nombre) {
        nombreField.setText(nombre);
    }

    public void setExperiencia(int experiencia) {
        experienciaField.setText(String.valueOf(experiencia));
    }

    public void actualizarNivel(int nivel) {
        nivelLabel.setText("Nivel: " + nivel);
    }
}
