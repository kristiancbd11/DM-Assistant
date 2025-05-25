package control;

import clases_partida.Mundo;
import clases_personaje.Personaje;
import dbhandlerCRUD.PersonajeCRUD;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import views.DesktopPersonajeView;

public class DesktopPersonajeController {

	private final DesktopPersonajeView view;
	private Personaje original;
	private DesktopController desktopController;
	private final Personaje copiaOriginal;
	private final PersonajeCRUD crud;

	public DesktopPersonajeController(Personaje personaje, DesktopController desktopController, Mundo mundo) {
		this.original = personaje;
		this.copiaOriginal = new Personaje(personaje); // Copia para restaurar
		this.view = new DesktopPersonajeView(personaje, this, mundo);
		this.crud = new PersonajeCRUD();
	}

	public StackPane getVista() {
		return view.crearView(this::guardarCambios, this::cancelarCambios);
	}

	private void guardarCambios() {
		try {
			original.setNombre(view.getNombre());
			original.setExperiencia(Integer.parseInt(view.getExperiencia()));
			original.setOro(Integer.parseInt(view.getOro()));
			original.setNivel();

			crud.savePersonaje(original);
			view.actualizarNivel(original.getNivel());

			new Alert(Alert.AlertType.INFORMATION, "Personaje actualizado correctamente.").showAndWait();
		} catch (NumberFormatException ex) {
			new Alert(Alert.AlertType.ERROR, "Experiencia y oro deben ser números válidos.").showAndWait();
		}
	}

	private void cancelarCambios() {
		view.setNombre(copiaOriginal.getNombre());
		view.setExperiencia(copiaOriginal.getExperiencia());
		view.actualizarNivel(copiaOriginal.getNivel());
	}

	public Personaje getOriginal() {
		return original;
	}

	public void setOriginal(Personaje original) {
		this.original = original;
	}

	public DesktopController getDesktopController() {
		return desktopController;
	}

	public void setDesktopController(DesktopController desktopController) {
		this.desktopController = desktopController;
	}
	
	

}
