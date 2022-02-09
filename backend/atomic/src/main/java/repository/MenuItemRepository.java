package repository;

import entity.MenuItem;
import entity.OrderItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByCategoryID(Long categoryId);

    @Query("SELECT od " +
            "FROM OrderItemDetail od " +
            "JOIN od.order o " +
            "WHERE o.ID = :orderId")
    List<OrderItemDetail> findMenuItemsByOrderId(@Param("orderId") Long orderID);
}
