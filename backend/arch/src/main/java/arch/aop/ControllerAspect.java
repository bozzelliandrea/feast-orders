package arch.aop;

import arch.validation.RequiredMethod;
import arch.validation.RequiredSupport;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class ControllerAspect {

    @Pointcut("execution(@(@org.springframework.web.bind.annotation.RequestMapping *) * *(..))")
    public void requestMappingAnnotations() {
    }

    @Before("requestMappingAnnotations()")
    public void requestInterceptor(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAnnotationPresent(RequestMapping.class)) {

                RequiredMethod rm = RequiredMethod.getByAnnotation(annotation.annotationType());
                Object[] request = joinPoint.getArgs();

                for (Object o : request) {
                    if (RequiredSupport.isPrimitiveWrapper(o.getClass())) {
                        RequiredSupport.validate(o, method);
                    } else {
                        RequiredSupport.validate(o, rm);
                    }
                }
            }
        }
    }

}
