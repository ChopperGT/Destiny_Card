package Code.Menu;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.geometry.Pos;  // Import de Pos
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.scene.Scene;

import Code.Game.Gameplay.Card.Monstre.Monstre;
import java.util.ArrayList;
import java.util.List;

public class Liste_Carte {
    private VBox mainMenu;
    private VBox listeCarteMenu;
    private VBox cardDetailsBox;
    private List<Monstre> availableCards;

    public Liste_Carte(VBox mainMenu) {
        this.mainMenu = mainMenu;
        this.availableCards = new ArrayList<>();
        
        // Ajouter des cartes disponibles
        availableCards.add(new Monstre()); // Le défenseur distordu
        
        // Ajouter le guerrier distordu en utilisant la méthode statique
        availableCards.add(Monstre.creerGuerrierDistordu());
        
        // Ajoutez d'autres cartes ici quand elles seront disponibles
    }

    public VBox createListeCarteMenu(Font customFont) {
        listeCarteMenu = new VBox(20);

        // Afficher les boîtes
        showBoxes(customFont);
        
        return listeCarteMenu;
    }
    
    /**
     * Affiche le contenu d'une boîte (les cartes disponibles)
     */
    private void showBoxContent(Font customFont) {
        // Effacer le contenu précédent
        listeCarteMenu.getChildren().clear();
        
        Text title = new Text("Cartes disponibles");
        title.setFont(customFont);
        title.setStyle("-fx-font-size: 30px; -fx-fill: black;");
        
        // Créer une disposition horizontale pour la liste et les détails
        HBox contentBox = new HBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(10));
        contentBox.setPrefWidth(800);
        contentBox.setMaxWidth(Double.MAX_VALUE);
        contentBox.setFillHeight(true);
        
        // Partie gauche : liste des cartes
        VBox leftPanel = new VBox(20);
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPrefWidth(450); // Réduit à moins de 2/3 de l'écran
        leftPanel.setMinWidth(450);
        leftPanel.setMaxWidth(450);
        leftPanel.setStyle("-fx-background-color: transparent;");
        
        // Créer une ScrollPane pour contenir les cartes
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(450, 500);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        
        // Créer un conteneur vertical pour les cartes
        VBox cardsContainer = new VBox(15);
        cardsContainer.setPadding(new Insets(10));
        cardsContainer.setStyle("-fx-background-color: transparent;");
        
        // Ajouter les cartes au conteneur
        for (Monstre card : availableCards) {
            VBox cardBox = createCardBox(card, customFont);
            cardsContainer.getChildren().add(cardBox);
        }
        
        scrollPane.setContent(cardsContainer);
        leftPanel.getChildren().add(scrollPane);
        
        // Partie droite : détails de la carte sélectionnée
        cardDetailsBox = new VBox(20);
        cardDetailsBox.setAlignment(Pos.CENTER);
        cardDetailsBox.setPrefWidth(650); // Augmenté à plus de 1/3 de l'écran
        cardDetailsBox.setPrefHeight(700);
        cardDetailsBox.setMinWidth(950);
        cardDetailsBox.setMaxWidth(950); // Limite maximale augmentée
        cardDetailsBox.setMaxHeight(Double.MAX_VALUE);
        cardDetailsBox.setPadding(new Insets(20));
        cardDetailsBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-border-radius: 10; -fx-background-radius: 10;");
        
        Text selectPrompt = new Text("Sélectionnez une carte pour voir ses détails");
        selectPrompt.setFont(customFont);
        selectPrompt.setStyle("-fx-font-size: 16px; -fx-fill: #555;");
        cardDetailsBox.getChildren().add(selectPrompt);
        
        // Afficher les détails de la première carte par défaut si disponible
        if (!availableCards.isEmpty()) {
            showCardDetails(availableCards.get(0), customFont);
        }
        
        contentBox.getChildren().addAll(leftPanel, cardDetailsBox);
        
        // Définir la croissance horizontale pour que cardDetailsBox prenne l'espace disponible
        HBox.setHgrow(cardDetailsBox, Priority.ALWAYS);
        
        Button backButton = new Button("Retour aux boîtes");
        backButton.setFont(customFont);
        backButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");

        backButton.setOnAction(event -> {
            // Retour à l'affichage des boîtes
            showBoxes(customFont);
        });

