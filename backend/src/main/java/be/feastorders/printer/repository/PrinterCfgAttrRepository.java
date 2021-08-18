package be.feastorders.printer.repository;

import be.feastorders.printer.entity.PrinterCfgAttribute;
import be.feastorders.printer.entity.PrinterCfgAttributePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterCfgAttrRepository extends JpaRepository<PrinterCfgAttribute, PrinterCfgAttributePk> {
}
