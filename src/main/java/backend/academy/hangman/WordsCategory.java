package backend.academy.hangman;

public interface WordsCategory {

    String getWord(DifficultyLevel difficulty);

    String getDefinition(String word, DifficultyLevel difficulty);

    boolean updateWord(String word, StringBuilder currentWord, char[] guessedLetter);

    void initializeDictionaries();
}
