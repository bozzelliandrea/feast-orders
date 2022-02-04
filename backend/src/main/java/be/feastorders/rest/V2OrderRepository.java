package be.feastorders.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface V2OrderRepository extends JpaRepository<V2Order, Long> {
}
