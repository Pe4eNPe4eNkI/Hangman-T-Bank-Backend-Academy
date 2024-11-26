package backend.academy.samples.hangman;

import backend.academy.hangman.DifficultyLevel;
import backend.academy.hangman.WordsAnimal;
import backend.academy.hangman.WordsCategoryAbstraction;
import backend.academy.hangman.WordsNature;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordsAnimalTest {

    @Test
    void testTrueChooseWord() {
        WordsCategoryAbstraction wordsAnimal = new WordsAnimal();
        wordsAnimal.initializeDictionaries();
        String easyWord = wordsAnimal.getWord(DifficultyLevel.EASY);
        String mediumWord = wordsAnimal.getWord(DifficultyLevel.MEDIUM);
        String hardWord = wordsAnimal.getWord(DifficultyLevel.HARD);

        assertTrue(WordsAnimal.easyWords().containsKey(easyWord), "Данного слова нет в категории!");
        assertTrue(WordsAnimal.mediumWords().containsKey(mediumWord), "Данного слова нет в категории!");
        assertTrue(WordsAnimal.hardWords().containsKey(hardWord), "Данного слова нет в категории!");
    }

    @Test
    void testCurrentLengthWord() {
        WordsCategoryAbstraction wordsAnimal = new WordsAnimal();
        wordsAnimal.initializeDictionaries();
        int easyWordLength = wordsAnimal.getWord(DifficultyLevel.EASY).length();
        int mediumWordLength = wordsAnimal.getWord(DifficultyLevel.MEDIUM).length();
        int hardWordLength = wordsAnimal.getWord(DifficultyLevel.HARD).length();

        assertTrue(easyWordLength > 0, "Некорректная длина слова!");
        assertTrue(mediumWordLength > 0, "Некорректная длина слова!");
        assertTrue(hardWordLength > 0, "Некорректная длина слова!");
    }
}
