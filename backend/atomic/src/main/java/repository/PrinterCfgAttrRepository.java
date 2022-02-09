package repository;

import entity.PrinterCfgAttribute;
import entity.PrinterCfgAttributePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterCfgAttrRepository extends JpaRepository<PrinterCfgAttribute, PrinterCfgAttributePk> {
}
