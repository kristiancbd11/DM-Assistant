package views;

import clases_partida.Criatura;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DesktopCriaturaView {
	
	public StackPane crearView(Criatura criatura) {

	    StackPane pane = new StackPane(new Label(criatura.getNombre()));
		return pane;
	}
}
