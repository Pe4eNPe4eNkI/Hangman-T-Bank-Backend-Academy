package backend.academy.samples.hangman;

import backend.academy.hangman.Mistake;
import backend.academy.hangman.MistakeCount;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MistakeTest {
    @Test
    void testGetCurrentMistake() {
        Mistake mistake = new Mistake();
        mistake.initializeMistakes();

        assertEquals(mistake.getMistakes(MistakeCount.FIRST_MISTAKE.getValue()), mistake.getMistakes(1),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.SECOND_MISTAKE.getValue()), mistake.getMistakes(2),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.THIRD_MISTAKE.getValue()), mistake.getMistakes(3),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.FOURTH_MISTAKE.getValue()), mistake.getMistakes(4),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.FIFTH_MISTAKE.getValue()), mistake.getMistakes(5),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.SIXTH_MISTAKE.getValue()), mistake.getMistakes(6),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.SEVENTH_MISTAKE.getValue()), mistake.getMistakes(7),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.EIGHTH_MISTAKE.getValue()), mistake.getMistakes(8),
            "Неверный номер ошибки!");
        assertEquals(mistake.getMistakes(MistakeCount.NINTH_MISTAKE.getValue()), mistake.getMistakes(9),
            "Неверный номер ошибки!");
    }
}
