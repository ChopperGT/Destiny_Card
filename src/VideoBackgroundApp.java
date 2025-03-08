import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;  // Importation du StackPane
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaException;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javax.swing.*;
import java.io.File;
import javafx.scene.text.Font;




public class VideoBackgroundApp extends Application {
    private MediaPlayer mediaPlayer;
    private MediaPlayer videoPlayer;

    @Override
    public void start(Stage primaryStage) {


        // Charger la police personnalisée
        String fontPath = getClass().getResource("/Police/Yu-Gi-Oh!_ITC_Stone_Serif_LT_Italic.ttf").toExternalForm();
        System.out.println("fontPath"+fontPath);

        Font customFont = Font.loadFont(fontPath, 30);  // Charger la police avec taille 30



        // Chemin vers la vidéo
        String videoPath = getClass().getResource("/Video/Menu.mp4").toExternalForm();
        Media video = new Media(videoPath);
        videoPlayer = new MediaPlayer(video);
        MediaView mediaView = new MediaView(videoPlayer);

        // Réglage de la taille de la vidéo à 1920x1080
        mediaView.setFitWidth(1920);
        mediaView.setFitHeight(1080);
        mediaView.setPreserveRatio(false);  // Pour éviter de maintenir le ratio original

        // Lecture de la vidéo en boucle
        videoPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        videoPlayer.play();

        // Chemin vers le fichier audio
        String audioPath = getClass().getResource("/Song/Song_Menu.mp3").toExternalForm();
        Media audio = new Media(audioPath);
        mediaPlayer = new MediaPlayer(audio);

        // Lecture du son en boucle
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        // Création du bouton
        Button button1 = new Button("Jouer");
        Button button2 = new Button("Options");
        Button button3 = new Button("Quitter");

        // Appliquer la police personnalisée aux boutons
        button1.setFont(customFont);
        button2.setFont(customFont);
        button3.setFont(customFont);
        // Style des boutons
        String buttonStyle = "-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px;";
        button1.setStyle(buttonStyle + " -fx-cursor: hand;");
        button2.setStyle(buttonStyle + " -fx-cursor: hand;");
        button3.setStyle(buttonStyle + " -fx-cursor: hand;");

        // Création du menu d'options
        Button buttonBack = new Button("Retour");
        buttonBack.setFont(customFont);
        buttonBack.setStyle(buttonStyle + " -fx-cursor: hand;");


        // Création du slider pour le volume sonore
        Slider volumeSlider = new Slider(0, 1, mediaPlayer.getVolume());
        volumeSlider.setShowTickMarks(false);
        volumeSlider.setShowTickLabels(false);

        // Label pour afficher le volume en pourcentage
        Label volumeLabel = new Label("Volume: " + (int) (mediaPlayer.getVolume() * 100) + "%");

        // Limiter la taille du slider
        volumeSlider.setPrefWidth(300);
        volumeSlider.setMaxWidth(300);

        // Création d'une VBox pour organiser les boutons
        VBox mainMenu = new VBox(20); // Espacement de 20px entre les boutons
        mainMenu.getChildren().addAll(button1, button2, button3);
        mainMenu.setAlignment(Pos.CENTER); // Centrage vertical et horizontal

        // Créer une VBox pour le menu d'options
        VBox optionsMenu = new VBox(20); // Espacement de 20px entre les boutons
        optionsMenu.getChildren().addAll(volumeLabel, volumeSlider, buttonBack);
        optionsMenu.setAlignment(Pos.CENTER);
        optionsMenu.setVisible(false);  // Masquer le menu d'options par défaut


        // Action pour le bouton "Options"
        button2.setOnAction(event -> {
            mainMenu.setVisible(false);  // Masquer le menu principal
            optionsMenu.setVisible(true);  // Afficher le menu d'options
        });


        // Mettre à jour le volume en fonction du slider
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue());
            volumeLabel.setText("Volume: " + (int) (newValue.doubleValue() * 100) + "%");
        });

        // Action pour revenir au menu principal
        buttonBack.setOnAction(event -> {
            optionsMenu.setVisible(false);  // Masquer le menu d'options
            mainMenu.setVisible(true);  // Afficher le menu principal
        });


        // Action pour le bouton "Quitter"
        button3.setOnAction(event -> {
            primaryStage.close(); // Ferme la fenêtre principale
        });



        // Utilisation d'un StackPane pour superposer les éléments
        StackPane root = new StackPane();
        root.getChildren().addAll(mediaView, mainMenu, optionsMenu); // La vidéo en fond, bouton au-dessus


        // Créer la scène avec la résolution de 1920x1080
        Scene scene = new Scene(root, 1920, 1080);
        // Paramètres de la scène (dimensions et titre)
        primaryStage.setTitle("Destiny Card");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @Override
    public void stop() {
        // Assure-toi que la lecture audio et vidéo est arrêtée à la fermeture de l'application
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