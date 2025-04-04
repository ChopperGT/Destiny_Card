package Code.Game.Player;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import Code.Game.Player.GameState;

public class GameStateManager {
    private static final String SAVE_DIRECTORY = "src/Code/Game/Player/Save";
    private static final String SAVE_FILE_PATH = SAVE_DIRECTORY + "/game_state.json";

    public static void saveGameState(GameState gameState) {
        Gson gson = new Gson();

        try {
            Path saveDirPath = Paths.get(SAVE_DIRECTORY);
            if (!Files.exists(saveDirPath)) {
                Files.createDirectories(saveDirPath);
            }

            try (FileWriter writer = new FileWriter(SAVE_FILE_PATH)) {
                gson.toJson(gameState, writer);
                System.out.println("Sauvegarde réussie !");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la sauvegarde.");
        }
    }

    public static GameState loadGameState() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(SAVE_FILE_PATH)) {
            return gson.fromJson(reader, GameState.class);
        } catch (IOException e) {
            System.out.println("Aucune sauvegarde existante.");
            return new GameState();
        }
    }
}
