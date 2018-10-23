
export class Player {

    private _name: string ;
    private _gameScore: string;
    private _tieBreakScore: number;
    private _setScores: Map<number,number>;
    private _globalGameScore: string;


    constructor() {
        this.setScores = new Map<number,number>();
        this.setScores.set(1, null);
        this.setScores.set(2, null);
        this.setScores.set(3, null);
        this.setScores.set(4, null);
        this.setScores.set(5, null);
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get gameScore(): string {
        return this._gameScore;
    }

    set gameScore(value: string) {
        this._gameScore = value;
    }


    get setScores(): Map<number,number> {
        return this._setScores;
    }

    set setScores(value: Map<number,number>) {
        this._setScores = value;
    }


    get tieBreakScore(): number {
        return this._tieBreakScore;
    }

    set tieBreakScore(value: number) {
        this._tieBreakScore = value;
    }


    get globalGameScore(): string {
        return this._globalGameScore;
    }

    set globalGameScore(value: string) {
        this._globalGameScore = value;
    }
}

