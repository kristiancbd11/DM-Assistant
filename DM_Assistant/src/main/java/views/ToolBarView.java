package views;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class ToolBarView {
    private ToolBar toolBar;
    private Button reiniciarButton;

    public ToolBarView() {
        reiniciarButton = new Button("Reiniciar");

        toolBar = new ToolBar(
            new Button("Archivo"),
            new Button("Editar"),
            new Button("Ver"),
            new Button("Mundo"),
            new Button("ROG Archive"),
            new Button("Nube"),
            new Button("Importar"),
            new Button("Exportar"),
            reiniciarButton
        );
    }

    public ToolBar getToolbar() {
        return toolBar;
    }

    public Button getReiniciarButton() {
        return reiniciarButton;
    }
}
