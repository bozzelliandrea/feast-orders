package be.feastorders.order.service;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.menuitem.dto.MenuItemDTO;
import be.feastorders.menuitem.entity.MenuItem;
import be.feastorders.order.dto.OrderDTO;
import be.feastorders.order.entity.Order;
import be.feastorders.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
public class OrderService extends BaseCRUDService<Order, Long> {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
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
