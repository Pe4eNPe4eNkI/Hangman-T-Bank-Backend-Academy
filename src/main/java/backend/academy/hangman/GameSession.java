package backend.academy.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;

public class GameSession {
    public GameSession() {
    }

    @Getter
    private StringBuilder currentWord;
    @Setter
    private Scanner in = new Scanner(System.in);
    @Setter
    String selectedWord;
    @Setter
    private WordsCategoryAbstraction wordsAbstraction = new WordsAnimal();
    @Getter
    private String resultStatus = "";
    @Getter
    int currentMaxMistakeCount;
    @Getter
    private final List<String> unsolvedLetters = new ArrayList<>();
    private final List<String> solvedLetters = new ArrayList<>();
    private final char[][] slipknot = {
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        {'_', '_', '_', '_', '_', '_', '_', '_'}
    };

    public void startGame() {
        DifficultyLevel difficultyLvl;

        if (selectedWord == null || selectedWord.isEmpty()) {
            WordsCategoryEnum category = selectWordCategory();
            difficultyLvl = selectDifficulty();

            WordsCategoryAbstraction wordsCategory = switch (category) {
                case NATURE -> new WordsNature();
                case TECHNIC -> new WordsTechnic();
                case ANIMAL -> new WordsAnimal();
            };
            wordsCategory.initializeDictionaries();
            selectedWord = wordsCategory.getWord(difficultyLvl);
        } else {
            // Если слово уже задано, то выбрать сложность (Для автотестов)
            difficultyLvl = selectDifficulty();
        }

        currentWord = new StringBuilder("_".repeat(selectedWord.length()));
        startSession(selectedWord, difficultyLvl, currentWord); // Запуск игровой сессии
    }

    // отрисовка новых элементов виселицы после новой ошибки
    public void updateSession(Integer mistakeCount) {
        Mistake mistake = new Mistake();
        mistake.initializeMistakes();
        Mistake currentMistake = mistake.getMistakes(mistakeCount);
        for (Integer i = currentMistake.firstY; i <= currentMistake.lastY; i++) {
            for (Integer j = currentMistake.firstX; j <= currentMistake.lastX; j++) {
                slipknot[i][j] = currentMistake.symbol();
            }
        }
    }

    public void drawing(Integer mistakeCount, DifficultyLevel difficultyLevel) {
        Integer mistakeCountCopy = mistakeCount;

        /*
            На легком уровне сложности пользователь может совершить 9 ошибок,
            на среднем уровне сложности (2 и 3) и (6 и 7) шаг склеиваются в один,
            на высоком уровне сложности склеиваются в два все, кроме первого шага
        */
        if (mistakeCountCopy != 0) {
            updateSession(mistakeCountCopy);
        }

        if ((Objects.equals(mistakeCountCopy, MistakeCount.SECOND_MISTAKE.getValue())
            || Objects.equals(mistakeCountCopy, MistakeCount.SIXTH_MISTAKE.getValue()))
            && difficultyLevel == DifficultyLevel.MEDIUM) {
            mistakeCountCopy++;
            updateSession(mistakeCountCopy);
        }

        if (!mistakeCountCopy.equals(MistakeCount.FIRST_MISTAKE.getValue())
            && !mistakeCountCopy.equals(0)
            && difficultyLevel == DifficultyLevel.HARD) {
            mistakeCountCopy++;
            updateSession(mistakeCountCopy);
        }

        //CHECKSTYLE:OFF
        System.out.println("-------------------");
        for (char[] chars : slipknot) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
        System.out.println("-------------------");
        //CHECKSTYLE:ON
    }

