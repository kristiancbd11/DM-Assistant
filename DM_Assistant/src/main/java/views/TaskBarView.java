package views;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TaskBarView {

	private HBox taskbar;
	
	public TaskBarView() {

        taskbar = new HBox(new Label("Taskbar"));
        taskbar.setStyle("-fx-background-color: #CCCCCC; -fx-padding: 5;");
	}

	public HBox getTaskbar() {
		return taskbar;
	}

	public void setTaskbar(HBox taskbar) {
		this.taskbar = taskbar;
	}

}
