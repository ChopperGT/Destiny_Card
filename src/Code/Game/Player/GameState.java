package Code.Game.Player;

public class GameState {
    private int level;
    private int score;
    private String playerName;
    private double volume;
    private String resolution;
    private boolean isFullscreen;

    // Constructeur avec des paramètres
    public GameState(int level, int score, String playerName) {
        this.level = level;
        this.score = score;
        this.playerName = playerName;
        this.volume = 1.0; // Valeur par défaut pour le volume
        this.resolution = "1920x1080"; // Valeur par défaut pour la résolution
        this.isFullscreen = false; // Valeur par défaut pour le mode plein écran
    }

    // Constructeur sans paramètres
    public GameState() {
        this.level = 1;
        this.score = 0;
        this.playerName = "Joueur1";
        this.volume = 1.0;
        this.resolution = "1920x1080";
        this.isFullscreen = false;
    }

    // Getters et setters

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        isFullscreen = fullscreen;
    }

    // Méthode toString() pour afficher l'état du jeu
    @Override
    public String toString() {
        return "GameState{" +
                "level=" + level +
                ", score=" + score +
                ", playerName='" + playerName + '\'' +
                ", volume=" + volume +
                ", resolution='" + resolution + '\'' +
                ", isFullscreen=" + isFullscreen +
                '}';
    }
}
