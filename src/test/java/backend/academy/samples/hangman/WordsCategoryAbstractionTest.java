package backend.academy.samples.hangman;

import backend.academy.hangman.WordsCategoryAbstraction;
import backend.academy.hangman.WordsCategoryEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordsCategoryAbstractionTest {
    @Test
    void getCategoryTest() {
        WordsCategoryEnum animalCategory = WordsCategoryAbstraction.getCategory("Животные");
        WordsCategoryEnum technicCategory = WordsCategoryAbstraction.getCategory("Техника");
        WordsCategoryEnum natureCategory = WordsCategoryAbstraction.getCategory("Природа");

        assertEquals(WordsCategoryEnum.ANIMAL, animalCategory, "Неверная категория слова!");
        assertEquals(WordsCategoryEnum.TECHNIC, technicCategory, "Неверная категория слова!");
        assertEquals(WordsCategoryEnum.NATURE, natureCategory, "Неверная категория слова!");
    }
}
