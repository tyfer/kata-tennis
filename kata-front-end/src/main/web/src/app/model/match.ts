import {Player} from "./player";

export class Match {

    private _currentSetNumber: number;
    private _player1: Player;
    private _player2: Player;
    private _refereeMessage: string;
    private _nbOfSetsToWin: number;
    private _finish: boolean;
    private _tiebreak: boolean;


    constructor() {
        this._player1 = new Player();
        this._player2 = new Player();
    }


    get currentSetNumber(): number {
        return this._currentSetNumber;
    }

    set currentSetNumber(value: number) {
        this._currentSetNumber = value;
    }

    get player1(): Player {
        return this._player1;
    }

    set player1(value: Player) {
        this._player1 = value;
    }

    get player2(): Player {
        return this._player2;
    }

    set player2(value: Player) {
        this._player2 = value;
    }


    get refereeMessage() {
        return this._refereeMessage;
    }

    set refereeMessage(value) {
        this._refereeMessage = value;
    }


    get nbOfSetsToWin() {
        return this._nbOfSetsToWin;
    }

    set nbOfSetsToWin(value) {
        this._nbOfSetsToWin = value;
    }


    get finish(): boolean {
        return this._finish;
    }

    set finish(value: boolean) {
        this._finish = value;
    }


    get tiebreak(): boolean {
        return this._tiebreak;
    }

    set tiebreak(value: boolean) {
        this._tiebreak = value;
    }
}

