package be.feastorders;

import be.feastorders.order.entity.Order;

public final class MockDataUtils {

    public static Order generateOrder() {
        Order order = new Order();
        order.setTotal(20.0f);
        order.setClient("TAVOLO");
        order.setTableNumber(1L);
        order.setPlaceSettingNumber(4L);
        return order;
    }
}
