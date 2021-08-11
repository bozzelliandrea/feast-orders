package be.feastorders.order.service;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.order.dto.OrderDTO;
import be.feastorders.order.entity.Order;
import be.feastorders.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@org.springframework.core.annotation.Order(1)
public class OrderService extends BaseCRUDService<Order, Long> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }

    public OrderDTO updateEntityValues(OrderDTO dto) {

        Order entity = super.read(dto.getID());

        if (Objects.nonNull(dto.getClient()))
            entity.setClient(dto.getClient());

        if (Objects.nonNull(dto.getTableNumber()))
            entity.setTableNumber(dto.getTableNumber());

        if (Objects.nonNull(dto.getPlaceSettingNumber()))
            entity.setPlaceSettingNumber(dto.getPlaceSettingNumber());

        if (Objects.nonNull(dto.getOrderTime()))
            entity.setOrderTime(dto.getOrderTime());

        if (Objects.nonNull(dto.getProgressNumber()))
            entity.setProgressNumber(dto.getProgressNumber());

        if (Objects.nonNull(dto.getDiscount()))
            entity.setDiscount(dto.getDiscount());

        if (Objects.nonNull(dto.getTotal()))
            entity.setTotal(dto.getTotal());

        return new OrderDTO(super.update(entity));
    }
}
