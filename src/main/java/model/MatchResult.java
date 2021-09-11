package model;

public enum MatchResult {
    WIN(2), LOSE(1);

    public int value;

    MatchResult(int value) {
        this.value = value;
    }
}
