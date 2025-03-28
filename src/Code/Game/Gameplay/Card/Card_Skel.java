package Code.Game.Gameplay.Card;

public class Card_Skel {
    // Attributs de la carte
    private String name;
    private String category;
    private String type;
    private String effect;

    // Constructeur pour initialiser une carte avec les informations de base
    public Card_Skel(String name, String category, String type, String effect) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.effect = effect;
    }

    // Getters et setters pour chaque attribut, permettant de modifier et d'obtenir les informations de la carte

    // Getter et Setter pour le nom
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter et Setter pour la catégorie
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter et Setter pour le type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter et Setter pour l'effet
    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    // Méthode toString() pour afficher une carte sous forme de texte
    @Override
    public String toString() {
        return "Carte: " + name + "\n" +
                "Catégorie: " + category + "\n" +
                "Type: " + type + "\n" +
                "Effet: " + effect;
    }
}
