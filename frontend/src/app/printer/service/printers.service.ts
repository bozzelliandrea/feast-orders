import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {RequestService} from "../../shared/service/request.service";
import {ApiResourceEnum} from "../../shared/enums/api-resource.enum";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class PrintersService extends RequestService {

  constructor(private _http: HttpClient) {
    super(ApiResourceEnum.PRINTERS);
  }

  getAll(): Observable<Array<string>> {
    return this._http.get(this._getUrl(), RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || []) as string[];
      })
    );
  }

}
