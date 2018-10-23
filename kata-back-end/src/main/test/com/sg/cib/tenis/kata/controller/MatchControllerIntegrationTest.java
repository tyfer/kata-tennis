
package com.sg.cib.tenis.kata.controller;

import com.sg.cib.tennis.kata.Application;
import com.sg.cib.tennis.kata.controller.MatchController;
import com.sg.cib.tennis.kata.model.Match;
import com.sg.cib.tennis.kata.service.IMatchService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MatchControllerIntegrationTest {

    private MockMvc mockMvc;
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    @Autowired
    IMatchService matchService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new MatchController(matchService)).build();
    }

    @Test
    public void getMatchInfos1() throws Exception {
        Match matchFromService = matchService.getInfos();

        MvcResult result = this.mockMvc
                .perform(get("/getInfos"))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.currentSetNumber", is(matchFromService.getCurrentSetNumber())))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(matchFromService.getNbOfSetsToWin())))
                .andExpect( jsonPath("$.player1.name", is(matchFromService.getPlayer1().getName())))
                .andExpect( jsonPath("$.player1.gameScore", is(matchFromService.getPlayer1().getGameScore().getValue())))
                .andExpect( jsonPath("$.player1.setScores.1", is(matchFromService.getPlayer1().getSetScores().get(1))))
                .andExpect( jsonPath("$.player1.setScores.2").doesNotExist())
                .andExpect( jsonPath("$.player1.setScores.3").doesNotExist())
                .andExpect( jsonPath("$.player1.setScores.4").doesNotExist())
                .andExpect( jsonPath("$.player1.setScores.5").doesNotExist())
                .andExpect( jsonPath("$.player2.name", is(matchFromService.getPlayer2().getName())))
                .andExpect( jsonPath("$.player2.gameScore", is(matchFromService.getPlayer2().getGameScore().getValue())))
                .andExpect( jsonPath("$.player2.setScores.1", is(matchFromService.getPlayer2().getSetScores().get(1))))
                .andExpect( jsonPath("$.player2.setScores.2").doesNotExist())
                .andExpect( jsonPath("$.player2.setScores.3").doesNotExist())
                .andExpect( jsonPath("$.player2.setScores.4").doesNotExist())
                .andExpect( jsonPath("$.player2.setScores.5").doesNotExist())
                .andReturn();
        Assertions.assertThat(matchFromService.getPlayer1().getSetScores().get(2)).isNull();
        Assertions.assertThat(matchFromService.getPlayer1().getSetScores().get(3)).isNull();
        Assertions.assertThat(matchFromService.getPlayer1().getSetScores().get(4)).isNull();
        Assertions.assertThat(matchFromService.getPlayer1().getSetScores().get(5)).isNull();
        Assertions.assertThat(matchFromService.getPlayer2().getSetScores().get(2)).isNull();
        Assertions.assertThat(matchFromService.getPlayer2().getSetScores().get(3)).isNull();
        Assertions.assertThat(matchFromService.getPlayer2().getSetScores().get(4)).isNull();
        Assertions.assertThat(matchFromService.getPlayer2().getSetScores().get(5)).isNull();
    }

    @Test
    public void startMatch() throws Exception {
        this.mockMvc
                .perform(get("/start"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player1.setScores.1", is(0)))
                .andExpect( jsonPath("$.player1.setScores.2").doesNotExist())
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.setScores.1", is(0)))
                .andExpect( jsonPath("$.player2.setScores.2").doesNotExist());

        System.out.println(matchService.getInfos().getCurrentSetNumber());
    }

    @Test
    public void sprint1UserStory1() throws Exception {
        this.mockMvc.perform(get("/start"))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")));

        this.mockMvc.perform(get("/1/winsPoint"))
        .andExpect( jsonPath("$.player1.gameScore", is(" 15")))
        .andExpect( jsonPath("$.player2.gameScore", is("  0")));

        this.mockMvc.perform(get("/1/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is(" 30")))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")));

        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is(" 30")))
                .andExpect( jsonPath("$.player2.gameScore", is(" 15")));

        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is(" 30")))
                .andExpect( jsonPath("$.player2.gameScore", is(" 30")));

        this.mockMvc.perform(get("/1/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is(" 40")))
                .andExpect( jsonPath("$.player2.gameScore", is(" 30")));

        this.mockMvc.perform(get("/1/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.refereeMessage", is("player 1" + IMatchService.WIN_THE_GAME)));
    }

    @Test
    public void sprint1UserStory2() throws Exception {
        this.mockMvc.perform(get("/start"));

        for (int i = 0; i < 3; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
            this.mockMvc.perform(get("/2/winsPoint"));
        }

        this.mockMvc.perform(get("/getInfos"))
                .andExpect( jsonPath("$.player1.gameScore", is(" 40")))
                .andExpect( jsonPath("$.player2.gameScore", is(" 40")));

        this.mockMvc.perform(get("/1/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is("ADV")))
                .andExpect( jsonPath("$.player2.gameScore", is(" 40")));

        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is("DEUCE")))
                .andExpect( jsonPath("$.player2.gameScore", is("DEUCE")));

        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is(" 40")))
                .andExpect( jsonPath("$.player2.gameScore", is("ADV")));

        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.refereeMessage", is("player 2" + IMatchService.WIN_THE_GAME)));
    }

    @Test
    public void sprint2UserStory1() throws Exception {
        this.mockMvc.perform(get("/start"));

        for (int i = 0; i < 20; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
        }

        for (int i = 0; i < 27; i++){
            this.mockMvc.perform(get("/2/winsPoint"));
        }

        this.mockMvc.perform(get("/getInfos"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(false)))
                .andExpect( jsonPath("$.finish", is(false)))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player1.setScores.1", is(5)))
                .andExpect( jsonPath("$.player2.gameScore", is(" 40")))
                .andExpect( jsonPath("$.player2.setScores.1", is(6)))
                .andExpect( jsonPath("$.refereeMessage").doesNotExist());

        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(false)))
                .andExpect( jsonPath("$.finish", is(true)))
                .andExpect( jsonPath("$.player1.gameScore").doesNotExist())
                .andExpect( jsonPath("$.player1.setScores.1", is(5)))
                .andExpect( jsonPath("$.player2.gameScore").doesNotExist())
                .andExpect( jsonPath("$.player2.setScores.1", is(7)))
                .andExpect( jsonPath("$.refereeMessage", is("player 2" + IMatchService.WIN_THE_SET_AND_MATCH)));
    }

    @Test
    public void sprint2UserStory2_1() throws Exception {
        this.mockMvc.perform(get("/start"));

        for (int i = 0; i < 20; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
        }

        for (int i = 0; i < 24; i++){
            this.mockMvc.perform(get("/2/winsPoint"));
        }

        for (int i = 0; i < 4; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
        }

        this.mockMvc.perform(get("/getInfos"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(true)))
                .andExpect( jsonPath("$.finish", is(false)))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player1.tieBreakScore", is(0)))
                .andExpect( jsonPath("$.player1.setScores.1", is(6)))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.tieBreakScore", is(0)))
                .andExpect( jsonPath("$.player2.setScores.1", is(6)))
                .andExpect( jsonPath("$.refereeMessage", is("player 1" + IMatchService.WIN_THE_GAME)));

        for (int i = 0; i < 5; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
            this.mockMvc.perform(get("/2/winsPoint"));
        }
        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(true)))
                .andExpect( jsonPath("$.finish", is(false)))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player1.tieBreakScore", is(5)))
                .andExpect( jsonPath("$.player1.setScores.1", is(6)))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.tieBreakScore", is(6)))
                .andExpect( jsonPath("$.player2.setScores.1", is(6)))
                .andExpect( jsonPath("$.refereeMessage").doesNotExist());


        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(false)))
                .andExpect( jsonPath("$.finish", is(true)))
                .andExpect( jsonPath("$.player1.gameScore").doesNotExist())
                .andExpect( jsonPath("$.player1.tieBreakScore").doesNotExist())
                .andExpect( jsonPath("$.player1.setScores.1", is(6)))
                .andExpect( jsonPath("$.player2.gameScore").doesNotExist())
                .andExpect( jsonPath("$.player2.tieBreakScore").doesNotExist())
                .andExpect( jsonPath("$.player2.setScores.1", is(7)))
                .andExpect( jsonPath("$.refereeMessage", is("player 2" + IMatchService.WIN_THE_SET_AND_MATCH)));
    }

    @Test
    public void sprint2UserStory2_2() throws Exception {
        this.mockMvc.perform(get("/start"));

        for (int i = 0; i < 20; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
        }

        for (int i = 0; i < 24; i++){
            this.mockMvc.perform(get("/2/winsPoint"));
        }

        for (int i = 0; i < 4; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
        }

        this.mockMvc.perform(get("/getInfos"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(true)))
                .andExpect( jsonPath("$.finish", is(false)))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player1.tieBreakScore", is(0)))
                .andExpect( jsonPath("$.player1.setScores.1", is(6)))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.tieBreakScore", is(0)))
                .andExpect( jsonPath("$.player2.setScores.1", is(6)))
                .andExpect( jsonPath("$.refereeMessage", is("player 1" + IMatchService.WIN_THE_GAME)));

        for (int i = 0; i < 5; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
            this.mockMvc.perform(get("/2/winsPoint"));
        }
        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(true)))
                .andExpect( jsonPath("$.finish", is(false)))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player1.tieBreakScore", is(5)))
                .andExpect( jsonPath("$.player1.setScores.1", is(6)))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.tieBreakScore", is(6)))
                .andExpect( jsonPath("$.player2.setScores.1", is(6)))
                .andExpect( jsonPath("$.refereeMessage").doesNotExist());

        for (int i = 0; i < 10; i++){
            this.mockMvc.perform(get("/1/winsPoint"));
            this.mockMvc.perform(get("/2/winsPoint"));
        }

        this.mockMvc.perform(get("/getInfos"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(true)))
                .andExpect( jsonPath("$.finish", is(false)))
                .andExpect( jsonPath("$.player1.gameScore", is("  0")))
                .andExpect( jsonPath("$.player1.tieBreakScore", is(15)))
                .andExpect( jsonPath("$.player1.setScores.1", is(6)))
                .andExpect( jsonPath("$.player2.gameScore", is("  0")))
                .andExpect( jsonPath("$.player2.tieBreakScore", is(16)))
                .andExpect( jsonPath("$.player2.setScores.1", is(6)))
                .andExpect( jsonPath("$.refereeMessage").doesNotExist());


        this.mockMvc.perform(get("/2/winsPoint"))
                .andExpect( jsonPath("$.currentSetNumber", is(1)))
                .andExpect( jsonPath("$.nbOfSetsToWin", is(1)))
                .andExpect( jsonPath("$.tiebreak", is(false)))
                .andExpect( jsonPath("$.finish", is(true)))
                .andExpect( jsonPath("$.player1.gameScore").doesNotExist())
                .andExpect( jsonPath("$.player1.tieBreakScore").doesNotExist())
                .andExpect( jsonPath("$.player1.setScores.1", is(6)))
                .andExpect( jsonPath("$.player2.gameScore").doesNotExist())
                .andExpect( jsonPath("$.player2.tieBreakScore").doesNotExist())
                .andExpect( jsonPath("$.player2.setScores.1", is(7)))
                .andExpect( jsonPath("$.refereeMessage", is("player 2" + IMatchService.WIN_THE_SET_AND_MATCH)));
    }


}
