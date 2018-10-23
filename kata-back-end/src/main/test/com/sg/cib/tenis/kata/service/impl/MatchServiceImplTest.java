package com.sg.cib.tenis.kata.service.impl;

import com.sg.cib.tennis.kata.model.GameScores;
import com.sg.cib.tennis.kata.model.Player;
import com.sg.cib.tennis.kata.service.IMatchService;
import com.sg.cib.tennis.kata.service.impl.MatchServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.fest.reflect.core.Reflection.method;


@SpringBootTest(classes = { MatchServiceImpl.class })
public class MatchServiceImplTest {

    MatchServiceImpl matchServiceImpl;

    @Before
    public void setup() {
        matchServiceImpl = new MatchServiceImpl();
    }

    @Test
    public void incrementScore1() {
        // GIVEN
        matchServiceImpl.start();
        Player player = matchServiceImpl.getInfos().getPlayer1();
        Player opponent = matchServiceImpl.getInfos().getPlayer2();

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), " 15");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), " 30");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), " 40");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), "  0");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(1));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(matchServiceImpl.getInfos().getRefereeMessage(), player.getName() + IMatchService.WIN_THE_GAME);
    }

    @Test
    public void incrementScore2() {
        // GIVEN
        matchServiceImpl.start();
        Player player = matchServiceImpl.getInfos().getPlayer1();
        Player opponent = matchServiceImpl.getInfos().getPlayer2();

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), " 40");
        Assert.assertEquals(opponent.getGameScore().getValue(), " 40");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), "ADV");
        Assert.assertEquals(opponent.getGameScore().getValue(), " 40");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), "DEUCE");
        Assert.assertEquals(opponent.getGameScore().getValue(), "DEUCE");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), "ADV");
        Assert.assertEquals(opponent.getGameScore().getValue(), " 40");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), "  0");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(1));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(0));
        Assert.assertEquals(matchServiceImpl.getInfos().getRefereeMessage(), player.getName() + IMatchService.WIN_THE_GAME);
    }

    @Test
    public void incrementScore3() {
        // GIVEN
        matchServiceImpl.start();
        matchServiceImpl.getInfos().getPlayer1().getSetScores().put(1, 6);
        matchServiceImpl.getInfos().getPlayer2().getSetScores().put(1, 5);
        Player player = matchServiceImpl.getInfos().getPlayer1();
        Player opponent = matchServiceImpl.getInfos().getPlayer2();

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), " 15");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(5));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), " 30");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(5));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), " 40");
        Assert.assertEquals(opponent.getGameScore().getValue(), "  0");
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(5));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getGameScore().getValue(), null);
        Assert.assertEquals(opponent.getGameScore().getValue(), null);
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(7));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(5));
        Assert.assertEquals(matchServiceImpl.getInfos().getRefereeMessage(), player.getName() + IMatchService.WIN_THE_SET_AND_MATCH);
    }

    @Test
    public void incrementScore4() {
        // GIVEN
        matchServiceImpl.start();
        matchServiceImpl.getInfos().getPlayer1().getSetScores().put(1, 6);
        matchServiceImpl.getInfos().getPlayer2().getSetScores().put(1, 5);
        matchServiceImpl.getInfos().getPlayer1().setGameScore(GameScores.ZERO);
        matchServiceImpl.getInfos().getPlayer2().setGameScore(GameScores.FORTY);
        Player player = matchServiceImpl.getInfos().getPlayer1();
        Player opponent = matchServiceImpl.getInfos().getPlayer2();

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), Integer.valueOf(0));
        Assert.assertEquals(opponent.getTieBreakScore(), Integer.valueOf(0));
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), Integer.valueOf(1));
        Assert.assertEquals(opponent.getTieBreakScore(), Integer.valueOf(0));
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));

        // WHEN
        for (int i = 0; i < 5; i++) {
            method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        }
        for (int i = 0; i < 5; i++) {
            method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        }
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), Integer.valueOf(6));
        Assert.assertEquals(opponent.getTieBreakScore(), Integer.valueOf(5));
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), null);
        Assert.assertEquals(opponent.getTieBreakScore(), null);
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(7));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(matchServiceImpl.getInfos().getRefereeMessage(), player.getName() + IMatchService.WIN_THE_SET_AND_MATCH);
    }

    @Test
    public void incrementScore5() {
        // GIVEN
        matchServiceImpl.start();
        matchServiceImpl.getInfos().getPlayer1().getSetScores().put(1, 6);
        matchServiceImpl.getInfos().getPlayer2().getSetScores().put(1, 5);
        matchServiceImpl.getInfos().getPlayer1().setGameScore(GameScores.ZERO);
        matchServiceImpl.getInfos().getPlayer2().setGameScore(GameScores.FORTY);
        Player player = matchServiceImpl.getInfos().getPlayer1();
        Player opponent = matchServiceImpl.getInfos().getPlayer2();

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), Integer.valueOf(0));
        Assert.assertEquals(opponent.getTieBreakScore(), Integer.valueOf(0));
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), Integer.valueOf(1));
        Assert.assertEquals(opponent.getTieBreakScore(), Integer.valueOf(0));
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));

        // WHEN
        for (int i = 0; i < 5; i++) {
            method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        }
        for (int i = 0; i < 5; i++) {
            method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
        }
        for (int i = 0; i < 10; i++) {
            method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(opponent, player);
            method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        }
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), Integer.valueOf(16));
        Assert.assertEquals(opponent.getTieBreakScore(), Integer.valueOf(15));
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));

        // WHEN
        method("incrementScore").withParameterTypes(Player.class, Player.class).in(matchServiceImpl).invoke(player, opponent);
        // THEN
        Assert.assertEquals(player.getTieBreakScore(), null);
        Assert.assertEquals(opponent.getTieBreakScore(), null);
        Assert.assertEquals(player.getSetScores().get(1), Integer.valueOf(7));
        Assert.assertEquals(opponent.getSetScores().get(1), Integer.valueOf(6));
        Assert.assertEquals(matchServiceImpl.getInfos().getRefereeMessage(), player.getName() + IMatchService.WIN_THE_SET_AND_MATCH);
    }

}
