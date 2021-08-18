package be.feastorders.printer.repository;

import be.feastorders.printer.entity.PrinterAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrinterAttrRepository extends JpaRepository<PrinterAttribute, String> {
}
