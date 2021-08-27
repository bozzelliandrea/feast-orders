export interface OrderItem {
  // menuItem: MenuItem
  quantity: number;
  totalPrice: number;
  orderId?: number;
  menuItemId?: number;
  menuItemName: string;
  menuItemPrice: number;
  menuItemCategoryId?: number;
  note?: string;
}
