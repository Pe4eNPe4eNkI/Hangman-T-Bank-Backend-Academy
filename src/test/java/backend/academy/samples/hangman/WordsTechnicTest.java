package backend.academy.samples.hangman;

import backend.academy.hangman.DifficultyLevel;
import backend.academy.hangman.WordsCategoryAbstraction;
import backend.academy.hangman.WordsTechnic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordsTechnicTest {

    @Test
    void testTrueChooseWord() {
        WordsCategoryAbstraction wordsTechnic = new WordsTechnic();
        wordsTechnic.initializeDictionaries();
        String easyWord = wordsTechnic.getWord(DifficultyLevel.EASY);
        String mediumWord = wordsTechnic.getWord(DifficultyLevel.MEDIUM);
        String hardWord = wordsTechnic.getWord(DifficultyLevel.HARD);

        assertTrue(WordsTechnic.easyWords().containsKey(easyWord), "Данного слова нет в категории!");
        assertTrue(WordsTechnic.mediumWords().containsKey(mediumWord), "Данного слова нет в категории!");
        assertTrue(WordsTechnic.hardWords().containsKey(hardWord), "Данного слова нет в категории!");
    }

    @Test
    void testCurrentLengthWord() {
        WordsCategoryAbstraction wordsTechnic = new WordsTechnic();
        wordsTechnic.initializeDictionaries();
        int easyWordLength = wordsTechnic.getWord(DifficultyLevel.EASY).length();
        int mediumWordLength = wordsTechnic.getWord(DifficultyLevel.MEDIUM).length();
        int hardWordLength = wordsTechnic.getWord(DifficultyLevel.HARD).length();

        assertTrue(easyWordLength > 0, "Некорректная длина слова!");
        assertTrue(mediumWordLength > 0, "Некорректная длина слова!");
        assertTrue(hardWordLength > 0, "Некорректная длина слова!");
    }
}
