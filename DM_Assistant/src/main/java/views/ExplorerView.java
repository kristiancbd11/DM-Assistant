package views;

import java.io.InputStream;

import clases_partida.Mundo;
import clases_partida.Nacion;
import clases_partida.Reino;
import clases_partida.Tienda;
import clases_partida.Escena;
import clases_partida.Criatura;
import clases_personaje.Personaje;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ExplorerView {
	private VBox explorer;
	private TreeItem<Object> raizGeneral;
	private TreeItem<Object> raizMundos;
	private TreeItem<Object> raizRogArchive;
	private TreeView<Object> arbolMundos;

	public ExplorerView() {
		// Crear raíz principal
		raizGeneral = new TreeItem<>("DM Assistant");
		raizGeneral.setExpanded(true);

		// 🌍 Mundos
		raizMundos = new TreeItem<>("Mundos");
		raizMundos.setExpanded(true);

		// 🗄 ROG Archive
		raizRogArchive = new TreeItem<>("🗄 ROG Archive");
		raizRogArchive.setExpanded(true);

		raizGeneral.getChildren().addAll(raizMundos, raizRogArchive);

		// Crear TreeView con raíz "DM Assistant"
		arbolMundos = new TreeView<>(raizGeneral);
		arbolMundos.setShowRoot(false);
		arbolMundos.setStyle("""
				    -fx-background-color: transparent;
				    -fx-background-insets: 0;
				    -fx-padding: 0;
				    -fx-border-width: 0;
				""");

		arbolMundos.setCellFactory(tv -> new TreeCell<Object>() {
			private final VBox cellContent = new VBox();
			private final HBox nodeBox = new HBox();
			private final Separator separator = new Separator();

			{
				cellContent.getChildren().addAll(nodeBox, separator);
			}

			@Override
			protected void updateItem(Object item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
					setGraphic(null);
				} else {
					nodeBox.getChildren().clear();

					TreeItem<Object> thisItem = getTreeItem();
					TreeItem<Object> parentItem = thisItem != null ? thisItem.getParent() : null;
					String parentText = parentItem != null && parentItem.getValue() != null
							? parentItem.getValue().toString()
							: "";

					String iconPath = null;

					if (thisItem == raizMundos) {
						iconPath = "/icons/cosmos.png";
					} else if (item instanceof Mundo) {
						iconPath = "/icons/mundo.png";
					} else if (item instanceof Nacion) {
						iconPath = "/icons/ubicacion.png";
					} else if (item instanceof Personaje && "Personajes".equals(parentText)) {
						iconPath = "/icons/personaje.png";
					} else if (item instanceof Personaje && "Npc".equals(parentText)) {
						iconPath = "/icons/npc.png";
					} else if (item instanceof Criatura) {
						iconPath = "/icons/criatura.png";
					} else if (item instanceof Reino) {
						iconPath = "/icons/reino.png";
					} else if (item instanceof Tienda) {
						iconPath = "/icons/tienda.png";
					} else if (item instanceof Escena) {
						iconPath = "/icons/escena.png";
					}

					if (iconPath != null) {
						InputStream iconStream = getClass().getResourceAsStream(iconPath);
						if (iconStream != null) {
							Image icon = new Image(iconStream);
							ImageView iconView = new ImageView(icon);
							iconView.setFitWidth(15);
							iconView.setFitHeight(15);
							nodeBox.getChildren().addAll(iconView, new Label(" "));
						} else {
							System.err.println("No se encontró el recurso: " + iconPath);
						}
					}

					nodeBox.getChildren().add(new Text(item.toString()));
					nodeBox.setSpacing(5);
					setGraphic(cellContent);
				}
			}

		});

		VBox.setVgrow(arbolMundos, Priority.ALWAYS);
		explorer = new VBox(arbolMundos);
		explorer.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 10;");
	}

	public VBox getExplorerPane() {
		return explorer;
	}

	public TreeView<Object> getTreeView() {
		return arbolMundos;
	}

	public TreeItem<Object> getRootItem() {
		return raizMundos;
	}

	public TreeItem<Object> getRogArchiveRoot() {
		return raizRogArchive;
	}
}
