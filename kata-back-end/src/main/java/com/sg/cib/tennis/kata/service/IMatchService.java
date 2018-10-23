package com.sg.cib.tennis.kata.service;

import com.sg.cib.tennis.kata.model.Match;

public interface IMatchService {

    static final String WIN_THE_SET = " win the set.";
    static final String WIN_THE_SET_AND_MATCH = " win the set and match.";
    static final String WIN_THE_GAME = " win the game.";

    /**
     * Effectue le démarage du match.
     * @return l'objet match
     */
    Match start();

    /**
     * Notifie que le joueur d'index passé en paramètre à renporté le point.
     * @param playerNumber L'index du joueur.
     *                     Les valeurs admises sont "1" et "2".
     * @return l'objet match
     */
    Match playerWinsPoint(Integer playerNumber);

    /**
     * Renvoie toutes les informations du match.
     * @return l'objet match
     */
    Match getInfos();

    /**
     * Effectue l'arrêt et la suppression de toutes les données du match..
     * return l'objet match
     */
    Match switchOff();

    /**
     * Permet de définir le nombre de paramètre nécessaire à la victoire.
     * @param nbOfSets le nombre de jeu nécessaire à la victoire.
     *
     * @return l'objet match
     */
    Match setNbOfSetsToWin(Integer nbOfSets);
}
