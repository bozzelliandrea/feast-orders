package controller;

import business.stats.dto.StatsDTO;
import business.stats.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/stats"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping
    public ResponseEntity<List<StatsDTO>> getStats(@RequestParam(value = "start", required = false) String startDate,
                                                   @RequestParam(value = "end", required = false) String endDate) throws ParseException {
        return ResponseEntity.ok(statsService.getStatsByDateRange(startDate, endDate));
    }
}
