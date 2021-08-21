import { CRUDService } from 'src/app/shared/interface/crud-service.interface';
import { RequestService } from 'src/app/shared/service/request.service';
import { Injectable } from "@angular/core";
import { MenuItem } from '../interface/menu-item.interface';
import { Observable } from 'rxjs';
import { ApiResourceEnum } from 'src/app/shared/enums/api-resource.enum';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';


@Injectable({
    providedIn: 'root'
})
export class MenuItemService extends RequestService implements CRUDService<MenuItem> {

    constructor(private _http: HttpClient) {
        super(ApiResourceEnum.MENU_ITEM);
    }

    getAll(categoryId: number): Observable<MenuItem[]> {

        return this._http.get(this._getUrl(categoryId), RequestService.baseHttpOptions).pipe(
            map((res: any) => {
                return (res || []) as MenuItem[];
            })
        );
    }

    getById(id: number, categoryId: number): Observable<MenuItem> {

        return this._http.get(this._getUrl(categoryId, id), RequestService.baseHttpOptions).pipe(
            map((res: any) => {
                return (res || {}) as MenuItem;
            })
        );
    }

    create(dto: MenuItem, categoryId: number): Observable<any> {

        dto.categoryId = categoryId;

        return this._http.post(this._getUrl(categoryId), dto, RequestService.baseHttpOptions).pipe(
            map((res: any) => {
                return (res || []) as MenuItem[];
            })
        );
    }

    update(dto: MenuItem, categoryId: number): Observable<any> {

        return this._http.put(this._getUrl(categoryId, dto.id), dto, RequestService.baseHttpOptions).pipe(
            map((res: any) => {
                return (res || []) as MenuItem[];
            })
        );
    }

    delete(id: number, categoryId: number): Observable<MenuItem[]> {

        return this._http.delete(this._getUrl(categoryId, id), RequestService.baseHttpOptions).pipe(
            map((res: any) => {
                return (res || []) as MenuItem[];
            })
        );
    }

    /**
     * Overloading _getUrl method extended in {@link RequestService}, the backend need category path for menu items.
     * 
     * @override
     * @param @requires categoryId selected category ID
     * @param menuItemId optional menu item id
     * @returns URL in string format
     */
    protected _getUrl(categoryId: number, menuItemId?: number): string {
        if (menuItemId)
            return `${RequestService.baseUrl}/${ApiResourceEnum.CATEGORY}/${categoryId}/${ApiResourceEnum.MENU_ITEM}/${menuItemId}`;
        else
            return `${RequestService.baseUrl}/${ApiResourceEnum.CATEGORY}/${categoryId}/${ApiResourceEnum.MENU_ITEM}`;
    }
}