package backend.academy.hangman;

public enum MistakeCount {
    FIRST_MISTAKE,
    SECOND_MISTAKE,
    THIRD_MISTAKE,
    FOURTH_MISTAKE,
    FIFTH_MISTAKE,
    SIXTH_MISTAKE,
    SEVENTH_MISTAKE,
    EIGHTH_MISTAKE,
    NINTH_MISTAKE;

    public Integer getValue() {
        return ordinal() + 1;
    }
}
