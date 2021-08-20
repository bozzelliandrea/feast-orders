import { Injectable } from '@angular/core';
import { Category } from '../interface/category.interface';
import { CRUDService } from 'src/app/shared/interface/crud-service.interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RequestService } from 'src/app/shared/service/request.service';
import { ApiResourceEnum } from 'src/app/shared/enums/api-resource.enum';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CategoryService extends RequestService implements CRUDService<Category> {

  constructor(private _http: HttpClient) {
    super(ApiResourceEnum.CATEGORY);
  }

  getAll(): Observable<Category[]> {

    return this._http.get(this._getUrl(), RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || []) as Category[];
      })
    );
  }

  getById(id: number): Observable<Category> {

    return this._http.get(this._getUrl(id), RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || {}) as Category;
      })
    );
  }

  create(dto: Category): Observable<Category> {

    return this._http.post(this._getUrl(), dto, RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || {}) as Category
      })
    );
  }

  update(dto: Category): Observable<Category> {

    return this._http.put(this._getUrl(), dto, RequestService.baseHttpOptions).pipe(
      map((res: any) => {
        return (res || {}) as Category
      })
    );
  }

  delete(id: number): Observable<any> {

    return this._http.delete(this._getUrl(id), RequestService.baseHttpOptions);
  }
}
