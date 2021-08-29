import {Injectable} from '@angular/core';
import {RequestService} from "./request.service";
import {ApiResourceEnum} from "../enums/api-resource.enum";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AuthService extends RequestService{

    constructor(private _http: HttpClient) {
        super(ApiResourceEnum.AUTH);
    }

    login(username: string, password: string): Observable<any> {
        return this._http.post(this._getUrl().concat('/signin'), {
            username,
            password
        }, RequestService.baseHttpOptions);
    }

    register(username: string, email: string, password: string): Observable<any> {
        return this._http.post(this._getUrl().concat('/signup'), {
            username,
            email,
            password
        }, RequestService.baseHttpOptions);
    }
}
