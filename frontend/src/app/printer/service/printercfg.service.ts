import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import { ApiResourceEnum } from "src/app/shared/enums/api-resource.enum";
import { CRUDService } from "src/app/shared/interface/crud-service.interface";
import { RequestService } from "src/app/shared/service/request.service";
import { PrinterCfg } from "../interface/printercfg.interface";

@Injectable({
    providedIn: 'root'
})
export class PrinterCfgService extends RequestService implements CRUDService<PrinterCfg> {

    constructor(private _http: HttpClient) {
        super(ApiResourceEnum.PRINTER_CFG);
    }

    getAll(): Observable<PrinterCfg[]> {
        return this._http.get(this._getUrl(), RequestService.baseHttpOptions).pipe(
            map((res: any) => {
              return (res || []) as PrinterCfg[];
            })
        );
    }

    getById(id: number): Observable<PrinterCfg> {
        return this._http.get(this._getUrl(id), RequestService.baseHttpOptions).pipe(
            map((res: any) => {
              return (res || {}) as PrinterCfg;
            })
          );
    }

    create(dto: PrinterCfg): Observable<PrinterCfg> {
        return this._http.post(this._getUrl(), dto, RequestService.baseHttpOptions).pipe(
            map((res: any) => {
                return (res || {}) as PrinterCfg
            })
        );
    }

    update(dto: PrinterCfg): Observable<PrinterCfg> {
        return this._http.put(this._getUrl(dto.id), dto, RequestService.baseHttpOptions).pipe(
            map((res: any) => {
              return (res || {}) as PrinterCfg
            })
          );
    }

    delete(id: number): Observable<any> {
        return this._http.delete(this._getUrl(id), RequestService.baseHttpOptions);
    }

}
