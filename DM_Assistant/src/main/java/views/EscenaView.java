package views;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class EscenaView {
	
	public StackPane crearView() {

	    StackPane pane = new StackPane(new Label("Vista de Escenas"));
		return pane;
	}
}
