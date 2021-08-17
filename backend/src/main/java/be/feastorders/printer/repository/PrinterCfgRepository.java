package be.feastorders.printer.repository;

import be.feastorders.printer.entity.PrinterCfg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterCfgRepository extends JpaRepository<PrinterCfg, Long> {
}
