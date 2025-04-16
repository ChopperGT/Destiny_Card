package Code.Menu;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

/**
 * Classe qui gère l'affichage des crédits du jeu
 */
public class Credit {
    private VBox mainMenu;
    private Font customFont;
    private VBox creditsMenu;
    
    /**
     * Constructeur de la classe Credit
     * @param mainMenu Le menu principal auquel revenir
     * @param customFont La police personnalisée à utiliser
     */
    public Credit(VBox mainMenu, Font customFont) {
        this.mainMenu = mainMenu;
        this.customFont = customFont;
        this.creditsMenu = createCreditsMenu();
    }
    
    /**
     * Retourne le menu des crédits
     * @return Le VBox contenant le menu des crédits
     */
    public VBox getCreditsMenu() {
        return creditsMenu;
    }
    
    /**
     * Crée le menu des crédits avec tous ses éléments
     * @return Un VBox contenant le menu des crédits
     */
    private VBox createCreditsMenu() {
        VBox creditsMenu = new VBox(20);
        creditsMenu.setAlignment(Pos.CENTER);
        
        // Titre
        Text titleText = new Text("Crédits");
        titleText.setFont(customFont);
        titleText.setStyle("-fx-font-size: 40px; -fx-fill: white;");
        
        // Liste des contributeurs
        VBox contributorsBox = new VBox(10);
        contributorsBox.setAlignment(Pos.CENTER);
        
        Text developersTitle = new Text("Développeurs");
        developersTitle.setFont(customFont);
        developersTitle.setStyle("-fx-font-size: 24px; -fx-fill: white; -fx-font-weight: bold;");
        
        Text developer1 = new Text("Christopher Leclerc - Développeur principal");
        Hyperlink developer2 = new Hyperlink("Mydmoov");
        
        developer1.setFont(customFont);
        developer2.setFont(customFont);
        
        developer1.setStyle("-fx-font-size: 18px; -fx-fill: white;");
        developer2.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        
        developer2.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.youtube.com/@mydmoov"));
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'ouverture du lien: " + ex.getMessage());
            }
        });
        
        Text artTitle = new Text("Illustrations");
        artTitle.setFont(customFont);
        artTitle.setStyle("-fx-font-size: 24px; -fx-fill: white; -fx-font-weight: bold;");
        
        // Création de l'hyperlien pour Pikapel Mushi
        Hyperlink artist1 = new Hyperlink("Pikapel Mushi");
        artist1.setFont(customFont);
        artist1.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        artist1.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.twitch.tv/pikapalemushi"));
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'ouverture du lien: " + ex.getMessage());
            }
        });
        
        Text specialThanksTitle = new Text("Remerciements spéciaux");
        specialThanksTitle.setFont(customFont);
        specialThanksTitle.setStyle("-fx-font-size: 24px; -fx-fill: white; -fx-font-weight: bold;");
        
        // Création des hyperliens pour les remerciements
        Hyperlink thanks1 = new Hyperlink("Posiplay");
        Hyperlink thanks2 = new Hyperlink("JuArtsAndGames");
        Hyperlink thanks3 = new Hyperlink("Astrographe_");
        
        thanks1.setFont(customFont);
        thanks2.setFont(customFont);
        thanks3.setFont(customFont);
        
        thanks1.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        thanks2.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        thanks3.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
        
        // Configuration des actions des hyperliens
        thanks1.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.twitch.tv/posiplay"));
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'ouverture du lien: " + ex.getMessage());
            }
        });
        
        thanks2.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.twitch.tv/juartsandgames"));
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'ouverture du lien: " + ex.getMessage());
            }
        });
        
        thanks3.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.twitch.tv/astrographe_"));
            } catch (Exception ex) {
                System.out.println("Erreur lors de l'ouverture du lien: " + ex.getMessage());
            }
        });
        
        contributorsBox.getChildren().addAll(
            developersTitle, 
            developer1, developer2,
            new Text(""), // Espacement
            artTitle,
            artist1,
            new Text(""), // Espacement
            specialThanksTitle,
            thanks1, thanks2, thanks3
        );
        
        // Bouton retour
        Button backButton = new Button("Retour");
        backButton.setFont(customFont);
        backButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-border-color: white; -fx-cursor: hand;");
        
        backButton.setOnAction(e -> {
            creditsMenu.setVisible(false);
            mainMenu.setVisible(true);
        });
        
        // Rectangle semi-transparent pour le fond
        Rectangle background = new Rectangle();
        background.setWidth(650);
        background.setHeight(600); // Augmenté pour tenir compte du contenu supplémentaire
        background.setFill(Color.rgb(0, 0, 0, 0.7));
        background.setArcWidth(20);
        background.setArcHeight(20);
        
        // Créer un StackPane pour mettre le fond et le contenu
        StackPane creditContent = new StackPane();
        
        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(titleText, contributorsBox, backButton);
        content.setMaxWidth(600);
        
        creditContent.getChildren().addAll(background, content);
        
        creditsMenu.getChildren().add(creditContent);
        
        return creditsMenu;
    }
} 