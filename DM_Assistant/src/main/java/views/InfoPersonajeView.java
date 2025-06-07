package views;

import clases_personaje.Personaje;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoPersonajeView {
	
	

    private Personaje personaje;
    
    

    public InfoPersonajeView(Personaje personaje) {
        this.personaje = personaje;
    }

    public VBox crearView() {
        VBox pane = new VBox();
        pane.setSpacing(20); // Espaciado entre elementos
        pane.setPadding(new Insets(20)); // Margen interno del VBox

        // Header: DM Assistant
        Label headerLabel = new Label("DM Assistant");
        headerLabel.setStyle("-fx-background-color: #c3dfb0; -fx-font-family: 'Copperplate Gothic Bold'; -fx-font-size: 18px;");
        headerLabel.setPadding(new Insets(10));
        headerLabel.setMaxWidth(Double.MAX_VALUE);

        // Datos del personaje
        String nombre = personaje.getNombre();
        int nivel = personaje.getNivel();
        String clase = personaje.getClase().getNombre();
        String raza = personaje.getRaza().getNombre();
        String sexo = personaje.getSexo();
        String religion = personaje.getReligion().getNombre();
        String nacion = personaje.getNacion().getNombre();
        String ideologia = personaje.getIdeologia().getNombre();
        int edad = personaje.getEdad();
        int nHechizos = personaje.getListaHechizos().size();

        // Crear etiquetas
        Label lblNombre = new Label("Nombre del personaje: " + nombre);
        Label lblNivel = new Label("Nivel del personaje: " + nivel);
        Label lblClase = new Label("Clase: " + clase);
        Label lblRaza = new Label("Raza: " + raza);
        Label lblSexo = new Label("Sexo: " + sexo);
        Label lblReligion = new Label("Religión: " + religion);
        Label lblNacion = new Label("Nación: " + nacion);
        Label lblIdeologia = new Label("Ideología: " + ideologia);
        Label lblEdad = new Label("Edad: " + edad);
        Label lblHechizos = new Label("Número de hechizos conocidos: " + nHechizos);

        // Agregar etiquetas al VBox
        pane.getChildren().addAll(
            headerLabel,
            lblNombre,
            lblNivel,
            lblClase,
            lblRaza,
            lblSexo,
            lblReligion,
            lblNacion,
            lblIdeologia,
            lblEdad,
            lblHechizos
        );

        return pane;
    }

}
