package model;

public class Team {
    private int number;
    private int score = 0;
    private int madePoints = 0;
    private int sufferedPoints = 0;
    private double averagePoints = 0.0;

    public Team(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public double getAverageScore() {
        if (averagePoints == 0.0) {
            averagePoints = sufferedPoints != 0 ? (double) madePoints / (double) sufferedPoints : (double) madePoints;
        }
        return averagePoints;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getMadePoints() {
        return madePoints;
    }

    public void addMadePoints(int madePoints) {
        this.madePoints += madePoints;
    }

    public int getSufferedPoints() {
        return sufferedPoints;
    }

    public void addSufferedPoints(int sufferedPoints) {
        this.sufferedPoints += sufferedPoints;
    }
}

