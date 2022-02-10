package atomic.repository;

import atomic.entity.PrinterCfgAttribute;
import atomic.entity.PrinterCfgAttributePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterCfgAttrRepository extends JpaRepository<PrinterCfgAttribute, PrinterCfgAttributePk> {
}
