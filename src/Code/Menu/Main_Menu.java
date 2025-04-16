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
import javafx.scene.text.FontWeight;
import Code.Save.Appli.Appli_Save;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Main_Menu extends Application {
    private MediaPlayer mediaPlayer;
    private MediaPlayer videoPlayer;
    private MediaView mediaView;

    @Override
    public void start(Stage primaryStage) {

        // Charger la police personnalisée
        String fontPath = getClass().getResource("/Police/Yu-Gi-Oh!_ITC_Stone_Serif_LT_Italic.ttf").toExternalForm();
        Font customFont;
        try {
            customFont = Font.loadFont(fontPath, 30);
            // Vérifier si la police a été correctement chargée
            if (customFont == null) {
                System.out.println("Impossible de charger la police personnalisée. Utilisation de la police par défaut.");
                customFont = Font.font("Arial", FontWeight.NORMAL, 30);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du chargement de la police : " + e.getMessage());
            customFont = Font.font("Arial", FontWeight.NORMAL, 30);
        }

        // Chemin vers la vidéo
        String videoPath = getClass().getResource("/Video/Menu.mp4").toExternalForm();
        Media video = new Media(videoPath);
        videoPlayer = new MediaPlayer(video);
        mediaView = new MediaView(videoPlayer);

        // Charger les paramètres sauvegardés
        Appli_Save savedSettings = Appli_Save.loadSettings();
        
        // Adapter la taille de la vidéo à la résolution
        updateVideoSize(savedSettings.getResolution());
        
        mediaView.setPreserveRatio(false);  // Pour éviter de maintenir le ratio original
        videoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        videoPlayer.play();

        // Chemin vers le fichier audio
        String audioPath = getClass().getResource("/Song/Song_Menu.mp3").toExternalForm();
        Media audio = new Media(audioPath);
        mediaPlayer = new MediaPlayer(audio);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(savedSettings.getVolume());
        mediaPlayer.play();

        // Création des boutons du menu principal
        Button button1 = new Button("Jouer");
        Button button4 = new Button("Liste carte");
        Button button2 = new Button("Options");
        Button buttonCredits = new Button("Crédits");
        Button button3 = new Button("Quitter");
        button1.setFont(customFont);
        button4.setFont(customFont);
        button2.setFont(customFont);
        buttonCredits.setFont(customFont);
        button3.setFont(customFont);
        String buttonStyle = "-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px;";
        button1.setStyle(buttonStyle + " -fx-cursor: hand;");
        button4.setStyle(buttonStyle + " -fx-cursor: hand;");
        button2.setStyle(buttonStyle + " -fx-cursor: hand;");
        buttonCredits.setStyle(buttonStyle + " -fx-cursor: hand;");
        button3.setStyle(buttonStyle + " -fx-cursor: hand;");

        // Création du menu principal
        VBox mainMenu = new VBox(20);
        mainMenu.getChildren().addAll(button1, button4, button2, buttonCredits, button3);
        mainMenu.setAlignment(Pos.CENTER);

        // Création du menu d'options
        Main_Menu_Option mainMenuOption = new Main_Menu_Option(mainMenu);
        VBox optionsMenu = mainMenuOption.createOptionMenu(customFont, primaryStage, mediaPlayer);
        optionsMenu.setVisible(false);
        
        // Ajouter un écouteur de changement pour la résolution
        mainMenuOption.getResolutionBox().setOnAction(event -> {
            String selectedResolution = mainMenuOption.getResolutionBox().getValue();
            updateVideoSize(selectedResolution);
            
            String[] dimensions = selectedResolution.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
        });

        // Création de l'instance Main_Menu_Play et ajout du menu Play à la scène
        Main_Menu_Play menuPlay = new Main_Menu_Play(mainMenu, primaryStage);
        menuPlay.setMainMenuController(this); // Permet à Main_Menu_Play d'accéder aux médias
        VBox playMenu = menuPlay.createPlayMenu(customFont);
        playMenu.setVisible(false);

        //Liste carte
        VBox listeCarteMenu = new Liste_Carte(mainMenu).createListeCarteMenu(customFont);
        listeCarteMenu.setVisible(false);
        
        // Création du menu des crédits en utilisant la nouvelle classe Credit
        Credit credit = new Credit(mainMenu, customFont);
        VBox creditsMenu = credit.getCreditsMenu();
        creditsMenu.setVisible(false);

        // Actions des boutons
        button1.setOnAction(event -> {
            playMenu.setVisible(true);
            mainMenu.setVisible(false);
        });

        button2.setOnAction(event -> {
            mainMenu.setVisible(false);
            optionsMenu.setVisible(true);
        });
        
        buttonCredits.setOnAction(event -> {
            mainMenu.setVisible(false);
            creditsMenu.setVisible(true);
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
        root.getChildren().addAll(mediaView, mainMenu, playMenu, optionsMenu, listeCarteMenu, creditsMenu);

        // Création de la scène
        String[] dimensions = savedSettings.getResolution().split("x");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Destiny Card");
        primaryStage.setScene(scene);
        
        // Définir le mode plein écran selon les paramètres
        primaryStage.setFullScreen(savedSettings.isFullscreen());
        
        primaryStage.show();
    }

    // Méthode pour mettre à jour la taille de la vidéo
    private void updateVideoSize(String resolution) {
        if (mediaView != null) {
            String[] dimensions = resolution.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            mediaView.setFitWidth(width);
            mediaView.setFitHeight(height);
        }
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

    // Getters pour accéder aux lecteurs media
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public MediaPlayer getVideoPlayer() {
        return videoPlayer;
    }
}
