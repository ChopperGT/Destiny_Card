package Code.Menu;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import Code.Game.Player.GameState;
import Code.Game.Player.GameStateManager;

public class Main_Menu extends Application {
    private MediaPlayer mediaPlayer;
    private MediaPlayer videoPlayer;

    @Override
    public void start(Stage primaryStage) {

        // Charger la police personnalisée
        String fontPath = getClass().getResource("/Police/Yu-Gi-Oh!_ITC_Stone_Serif_LT_Italic.ttf").toExternalForm();
        Font customFont = Font.loadFont(fontPath, 30);

        // Chemin vers la vidéo
        String videoPath = getClass().getResource("/Video/Menu.mp4").toExternalForm();
        Media video = new Media(videoPath);
        videoPlayer = new MediaPlayer(video);
        MediaView mediaView = new MediaView(videoPlayer);

        // Réglage de la taille de la vidéo à 1920x1080
        mediaView.setFitWidth(1920);
        mediaView.setFitHeight(1080);
        mediaView.setPreserveRatio(false);  // Pour éviter de maintenir le ratio original
        videoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        videoPlayer.play();

        // Chemin vers le fichier audio
        String audioPath = getClass().getResource("/Song/Song_Menu.mp3").toExternalForm();
        Media audio = new Media(audioPath);
        mediaPlayer = new MediaPlayer(audio);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        //Création d'un état de jeu

        GameState gameState = new GameState(1, 0, "Joueur1");

        // Sauvegarder l'état du jeu
        GameStateManager.saveGameState(gameState);

        // Charger l'état du jeu (si un fichier existe)
        GameState loadedGameState = GameStateManager.loadGameState();
        if (loadedGameState != null) {
            System.out.println("Nom du joueur : " + loadedGameState.getPlayerName());
            System.out.println("Niveau : " + loadedGameState.getLevel());
            System.out.println("Score : " + loadedGameState.getScore());
        } else {
            System.out.println("Aucun état de jeu sauvegardé trouvé.");
        }



        // Création des boutons du menu principal
        Button button1 = new Button("Jouer");
        Button button4 = new Button("Liste carte");
        Button button2 = new Button("Options");
        Button button3 = new Button("Quitter");
        button1.setFont(customFont);
        button4.setFont(customFont);
        button2.setFont(customFont);
        button3.setFont(customFont);
        String buttonStyle = "-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px;";
        button1.setStyle(buttonStyle + " -fx-cursor: hand;");
        button4.setStyle(buttonStyle + " -fx-cursor: hand;");
        button2.setStyle(buttonStyle + " -fx-cursor: hand;");
        button3.setStyle(buttonStyle + " -fx-cursor: hand;");

        // Création du menu principal
        VBox mainMenu = new VBox(20);
        mainMenu.getChildren().addAll(button1, button4, button2, button3);
        mainMenu.setAlignment(Pos.CENTER);

        // Création du menu d'options
        Main_Menu_Option mainMenuOption = new Main_Menu_Option(mainMenu);
        VBox optionsMenu = mainMenuOption.createOptionMenu(customFont, primaryStage, mediaPlayer);

        // Création du menu Play
        Main_Menu_Play mainMenuPlay = new Main_Menu_Play(mainMenu);
        VBox playMenu = mainMenuPlay.createPlayMenu(customFont);
        playMenu.setVisible(false);

        //Liste carte

        VBox listeCarteMenu = new Liste_Carte(mainMenu).createListeCarteMenu(customFont);
        listeCarteMenu.setVisible(false);

        // Actions des boutons
        button1.setOnAction(event -> {
            playMenu.setVisible(true);
            mainMenu.setVisible(false);
        });

        button2.setOnAction(event -> {
            mainMenu.setVisible(false);
            optionsMenu.setVisible(true);
        });

        button3.setOnAction(event -> {
            primaryStage.close();
        });

        button4.setOnAction(event -> {
            mainMenu.setVisible(false);
            listeCarteMenu.setVisible(true);
        });

        // Utilisation d'un StackPane pour superposer les éléments
        StackPane root = new StackPane();
        root.getChildren().addAll(mediaView, mainMenu, playMenu, optionsMenu, listeCarteMenu);

        // Création de la scène
        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setTitle("Destiny Card");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Arrêter la lecture audio et vidéo à la fermeture
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        if (videoPlayer != null) {
            videoPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
