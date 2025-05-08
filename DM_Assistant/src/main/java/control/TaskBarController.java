package control;

import javafx.scene.Node;
import views.TaskBarView;

public class TaskBarController {

    private final TaskBarView taskBarView;

    public TaskBarController(TaskBarView taskBarView) {
        this.taskBarView = taskBarView;
    }

    /**
     * Establece un mensaje o contenido visual en la barra de tareas.
     * @param node Elemento visual a mostrar (por ejemplo, un Label)
     */
    public void setContent(Node node) {
        taskBarView.getTaskbar().getChildren().setAll(node);
    }

    /**
     * Agrega un nuevo nodo a la barra de tareas sin borrar los existentes.
     * @param node Elemento visual a agregar
     */
    public void addContent(Node node) {
        taskBarView.getTaskbar().getChildren().add(node);
    }

    /**
     * Limpia todo el contenido de la TaskBar.
     */
    public void clear() {
        taskBarView.getTaskbar().getChildren().clear();
    }

    /**
     * Devuelve la vista asociada para integrarla en el layout principal.
     */
    public TaskBarView getView() {
        return taskBarView;
    }
}
