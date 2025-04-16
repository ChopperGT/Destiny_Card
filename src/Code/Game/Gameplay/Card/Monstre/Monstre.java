package Code.Game.Gameplay.Card.Monstre;

import Code.Game.Gameplay.Card.Card_Skel;

/**
 * Classe représentant une carte de type Monstre
 */
public class Monstre extends Card_Skel {
    
    // Chemin vers l'image de la carte
    private static final String IMAGE_PATH = "/Image/Card/Le défenseur distordu.png";
    private static final String GUERRIER_IMAGE_PATH = "/Image/Card/Le guerrier distordu.png";
    
    /**
     * Constructeur par défaut qui initialise la carte avec ses attributs spécifiques
     */
    public Monstre() {
        super(
            "Le défenseur distordu",  // Nom
            Card_Skel.TYPE_TENEBRE,   // Type (Ténèbre)
            "Protecteur corrompu par les ténèbres.", // Description de l'effet
            2,                        // Attaque
            4,                        // Défense
            3,                        // Mana
            IMAGE_PATH                // Chemin de l'image
        );
        
        // Désactiver l'effet initialement
        this.setEffectActive(false);
    }
    
    /**
     * Crée une instance du Guerrier distordu
     * @return Une nouvelle instance représentant le Guerrier distordu
     */
    public static Monstre creerGuerrierDistordu() {
        Monstre guerrier = new Monstre();
        guerrier.setName("Le guerrier Distordu");
        guerrier.setEffect("Guerrier corrompu par les ténèbres.");
        guerrier.setAttack(4);
        guerrier.setDefense(2);
        guerrier.setImagePath(GUERRIER_IMAGE_PATH);
        return guerrier;
    }
    
    /**
     * Méthode spécifique à cette carte qui pourra être implémentée plus tard
     * pour définir un comportement unique
     */
    public void capaciteSpeciale() {
        // Implémentation future de la capacité spéciale du défenseur
        System.out.println("Le défenseur distordu utilise sa capacité spéciale!");
    }
} 