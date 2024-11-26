package backend.academy.samples.hangman;

import backend.academy.hangman.DifficultyLevel;
import backend.academy.hangman.GameSession;
import backend.academy.hangman.Mistake;
import backend.academy.hangman.WordsAnimal;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameSessionTest {

    private WordsAnimal mockWordsAnimal;
    private Scanner mockScanner;
    private GameSession gameSession;
    private Mistake mockMistake;

    @BeforeEach
    public void setUp() {
        mockWordsAnimal = mock(WordsAnimal.class);
        mockScanner = mock(Scanner.class);
        mockMistake = mock(Mistake.class);

        mockMistake.initializeMistakes();
        mockWordsAnimal.initializeDictionaries();
        gameSession = new GameSession();

        gameSession.in(mockScanner);
        gameSession.wordsAbstraction(mockWordsAnimal);
        gameSession.selectedWord("кот");
    }

    void winning() {
        when(mockScanner.nextLine()).thenReturn("Животные", "Легкий", "К", "о", "Т");
        when(mockWordsAnimal.getWord(DifficultyLevel.EASY)).thenReturn("кот");
        when(mockWordsAnimal.getDefinition("кот", DifficultyLevel.EASY)).thenReturn(
            "домашнее животное семейства кошачьих.");
        when(mockWordsAnimal.updateWord(anyString(), any(StringBuilder.class), any(char[].class)))
            .thenAnswer(invocation -> {
                String word = invocation.getArgument(0);
                StringBuilder wordBuilder = invocation.getArgument(1);
                char[] letters = invocation.getArgument(2);

                for (char guessedLetter : letters) {
                    boolean letterFound = false;
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == guessedLetter) {
                            wordBuilder.setCharAt(i, guessedLetter);
                            letterFound = true;
                        }
                    }
                    if (!letterFound) {
                        System.out.println("Такой буквы нет в слове!");
                        return false;
                    }
                }
                return true;
            });
    }

    void losing() {
        when(mockScanner.nextLine()).thenReturn("Животные", "Легкий", "а", "у", "и", "й", "щ", "х", "я", "ф", "ц");
        when(mockWordsAnimal.getWord(DifficultyLevel.EASY)).thenReturn("кот");
        when(mockWordsAnimal.getDefinition("кот", DifficultyLevel.EASY)).thenReturn(
            "мелкое домашнее животное с мягкой шерстью.");
        when(mockWordsAnimal.updateWord(anyString(), any(StringBuilder.class), any(char[].class)))
            .thenAnswer(invocation -> {
                String word = invocation.getArgument(0);
                StringBuilder wordBuilder = invocation.getArgument(1);
                char[] letters = invocation.getArgument(2);

                for (char guessedLetter : letters) {
                    boolean letterFound = false;
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == guessedLetter) {
                            wordBuilder.setCharAt(i, guessedLetter);
                            letterFound = true;
                        }
                    }
                    if (!letterFound) {
                        System.out.println("Такой буквы нет в слове!");
                        return false;
                    }
                }
                return true;
            });
    }

    @Test
    void testCurrentCase() {
        winning();
        gameSession.startGame();
        assertEquals(-1, gameSession.currentWord().indexOf("К"), "Неправильный регистр буквы!");
        assertEquals(-1, gameSession.currentWord().indexOf("О"), "Неправильный регистр буквы!");
        assertEquals(-1, gameSession.currentWord().indexOf("Т"), "Неправильный регистр буквы!");
    }

    @Test
    void testIsWinning() {
        winning();
        gameSession.startGame();
        assertEquals("Вы выиграли!", gameSession.resultStatus(), "Неправильный результат игры!");
    }

    @Test
    void testIsLosing() {
        losing();
        gameSession.startGame();
        assertEquals("Вы проиграли!", gameSession.resultStatus(), "Неправильный результат игры!");
    }

    @Test
    void testCurrentMistakeCount() {
        when(mockScanner.nextLine()).thenReturn("Животные", "Легкий", "а", "у", "и", "й", "щ", "х", "я", "ф",
            "ц", "в", "ь");
        when(mockWordsAnimal.getWord(DifficultyLevel.EASY)).thenReturn("кот");
        when(mockWordsAnimal.getDefinition("кот", DifficultyLevel.EASY)).thenReturn(
            "мелкое домашнее животное с мягкой шерстью.");
        when(mockWordsAnimal.updateWord(anyString(), any(StringBuilder.class), any(char[].class)))
            .thenAnswer(invocation -> {
                String word = invocation.getArgument(0);
                StringBuilder wordBuilder = invocation.getArgument(1);
                char[] letters = invocation.getArgument(2);

                for (char guessedLetter : letters) {
                    boolean letterFound = false;
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) == guessedLetter) {
                            wordBuilder.setCharAt(i, guessedLetter);
                            letterFound = true;
                        }
                    }
                    if (!letterFound) {
                        System.out.println("Такой буквы нет в слове!");
                        return false;
                    }
                }
                return true;
            });
        gameSession.startGame();

        assertEquals(gameSession.currentMaxMistakeCount(), gameSession.unsolvedLetters().size(),
            "Превышено количество ошибок!");
    }

}
