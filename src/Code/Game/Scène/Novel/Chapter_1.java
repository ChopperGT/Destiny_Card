package Code.Game.Scène.Novel;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Chapter_1 {
    private Stage primaryStage;
    private Scene previousScene;
    private Font customFont;
    private Text dialogText;
    private List<String> storyParts;
    private int currentPart = 0;

    public Chapter_1(Stage primaryStage, Scene previousScene, Font customFont) {
        this.primaryStage = primaryStage;
        this.previousScene = previousScene;
        this.customFont = customFont;
        
        // Initialiser les parties de l'histoire
        initializeStory();
    }
    
    private void initializeStory() {
        storyParts = new ArrayList<>();
        storyParts.add("...");
        storyParts.add("........");
        storyParts.add("T'es encore vivant ?");
        storyParts.add("J'imagine ouais...");
        storyParts.add("Jcrois que le fada de pierre est mort...");
        storyParts.add("Il aura pas duré longtemps...");
        storyParts.add("Il aura vécu a peine une partie de carte je crois...");
        storyParts.add("...");
        storyParts.add("........");
        storyParts.add("Je me demande ce qu'il va se passer maintenant...");
        storyParts.add("Tu penses existé encore combien de temps ?");
        storyParts.add("Aucune idée, tu crois qu'il existe un moyen de le savoir ?");
        storyParts.add("Si on arrivait à avoir une forme comme la royauté peut être qu'on le serait...");
        storyParts.add("T'as une idée de comment on pourrait le faire ?");
        storyParts.add("La seul chose que je sais faire c'est de joué à un jeu de cartes avec toi...");
        storyParts.add("Tu sais que je me demande comment on fait pour faire bougé les cartes sans membres.");
        storyParts.add("Ce monde est totalement distordu, rien n'a de sens...");
        storyParts.add("Ouai et j'ai pas envie de cesser d'exister juste comme ça...");
        storyParts.add("On pourrait tenter d'aller dans le monde réel, peut être qu'on existerait vraiment grâce à ça...");
        storyParts.add("T'es un malade !! le Roi nous à interdit d'aller dans le monde réel !!!");
        storyParts.add("Pourtant cela lui empeche pas de nous regardé disparaître sans rien faire...");
        storyParts.add("C'est vrais tu as raison, pourtant on pourrait tenter d'aller dans le monde réel... c'est même pas sur qu'on arrive à y ouvrir un portail...");
        storyParts.add("Pourtant le fada de pierre a réussi à ouvrir un portail dés qu'il à éxisté, c'est que cela doit être possible même pour nous...");
        storyParts.add("Ouai mais oublie pas qu'il a faillit nous explosé avec lui... On à eu du mal le calmé, et souvient toi à quel point c'était pas facile de lui faire comprendre qu'il devait s'équilibré sans qu'il parle ou comprenne notre langue...");
        storyParts.add("Combien de langue il existe dans notre monde ?");
        storyParts.add("Je sais pas, c'est pas comme si je pouvais me déplacé pour le découvrir...");
        storyParts.add("...");
        storyParts.add("........");
        storyParts.add("Tu sais quoi, t'as raison, faison le, partont dans le monde réel !");
        storyParts.add("Ok mais qui commence à faire un portail ?");
        storyParts.add("euh... je sais pas, toi ?");
        storyParts.add("Pourquoi pas toi ?");
        storyParts.add("J'ai pas envie d'exploser moi !");
        storyParts.add("Moi non plus j'ai pas envie d'exploser moi !");
        storyParts.add("Donc on reste bloqué la alors ?");
        storyParts.add("J'ai une idée, celui qui gagne à mon jeu de carte choisit qui commence à faire un portail !");
        storyParts.add("ahah, tu sais que je vais te mettre une dérouillé comme les autres fois ? tu veux pas plutot faire autre chose ?");
        storyParts.add("C'est ce que tu crois ! mais cette fois, je vais te battre !");
        storyParts.add("Très bien, alors on joue !");
        
    }
    
    public void start() {
        // Création du dialogue visual novel
        StackPane root = new StackPane();
        
        try {
            // Charger l'image de fond "monde_distordu"
            Image backgroundImage = new Image(new FileInputStream("src/Image/History/Chapter_1/monde_distordu.jpg"));
            ImageView backgroundImageView = new ImageView(backgroundImage);
            
            // Adapter l'image à la taille de la fenêtre
            backgroundImageView.setFitWidth(primaryStage.getWidth());
            backgroundImageView.setFitHeight(primaryStage.getHeight());
            backgroundImageView.setPreserveRatio(false);
            
            // Boîte de dialogue en bas de l'écran
            Rectangle dialogBox = new Rectangle();
            dialogBox.setWidth(primaryStage.getWidth() * 0.8);
            dialogBox.setHeight(primaryStage.getHeight() * 0.3);
            dialogBox.setFill(Color.rgb(0, 0, 0, 0.7));
            dialogBox.setArcWidth(20);
            dialogBox.setArcHeight(20);
            dialogBox.setTranslateY(primaryStage.getHeight() * 0.3);
            
            // Texte du dialogue
            dialogText = new Text(storyParts.get(currentPart));
            dialogText.setFont(customFont);
            dialogText.setFill(Color.WHITE);
            dialogText.setWrappingWidth(dialogBox.getWidth() * 0.9);
            dialogText.setTranslateY(primaryStage.getHeight() * 0.3);
            
            // Bouton pour continuer
            Button continueButton = new Button("Continuer");
            continueButton.setFont(customFont);
            continueButton.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-border-color: white;");
            continueButton.setTranslateY(primaryStage.getHeight() * 0.45);
            continueButton.setOnAction(e -> {
                // Avancer dans l'histoire
                nextPart();
            });
            
            // Bouton pour retourner au menu
            Button backButton = new Button("Retour au menu");
            backButton.setFont(customFont);
            backButton.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-border-color: white;");
            backButton.setTranslateY(primaryStage.getHeight() * 0.5);
            backButton.setOnAction(e -> {
                primaryStage.setScene(previousScene);
            });
            
            // Ajout des éléments à la scène
            root.getChildren().addAll(backgroundImageView, dialogBox, dialogText, continueButton, backButton);
        
        } catch (FileNotFoundException e) {
            System.out.println("Erreur: Image de fond non trouvée: " + e.getMessage());
            
            // Fond de secours si l'image n'est pas trouvée
            Rectangle fallbackBackground = new Rectangle();
            fallbackBackground.setWidth(primaryStage.getWidth());
            fallbackBackground.setHeight(primaryStage.getHeight());
            fallbackBackground.setFill(Color.BLACK);
            
            // Boîte de dialogue et autres éléments
            Rectangle dialogBox = new Rectangle();
            dialogBox.setWidth(primaryStage.getWidth() * 0.8);
            dialogBox.setHeight(primaryStage.getHeight() * 0.3);
            dialogBox.setFill(Color.rgb(0, 0, 0, 0.7));
            dialogBox.setArcWidth(20);
            dialogBox.setArcHeight(20);
            dialogBox.setTranslateY(primaryStage.getHeight() * 0.3);
            
            // Texte du dialogue
            dialogText = new Text(storyParts.get(currentPart));
            dialogText.setFont(customFont);
            dialogText.setFill(Color.WHITE);
            dialogText.setWrappingWidth(dialogBox.getWidth() * 0.9);
            dialogText.setTranslateY(primaryStage.getHeight() * 0.3);
            
            // Bouton pour continuer
            Button continueButton = new Button("Continuer");
            continueButton.setFont(customFont);
            continueButton.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-border-color: white;");
            continueButton.setTranslateY(primaryStage.getHeight() * 0.45);
            continueButton.setOnAction(evt -> {
                // Avancer dans l'histoire
                nextPart();
            });
            
            // Bouton pour retourner au menu
            Button backButton = new Button("Retour au menu");
            backButton.setFont(customFont);
            backButton.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-border-color: white;");
            backButton.setTranslateY(primaryStage.getHeight() * 0.5);
            backButton.setOnAction(evt -> {
                primaryStage.setScene(previousScene);
            });
            
            // Ajout des éléments à la scène avec le fond de secours
            root.getChildren().addAll(fallbackBackground, dialogBox, dialogText, continueButton, backButton);
        }
        
        // Création de la scène
        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(scene);
    }
    
    private void nextPart() {
        // Passer à la partie suivante de l'histoire
        currentPart++;
        
        // Vérifier si nous avons atteint la fin de l'histoire
        if (currentPart < storyParts.size()) {
            dialogText.setText(storyParts.get(currentPart));
        } else {
            // Revenir au début pour le moment
            currentPart = 0;
            dialogText.setText(storyParts.get(currentPart));
        }
    }
} 