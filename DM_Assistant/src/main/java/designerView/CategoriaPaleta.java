package designerView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaPaleta {

    private final String titulo;
    private final List<?> elementos;
    private final Map<Integer, Boolean> estadoColocados = new HashMap<>();
    private final Map<String, Object> objetosPorNombre = new HashMap<>();
    private final GridPane contenedor = new GridPane(); // Usamos GridPane en lugar de VBox

    public CategoriaPaleta(String titulo, List<?> elementos) {
        this.titulo = titulo;
        this.elementos = elementos;
        inicializar();
    }

    private void inicializar() {
        // Ajustamos el padding y la alineación del contenedor
        contenedor.setPadding(new Insets(5));
        contenedor.setHgap(10);  // Espacio entre las columnas
        contenedor.setVgap(10);  // Espacio entre las filas
        contenedor.setAlignment(Pos.TOP_CENTER);

        // Recorremos los elementos y los agregamos al contenedor
        int fila = 0, columna = 0;
        for (Object obj : elementos) {
            String nombre = obj.toString();
            int id = obtenerIdDesdeObjeto(obj);
            objetosPorNombre.put(nombre, obj);
            estadoColocados.put(id, false);

            // Obtenemos el nodo visual de ElementoVisual
            ElementoVisual ev = new ElementoVisual(obj, 0, 0);

            // Agregamos el nodo visual al contenedor en la posición adecuada
            contenedor.add(ev.getNodoVisual(), columna, fila);

            // Aumentamos la columna y fila, respetando el número de columnas por fila
            columna++;
            if (columna >= 4) {  // Si hay 4 elementos por fila, pasamos a la siguiente fila
                columna = 0;
                fila++;
            }

            // Soporte para arrastrar (seguimos usando el mismo sistema)
            ev.getNodoVisual().setOnDragDetected(event -> {
                var db = ev.getNodoVisual().startDragAndDrop(javafx.scene.input.TransferMode.COPY);
                var content = new javafx.scene.input.ClipboardContent();

                // Guardar el nombre para búsqueda
                content.putString(ev.getNombre());

                db.setContent(content);
                db.setDragView(ev.getNodoVisual().snapshot(null, null));
                event.consume();
            });
        }
    }

    private int obtenerIdDesdeObjeto(Object obj) {
        try {
            var method = obj.getClass().getMethod("getId");
            return (int) method.invoke(obj);
        } catch (Exception e) {
            System.err.println("Error al obtener ID de objeto: " + e.getMessage());
            return -1;
        }
    }
    
    public void reinsertarObjeto(Object obj) {
        String nombre = obj.toString();
        int id = obtenerIdDesdeObjeto(obj);

        // Solo volver a insertar si estaba marcado como colocado
        if (estadoColocados.containsKey(id) && estadoColocados.get(id)) {
            estadoColocados.put(id, false);  // Ya no está colocado

            // Crear nuevo ElementoVisual
            ElementoVisual ev = new ElementoVisual(obj, 0, 0);
            VBox nodo = ev.getNodoVisual();

            // Hacerlo arrastrable
            nodo.setOnDragDetected(event -> {
                var db = nodo.startDragAndDrop(javafx.scene.input.TransferMode.COPY);
                var content = new javafx.scene.input.ClipboardContent();
                content.putString(nombre);
                db.setContent(content);
                db.setDragView(nodo.snapshot(null, null));
                event.consume();
            });

            // Calcular posición libre en el GridPane (por filas y columnas)
            int total = contenedor.getChildren().size();
            int fila = total / 4;
            int columna = total % 4;

            contenedor.add(nodo, columna, fila);
        }
    }

    public GridPane getContenedor() {
        return contenedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public Map<Integer, Boolean> getEstadoColocados() {
        return estadoColocados;
    }

    public Map<String, Object> getObjetosPorNombre() {
        return objetosPorNombre;
    }
}
