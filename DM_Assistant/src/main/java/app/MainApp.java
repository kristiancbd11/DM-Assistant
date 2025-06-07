package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import control.*;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import views.DesktopView;
import views.ExplorerView;
import views.InfoView;
import views.LoadingView;
import views.MainView;
import views.TaskBarView;
import views.ToolBarView;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/app_icon.png")));

        // Crear MainView
        MainView mainView = new MainView();

        // Mostrar pantalla de carga mientras se inicializa
        mainView.setCenter(new LoadingView());

        // Configurar escena y ventana
        Scene scene = new Scene(mainView.getRoot(), 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DM Assistant");
        primaryStage.setMaximized(true);
        primaryStage.show();

        // Cargar datos y construir componentes en segundo plano
        Task<Void> loadDataTask = new Task<>() {
            @Override
            protected Void call() {
                simulateHibernateLoading(); // Simula la carga (Hibernate o similar)
                return null;
            }
        };

        loadDataTask.setOnSucceeded(event -> {
            // Crear vistas
            ToolBarView toolBarView = new ToolBarView();
            ExplorerView explorerView = new ExplorerView();
            DesktopView desktopView = new DesktopView();
            InfoView infoView = new InfoView();
            TaskBarView taskBarView = new TaskBarView();

            // Crear controladores
            DesktopController desktopController = new DesktopController(desktopView);
            toolBarView.setDesktopController(desktopController);
            InfoController infoController = new InfoController(infoView);
            ExplorerController explorerController = new ExplorerController(explorerView, infoController, desktopController);
            desktopController.setExplorerController(explorerController);
            ToolBarController toolBarController = new ToolBarController(toolBarView);
            TaskBarController taskBarController = new TaskBarController(taskBarView);

            try {
            	InputStream serviceAccount = getClass().getResourceAsStream("/firebase/dm-assistant-c8050-firebase-adminsdk-fbsvc-70876fba75.json");
            	if (serviceAccount == null) {
                    throw new FileNotFoundException("No se encontró el archivo JSON en /firebase/");
                }
            	
                FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

                FirebaseApp.initializeApp(options);
            } catch (IOException e) {
            	e.printStackTrace();
            }
            
            // Mostrar el contenido principal en la UI
            mainView.showMainContent(toolBarView, explorerView, desktopView, infoView, taskBarView);
        });

        new Thread(loadDataTask).start();
    }

    private void simulateHibernateLoading() {
        try {
            Thread.sleep(3000); // Simula carga de 3 segundos
            // Aquí debería ir tu lógica de carga real de datos (Hibernate, etc.)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