        listeCarteMenu.getChildren().addAll(title, contentBox, backButton);
    }
    
    /**
     * Affiche la liste des boîtes disponibles
     */
    private void showBoxes(Font customFont) {
        // Effacer le contenu précédent
        listeCarteMenu.getChildren().clear();
        
        // Créer et centrer le titre
        Text title = new Text("Liste des Cartes");
        title.setFont(customFont);
        title.setStyle("-fx-font-size: 30px; -fx-fill: black;");
        
        // Conteneur pour centrer le titre
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);
        titleBox.setPadding(new Insets(20, 0, 30, 0));
        
        // Créer une ScrollPane pour contenir les boîtes
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(800, 500);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        
        // Conteneur principal pour les boîtes
        VBox boxesContainer = new VBox();
        boxesContainer.setAlignment(Pos.CENTER);
        boxesContainer.setPadding(new Insets(20));
        
        // Créer un conteneur pour organiser les boîtes
        GridPane boxesGrid = new GridPane();
        boxesGrid.setHgap(30);
        boxesGrid.setVgap(30);
        boxesGrid.setPadding(new Insets(20));
        boxesGrid.setAlignment(Pos.CENTER);
        
        // Créer la VBox pour Box_1
        VBox boxVBox = createBoxVBox("Box_1", "Box of the beginning", customFont);
        boxesGrid.add(boxVBox, 0, 0);
        
        // Définir l'action au clic sur la boîte
        boxVBox.setOnMouseClicked(event -> {
            // Afficher les cartes disponibles dans cette boîte
            showBoxContent(customFont);
        });
        
        boxesContainer.getChildren().add(boxesGrid);
        scrollPane.setContent(boxesContainer);

        Button backButton = new Button("Retour");
        backButton.setFont(customFont);
        backButton.setStyle("-fx-padding: 10px 20px; -fx-background-color: rgba(255,255,255,0); -fx-border-radius: 10px; -fx-cursor: hand;");

        backButton.setOnAction(event -> {
            listeCarteMenu.setVisible(false);
            mainMenu.setVisible(true);
        });

        // Conteneur pour centrer le bouton
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(backButton);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));

        listeCarteMenu.getChildren().addAll(titleBox, scrollPane, buttonBox);
        listeCarteMenu.setAlignment(Pos.CENTER);
    }
    
    /**
     * Crée une VBox pour représenter une carte dans la liste
     * @param card La carte à représenter
     * @param customFont Police personnalisée
     * @return VBox configurée
     */
    private VBox createCardBox(Monstre card, Font customFont) {
        VBox cardBox = new VBox(5);
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setPadding(new Insets(10));
        cardBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-border-radius: 10; -fx-background-radius: 10;");
        cardBox.setPrefWidth(200);
        cardBox.setCursor(javafx.scene.Cursor.HAND);
        
        // Miniature de l'image de la carte
        ImageView cardThumbnail = new ImageView();
        try {
            if (card.getImagePath() != null) {
                // Essayer d'abord avec Class.getResourceAsStream
                java.io.InputStream inputStream = getClass().getResourceAsStream(card.getImagePath());
                
                // Si null, essayer avec File
                if (inputStream == null) {
                    java.io.File file = new java.io.File("src" + card.getImagePath());
                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        cardThumbnail.setImage(image);
                    } else {
                        throw new Exception("Image de carte non trouvée : " + card.getImagePath());
                    }
                } else {
                    Image image = new Image(inputStream);
                    cardThumbnail.setImage(image);
                }
                
                cardThumbnail.setFitWidth(80);
                cardThumbnail.setFitHeight(120);
                cardThumbnail.setPreserveRatio(true);
            } else {
                throw new Exception("Chemin d'image non défini");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image de la carte: " + e.getMessage());
            // Image de remplacement
            Text placeholder = new Text("?");
            placeholder.setStyle("-fx-font-size: 18px; -fx-fill: #888;");
            cardBox.getChildren().add(placeholder);
        }
        
        // Ajouter le nom de la carte sous l'image
        Text cardName = new Text(card.getName());
        cardName.setFont(customFont);
        cardName.setStyle("-fx-font-size: 20px; -fx-fill: black;");
        
        Text cardType = new Text(card.getType());
        cardType.setFont(customFont);
        cardType.setStyle("-fx-font-size: 12px; -fx-fill: #555;");
        
        cardBox.getChildren().addAll(cardThumbnail, cardName, cardType);
        
        // Définir l'action au clic sur la carte
        cardBox.setOnMouseClicked(event -> {
            showCardDetails(card, customFont);
        });
        
        return cardBox;
    }
    
    /**
     * Affiche les détails d'une carte dans le panneau de droite
     * @param card La carte à afficher
     * @param customFont Police personnalisée
     */
    private void showCardDetails(Monstre card, Font customFont) {
        // Effacer le contenu précédent
        cardDetailsBox.getChildren().clear();
        
        // Titre de la carte en haut
        Text titleText = new Text(card.getName());
        titleText.setFont(customFont);
        titleText.setStyle("-fx-font-size: 36px; -fx-fill: black; -fx-font-weight: bold;");
        
        // Conteneur principal horizontal: image à gauche, infos à droite
        HBox mainLayout = new HBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPrefWidth(345);
        mainLayout.setMaxWidth(345);
        
        // Image de la carte (à gauche)
        VBox imageBox = new VBox(5);
        imageBox.setAlignment(Pos.TOP_CENTER);
        imageBox.setPrefWidth(160);
        imageBox.setMaxWidth(160);
        
        ImageView cardImage = new ImageView();
        try {
            if (card.getImagePath() != null) {
                // Essayer d'abord avec Class.getResourceAsStream
                java.io.InputStream inputStream = getClass().getResourceAsStream(card.getImagePath());
                
                // Si null, essayer avec File
                if (inputStream == null) {
                    java.io.File file = new java.io.File("src" + card.getImagePath());
                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        cardImage.setImage(image);
                    } else {
                        throw new Exception("Image de carte non trouvée : " + card.getImagePath());
                    }
                } else {
                    Image image = new Image(inputStream);
                    cardImage.setImage(image);
                }
                
                cardImage.setFitWidth(660);
                cardImage.setFitHeight(940);
                cardImage.setPreserveRatio(true);
                imageBox.getChildren().add(cardImage);
            } else {
                throw new Exception("Chemin d'image non défini");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image de la carte: " + e.getMessage());
            // Image de remplacement
            Text placeholder = new Text("Image\nnon disponible");
            placeholder.setStyle("-fx-font-size: 20px; -fx-fill: #888;");
            imageBox.getChildren().add(placeholder);
        }
        
        // Conteneur pour toutes les informations à droite
        VBox infoContainer = new VBox(15);
        infoContainer.setAlignment(Pos.TOP_LEFT);
        infoContainer.setPrefWidth(165);
        infoContainer.setMaxWidth(165);
        
        // Propriétés de la carte
        GridPane propertiesGrid = new GridPane();
        propertiesGrid.setHgap(10);
        propertiesGrid.setVgap(8);
        propertiesGrid.setAlignment(Pos.TOP_LEFT);
        
        // Créer des textes pour les propriétés
        Text categoryLabel = new Text("Catégorie:");
        categoryLabel.setFont(customFont);
        categoryLabel.setStyle("-fx-font-size: 18px; -fx-fill: black; -fx-font-weight: bold;");
        
        Text categoryValue = new Text(card.getCategory());
        categoryValue.setFont(customFont);
        categoryValue.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        
        Text typeLabel = new Text("Type:");
        typeLabel.setFont(customFont);
        typeLabel.setStyle("-fx-font-size: 18px; -fx-fill: black; -fx-font-weight: bold;");
        
        Text typeValue = new Text(card.getType());
        typeValue.setFont(customFont);
        typeValue.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        
        Text manaLabel = new Text("Mana:");
        manaLabel.setFont(customFont);
        manaLabel.setStyle("-fx-font-size: 18px; -fx-fill: black; -fx-font-weight: bold;");
        
        Text manaValue = new Text(String.valueOf(card.getMana()));
        manaValue.setFont(customFont);
        manaValue.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        
        Text attackLabel = new Text("Attaque:");
        attackLabel.setFont(customFont);
        attackLabel.setStyle("-fx-font-size: 18px; -fx-fill: black; -fx-font-weight: bold;");
        
        Text attackValue = new Text(String.valueOf(card.getAttack()));
        attackValue.setFont(customFont);
        attackValue.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        
        Text defenseLabel = new Text("Défense:");
        defenseLabel.setFont(customFont);
        defenseLabel.setStyle("-fx-font-size: 18px; -fx-fill: black; -fx-font-weight: bold;");
        
        Text defenseValue = new Text(String.valueOf(card.getDefense()));
        defenseValue.setFont(customFont);
        defenseValue.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        
        Text activeLabel = new Text("Effet actif:");
        activeLabel.setFont(customFont);
        activeLabel.setStyle("-fx-font-size: 18px; -fx-fill: black; -fx-font-weight: bold;");
        
        Text activeValue = new Text(card.isEffectActive() ? "Oui" : "Non");
        activeValue.setFont(customFont);
        activeValue.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        
        // Ajouter les propriétés à la grille (1 colonne)
        int row = 0;
        propertiesGrid.add(categoryLabel, 0, row++);
        propertiesGrid.add(categoryValue, 0, row++);
        propertiesGrid.add(typeLabel, 0, row++);
        propertiesGrid.add(typeValue, 0, row++);
        propertiesGrid.add(manaLabel, 0, row++);
        propertiesGrid.add(manaValue, 0, row++);
        propertiesGrid.add(attackLabel, 0, row++);
        propertiesGrid.add(attackValue, 0, row++);
        propertiesGrid.add(defenseLabel, 0, row++);
        propertiesGrid.add(defenseValue, 0, row++);
        propertiesGrid.add(activeLabel, 0, row++);
        propertiesGrid.add(activeValue, 0, row++);
        
        // Section effet
        VBox effectSection = new VBox(10);
        effectSection.setAlignment(Pos.TOP_LEFT);
        effectSection.setPadding(new Insets(10, 0, 0, 0));
        
        // Titre de la section effet
        Text effectTitle = new Text("Effet:");
        effectTitle.setFont(customFont);
        effectTitle.setStyle("-fx-font-size: 20px; -fx-fill: black; -fx-font-weight: bold;");
        
        // Texte de l'effet
        Text effectText = new Text(card.getEffect());
        effectText.setFont(customFont);
        effectText.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        effectText.setWrappingWidth(160);
        
        // Ajouter les éléments à la section effet
        effectSection.getChildren().addAll(effectTitle, effectText);
        
        // Ajouter les sections au conteneur d'informations
        infoContainer.getChildren().addAll(propertiesGrid, effectSection);
        
        // Ajouter les conteneurs principaux à la disposition horizontale
        mainLayout.getChildren().addAll(imageBox, infoContainer);
        
        // Ajouter le titre et la disposition principale au panneau de détails
        VBox finalContainer = new VBox(15);
        finalContainer.setAlignment(Pos.TOP_CENTER);
        finalContainer.getChildren().addAll(titleText, mainLayout);
        
        // Ajouter le conteneur final au panneau d'informations
        cardDetailsBox.getChildren().add(finalContainer);
    }
    
    /**
     * Crée une VBox contenant une image de boîte et son nom
     * @param imageName Nom de l'image de la boîte
     * @param boxName Nom de la boîte à afficher
     * @param customFont Police personnalisée
     * @return VBox configurée
     */
    private VBox createBoxVBox(String imageName, String boxName, Font customFont) {
        VBox boxVBox = new VBox(10);
        boxVBox.setAlignment(Pos.CENTER);
        boxVBox.setPadding(new Insets(10));
        boxVBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-border-radius: 10; -fx-background-radius: 10;");
        boxVBox.setPrefWidth(200);
        boxVBox.setCursor(javafx.scene.Cursor.HAND);
        
        // Charger l'image de la boîte
        ImageView boxImage = new ImageView();
        try {
            // Essayer d'abord avec Class.getResourceAsStream
            java.io.InputStream inputStream = getClass().getResourceAsStream("/Image/Box/" + imageName + ".png");
            
            // Si null, essayer avec File
            if (inputStream == null) {
                java.io.File file = new java.io.File("src/Image/Box/" + imageName + ".png");
                if (file.exists()) {
                    Image image = new Image(file.toURI().toString());
                    boxImage.setImage(image);
                } else {
                    throw new Exception("Image non trouvée : " + imageName);
                }
            } else {
                Image image = new Image(inputStream);
                boxImage.setImage(image);
            }
            
            boxImage.setFitWidth(150);
            boxImage.setFitHeight(150);
            boxImage.setPreserveRatio(true);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
            // Image de remplacement
            Text placeholder = new Text("Image\nnon disponible");
            placeholder.setStyle("-fx-font-size: 18px; -fx-fill: #888;");
            boxVBox.getChildren().add(placeholder);
        }
        
        // Texte du nom de la boîte
        Text boxNameText = new Text(boxName);
        boxNameText.setFont(customFont);
        boxNameText.setStyle("-fx-font-size: 18px; -fx-fill: black;");
        
        // Ajouter les éléments à la VBox
        boxVBox.getChildren().addAll(boxImage, boxNameText);
        
        return boxVBox;
    }
}