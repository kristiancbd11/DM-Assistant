package views;

import clases_partida.Escena;
import clases_partida.Tienda;
import clases_partida.TiendaObjeto;
import clases_partida.Ubicacion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class DesktopUbicacionView {

    private Ubicacion ubicacion;

    public DesktopUbicacionView(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public StackPane getVista() {
        if (ubicacion instanceof Tienda tienda) {
            VBox tiendaVista = new VBox(15);
            tiendaVista.setPadding(new Insets(15));
            tiendaVista.setAlignment(Pos.TOP_CENTER);

            Label titulo = new Label("Vista de Tienda: " + tienda.getNombre());
            titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            tiendaVista.getChildren().add(titulo);

            GridPane grid = new GridPane();
            grid.setHgap(20);
            grid.setVgap(20);
            grid.setAlignment(Pos.CENTER);

            int columnas = 3;
            int row = 0;
            int col = 0;

            for (TiendaObjeto tiendaObjeto : tienda.getObjetosConCantidad()) {
                VBox objetoBox = new VBox(5);
                objetoBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
                objetoBox.setAlignment(Pos.CENTER);

                // Nombre del objeto
                Label nombreLabel = new Label(tiendaObjeto.getObjeto().getNombre());
                nombreLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

                // Imagen del objeto
                ImageView imagenObjeto;
                try {
                    Image img = new Image(getClass().getResourceAsStream("/icons/default_object.png"));
                    imagenObjeto = new ImageView(img);
                    imagenObjeto.setFitWidth(64);
                    imagenObjeto.setFitHeight(64);
                } catch (Exception e) {
                    imagenObjeto = new ImageView(); // fallback vacío
                }

                // Detalles: peso - rareza - valor
                HBox detallesBox = new HBox(20);
                detallesBox.setAlignment(Pos.CENTER);
                detallesBox.getChildren().addAll(
                        new Label("Peso: " + tiendaObjeto.getObjeto().getPeso()),
                        new Label("Rareza: " + tiendaObjeto.getObjeto().getRareza()),
                        new Label("Valor: " + tiendaObjeto.getObjeto().getValor())
                );

                // Botón de compra
                Button comprarBtn = new Button("Comprar");
                comprarBtn.setOnAction(e -> {
                    if (tienda.getFondos() >= tiendaObjeto.getObjeto().getValor()) {
                        tienda.setFondos(tienda.getFondos() - tiendaObjeto.getObjeto().getValor());
                        comprarBtn.setDisable(true);
                        comprarBtn.setText("Comprado");
                    } else {
                        comprarBtn.setText("Fondos insuficientes");
                        comprarBtn.setDisable(true);
                    }
                });

                objetoBox.getChildren().addAll(nombreLabel, imagenObjeto, detallesBox, comprarBtn);
                grid.add(objetoBox, col, row);

                col++;
                if (col >= columnas) {
                    col = 0;
                    row++;
                }
            }

            // Añadir un único botón al final para "Añadir objeto"
            VBox anadirBox = new VBox(5);
            anadirBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
            anadirBox.setAlignment(Pos.CENTER);

            ImageView imagenAnadir;
            try {
                Image img = new Image(getClass().getResourceAsStream("/icons/icono_aniadir.png"));
                imagenAnadir = new ImageView(img);
                imagenAnadir.setFitWidth(64);
                imagenAnadir.setFitHeight(64);
            } catch (Exception e) {
                imagenAnadir = new ImageView(); // fallback vacío
            }

            Button anadirBtn = new Button();
            anadirBtn.setGraphic(imagenAnadir);
            anadirBtn.setStyle("-fx-background-color: transparent;");
            anadirBtn.setOnAction(e -> {
                System.out.println("Acción de añadir objeto.");
                // Aquí puedes abrir una nueva ventana o lanzar un formulario
            });

            anadirBox.getChildren().add(anadirBtn);
            grid.add(anadirBox, col, row);

            tiendaVista.getChildren().add(grid);
            return new StackPane(tiendaVista);
        } else {
            Escena escena = (Escena) ubicacion;
            StackPane pane = new StackPane(new Label("Vista de Escena: " + escena.getNombre()));
            return pane;
        }
    }
}
