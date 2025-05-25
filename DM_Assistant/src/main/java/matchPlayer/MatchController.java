package matchPlayer;

import java.util.*;
import clases_estadisticas.*;
import clases_partida.Criatura;
import clases_partida.Escena;
import clases_personaje.Personaje;
import designerView.ElementoVisualData;
import designerView.ElementoVisualType;
import designerView.Tablero;
import designerView.TableroView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Rectangle2D;
import javafx.stage.WindowEvent;

public class MatchController {

    private Escena escena;
    private TreeMap<Double, Object> ordenTurnos = new TreeMap<>(Collections.reverseOrder());

    public MatchController(Escena escena) {
        this.escena = escena;
        inicializarTurnos();
    }

    public void mostrarVistaTurnos(Stage stageTurnos) {
        // Crear TableroView
        Stage stageTablero = new Stage();
        TableroView tableroView = new TableroView(Tablero.desdeJson(escena.getTableroJson()));
        tableroView.inicializar();
        Scene tableroScene = new Scene(tableroView.getRoot(), 1024, 768);
        stageTablero.setScene(tableroScene);
        stageTablero.setTitle("Tablero de Batalla");

        // Obtener personaje con mayor iniciativa
        ArrayList<Object> listaTurnosOrdenada = new ArrayList<>(ordenTurnos.values());

        // Crear ActionController
        ActionController actionController = new ActionController(escena.getReino().getNacion().getMundo());
        
        // Crear MatchInfoView con ArrayList ordenado
        MatchInfoView matchInfoView = new MatchInfoView(listaTurnosOrdenada);
        matchInfoView.iniciar(stageTurnos);
        
        // Crear ActionView
        Stage stageAction = new Stage();
        ActionView actionView = new ActionView(tableroView, matchInfoView, actionController);
        actionView.iniciar(stageAction);


        // Obtener tamaño de pantalla
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        double vistaTurnosWidth = 300;
        double vistaTurnosHeight = 768;
        double tableroWidth = 1024;
        double tableroHeight = 768;
        double actionViewWidth = 300;
        double actionViewHeight = 768;

        double centerY = (screenHeight - tableroHeight) / 2;
        double tableroX = (screenWidth - tableroWidth) / 2;
        double vistaTurnosX = tableroX - vistaTurnosWidth;
        double actionViewX = tableroX + tableroWidth;

        // Posicionar y mostrar ventanas
        stageTurnos.setX(vistaTurnosX);
        stageTurnos.setY(centerY);
        stageTurnos.setWidth(vistaTurnosWidth);
        stageTurnos.setHeight(vistaTurnosHeight);
        stageTurnos.show();

        stageTablero.setX(tableroX);
        stageTablero.setY(centerY);
        stageTablero.setWidth(tableroWidth);
        stageTablero.setHeight(tableroHeight);
        stageTablero.show();

        stageAction.setX(actionViewX);
        stageAction.setY(centerY);
        stageAction.setWidth(actionViewWidth);
        stageAction.setHeight(actionViewHeight);
        stageAction.show();

        // Vincular el cierre de una ventana con el cierre de las demás
        stageTurnos.setOnCloseRequest((WindowEvent e) -> {
            stageTablero.close();
            stageAction.close();
        });

        stageTablero.setOnCloseRequest((WindowEvent e) -> {
            stageTurnos.close();
            stageAction.close();
        });

        stageAction.setOnCloseRequest((WindowEvent e) -> {
            stageTurnos.close();
            stageTablero.close();
        });
    }

    private void inicializarTurnos() {
        String tableroJson = escena.getTableroJson();
        Tablero tablero = Tablero.desdeJson(tableroJson);
        HashMap<UUID, ElementoVisualData> elementos = tablero.getElementosColocados();

        Random random = new Random();

        for (ElementoVisualData data : elementos.values()) {
            ElementoVisualType tipo = data.getTipo();
            int id = data.getId();
            Object obj = data.BuscarObjeto(tipo, id);

            if (obj instanceof Personaje pj) {
                EstadoPjJson estado = EstadoPjJson.desdeJson(pj.getEstadoJson());
                int iniciativa = estado.getStatBase().getStatGeneral().getIniciativa();
                double iniciativaTotal = iniciativa + (random.nextInt(6) + 1);

                // Para evitar colisiones en clave, si clave ya existe, sumamos 1 (o +0.1 si usas Double)
                // Aquí un pequeño loop para encontrar clave única:
                double claveUnica = iniciativaTotal;
                while (ordenTurnos.containsKey(claveUnica)) {
                    claveUnica += 0.1;
                }
                ordenTurnos.put(claveUnica, pj);

            } else if (obj instanceof Criatura) {
                System.out.println("Se detecta objeto criatura que es ignorado temporalmente");
            }
        }
    }

    public TreeMap<Double, Object> getOrdenTurnos() {
        return ordenTurnos;
    }
}
