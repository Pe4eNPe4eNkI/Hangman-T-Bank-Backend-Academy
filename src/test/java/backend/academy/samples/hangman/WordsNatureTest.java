package backend.academy.samples.hangman;

import backend.academy.hangman.DifficultyLevel;
import backend.academy.hangman.WordsCategoryAbstraction;
import backend.academy.hangman.WordsNature;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordsNatureTest {

    @Test
    void testTrueChooseWord() {
        WordsCategoryAbstraction wordsNature = new WordsNature();
        wordsNature.initializeDictionaries();
        String easyWord = wordsNature.getWord(DifficultyLevel.EASY);
        String mediumWord = wordsNature.getWord(DifficultyLevel.MEDIUM);
        String hardWord = wordsNature.getWord(DifficultyLevel.HARD);

        assertTrue(WordsNature.easyWords().containsKey(easyWord), "Данного слова нет в категории!");
        assertTrue(WordsNature.mediumWords().containsKey(mediumWord), "Данного слова нет в категории!");
        assertTrue(WordsNature.hardWords().containsKey(hardWord), "Данного слова нет в категории!");
    }

    @Test
    void testCurrentLengthWord() {
        WordsCategoryAbstraction wordsNature = new WordsNature();
        wordsNature.initializeDictionaries();
        int easyWordLength = wordsNature.getWord(DifficultyLevel.EASY).length();
        int mediumWordLength = wordsNature.getWord(DifficultyLevel.MEDIUM).length();
        int hardWordLength = wordsNature.getWord(DifficultyLevel.HARD).length();

        assertTrue(easyWordLength > 0, "Некорректная длина слова!");
        assertTrue(mediumWordLength > 0, "Некорректная длина слова!");
        assertTrue(hardWordLength > 0, "Некорректная длина слова!");
    }
}
