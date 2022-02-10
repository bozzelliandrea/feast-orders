package atomic.repository;

import atomic.entity.PrinterAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterAttrRepository extends JpaRepository<PrinterAttribute, String> {
}
