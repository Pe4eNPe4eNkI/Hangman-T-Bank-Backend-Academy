package backend.academy.hangman;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.Getter;

public class Difficulty {

    private Difficulty() {
    }

    @Getter
    static Map<DifficultyLevel, String> difficultyLevelMap = Map.of(
        DifficultyLevel.EASY, "Легкий",
        DifficultyLevel.MEDIUM, "Средний",
        DifficultyLevel.HARD, "Высокий"
    );

    public static DifficultyLevel getDifficultyLevel(String selectedDifficulty) {
        String selectedDifficultyCopy;
        HashMap<String, DifficultyLevel> difficultyLevelMapRevers = new HashMap<>();
        Random random = new Random();
        String[] values = difficultyLevelMap.values().toArray(new String[0]);

        for (HashMap.Entry<DifficultyLevel, String> entry : difficultyLevelMap.entrySet()) {
            difficultyLevelMapRevers.put(entry.getValue(), entry.getKey());
        }

        selectedDifficultyCopy =
            selectedDifficulty.substring(0, 1).toUpperCase() + selectedDifficulty.substring(1).toLowerCase();
        if (difficultyLevelMap().containsValue(selectedDifficulty)) {
            return difficultyLevelMapRevers.get(selectedDifficultyCopy);
        }

        int randomIndex = random.nextInt(difficultyLevelMap().size());
        return difficultyLevelMapRevers.get(values[randomIndex]);
    }
}

