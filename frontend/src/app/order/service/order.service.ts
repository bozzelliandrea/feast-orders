import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { RequestService } from 'src/app/shared/service/request.service';
import { Observable } from 'rxjs';
import { map } from "rxjs/operators";
import { Order } from '../interface/order.interface';
import { ApiResourceEnum } from 'src/app/shared/enums/api-resource.enum';
import { CRUDService } from 'src/app/shared/interface/crud-service.interface';
import { OrderItem } from '../interface/order-item.interface';

@Injectable({
  providedIn: 'root'
})
export class OrderService extends RequestService implements CRUDService<Order> {

  constructor(private _http: HttpClient) {
    super(ApiResourceEnum.ORDER);
  }

  public getAll(): Observable<Order[]> {

    return this._http.get(this._getUrl(), RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || []) as Order[];
      })
    );
  }

  public getById(id: number): Observable<Order> {

    return this._http.get(this._getUrl(id), RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || {}) as Order;
      })
    );
  }

  public create(dto: Order): Observable<Order> {

    return this._http.post(this._getUrl(), dto, RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || {}) as Order
      })
    );
  }

  public update(dto: Order): Observable<Order> {

    return this._http.put(this._getUrl(dto.id), dto, RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || {}) as Order
      })
    );
  }

  public getOrderItemDetails(orderId: number): Observable<any[]> {
    
    return this._http.get(this._getUrl(orderId).concat('/menuitem'), RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || []) as OrderItem[];
      })
    );
  }

  public delete(id: number): Observable<any> {
    
    return this._http.delete(this._getUrl(id), RequestService.baseHttpOptions);
  }
}
