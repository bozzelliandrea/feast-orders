package aop;

import entity.ErrorRepository;
import entity.ErrorTracking;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    @Autowired
    private ErrorRepository errorRepository;

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    @Pointcut("within(be.feastorders..*)")
    public void feastBearPointcut() {
    }


    @AfterThrowing(pointcut = "feastBearPointcut() && springBeanPointcut()", throwing = "e")
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
