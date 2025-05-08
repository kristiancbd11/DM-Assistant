package control;

import clases_partida.Nacion;
import javafx.scene.layout.StackPane;
import views.DesktopNacionView;
import views.DesktopView;

public class DesktopNacionController {
	private final DesktopNacionView desktopNacionView;
	private final DesktopView desktopView;
	private Nacion nacion;
	
	public DesktopNacionController(DesktopView desktopView) {
		this.desktopView = desktopView;
		this.desktopNacionView = new DesktopNacionView();
	}

	public StackPane getVista() {
		return desktopNacionView.crearView(nacion);
	}
	
    public void cambiarVista(Nacion nacion) {
            desktopNacionView.desplazarAHacia(nacion.getNombre());
    }

	public Nacion getNacion() {
		return nacion;
	}

	public void setNacion(Nacion nacion) {
		this.nacion = nacion;
	}
}
