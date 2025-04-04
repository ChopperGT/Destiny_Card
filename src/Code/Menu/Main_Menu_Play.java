package Code.Menu;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

public class Main_Menu_Play {
    private VBox mainMenu;  // Stocke le menu principal
    private VBox playMenu;  // Stocke le menu Play

    // Constructeur qui prend le menu principal en paramètre
    public Main_Menu_Play(VBox mainMenu) {
        this.mainMenu = mainMenu;
    }

    // Méthode qui crée le menu Play avec la difficulté
    public VBox createPlayMenu(Font customFont) {
        playMenu = new VBox(20); // Stocke playMenu pour le cacher plus tard

        // Création du texte "Difficulté"
        Text difficultyText = new Text("Difficulté");
        difficultyText.setFont(customFont); // Appliquer la police personnalisée
        difficultyText.setStyle("-fx-font-size: 30px; -fx-fill: black;");

        // Création des boutons pour les différents niveaux de difficulté
        Button easyButton = createDifficultyButton("Facile", customFont);
        Button normalButton = createDifficultyButton("Normal", customFont);
        Button hardButton = createDifficultyButton("Difficile", customFont);
        Button distortedButton = createDifficultyButton("Distordue", customFont);

        // Créer un HBox pour aligner les boutons horizontalement
        HBox difficultyButtons = new HBox(20); // 20px d'espacement entre les boutons
        difficultyButtons.getChildren().addAll(easyButton, normalButton, hardButton, distortedButton);
        difficultyButtons.setStyle("-fx-alignment: center;"); // Centrer les boutons dans le HBox

        // Créer le bouton "Retour"
        Button backButton = new Button("Retour");
        backButton.setFont(customFont);
        backButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");

        // Action pour le bouton "Retour"
        backButton.setOnAction(event -> {
            if (playMenu != null && mainMenu != null) {
                playMenu.setVisible(false);
                mainMenu.setVisible(true);
            }
        });

        // Ajouter tous les éléments au playMenu
        playMenu.getChildren().addAll(difficultyText, difficultyButtons, backButton);
        playMenu.setStyle("-fx-alignment: center;"); // Centrer le contenu dans la VBox

        // Retourner le playMenu au lieu de créer une nouvelle VBox
        return playMenu;
    }

    // Méthode pour créer les boutons de difficulté
    private Button createDifficultyButton(String label, Font customFont) {
        Button button = new Button(label);
        button.setFont(customFont);
        button.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");
        return button;
    }
}
