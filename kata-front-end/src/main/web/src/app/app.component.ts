import {Component, OnInit} from '@angular/core';
import {Match} from "./model/match";
import { AppService } from './app.service';
import {Observable} from "rxjs";
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

    public app_version: string = environment.app_version;

    title = 'Tennis match';
    match = new Match();
    onOffButtonLabel = 'Start a match';
    nbOfSetsEntries = [{libelle: '1', value: 1}, {libelle: '2', value: 2}, {libelle: '3', value: 3}];
    nbOfSets = null;

    constructor(private appService: AppService) { }

    ngOnInit(): void {
        this.appService.getMatchInfos()
            .subscribe(
                data => {
                    this.nbOfSets = this.nbOfSetsEntries[0].value;
                    this.reloadData(data);
                },
                error => console.log('ERROR: ' + error));
    }

    onSelectionChange(nbOfSets: number) {
        this.appService.setNbOfSetsToWin(nbOfSets)
            .subscribe(
                data => {
                    this.nbOfSets = nbOfSets
                    this.reloadData(data);
                },
                error => console.log('ERROR: ' + error));
    }

    togleOnOff(match: Match) {
        if (!match.currentSetNumber || match.currentSetNumber < 0){
            this.onStartMatch();
        } else {
            this.onSwitchOff();
        }
    }

    onStartMatch() {
        this.appService.startMatch()
            .subscribe(
                data => {
                    this.reloadData(data);
                    this.onOffButtonLabel = 'Switch-off';
                },
                error => console.log('ERROR: ' + error));
    }

    onSwitchOff() {
        this.appService.switchOff()
            .subscribe(
                data => {
                    this.reloadData(data);
                    this.onOffButtonLabel = 'Start a match';
                },
                error => console.log('ERROR: ' + error));
    }

    onPlayerWinsPoint(playerNumber: number) {
        this.appService.playerWinsPoint(playerNumber)
            .subscribe(
                data => {
                    this.reloadData(data);
                },
                error => console.log('ERROR: ' + error));
    }

    reloadData(data) {
        console.log(data);
        if(data) {
            Object.assign(this.match, data);

            if (this.match.tiebreak){
                this.match.player1.globalGameScore = String(this.match.player1.tieBreakScore);
                this.match.player2.globalGameScore = String(this.match.player2.tieBreakScore);
            } else {
                this.match.player1.globalGameScore = this.match.player1.gameScore;
                this.match.player2.globalGameScore = this.match.player2.gameScore;
            }
        } else {
            this.match = new Match();
        }
        console.log(this.match);

    }

}