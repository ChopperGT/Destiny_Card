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
import java.io.File;




public class VideoBackgroundApp extends Application {
    private MediaPlayer mediaPlayer;
    private MediaPlayer videoPlayer;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Chemin vers la vidéo
            String videoPath = new File("C:/Users/Lecle/Bureau/teste/untitled/src/Video/Menu.mp4").toURI().toString();
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
            String audioPath = new File("C:/Users/Lecle/Bureau/teste/untitled/src/Song/Song_Menu.mp3").toURI().toString();
            Media audio = new Media(audioPath);
            mediaPlayer = new MediaPlayer(audio);

            // Lecture du son en boucle
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();

            // Utilisation d'un StackPane pour superposer les éléments
            StackPane root = new StackPane();
            root.getChildren().add(mediaView);  // Ajouter la vidéo au StackPane

            // Créer la scène avec la résolution de 1920x1080
            Scene scene = new Scene(root, 1920, 1080);

            // Paramètres de la scène (dimensions et titre)
            primaryStage.setTitle("Video Background App");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (MediaException e) {
            e.printStackTrace();
        }
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