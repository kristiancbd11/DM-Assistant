package views;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class InfoView {
    private Label headerLabel;
    private Label infoLabel;
    private VBox container;
    private VBox infoBox;

    public InfoView() {
        // Header con texto "DM Assistant"
        headerLabel = new Label("DM Assistant");
        headerLabel.setStyle("-fx-background-color: #c3dfb0; -fx-font-family: 'Copperplate Gothic Bold'; -fx-font-size: 18px;");
        headerLabel.setPadding(new Insets(10));
        headerLabel.setMaxWidth(Double.MAX_VALUE); // Para que se extienda horizontalmente

        // Label de información
        infoLabel = new Label("📁 InfoView");

        // Contenedor para el contenido dinámico
        infoBox = new VBox();

        // Contenedor principal
        container = new VBox(headerLabel, infoLabel, infoBox);
        container.setMinWidth(100);
        container.setStyle("-fx-background-color: #FFFFFF;");
        container.setPadding(new Insets(20, 30, 20, 30));  // arriba, derecha, abajo, izquierda

        // Espaciado entre elementos
        VBox.setMargin(infoLabel, new Insets(10, 0, 0, 0));
        VBox.setVgrow(infoBox, Priority.ALWAYS);
    }

    public void setContenido(VBox nuevoContenido) {
        infoBox.getChildren().setAll(nuevoContenido.getChildren());
    }

    public VBox showInfoView() {
        return container; // Corregido: debería devolver el contenedor completo, no solo infoBox
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(Label infoLabel) {
        this.infoLabel = infoLabel;
    }

    public VBox getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(VBox infoBox) {
        this.infoBox = infoBox;
    }
}
