package designerView;

import clases_partida.Mundo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaletaView {

    private final Mundo mundo;
    private final TableroView tableroView;
    private final Map<String, Object> objetosPorNombre = new HashMap<>();
    private List<CategoriaPaleta> categorias = new ArrayList<>();
    private Button guardarButton = new Button("Guardad");

    public PaletaView(Mundo mundo, TableroView tableroView) {
        this.mundo = mundo;
        this.tableroView = tableroView;
    }

    public Stage crearVentana(double x, double y) {
        VBox contenido = new VBox(10);
        contenido.setPadding(new Insets(10));
        contenido.setAlignment(Pos.TOP_CENTER);

        TitledPane vegetacionPane = crearCategoria("Bosque", fetchElementosAux("/tablero/forest"));
        TitledPane cuevaPane = crearCategoria("Cueva", fetchElementosAux("/tablero/cave"));
        TitledPane decoracionPane = crearCategoria("Decoración", fetchElementosAux("/tablero/decoration"));

        TitledPane personajesPane = crearCategoria("Personajes", mundo.getPersonajes());
        TitledPane npcsPane = crearCategoria("NPCs", mundo.getNpcs());
        TitledPane criaturasPane = crearCategoria("Criaturas", mundo.getCriaturas());
        TitledPane tablerosPane = crearPaneTableros();

        Accordion accordion = new Accordion(vegetacionPane, cuevaPane, decoracionPane, personajesPane, npcsPane,
                criaturasPane, tablerosPane);

        contenido.getChildren().add(accordion);

        // Añadir el botón al VBox principal
        contenido.getChildren().add(guardarButton);

        Stage stage = new Stage();
        stage.setTitle("Paleta de Elementos");
        stage.setScene(new Scene(contenido, 200, 600));
        stage.setX(x);
        stage.setY(y);
        stage.setResizable(false);
        stage.show();
        return stage;
    }

    private TitledPane crearCategoria(String titulo, List<?> elementos) {
        CategoriaPaleta categoria = new CategoriaPaleta(titulo, elementos);
        categorias.add(categoria);
        objetosPorNombre.putAll(categoria.getObjetosPorNombre());
        return new TitledPane(titulo, categoria.getContenedor());
    }

    public Object getObjetoPorNombre(String nombre) {
        return objetosPorNombre.get(nombre);
    }

    public void removerElemento(String nombre) {
        for (CategoriaPaleta categoria : categorias) {
            Object obj = categoria.getObjetosPorNombre().get(nombre);
            if (obj != null && obj instanceof clases_personaje.Personaje) {
                // Buscar el nodo en el GridPane y eliminarlo
                categoria.getContenedor().getChildren().removeIf(node -> {
                    if (node instanceof VBox vbox) {
                        Label label = (Label) vbox.getChildren().get(0);
                        return label.getText().equals(nombre);
                    }
                    return false;	
                });
                break;
            }
        }
    }

    public void reinsertarElemento(Object obj) {
        for (CategoriaPaleta categoria : categorias) {
            if (categoria.getObjetosPorNombre().containsValue(obj)) {
                categoria.reinsertarObjeto(obj);
                break;
            }
        }
    }

    private TitledPane crearPaneTableros() {
        VBox contenedor = new VBox(5);
        contenedor.setPadding(new Insets(10));

        try {
            var path = getClass().getResource("/tablero/grounds/");
            if (path != null) {
                var folder = new java.io.File(path.toURI());
                var archivos = folder.listFiles((dir, name) -> name.endsWith(".png") || name.endsWith(".jpg"));

                if (archivos != null) {
                    for (var archivo : archivos) {
                        String nombre = archivo.getName();
                        Label label = new Label(nombre);
                        label.setOnMouseClicked(e -> {
                            String rutaCss = String.format(
                                    "-fx-background-image: url('/tablero/grounds/%s'); -fx-background-size: %fpx %fpx;",
                                    nombre, tableroView.getWidth(), tableroView.getHeight());
                            tableroView.actualizarFondo(rutaCss);
                        });
                        contenedor.getChildren().add(label);
                    }
                }
            }
        } catch (Exception e) {
            contenedor.getChildren().add(new Label("Error al cargar tableros."));
            e.printStackTrace();
        }

        return new TitledPane("Tableros", contenedor);
    }

    public ArrayList<ElementoAux> fetchElementosAux(String path) {
        ArrayList<ElementoAux> listAux = new ArrayList<ElementoAux>();
        try {
            // Obtener la URL del recurso
            java.net.URL resourceUrl = getClass().getResource(path);
            if (resourceUrl != null) {
                File folder = new File(resourceUrl.toURI());
                File[] listOfFiles = folder.listFiles();

                if (listOfFiles != null) {
                    for (File file : listOfFiles) {
                        if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
                            String token = file.getName(); // Nombre completo del archivo
                            listAux.add(new ElementoAux(token, path));
                        }
                    }
                }
            } else {
                System.err.println("No se encontró el recurso: " + path);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar los recursos: " + e.getMessage());
        }

        return listAux;
    }

    public List<CategoriaPaleta> getCategorias() {
        return categorias;
    }

	public Button getGuardarButton() {
		return guardarButton;
	}
}
