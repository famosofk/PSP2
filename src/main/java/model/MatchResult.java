package model;

public enum MatchResult {
    WIN(2), LOSE(1);

    public int value;

    /**
     * Complexidade: 1. Construtor do enum é sequêncial.
     **/
    MatchResult(int value) {
        this.value = value;
    }
}
