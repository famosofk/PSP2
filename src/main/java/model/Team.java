package model;

public class Team {

    private final int number;
    private int score = 0;
    private int madePoints = 0;
    private int sufferedPoints = 0;
    private double averagePoints = 0.0;

    /**
     * Complexidade: 1. Método sequencial
     **/
    public Team(int number) {
        this.number = number;
    }

    /**
     * Complexidade: 1. Método sequencial
     **/
    public int getNumber() {
        return number;
    }

    /**
     * Complexidade: 2.
     * o if é utilizado para ver se o valor já foi calculado. +1
     **/
    public double getAverageScore() {
        if (averagePoints == 0.0) {
            averagePoints = sufferedPoints != 0 ? (double) madePoints / (double) sufferedPoints : (double) madePoints;
        }
        return averagePoints;
    }

    /**
     * Complexidade: 1. Método sequencial
     **/
    public int getScore() {
        return score;
    }

    /**
     * Complexidade: 1. Método sequencial
     **/
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Complexidade: 1. Método sequencial
     **/
    public int getMadePoints() {
        return madePoints;
    }

    /**
     * Complexidade: 1. Método sequencial
     **/
    public void addMadePoints(int madePoints) {
        this.madePoints += madePoints;
    }

    /**
     * Complexidade: 1. Método sequencial
     **/
    public int getSufferedPoints() {
        return sufferedPoints;
    }

    /**
     * Complexidade: 1. Método sequencial
     **/
    public void addSufferedPoints(int sufferedPoints) {
        this.sufferedPoints += sufferedPoints;
    }
}