package Code.Menu;

import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import Code.Game.Scène.Novel.Chapter_1;

public class Main_Menu_Play {
    private VBox mainMenu;
    private Stage primaryStage;
    private Main_Menu mainMenuController;

    public Main_Menu_Play(VBox mainMenu, Stage primaryStage) {
        this.mainMenu = mainMenu;
        this.primaryStage = primaryStage;
    }
    
    // Setter pour avoir accès au contrôleur Main_Menu
    public void setMainMenuController(Main_Menu controller) {
        this.mainMenuController = controller;
    }

    public VBox createPlayMenu(Font customFont) {
        VBox playMenu = new VBox(20);
        playMenu.setAlignment(Pos.CENTER);
        
        // Titre du menu
        Text difficultyText = new Text("Difficulté");
        difficultyText.setFont(customFont);
        difficultyText.setStyle("-fx-font-size: 30px; -fx-fill: black;");

        // Création des boutons pour les différents niveaux de difficulté
        Button easyButton = createDifficultyButton("Facile", customFont);
        Button normalButton = createDifficultyButton("Normal", customFont);
        Button hardButton = createDifficultyButton("Difficile", customFont);
        Button distortedButton = createDifficultyButton("Distordu", customFont);
        
        // Action pour le bouton Normal - lance Chapter_1 avec effet de fondu
        normalButton.setOnAction(e -> startGameWithFade(customFont));

        // Créer un HBox pour aligner les boutons horizontalement
        HBox difficultyButtons = new HBox(20); // 20px d'espacement entre les boutons
        difficultyButtons.getChildren().addAll(easyButton, normalButton, hardButton, distortedButton);
        difficultyButtons.setStyle("-fx-alignment: center;"); // Centrer les boutons dans le HBox

        // Bouton retour
        Button backButton = new Button("Retour");
        backButton.setFont(customFont);
        backButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");
        backButton.setOnAction(e -> {
            playMenu.setVisible(false);
            mainMenu.setVisible(true);
        });

        playMenu.getChildren().addAll(difficultyText, difficultyButtons, backButton);
        return playMenu;
    }

    // Méthode pour créer les boutons de difficulté
    private Button createDifficultyButton(String label, Font customFont) {
        Button button = new Button(label);
        button.setFont(customFont);
        button.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");
        return button;
    }

    private void startGameWithFade(Font customFont) {
        // Créer un rectangle noir pour l'effet de fondu
        Rectangle overlay = new Rectangle(primaryStage.getWidth(), primaryStage.getHeight(), Color.BLACK);
        overlay.setOpacity(0);

        // Ajouter le rectangle à la scène actuelle
        StackPane root = (StackPane) primaryStage.getScene().getRoot();
        root.getChildren().add(overlay);

        // Arrêter la musique et la vidéo si le contrôleur Main_Menu est disponible
        if (mainMenuController != null) {
            if (mainMenuController.getMediaPlayer() != null) {
                mainMenuController.getMediaPlayer().pause();
            }
            if (mainMenuController.getVideoPlayer() != null) {
                mainMenuController.getVideoPlayer().pause();
            }
        }

        // Créer une transition de fondu
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), overlay);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        
        // Quand le fondu est terminé, attendre 5 secondes puis lancer Chapter_1
        fadeIn.setOnFinished(e -> {
            // Cacher les menus
            mainMenu.setVisible(false);
            
            // Attendre 5 secondes puis revenir et lancer Chapter_1
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    
                    // Exécuter sur le thread JavaFX
                    javafx.application.Platform.runLater(() -> {
                        // Lancer Chapter_1
                        Chapter_1 chapter1 = new Chapter_1(primaryStage, primaryStage.getScene(), customFont);
                        chapter1.start();
                        
                        // Supprimer le rectangle de fondu
                        root.getChildren().remove(overlay);
                    });
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }).start();
        });
        
        // Démarrer la transition
        fadeIn.play();
    }
}
