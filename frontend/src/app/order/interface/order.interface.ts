import { AbstractDTO } from './../../shared/interface/abstract-dto.interface';
import { OrderItem } from './order-item.interface';

export interface Order extends AbstractDTO {
    client: string;
    tableNumber: number;
    placeSettingNumber: number;
    note?: string;
    cashier?: string;
    takeAway?: boolean;
    total: number;
    menuItemList: Array<OrderItem>;
    printOrder?: boolean;
}