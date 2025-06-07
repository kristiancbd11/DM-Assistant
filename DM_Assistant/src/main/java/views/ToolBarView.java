package views;

import clases_partida.Mundo;
import clases_personaje.Personaje;
import control.DesktopController;
import dbhandlerCRUD.MundoCRUD;
import fbhandler.FirebaseAuthService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ToolBarView {
	private ToolBar toolBar;
	private Button reiniciarButton;
	private DesktopController desktopController;

	private Button loginButton;
	private Button registerButton;
	private Button perfilButton;
	private Button inicioButton;
	
	public ToolBarView() {
        reiniciarButton = new Button("Reiniciar");
        loginButton = new Button("Login");
        registerButton = new Button("Register");
        perfilButton = new Button("Perfil");

        // Botón Inicio separado para asignarle la acción
        inicioButton = new Button("Inicio");

        // Estilo personalizado para el botón Perfil
        perfilButton.setStyle("-fx-background-color: #c3dfb0;");

        loginButton.setOnAction(e -> showLoginDialog());
        registerButton.setOnAction(e -> showRegisterDialog());
        perfilButton.setOnAction(e -> showPerfilDialog());

        // Asignamos la acción del botón Inicio para llamar a desktopController.vistaInicio()
        inicioButton.setOnAction(e -> {
            if (desktopController != null) {
                desktopController.vistaInicio();
            } else {
                System.err.println("DesktopController no asignado en ToolBarView.");
            }
        });

        toolBar = new ToolBar(loginButton, registerButton, inicioButton, new Button("Mundo"),
                new Button("ROG Archive"), new Button("Nube"), new Button("Importar"), new Button("Exportar"),
                reiniciarButton);
	}

	public ToolBar getToolbar() {
		return toolBar;
	}

	public Button getReiniciarButton() {
		return reiniciarButton;
	}

	private void showLoginDialog() {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setTitle("Iniciar Sesión");

		TextField emailField = new TextField();
		emailField.setPromptText("Correo electrónico");

		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Contraseña");

		ImageView googleImageView = new ImageView();
		try {
			Image googleImage = new Image(getClass().getResource("/icons/google.png").toExternalForm());
			googleImageView.setImage(googleImage);
			googleImageView.setFitHeight(32);
			googleImageView.setPreserveRatio(true);
		} catch (Exception e) {
			System.err.println("No se pudo cargar la imagen de Google: " + e.getMessage());
		}

		// Aquí añadimos el evento click para login con Google
		googleImageView.setOnMouseClicked(e -> {
			try {
				String idToken = FirebaseAuthService.signInWithGoogle();
				System.out.println("Login con Google exitoso. ID Token: " + idToken);
				dialog.close();
				updateUIAfterLogin();
			} catch (Exception ex) {
				ex.printStackTrace(); // Esto mostrará la excepción completa en la consola
				showError("No se pudo iniciar sesión con Google", ex.getMessage());

			}
		});

		Button aceptarBtn = new Button("Aceptar");
		Button cancelarBtn = new Button("Cancelar");

		aceptarBtn.setOnAction(e -> {
			String email = emailField.getText();
			String password = passwordField.getText();

			try {
				String idToken = FirebaseAuthService.signInWithEmailAndPassword(email, password);
				System.out.println("Login exitoso. ID Token: " + idToken);
				dialog.close();
				updateUIAfterLogin();
			} catch (Exception ex) {
				showError("No se pudo iniciar sesión", ex.getMessage());
			}
		});

		cancelarBtn.setOnAction(e -> dialog.close());

		HBox buttonBox = new HBox(20, aceptarBtn, cancelarBtn);
		buttonBox.setAlignment(Pos.CENTER);
		VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20));
		layout.setAlignment(Pos.CENTER);

		layout.getChildren().addAll(new Label("Correo electrónico:"), emailField, new Label("Contraseña:"),
				passwordField, new Label("O inicia sesión con Google:"), googleImageView, buttonBox);

		dialog.setScene(new Scene(layout, 300, 400));
		dialog.showAndWait();
	}

	private void showRegisterDialog() {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setTitle("Registrarse");

		TextField emailField = new TextField();
		emailField.setPromptText("Correo electrónico");

		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Contraseña");

		PasswordField repeatPasswordField = new PasswordField();
		repeatPasswordField.setPromptText("Repetir contraseña");

		Button aceptarBtn = new Button("Aceptar");
		Button cancelarBtn = new Button("Cancelar");

		aceptarBtn.setOnAction(e -> {
			String email = emailField.getText();
			String password = passwordField.getText();
			String repeatPassword = repeatPasswordField.getText();

			if (!password.equals(repeatPassword)) {
				showError("Las contraseñas no coinciden", "Por favor, vuelve a ingresarlas.");
				return;
			}

			try {
				String idToken = FirebaseAuthService.createUserWithEmailAndPassword(email, password);
				System.out.println("Registro exitoso. ID Token: " + idToken);
				dialog.close();
				updateUIAfterLogin();
			} catch (Exception ex) {
				showError("No se pudo registrar el usuario", ex.getMessage());
			}
		});

		cancelarBtn.setOnAction(e -> dialog.close());

		HBox buttonBox = new HBox(20, aceptarBtn, cancelarBtn);
		buttonBox.setAlignment(Pos.CENTER);
		VBox.setMargin(buttonBox, new Insets(20, 0, 0, 0));

		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20));
		layout.setAlignment(Pos.CENTER);

		layout.getChildren().addAll(new Label("Correo electrónico:"), emailField, new Label("Contraseña:"),
				passwordField, new Label("Repetir contraseña:"), repeatPasswordField, buttonBox);

		dialog.setScene(new Scene(layout, 300, 400));
		dialog.showAndWait();
	}

	private void updateUIAfterLogin() {
		// Buscar el índice de loginButton en la ToolBar
		int loginIndex = toolBar.getItems().indexOf(loginButton);

		// Eliminar login y register
		toolBar.getItems().removeAll(loginButton, registerButton);

		// Insertar el botón Perfil en la posición de Login
		if (loginIndex >= 0) {
			toolBar.getItems().add(loginIndex, perfilButton);
		} else {
			toolBar.getItems().add(0, perfilButton); // Fallback
		}
	}
	
	private void showPerfilDialog() {
		Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setTitle("Perfil");

		// Primer ScrollView con título y botón alineado a la derecha
		Label localesLabel = new Label("Personajes locales");
		Button subirBtn = new Button("Subir");
		Region spacer1 = new Region();
		HBox.setHgrow(spacer1, Priority.ALWAYS);
		HBox localesHeader = new HBox(10, localesLabel, spacer1, subirBtn);
		localesHeader.setAlignment(Pos.CENTER_LEFT);

		// Lista de personajes locales
		ListView<String> personajesLocalesList = new ListView<>();
		personajesLocalesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		personajesLocalesList.setPrefHeight(150);

		// Cargar datos dinámicamente
		MundoCRUD mCrud = new MundoCRUD();
		for (Mundo mundo : mCrud.fetchAllMundos()) {
			for (Personaje personaje : mundo.getPersonajes()) {
				String muestra = personaje.getNombre() + " (" + mundo.getNombre() + ")";
				personajesLocalesList.getItems().add(muestra);
			}
		}

		ScrollPane scrollLocales = new ScrollPane(personajesLocalesList);
		scrollLocales.setFitToWidth(true);
		VBox localesSection = new VBox(5, localesHeader, scrollLocales);

		// Segundo ScrollView con título y botón alineado a la derecha
		Label remotosLabel = new Label("Personajes remotos");
		Button bajarBtn = new Button("Bajar");
		Region spacer2 = new Region();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		HBox remotosHeader = new HBox(10, remotosLabel, spacer2, bajarBtn);
		remotosHeader.setAlignment(Pos.CENTER_LEFT);

		VBox personajesRemotosContent = new VBox();
		personajesRemotosContent.setSpacing(5);
		personajesRemotosContent.setPadding(new Insets(5));
		// Aquí también puedes añadir contenido real si es necesario

		ScrollPane scrollRemotos = new ScrollPane(personajesRemotosContent);
		scrollRemotos.setFitToWidth(true);
		VBox remotosSection = new VBox(5, remotosHeader, scrollRemotos);

		// Botones al final
		Button volverBtn = new Button("Volver");
		Button cerrarSesionBtn = new Button("Cerrar sesión");

		volverBtn.setOnAction(e -> dialog.close());

		cerrarSesionBtn.setOnAction(e -> {
			dialog.close();
			// Simula cierre de sesión
			toolBar.getItems().remove(perfilButton);
			toolBar.getItems().add(0, loginButton);
			toolBar.getItems().add(1, registerButton);
		});

		HBox actionButtons = new HBox(20, volverBtn, cerrarSesionBtn);
		actionButtons.setAlignment(Pos.CENTER);

		VBox layout = new VBox(15, localesSection, remotosSection, actionButtons);
		layout.setPadding(new Insets(20));
		layout.setPrefSize(400, 500);

		dialog.setScene(new Scene(layout));
		dialog.showAndWait();
	}

	private void showError(String header, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(header);
		alert.setContentText(message);
		alert.showAndWait();
	}

	public void setDesktopController(DesktopController desktopController) {
		this.desktopController = desktopController;
	}
	
	
}
