import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Match} from "./model/match";

@Injectable({
    providedIn: 'root'
})
export class AppService {

    private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) { }

    startMatch(): Observable<Object> {
        return this.http.get(`${this.baseUrl}/start`);
    }

    switchOff(): Observable<Object> {
        return this.http.get(`${this.baseUrl}/switchOff`);
    }

    getMatchInfos(): Observable<Object> {
        return this.http.get(`${this.baseUrl}/getInfos`);
    }

    playerWinsPoint(playerNumber: number): Observable<Object> {
        return this.http.get(`${this.baseUrl}` + `/${playerNumber}/winsPoint`);
    }

    setNbOfSetsToWin(nbOfSets: number): Observable<Object> {
        return this.http.get(`${this.baseUrl}` + `/${nbOfSets}/setsToWin`);
    }
}
