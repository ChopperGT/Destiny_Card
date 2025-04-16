package Code.Save.Appli;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Appli_Save {
    private static final String SAVE_DIRECTORY = "src/Code/Save/Appli";
    private static final String SAVE_FILE_PATH = SAVE_DIRECTORY + "/appli_settings.json";

    private double volume;
    private String resolution;
    private boolean isFullscreen;

    // Constructeur par défaut
    public Appli_Save() {
        this.volume = 1.0;
        this.resolution = "1920x1080";
        this.isFullscreen = false;
    }

    // Constructeur avec paramètres
    public Appli_Save(double volume, String resolution, boolean isFullscreen) {
        this.volume = volume;
        this.resolution = resolution;
        this.isFullscreen = isFullscreen;
    }

    // Getters et Setters
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

    // Méthode pour sauvegarder les paramètres
    public static void saveSettings(Appli_Save settings) {
        Gson gson = new Gson();

        try {
            // Créer le dossier s'il n'existe pas
            Path saveDirPath = Paths.get(SAVE_DIRECTORY);
            if (!Files.exists(saveDirPath)) {
                Files.createDirectories(saveDirPath);
            }

            // Sauvegarder les paramètres dans le fichier JSON
            try (FileWriter writer = new FileWriter(SAVE_FILE_PATH)) {
                gson.toJson(settings, writer);
                System.out.println("Paramètres sauvegardés avec succès !");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la sauvegarde des paramètres.");
        }
    }

    // Méthode pour charger les paramètres
    public static Appli_Save loadSettings() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(SAVE_FILE_PATH)) {
            return gson.fromJson(reader, Appli_Save.class);
        } catch (IOException e) {
            System.out.println("Aucun fichier de paramètres trouvé. Utilisation des paramètres par défaut.");
            return new Appli_Save();
        }
    }
} 