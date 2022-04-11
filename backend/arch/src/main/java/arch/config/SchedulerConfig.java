package arch.config;

import arch.component.CSVUtils;
import arch.entity.ErrorTracking;
import arch.repository.ErrorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertyResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@EnableScheduling
@Configuration
public class SchedulerConfig {

    private static final Logger _LOGGER = LoggerFactory.getLogger(SchedulerConfig.class);

    private final ErrorRepository errorRepository;
    private final PropertyResolver propertyResolver;

    public SchedulerConfig(ErrorRepository errorRepository,
                           PropertyResolver propertyResolver) {
        this.errorRepository = errorRepository;
        this.propertyResolver = propertyResolver;
    }

    @Scheduled(cron = "${application.task.errors.cron}")
    public void cleanTrackingErrorsTask() throws FileNotFoundException {
        _LOGGER.info("Start checking tracking errors");

        long errorsCount = errorRepository.count();

        if (errorsCount > propertyResolver.getProperty("application.task.errors.cron", Integer.class, 1000)){

            if(Boolean.TRUE.equals(propertyResolver.getProperty("application.task.errors.export", boolean.class))){
                String filePath = propertyResolver.getProperty("application.task.errors.path", String.class, "/");
                String fileName = "REPORT_".concat(new SimpleDateFormat("ss_mm_hh_dd_MM_yyyy").format(new Date()));
                File csvOutputFile = new File(filePath + fileName);
                try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
                    errorRepository.findAll().stream()
                            .map(ErrorTracking::toStringArray)
                            .map(CSVUtils::convertToCSV)
                            .forEach(pw::println);
                }
            }

            errorRepository.deleteAll();
            _LOGGER.info("End checking tracking errors task, found and removed {} errors", errorsCount);
        } else {
            _LOGGER.info("End checking tracking errors task, found {} errors", errorsCount);
        }
    }
}
