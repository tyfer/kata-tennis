package com.sg.cib.tennis.kata.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Player {
    /**
     * Nom du joueur (voire de l'équpe).
     */
    private String name;

    /**
     * Score du joueur dans le jeu en cours.
     * A condition que ce dernier ne soit pas un jeu décisif.
     */
    private GameScores gameScore;

    /**
     * Score du joueur dans un jeu décisif.
     */
    private Integer tieBreakScore;

    /**
     * Liste contenant les numéros des sets gagnés par le joueur.
     */
    private List<Integer> wonSets;

    /**
     * Map des nombres de jeux gagnés par le joueur pour chacun des set du match.
     * La clée est donc le numéro du set.
     */
    private Map<Integer, Integer> setScores;

    public Player() {
    }

    public Player(String name, Integer nbOfSetsTowin) {
        this.name = name;
        this.gameScore = GameScores.ZERO;
        this.setScores = new HashMap<>();
        if(nbOfSetsTowin != null) {
            if (nbOfSetsTowin >= 1) {
                setScores.put(1, 0);
            }
            if (nbOfSetsTowin >= 2) {
                setScores.put(2, 0);
                setScores.put(3, 0);
            }
            if (nbOfSetsTowin == 3) {
                setScores.put(4, 0);
                setScores.put(5, 0);
            }
        }
        this.wonSets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonSerialize(using = GameStoresSerializer.class)
    public GameScores getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScores gameScore) {
        this.gameScore = gameScore;
    }

    public Map<Integer, Integer> getSetScores() {
        return setScores;
    }

    public void setSetScores(Map<Integer, Integer> setScores) {
        this.setScores = setScores;
    }

    public Integer getTieBreakScore() {
        return tieBreakScore;
    }

    public void setTieBreakScore(Integer tieBreakScore) {
        this.tieBreakScore = tieBreakScore;
    }

    public List<Integer> getWonSets() {
        return wonSets;
    }

    public void setWonSets(List<Integer> wonSets) {
        this.wonSets = wonSets;
    }
}
