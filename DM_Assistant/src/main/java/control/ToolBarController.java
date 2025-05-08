package control;

import views.ToolBarView;

public class ToolBarController {

    private final ToolBarView toolBarView;

    public ToolBarController(ToolBarView toolBarView) {
        this.toolBarView = toolBarView;
        initListeners();
    }

    private void initListeners() {
        toolBarView.getReiniciarButton().setOnAction(event -> reiniciarAplicacion());
    }

    private void reiniciarAplicacion() {
        try {
            String javaBin = System.getProperty("java.home") + "/bin/java";
            String jarPath = new java.io.File(
                ToolBarController.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI()).getPath();

            ProcessBuilder builder = new ProcessBuilder(javaBin, "-jar", jarPath);
            builder.start();

            System.exit(0); // Termina la instancia actual
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ToolBarView getView() {
        return toolBarView;
    }
}
