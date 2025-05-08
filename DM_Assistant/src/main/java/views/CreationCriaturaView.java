package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class CreationCriaturaView extends VBox {

    private TextField nombreField;
    private Button btnCrear;
    private Button btnCancelar;

    public CreationCriaturaView() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.TOP_CENTER);

        Label title = new Label("Formulario de nueva criatura");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label nombreLabel = new Label("Nombre:");
        nombreField = new TextField();
        nombreField.setPromptText("Introduce el nombre de la criatura");

        btnCrear = new Button("Crear");
        btnCancelar = new Button("Cancelar");

        HBox buttonBox = new HBox(10, btnCrear, btnCancelar);
        buttonBox.setAlignment(Pos.CENTER);

        getChildren().addAll(title, nombreLabel, nombreField, buttonBox);
    }

    public TextField getNombreField() {
        return nombreField;
    }

    public Button getBtnCrear() {
        return btnCrear;
    }

    public Button getBtnCancelar() {
        return btnCancelar;
    }
}
