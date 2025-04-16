package Code.Game.Gameplay.Card;

import javafx.scene.image.Image;

public class Card_Skel {
    // Attributs de la carte
    private String name;
    private String category; // Contiendra uniquement Monstre, Magie, Leader
    private String type;     // Contiendra uniquement Ténèbre, Lumière, Eau, Plante, Feu
    private String effect;
    private boolean effectActive; // True = effet actif, False = effet désactivé
    private int attack;      // Valeur d'attaque (uniquement pour les monstres)
    private int defense;     // Valeur de défense (uniquement pour les monstres)
    private int mana;        // Coût en mana pour jouer la carte
    private String imagePath; // Chemin vers l'image de la carte
    private Image image;      // L'image de la carte (lazy loading)
    
    // Types d'éléments valides
    public static final String TYPE_TENEBRE = "Ténèbre";
    public static final String TYPE_LUMIERE = "Lumière";
    public static final String TYPE_EAU = "Eau";
    public static final String TYPE_PLANTE = "Plante";
    public static final String TYPE_FEU = "Feu";
    
    // Catégories valides
    public static final String CATEGORY_MONSTRE = "Monstre";
    public static final String CATEGORY_MAGIE = "Magie";
    public static final String CATEGORY_LEADER = "Leader";

    // Constructeur pour initialiser une carte avec les informations de base
    public Card_Skel(String name, String category, String type, String effect) {
        this(name, category, type, effect, true, 0, 0, 0, null);
    }
    
    // Constructeur complet avec l'état de l'effet
    public Card_Skel(String name, String category, String type, String effect, boolean effectActive) {
        this(name, category, type, effect, effectActive, 0, 0, 0, null);
    }
    
    // Constructeur spécifique pour les cartes Monstre
    public Card_Skel(String name, String type, String effect, int attack, int defense) {
        this(name, CATEGORY_MONSTRE, type, effect, true, attack, defense, 0, null);
    }
    
    // Constructeur pour les cartes Monstre avec mana
    public Card_Skel(String name, String type, String effect, int attack, int defense, int mana) {
        this(name, CATEGORY_MONSTRE, type, effect, true, attack, defense, mana, null);
    }
    
    // Constructeur pour les cartes Monstre avec mana et image
    public Card_Skel(String name, String type, String effect, int attack, int defense, int mana, String imagePath) {
        this(name, CATEGORY_MONSTRE, type, effect, true, attack, defense, mana, imagePath);
    }
    
    // Constructeur pour les cartes non-Monstre avec mana
    public Card_Skel(String name, String category, String type, String effect, int mana) {
        this(name, category, type, effect, true, 0, 0, mana, null);
    }
    
    // Constructeur pour les cartes non-Monstre avec mana et image
    public Card_Skel(String name, String category, String type, String effect, int mana, String imagePath) {
        this(name, category, type, effect, true, 0, 0, mana, imagePath);
    }
    
    // Constructeur complet avec tous les attributs
    public Card_Skel(String name, String category, String type, String effect, boolean effectActive, int attack, int defense, int mana, String imagePath) {
        this.name = name;
        
        // Vérifier que la catégorie est valide
        if (isValidCategory(category)) {
            this.category = category;
        } else {
            throw new IllegalArgumentException("Catégorie invalide. La catégorie doit être : Monstre, Magie ou Leader.");
        }
        
        // Vérifier que le type est valide
        if (isValidType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type invalide. Le type doit être : Ténèbre, Lumière, Eau, Plante ou Feu.");
        }
        
        this.effect = effect;
        this.effectActive = effectActive;
        
        // Définir les valeurs d'attaque et de défense
        if (category.equals(CATEGORY_MONSTRE)) {
            this.attack = attack;
            this.defense = defense;
        } else {
            this.attack = 0;
            this.defense = 0;
        }
        
        // Définir le coût en mana (doit être positif ou nul)
        if (mana >= 0) {
            this.mana = mana;
        } else {
            throw new IllegalArgumentException("Le coût en mana doit être un nombre positif ou nul.");
        }
        
        // Définir le chemin de l'image
        this.imagePath = imagePath;
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
        if (isValidCategory(category)) {
            this.category = category;
            
            // Réinitialiser attaque et défense si la carte n'est plus un monstre
            if (!category.equals(CATEGORY_MONSTRE)) {
                this.attack = 0;
                this.defense = 0;
            }
        } else {
            throw new IllegalArgumentException("Catégorie invalide. La catégorie doit être : Monstre, Magie ou Leader.");
        }
    }
    
