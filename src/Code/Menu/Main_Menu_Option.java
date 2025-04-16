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
import javafx.geometry.Pos;
import Code.Save.Appli.Appli_Save;

public class Main_Menu_Option {
    private VBox mainMenu;
    private VBox optionsMenu;
    private MediaPlayer mediaPlayer;

    // Déclarer resolutionBox et fullscreenCheckBox comme variables d'instance
    private ComboBox<String> resolutionBox;
    private CheckBox fullscreenCheckBox;
    private Slider volumeSlider;

    // Constructeur
    public Main_Menu_Option(VBox mainMenu) {
        this.mainMenu = mainMenu;
    }

    // Méthode pour créer le menu des options
    public VBox createOptionMenu(Font customFont, Stage primaryStage, MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        optionsMenu = new VBox(20);

        // Charger les paramètres sauvegardés
        Appli_Save savedSettings = Appli_Save.loadSettings();
        
        // Création du texte "Options"
        Text optionText = new Text("Options");
        optionText.setFont(customFont);
        optionText.setStyle("-fx-font-size: 30px; -fx-fill: black;");

        // Création du slider pour le volume sonore
        volumeSlider = new Slider(0, 1, savedSettings.getVolume());
        Label volumeLabel = new Label("Volume: " + (int) (savedSettings.getVolume() * 100) + "%");
        volumeLabel.setFont(customFont);
        volumeLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");

        volumeSlider.setPrefWidth(300);
        volumeSlider.setMaxWidth(300);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            mediaPlayer.setVolume(newValue.doubleValue());
            volumeLabel.setText("Volume: " + (int) (newValue.doubleValue() * 100) + "%");
        });

        // Création du label pour la résolution
        Text resolutionLabel = new Text("Résolution de l'écran");
        resolutionLabel.setFont(customFont);
        resolutionLabel.setStyle("-fx-font-size: 20px; -fx-fill: black;");

        // Création de la liste déroulante pour la résolution
        resolutionBox = new ComboBox<>();
        resolutionBox.setItems(FXCollections.observableArrayList(
                "2560x1440", "1920x1080", "1600x900", "1280x720"
        ));
        resolutionBox.setValue(savedSettings.getResolution());

        resolutionBox.setStyle("-fx-font-size: 18px; -fx-cursor: hand;");
        resolutionBox.setOnAction(event -> {
            String selectedResolution = resolutionBox.getValue();
            String[] dimensions = selectedResolution.split("x");
            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
        });

        // Création de la case à cocher pour passer en plein écran
        fullscreenCheckBox = new CheckBox("Plein écran");
        fullscreenCheckBox.setFont(customFont);
        fullscreenCheckBox.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");
        fullscreenCheckBox.setSelected(savedSettings.isFullscreen());
        fullscreenCheckBox.setOnAction(event -> {
            if (fullscreenCheckBox.isSelected()) {
                primaryStage.setFullScreen(true);
            } else {
                primaryStage.setFullScreen(false);
            }
        });

        // Bouton Sauvegarder
        Button saveButton = new Button("Sauvegarder");
        saveButton.setFont(customFont);
        saveButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");
        saveButton.setOnAction(event -> {
            Appli_Save settings = new Appli_Save(
                volumeSlider.getValue(),
                resolutionBox.getValue(),
                fullscreenCheckBox.isSelected()
            );
            Appli_Save.saveSettings(settings);
            System.out.println("Paramètres sauvegardés avec succès !");
        });

        // Bouton Retour
        Button backButton = new Button("Retour");
        backButton.setFont(customFont);
        backButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");
        backButton.setOnAction(event -> {
            optionsMenu.setVisible(false);
            mainMenu.setVisible(true);
        });

        // Ajouter tous les éléments au optionsMenu
        optionsMenu.getChildren().addAll(optionText, volumeLabel, volumeSlider, resolutionLabel, resolutionBox, fullscreenCheckBox, saveButton, backButton);
        optionsMenu.setStyle("-fx-alignment: center;");

        return optionsMenu;
    }

    public ComboBox<String> getResolutionBox() {
        return resolutionBox;
    }

    public CheckBox getFullscreenCheckBox() {
        return fullscreenCheckBox;
    }
}
