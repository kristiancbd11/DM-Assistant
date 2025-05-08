package control;

import clases_partida.Mundo;
import javafx.scene.layout.VBox;
import views.InfoMundoView;

public class InfoMundoController {
	private InfoMundoView view;
	private Mundo mundo;
	
	public InfoMundoController(Mundo mundo) {
		this.view = new InfoMundoView(mundo);
		this.mundo = mundo;
	}
	
	public VBox getVista() {
		return view.crearView();
	}
}
