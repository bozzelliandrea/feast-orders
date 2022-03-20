package arch.aop;

import arch.entity.ErrorTracking;
import arch.repository.ErrorRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggerAspect {

    private final ErrorRepository errorRepository;

    public LoggerAspect(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)")
    public void springBeanPointcut() {
    }

    @AfterThrowing(pointcut = "springBeanPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());

        logger.error("Transaction error in {} method, exception details: cause = {}, message = {}",
                joinPoint.getSignature().getName(),
                e.getCause(),
                e.getMessage());

        try {
            String message = "";
            if (e.getMessage() != null) {
                int maxLength = Math.min(e.getMessage().length(), 200);
                message = e.getMessage() != null ? e.getMessage().substring(0, maxLength) : "";
            }

            ErrorTracking error = new ErrorTracking(message, e.getClass().getName());
            errorRepository.saveAndFlush(error);
        } catch (Exception internalException) {
            logger.error("Internal Aspect Error: cannot save message to ErrorTracking entity, cause {}", internalException.getMessage());
        }
    }
}
