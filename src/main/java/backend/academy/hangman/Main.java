package backend.academy.hangman;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        GameSession gameSession = new GameSession();
        gameSession.startGame();
    }
}
