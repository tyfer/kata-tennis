package com.sg.cib.tennis.kata.model;

public class Match {

    /**
     * Numéro du set courrant.
     */
    private Integer currentSetNumber;

    /**
     *  Le premier joueur.
     *  (voire la première équipe en cas de double...).
     */
    private Player player1;

    /**
     *  Le second joueur.
     *  (voire la seconde équipe en cas de double...).
     */
    private Player player2;

    /**
     * Le message de l'arbitre de chaise.
     */
    private String refereeMessage;

    /**
     * Flag qui indique si le jeu en cour est un "jeu décisif".
     */
    private boolean tiebreak;

    /**
     * Flag qui indique si le match est terminé.
     */
    private boolean finish;

    /**
     * Paramètre indiquant le nombre de jeu nécessaire à la victoire.
     */
    private Integer nbOfSetsToWin;


    public Match() {
    }

    /**
     * Construit l'objet match en definissant des valeur par défaut aux noms des joueurs.
     * @param nbOfSetsTowin  le nombre de jeu nécessaire à la victoire
     */
    public Match(Integer nbOfSetsTowin) {
        this("player 1", "player 2", nbOfSetsTowin);
    }

    public Match(String player1Name, String player2Name, Integer nbOfSetsTowin) {
        this.player1 = new Player(player1Name, nbOfSetsTowin);
        this.player2 = new Player(player2Name, nbOfSetsTowin);
        this.currentSetNumber = 1;
        this.nbOfSetsToWin = nbOfSetsTowin;
    }

    public Integer getCurrentSetNumber() {
        return currentSetNumber;
    }

    public void setCurrentSetNumber(Integer currentSetNumber) {
        this.currentSetNumber = currentSetNumber;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public String getRefereeMessage() {
        return refereeMessage;
    }

    public void setRefereeMessage(String refereeMessage) {
        this.refereeMessage = refereeMessage;
    }

    public boolean isTiebreak() {
        return tiebreak;
    }

    public void setTiebreak(boolean tiebreak) {
        this.tiebreak = tiebreak;
    }

    public Integer getNbOfSetsToWin() {
        return nbOfSetsToWin;
    }

    public void setNbOfSetsToWin(Integer nbOfSetsToWin) {
        this.nbOfSetsToWin = nbOfSetsToWin;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
