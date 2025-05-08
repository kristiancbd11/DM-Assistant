package control;

import clases_partida.Mundo;
import clases_partida.Criatura;
import dbhandlerCRUD.CriaturaCRUD;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import views.CreationCriaturaView;

public class CreationCriaturaController {

    private final Stage window;
    private final CreationCriaturaView view;
    private final ExplorerController explorerController;
    private final Mundo mundo;
    private final CriaturaCRUD criaturaCRUD;

    public CreationCriaturaController(Stage window, CreationCriaturaView view, ExplorerController explorerController,
                                      Mundo mundo) {
        this.window = window;
        this.view = view;
        this.explorerController = explorerController;
        this.mundo = mundo;
        this.criaturaCRUD = new CriaturaCRUD();  // Asegúrate de tener esta clase implementada

        // Configurar evento para el botón Cancelar
        view.getBtnCancelar().setOnAction(event -> {
            view.getNombreField().clear();  // Limpiar el campo de nombre
        });

        // Configurar evento para el botón Crear
        view.getBtnCrear().setOnAction(event -> {
            String nombre = view.getNombreField().getText().trim();

            if (nombre.isEmpty()) {
                mostrarAlerta("El nombre de la criatura no puede estar vacío.");
                return;
            }
            
            Criatura criatura = new Criatura(nombre);
            mundo.addCriatura(criatura);
            
            // Guardar en la base de datos
            boolean exito = criaturaCRUD.saveCriatura(criatura);  // Asume que retorna true si fue exitoso

            if (exito) {
                mostrarAlerta("Criatura creada exitosamente.", AlertType.INFORMATION);
                explorerController.refreshTreeView();
                view.getNombreField().clear();  // Limpiar después de crear
            } else {
                mostrarAlerta("Hubo un error al guardar la criatura.");
            }
        });
    }

    private void mostrarAlerta(String mensaje) {
        mostrarAlerta(mensaje, AlertType.WARNING);
    }

    private void mostrarAlerta(String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