    // Vérifier si une catégorie est valide
    public static boolean isValidCategory(String category) {
        return category != null && (
               category.equals(CATEGORY_MONSTRE) || 
               category.equals(CATEGORY_MAGIE) || 
               category.equals(CATEGORY_LEADER)
        );
    }

    // Getter et Setter pour le type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (isValidType(type)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type invalide. Le type doit être : Ténèbre, Lumière, Eau, Plante ou Feu.");
        }
    }

    // Vérifier si un type est valide
    public static boolean isValidType(String type) {
        return type != null && (
               type.equals(TYPE_TENEBRE) || 
               type.equals(TYPE_LUMIERE) || 
               type.equals(TYPE_EAU) || 
               type.equals(TYPE_PLANTE) || 
               type.equals(TYPE_FEU)
        );
    }

    // Getter et Setter pour l'effet
    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
    
    // Getter et Setter pour l'état de l'effet
    public boolean isEffectActive() {
        return effectActive;
    }
    
    public void setEffectActive(boolean effectActive) {
        this.effectActive = effectActive;
    }
    
    // Activer l'effet
    public void activateEffect() {
        this.effectActive = true;
    }
    
    // Désactiver l'effet
    public void deactivateEffect() {
        this.effectActive = false;
    }
    
    // Getter et Setter pour l'attaque
    public int getAttack() {
        return attack;
    }
    
    public void setAttack(int attack) {
        if (category.equals(CATEGORY_MONSTRE)) {
            this.attack = attack;
        } else {
            throw new IllegalStateException("Seules les cartes Monstre peuvent avoir une valeur d'attaque.");
        }
    }
    
    // Getter et Setter pour la défense
    public int getDefense() {
        return defense;
    }
    
    public void setDefense(int defense) {
        if (category.equals(CATEGORY_MONSTRE)) {
            this.defense = defense;
        } else {
            throw new IllegalStateException("Seules les cartes Monstre peuvent avoir une valeur de défense.");
        }
    }
    
    // Getter et Setter pour le mana
    public int getMana() {
        return mana;
    }
    
    public void setMana(int mana) {
        if (mana >= 0) {
            this.mana = mana;
        } else {
            throw new IllegalArgumentException("Le coût en mana doit être un nombre positif ou nul.");
        }
    }
    
    // Getter et Setter pour le chemin de l'image
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        this.image = null; // Réinitialiser l'image pour forcer le rechargement
    }
    
    // Méthode pour charger et obtenir l'image
    public Image getImage() {
        if (image == null && imagePath != null) {
            try {
                // Charger l'image depuis le chemin spécifié
                image = new Image(getClass().getResourceAsStream(imagePath));
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
            }
        }
        return image;
    }
    
    // Vérifier si c'est une carte Monstre
    public boolean isMonstre() {
        return category.equals(CATEGORY_MONSTRE);
    }

    // Méthode toString() pour afficher une carte sous forme de texte
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carte: ").append(name).append("\n");
        sb.append("Catégorie: ").append(category).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Coût en mana: ").append(mana).append("\n");
        
        if (category.equals(CATEGORY_MONSTRE)) {
            sb.append("Attaque: ").append(attack).append("\n");
            sb.append("Défense: ").append(defense).append("\n");
        }
        
        sb.append("Effet: ").append(effect).append("\n");
        sb.append("État de l'effet: ").append(effectActive ? "Actif" : "Désactivé");
        
        if (imagePath != null) {
            sb.append("\nImage: ").append(imagePath);
        }
        
        return sb.toString();
    }
}
