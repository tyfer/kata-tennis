package com.sg.cib.tennis.kata.model;

/**
 * Enum de décompte des points d'un jeu de tennis.
 */
public enum GameScores {
    ZERO("  0"),
    FIFTEEN(" 15"),
    THIRTY(" 30"),
    FORTY(" 40"),
    DEUCE("DEUCE"),
    ADVANTAGE("ADV"),
    EMPTY(null);

    /**
     * Valeur d'affichage.
     */
    private String value;

    GameScores(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
