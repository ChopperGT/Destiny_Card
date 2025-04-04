package Code.Menu;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.geometry.Pos;  // Import de Pos


public class Liste_Carte {
    private VBox mainMenu;
    private VBox listeCarteMenu;

    public Liste_Carte(VBox mainMenu) {
        this.mainMenu = mainMenu;
    }

    public VBox createListeCarteMenu(Font customFont) {
        listeCarteMenu = new VBox(20);

        Text title = new Text("Liste des Cartes");
        title.setFont(customFont);
        title.setStyle("-fx-font-size: 30px; -fx-fill: black;");

        Button backButton = new Button("Retour");
        backButton.setFont(customFont);
        backButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");

        backButton.setOnAction(event -> {
            listeCarteMenu.setVisible(false);
            mainMenu.setVisible(true);
        });

        listeCarteMenu.getChildren().addAll(title, backButton);
        listeCarteMenu.setStyle("-fx-alignment: center;");

        return listeCarteMenu;
    }
}