import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { RequestService } from 'src/app/shared/service/request.service';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { Order } from '../interface/order.interface';
import { ApiResourceEnum } from 'src/app/shared/enums/api-resource.enum';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private _http: HttpClient) { }

  public getAllOrders(): Observable<Order[]> {

    return this._http.get(this._getUrl(), RequestService.baseHttpOptions)
      .pipe(
        map((res: any) => {
          return (res || []) as Order[];
        }
        )
      );
  }

  public getOrderById(id: number): Observable<Order> {

    return this._http.get(this._getUrl(id), RequestService.baseHttpOptions)
      .pipe(
        map((res: any) => {
          return (res || {}) as Order;
        }
        )
      );
  }

  private _getUrl(id?: number) {
    if (id)
      return `${RequestService.baseUrl}/${ApiResourceEnum.ORDER}/${id}`;
    else
      return `${RequestService.baseUrl}/${ApiResourceEnum.ORDER}`;
  }
}
