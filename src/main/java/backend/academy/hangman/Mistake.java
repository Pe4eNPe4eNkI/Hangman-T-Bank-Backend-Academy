package backend.academy.hangman;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mistake {
    Integer firstX;
    Integer lastX;
    Integer firstY;
    Integer lastY;
    char symbol;
    @Getter
    private static HashMap<Integer, Mistake> mistakes = new HashMap<>();
    //CHECKSTYLE:OFF
    @Getter
    int currentEasyMistakeCount = 9;
    @Getter
    int currentMediumMistakeCount = 7;
    @Getter
    int currentHardMistakeCount = 5;

    //CHECKSTYLE:ON
    public Mistake() {
    }

    public Mistake(Integer firstX, Integer lastX, Integer firstY, Integer lastY, char symbol) {
        this.firstX = firstX;
        this.lastX = lastX;
        this.firstY = firstY;
        this.lastY = lastY;
        this.symbol = symbol;
    }

    // пошаговая отрисовка частей виселицы
    public void initializeMistakes() {
        //CHECKSTYLE:OFF
        mistakes.put(MistakeCount.FIRST_MISTAKE.getValue(), new Mistake(1, 1, 1, 6, '|'));
        mistakes.put(MistakeCount.SECOND_MISTAKE.getValue(), new Mistake(0, 5, 0, 0, '_'));
        mistakes.put(MistakeCount.THIRD_MISTAKE.getValue(), new Mistake(5, 5, 1, 1, '|'));
        mistakes.put(MistakeCount.FOURTH_MISTAKE.getValue(), new Mistake(5, 5, 2, 2, '0'));
        mistakes.put(MistakeCount.FIFTH_MISTAKE.getValue(), new Mistake(5, 5, 3, 4, '|'));
        mistakes.put(MistakeCount.SIXTH_MISTAKE.getValue(), new Mistake(4, 4, 3, 3, '/'));
        mistakes.put(MistakeCount.SEVENTH_MISTAKE.getValue(), new Mistake(6, 6, 3, 3, '/'));
        mistakes.put(MistakeCount.EIGHTH_MISTAKE.getValue(), new Mistake(4, 4, 5, 5, '|'));
        mistakes.put(MistakeCount.NINTH_MISTAKE.getValue(), new Mistake(6, 6, 5, 5, '|'));
        //CHECKSTYLE:ON
    }

    public Mistake getMistakes(Integer mistakeCount) {
        return mistakes.get(mistakeCount);
    }
}
