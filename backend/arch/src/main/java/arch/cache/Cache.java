package arch.cache;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Service
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@DependsOn({"cacheConfigRepository"})
public @interface Cache {

    String name() default "";

    CachePolicy policy() default CachePolicy.CLEAR;

    int minutes() default 30;
}
