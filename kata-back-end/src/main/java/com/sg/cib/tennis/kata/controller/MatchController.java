/**
 * 
 */
package com.sg.cib.tennis.kata.controller;

import com.sg.cib.tennis.kata.model.Match;
import com.sg.cib.tennis.kata.service.IMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class MatchController {

    IMatchService matchService;

    @Autowired
    public MatchController(IMatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
	public String index() {
		return "forward:/index.html";
	}

    @GetMapping("/start")
    @ResponseBody
    public Match start() {
        return matchService.start();
    }

    @GetMapping("/switchOff")
    @ResponseBody
    public Match switchOff() {
        return matchService.switchOff();
    }

    @GetMapping("/getInfos")
    @ResponseBody
    public Match getInfos() {
        return matchService.getInfos();
    }

    @GetMapping("/{playerNumber}/winsPoint")
    @ResponseBody
    public Match playerWinsPoint(@PathVariable("playerNumber") Integer playerNumber) {
        return matchService.playerWinsPoint(playerNumber);
    }

    @GetMapping("/{nbOfSets}/setsToWin")
    @ResponseBody
    public Match setNbOfSetsToWin(@PathVariable("nbOfSets") Integer nbOfSets) {
        return matchService.setNbOfSetsToWin(nbOfSets);
    }

}