    public void startSession(String word, DifficultyLevel difficulty, StringBuilder currentWord) {
        char[] guessedLetter;
        //CHECKSTYLE:OFF
        Mistake mistake = new Mistake();
        mistake.initializeMistakes();

        int mistakeCount = 0;
        int currentMistakeCount = 0;
        int maxMistakeCount = Mistake.mistakes().size();
        currentMaxMistakeCount = switch (difficulty) {
            case EASY -> mistake.currentEasyMistakeCount;
            case MEDIUM -> mistake.currentMediumMistakeCount;
            case HARD -> mistake.currentHardMistakeCount;
        };
        //CHECKSTYLE:ON

        // играем пока в слове есть пропуски или попытки не закончились
        while (currentWord.toString().contains("_") && mistakeCount <= maxMistakeCount) {
            drawing(mistakeCount, difficulty);
            //CHECKSTYLE:OFF
            int remainingMistakeCount = currentMaxMistakeCount - currentMistakeCount;
            System.out.println("Осталось " + remainingMistakeCount + " попыток");
            System.out.println("Введенные буквы: " + String.join(", ", unsolvedLetters));
            //CHECKSTYLE:ON
            if (Objects.equals(mistakeCount, MistakeCount.EIGHTH_MISTAKE.getValue())
                && difficulty == DifficultyLevel.HARD) {
                mistakeCount = maxMistakeCount;
            }

            if (mistakeCount < maxMistakeCount) {
                //CHECKSTYLE:OFF
                System.out.println("Определение: " + wordsAbstraction.getDefinition(word, difficulty));
                System.out.println(currentWord);
                System.out.println("Введите букву: ");
                //CHECKSTYLE:ON
                guessedLetter = in.nextLine().toLowerCase().toCharArray();

                // пока пользователь не ввел ровно 1 букву
                while (guessedLetter.length > 1) {
                    //CHECKSTYLE:OFF
                    System.out.println("Введите только один символ!");
                    //CHECKSTYLE:ON
                    guessedLetter = in.nextLine().toLowerCase().toCharArray();
                }

                // если пользователь ввел букву повторно
                isRepeatLetter(unsolvedLetters, guessedLetter);
                isRepeatLetter(solvedLetters, guessedLetter);

                boolean isUpdate = wordsAbstraction.updateWord(word, currentWord, guessedLetter);
                if (isUpdate) {
                    solvedLetters.add(String.valueOf(guessedLetter[0]));
                } else {
                    unsolvedLetters.add(String.valueOf(guessedLetter[0]));
                    mistakeCount = addMistake(mistakeCount, difficulty);
                    currentMistakeCount++;
                }

                //CHECKSTYLE:OFF
                System.out.println(currentWord);
                //CHECKSTYLE:ON
            } else {
                break;
            }
        }
        //CHECKSTYLE:OFF
        if (mistakeCount >= maxMistakeCount) {
            resultStatus = "Вы проиграли!";
        } else {
            resultStatus = "Вы выиграли!";
        }
        //CHECKSTYLE:OFF
        System.out.println(resultStatus);
        //CHECKSTYLE:ON
    }

    public void isRepeatLetter(List<String> letters, char[] guessedLetter) {
        while (letters.contains(String.valueOf(guessedLetter[0]))) {
            //CHECKSTYLE:OFF
            System.out.println("Вы уже вводили букву " + guessedLetter[0] + "!");
            guessedLetter = in.nextLine().toLowerCase().toCharArray();
            //CHECKSTYLE:ON
        }
    }

    public WordsCategoryEnum selectWordCategory() {
        //CHECKSTYLE:OFF
        System.out.println("Выберете категорию слов:");
        for (String category : WordsCategoryAbstraction.wordsCategory.values()) {
            System.out.println(category);
            //CHECKSTYLE:ON
        }
        String selectedCategory = in.nextLine();
        WordsCategoryEnum category = WordsCategoryAbstraction.getCategory(selectedCategory);
        //CHECKSTYLE:OFF
        System.out.println("Категория: " + WordsCategoryAbstraction.wordsCategory().get(category));
        //CHECKSTYLE:ON
        return category;
    }

    public DifficultyLevel selectDifficulty() {
        //CHECKSTYLE:OFF
        System.out.println("Выберете уровень сложности:");
        for (String difficulty : Difficulty.difficultyLevelMap.values()) {
            System.out.println(difficulty);
            //CHECKSTYLE:ON
        }
        String selectDifficulty = in.nextLine();
        DifficultyLevel difficultyLvl = Difficulty.getDifficultyLevel(selectDifficulty);
        //CHECKSTYLE:OFF
        System.out.println("Сложность: " + Difficulty.difficultyLevelMap.get(difficultyLvl));
        //CHECKSTYLE:ON
        return difficultyLvl;
    }

    public Integer addMistake(Integer mistakeCount, DifficultyLevel difficultyLevel) {
        Integer mistakeCountCopy = mistakeCount;
        // на уровне сложности медиум и хард добавляется по 2 ошибки
        int twoMistake = 2;
        if ((Objects.equals(mistakeCountCopy, MistakeCount.SECOND_MISTAKE.getValue())
            || Objects.equals(mistakeCountCopy, MistakeCount.SIXTH_MISTAKE.getValue()))
            && difficultyLevel == DifficultyLevel.MEDIUM) {
            mistakeCountCopy += twoMistake;
        } else if ((
            Objects.equals(mistakeCountCopy, MistakeCount.SECOND_MISTAKE.getValue())
                || Objects.equals(mistakeCountCopy, MistakeCount.FOURTH_MISTAKE.getValue())
                || Objects.equals(mistakeCountCopy, MistakeCount.SIXTH_MISTAKE.getValue()))
            && difficultyLevel == DifficultyLevel.HARD) {
            mistakeCountCopy += twoMistake;
        } else {
            mistakeCountCopy++;
        }
        return mistakeCountCopy;
    }
}
