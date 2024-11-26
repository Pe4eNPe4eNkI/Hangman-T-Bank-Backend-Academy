package backend.academy.samples.hangman;

import backend.academy.hangman.Difficulty;
import backend.academy.hangman.DifficultyLevel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifficultyTest {

    @Test
    void testGetCurrentDifficulty() {
        DifficultyLevel easy = Difficulty.getDifficultyLevel("Легкий");
        DifficultyLevel medium = Difficulty.getDifficultyLevel("Средний");
        DifficultyLevel hard = Difficulty.getDifficultyLevel("Высокий");

        assertEquals(DifficultyLevel.EASY, easy, "Неверный уровень сложности!");
        assertEquals(DifficultyLevel.MEDIUM, medium, "Неверный уровень сложности!");
        assertEquals(DifficultyLevel.HARD, hard, "Неверный уровень сложности!");
    }
}
