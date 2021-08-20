import { Observable } from 'rxjs';

export interface CRUDService<T> {

    getAll(): Observable<Array<T>>;

    getById(id: number): Observable<T>;

    create(dto: T): Observable<T>;

    update(dto: T): Observable<T>;

    delete(id: number): Observable<boolean> | Observable<any>;
}