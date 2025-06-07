package views;

import clases_partida.Mundo;
import control.CreationCriaturaController;
import control.CreationPersonajeController;
import control.CreationUbicacionController;
import control.ExplorerController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreationWindow extends Stage {

    private CreationViewType type;
    private ExplorerController explorerController;
    private Mundo mundo;
    private TreeItem<Object> item;

    public CreationWindow(CreationViewType type, ExplorerController explorerController, Mundo mundo,
                          TreeItem<Object> item) {
        super();
        this.type = type;
        this.explorerController = explorerController;
        this.mundo = mundo;
        this.item = item;
        crearVentana();
    }

    public CreationWindow(CreationViewType type, ExplorerController explorerController, Mundo mundo) {
        super();
        this.type = type;
        this.explorerController = explorerController;
        this.mundo = mundo;
        crearVentana();
    }

    public void crearVentana() {
        this.getIcons().add(new Image(getClass().getResourceAsStream("/icons/app_icon.png")));
        setTitle("Nuevo " + type.name().toLowerCase());

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 650);

        // Header con imagen de fondo
        StackPane headerPane = new StackPane();
        headerPane.setPrefHeight(100); // Ajusta la altura si es necesario

        String headerImagePath = switch (type) {
            case PERSONAJE -> "/headers/personaje_header.png";
            case NPC -> "/headers/npc_header.png";
            case CRIATURA -> "/headers/criatura_header.png";
            case UBICACION -> "/headers/ubicacion_header.png";
            default -> null;
        };

        if (headerImagePath != null) {
            Image headerImage = new Image(getClass().getResourceAsStream(headerImagePath));
            
            headerPane.setPrefHeight(headerImage.getHeight());

            BackgroundImage backgroundImage = new BackgroundImage(
                headerImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(
                    headerImage.getWidth(), 
                    headerImage.getHeight(), 
                    false, false, false, false
                )
            );
            headerPane.setBackground(new Background(backgroundImage));
        }


        root.setTop(headerPane);

        StackPane contenidoConPadding = new StackPane();
        contenidoConPadding.setPadding(new Insets(15));

        switch (type) {
            case PERSONAJE -> {
                boolean npcPj = false;
                CreationPersonajeView viewPj = new CreationPersonajeView();
                new CreationPersonajeController(this, viewPj, explorerController, mundo, npcPj);
                contenidoConPadding.getChildren().add(viewPj);
            }
            case NPC -> {
                boolean npcNpc = true;
                CreationPersonajeView viewNpc = new CreationPersonajeView();
                new CreationPersonajeController(this, viewNpc, explorerController, mundo, npcNpc);
                contenidoConPadding.getChildren().add(viewNpc);
            }
            case CRIATURA -> {
                CreationCriaturaView viewCriatura = new CreationCriaturaView();
                new CreationCriaturaController(this, viewCriatura, explorerController, mundo);
                contenidoConPadding.getChildren().add(viewCriatura);
            }
            case UBICACION -> {
                CreationUbicacionView viewUbicacion = new CreationUbicacionView(mundo);
                new CreationUbicacionController(this, viewUbicacion, explorerController, mundo, item);
                contenidoConPadding.getChildren().add(viewUbicacion);
            }
        }

        root.setCenter(contenidoConPadding);

        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }

    public TreeItem<Object> getItem() {
        return item;
    }
}
