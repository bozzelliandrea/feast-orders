package atomic.repository;

import atomic.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s LEFT OUTER JOIN s.item m WHERE m.id = :menuItemId")
    Stock getByMenuItemId(@Param("menuItemId") Long menuItemId);
}
