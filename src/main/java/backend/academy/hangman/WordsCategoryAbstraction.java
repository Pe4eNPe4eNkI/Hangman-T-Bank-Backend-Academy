package backend.academy.hangman;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.Getter;

public abstract class WordsCategoryAbstraction implements WordsCategory {
    // словари с разными уровнями сложности
    @Getter
    static HashMap<String, String> easyWords = new HashMap<>();
    @Getter
    static HashMap<String, String> mediumWords = new HashMap<>();
    @Getter
    static HashMap<String, String> hardWords = new HashMap<>();
    @Getter
    static Map<WordsCategoryEnum, String> wordsCategory = Map.of(
        WordsCategoryEnum.NATURE, "Природа",
        WordsCategoryEnum.TECHNIC, "Техника",
        WordsCategoryEnum.ANIMAL, "Животные"
    );

    public String getWord(DifficultyLevel difficulty) {
        Random random = new Random();
        String[] wordsArray = switch (difficulty) {
            case EASY -> WordsCategoryAbstraction.easyWords.keySet().toArray(new String[0]);
            case MEDIUM -> WordsCategoryAbstraction.mediumWords.keySet().toArray(new String[0]);
            case HARD -> WordsCategoryAbstraction.hardWords.keySet().toArray(new String[0]);
        };

        int randomIndex = random.nextInt(wordsArray.length);
        return wordsArray[randomIndex];
    }

    public static WordsCategoryEnum getCategory(String category) {
        String categoryCopy;
        HashMap<String, WordsCategoryEnum> wordsCategoryRevers = new HashMap<>();
        Random random = new Random();
        String[] values = wordsCategory().values().toArray(new String[0]);

        for (HashMap.Entry<WordsCategoryEnum, String> entry : wordsCategory.entrySet()) {
            wordsCategoryRevers.put(entry.getValue(), entry.getKey());
        }
        categoryCopy = category.substring(0, 1).toUpperCase() + category.substring(1).toLowerCase();
        if (wordsCategory().containsValue(category)) {
            return wordsCategoryRevers.get(categoryCopy);
        }

        int randomIndex = random.nextInt(wordsCategory().size());
        return wordsCategoryRevers.get(values[randomIndex]);
    }

    public String getDefinition(String word, DifficultyLevel difficulty) {
        return switch (difficulty) {
            case EASY -> WordsCategoryAbstraction.easyWords.get(word);
            case MEDIUM -> WordsCategoryAbstraction.mediumWords.get(word);
            case HARD -> WordsCategoryAbstraction.hardWords.get(word);
        };
    }

    public boolean updateWord(String word, StringBuilder currentWord, char[] guessedLetter) {
        boolean letterFound = false;
        char firstGuessedLetter = guessedLetter[0];
        // пробегаемся по слову и смотрим, есть ли в нем введенная буква
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == firstGuessedLetter) {
                currentWord.setCharAt(i, firstGuessedLetter);
                letterFound = true;
            }
        }
        if (!letterFound) {
            //CHECKSTYLE:OFF
            System.out.println("Такой буквы нет в слове!");
            //CHECKSTYLE:ON
            return false;
        }
        return true;
    }
}
