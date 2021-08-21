import { AbstractDTO } from './../../shared/interface/abstract-dto.interface';
import { OrderItem } from './order-item.interface';

export interface Order extends AbstractDTO {
    client: string;
    tableNumber: number;
    placeSettingNumber: number;
    progressNumber: number;
    discount: number;
    total: number;
    menuItemList: Array<OrderItem>;
}