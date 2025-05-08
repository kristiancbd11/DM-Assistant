package views;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class UbicacionView {
	
	public StackPane crearView() {
		StackPane pane = new StackPane(new Label("Vista de Ubicaciones"));
		return pane;
	}
}
