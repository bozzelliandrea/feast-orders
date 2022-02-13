package arch.validation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    RequiredMethod[] value() default {};
}
