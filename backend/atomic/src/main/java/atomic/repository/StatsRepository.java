package atomic.repository;

import atomic.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Long> {

    @Query("SELECT COUNT(*) FROM Stats s WHERE s.date = :date")
    Integer countStatsForDate(Date date);

    List<Stats> findByDateGreaterThanEqualAndDateLessThanEqual(Date start, Date end);
}
