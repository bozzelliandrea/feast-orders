import { MenuItem } from './../../menu/interface/menu-item.interface';

export interface OrderItem {
    menuItem: MenuItem
    quantity: number;
    totalPrice: number;
    orderId?: number;
    menuItemId?: number;
    note?: string;
}