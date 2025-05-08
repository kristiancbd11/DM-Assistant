// InfoView.java
package views;

import clases_partida.Mundo;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InfoView {
    private Label infoLabel;
    private VBox container;
    private VBox infoBox;

    public InfoView() {
        infoLabel = new Label("📁 InfoView");
        infoBox = new VBox();
        
        container = new VBox(infoLabel, infoBox);
        container.setMinWidth(100);
        container.setStyle("-fx-padding: 10; -fx-background-color: #FFFFFF;");
    }
    
    public void setContenido(VBox nuevoContenido) {
    	infoBox.getChildren().setAll(nuevoContenido.getChildren());
    }

    public VBox showInfoView() {
        return infoBox;
    }

	public Label getInfoLabel() {
		return infoLabel;
	}

	public void setInfoLabel(Label infoLabel) {
		this.infoLabel = infoLabel;
	}

	public VBox getInfoBox() {
		return infoBox;
	}

	public void setInfoBox(VBox infoBox) {
		this.infoBox = infoBox;
	}
    
    
}
