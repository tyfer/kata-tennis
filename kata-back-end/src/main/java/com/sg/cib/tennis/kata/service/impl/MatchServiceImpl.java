package com.sg.cib.tennis.kata.service.impl;

import com.sg.cib.tennis.kata.model.GameScores;
import com.sg.cib.tennis.kata.model.Match;
import com.sg.cib.tennis.kata.model.Player;
import com.sg.cib.tennis.kata.service.IMatchService;
import org.springframework.stereotype.Component;

@Component
public class MatchServiceImpl implements IMatchService {


    /**
     * L'object match.
     *
     * NB. Il est sur le scope applicatif, puisse variable d'un singleton Spring.
     * Nous choisirons très probablement dans une version postérieur de le stocker en BDD,
     * pour répontre au problématique d'ajournement de match...
     */
    private Match match;

    /**
     * Le nombre de jeu nécessaire à la victoire.
     *
     * Attention: Il y a redondance avec le paramètre de la classe Match.
     * Il s'agit d'une solution provisoire, pour palier au cas ou l'objet match
     * n'existe pas encore...
     *
     * Nous opérerons à un refactoring dans une version postérieure.
     *
     */
    private Integer nbOfSetsTowin = 1;

    @Override
    public Match playerWinsPoint(Integer playerNumber) {
        if(match != null) {
            match.setRefereeMessage(null);
            if (Integer.valueOf(1).equals(playerNumber)) {
                incrementScore(match.getPlayer1(), match.getPlayer2());
            } else if (Integer.valueOf(2).equals(playerNumber)) {
                incrementScore(match.getPlayer2(), match.getPlayer1());
            }
        }
        return match;
    }

    private void incrementScore(Player player, Player opponent) {
        if(match.isTiebreak()){
             incrementTieBreakScore(player, opponent);
        } else {
            incrementNormalGameScore(player, opponent);
        }

    }

    private void incrementTieBreakScore(Player player, Player opponent) {
        player.setTieBreakScore(player.getTieBreakScore() + 1);
        int playerScore = player.getTieBreakScore();
        int opponentScore = opponent.getTieBreakScore();

        if (playerScore >= 7 && (playerScore - opponentScore) >= 2) {
            playerWonTheGame(player, opponent);
            player.setTieBreakScore(null);
            opponent.setTieBreakScore(null);
            match.setTiebreak(false);
        }
    }

    private void incrementNormalGameScore(Player player, Player opponent) {
        if(player != null && opponent != null) {
            switch (player.getGameScore()) {
                case ZERO:
                    player.setGameScore(GameScores.FIFTEEN);
                    break;
                case FIFTEEN:
                    player.setGameScore(GameScores.THIRTY);
                    break;
                case THIRTY:
                    player.setGameScore(GameScores.FORTY);
                    break;
                case FORTY:
                    switch (opponent.getGameScore()) {
                        case FORTY:
                            player.setGameScore(GameScores.ADVANTAGE);
                            break;
                        case ADVANTAGE:
                            player.setGameScore(GameScores.DEUCE);
                            opponent.setGameScore(GameScores.DEUCE);
                            break;
                        case EMPTY:
                            break;
                        default:
                            playerWonTheGame(player, opponent);
                            break;
                    }
                    break;
                case DEUCE:
                    player.setGameScore(GameScores.ADVANTAGE);
                    opponent.setGameScore(GameScores.FORTY);
                    break;
                case ADVANTAGE:
                    playerWonTheGame(player, opponent);
                    break;
            }
        }
    }

    private void playerWonTheGame(Player player, Player opponent) {
        match.setRefereeMessage(player.getName() + WIN_THE_GAME);
        player.setGameScore(GameScores.ZERO);
        opponent.setGameScore(GameScores.ZERO);
        incrementSetScore(player, opponent);

    }

    private void incrementSetScore(Player player, Player opponent) {
        Integer playerCurrentSetScore = player.getSetScores().get(match.getCurrentSetNumber());
        Integer opponentCurrentSetScore = opponent.getSetScores().get(match.getCurrentSetNumber());
        playerCurrentSetScore++;
        player.getSetScores().put(match.getCurrentSetNumber(), playerCurrentSetScore);

        if (playerCurrentSetScore == 6 && opponentCurrentSetScore <= 4 ||
            playerCurrentSetScore == 7 && opponentCurrentSetScore == 5  ||
            playerCurrentSetScore == 7 && opponentCurrentSetScore == 6)  {
            playerWonTheSet(player, opponent);
        } else if (playerCurrentSetScore == 6 && opponentCurrentSetScore == 6)  {
            match.setTiebreak(true);
            player.setTieBreakScore(0);
            opponent.setTieBreakScore(0);
        }
    }

    private void playerWonTheSet(Player player, Player opponent) {
        match.setRefereeMessage(player.getName() + WIN_THE_SET);
        player.getWonSets().add(match.getCurrentSetNumber());

        if (player.getWonSets().size() == nbOfSetsTowin) {
            match.setRefereeMessage(player.getName() + WIN_THE_SET_AND_MATCH);
            player.setGameScore(GameScores.EMPTY);
            opponent.setGameScore(GameScores.EMPTY);
            match.setFinish(true);
        } else {
            match.setCurrentSetNumber(match.getCurrentSetNumber() + 1);
        }

    }

    @Override
    public Match getInfos() {
        return match;
    }


    @Override
    public Match start() {
        this.match = new Match(this.nbOfSetsTowin);
        return match;
    }

    @Override
    public Match switchOff() {
        this.match = null;
        return match;
    }

    @Override
    public Match setNbOfSetsToWin(Integer nbOfSets) {
        this.nbOfSetsTowin = nbOfSets;
        return match;
    }
}
