package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.function.BiConsumer;

public class ImageSelectorPopup extends Stage {
    private static final int IMAGE_SIZE = 64;
    private static final int COLUMNS = 5;

    public ImageSelectorPopup(String imageDirectoryPath, BiConsumer<Image, String> onImageSelected) {
        setTitle("Selecciona un token");
        initStyle(StageStyle.UTILITY);

        TilePane tilePane = new TilePane();
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.setPadding(new Insets(10));
        tilePane.setPrefColumns(COLUMNS);
        tilePane.setAlignment(Pos.CENTER);

        File dir = new File(imageDirectoryPath);
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles((f, name) -> name.endsWith(".png") || name.endsWith(".jpg"))) {
                Image img = new Image(file.toURI().toString(), IMAGE_SIZE, IMAGE_SIZE, true, true);
                ImageView imgView = new ImageView(img);
                imgView.setFitWidth(IMAGE_SIZE);
                imgView.setFitHeight(IMAGE_SIZE);

                imgView.setOnMouseClicked(e -> {
                    onImageSelected.accept(img, file.getName()); // <-- Aquí se pasa el nombre del archivo
                    close();
                });

                tilePane.getChildren().add(imgView);
            }
        }

        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);
        Scene scene = new Scene(scrollPane, 400, 300);
        setScene(scene);
    }
}
