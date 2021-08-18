import { AbstractDTO } from './../../shared/interface/abstract-dto.interface';

export interface Order extends AbstractDTO {
    client: string;
    tableNumber: number;
    placeSettingNumber: number;
    orderTime: Date;
    progressNumber: number;
    discount: number;
    total: number;
}