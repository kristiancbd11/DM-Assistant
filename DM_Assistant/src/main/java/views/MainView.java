package views;

import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;

public class MainView {
    private BorderPane root;

    public MainView() {
        root = new BorderPane();
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setCenter(Node node) {
        root.setCenter(node);
    }

    public void setTop(Node node) {
        root.setTop(node);
    }

    public void setBottom(Node node) {
        root.setBottom(node);
    }

    public void setLeft(Node node) {
        root.setLeft(node);
    }

    public void setRight(Node node) {
        root.setRight(node);
    }

    public void showMainContent(
    	    ToolBarView toolBarView,
    	    ExplorerView explorerView,
    	    DesktopView desktopView,
    	    InfoView infoView,
    	    TaskBarView taskBarView
    	) {
    	    // Crear el SplitPane correctamente con tres nodos
    	    SplitPane splitPane = new SplitPane();
    	    splitPane.getItems().addAll(explorerView.getExplorerPane(), desktopView.getDesktop(), infoView.getInfoBox());
    	    splitPane.setDividerPositions(0.2, 0.8);

    	    // Establecer solo el SplitPane en el centro
    	    setTop(toolBarView.getToolbar());
    	    setCenter(splitPane);
    	    setBottom(taskBarView.getTaskbar());

    	    // NO pongas setLeft() ni setRight() ya que ya están dentro del splitPane
    	}

}
