package be.feastorders.menuitem.repository;

import be.feastorders.menuitem.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByCategoryID(Long categoryId);

    @Query("SELECT m " +
            "FROM MenuItem m " +
            "JOIN m.orders o " +
            "JOIN o.menuItems " +
            "WHERE m.ID = :itemId AND o.ID = :orderId")
    MenuItem readMenuItemByIdAndOrderId(@Param("orderId") Long orderID,
                                        @Param("itemId") Long menuItemID);

    @Query("SELECT m " +
            "FROM MenuItem m " +
            "JOIN m.orders o " +
            "JOIN o.menuItems " +
            "WHERE o.ID = :orderId")
    List<MenuItem> findMenuItemsByOrderId(@Param("orderId") Long orderID);
}
