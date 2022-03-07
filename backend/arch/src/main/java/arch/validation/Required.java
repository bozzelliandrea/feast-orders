package arch.validation;

import java.lang.annotation.*;

/**
 * Request validation annotation.
 *
 * <p>
 * Used on @RestController class for inline param validation or on request type bean as a fields validation.
 * <p>
 * The annotation will be handled by {@link arch.aop.ControllerAspect} for each application controller.
 * </p>
 * <i>Example</i>
 * <p>bean validation example</p>
 * <pre>
 * {@code
 *  class RequestBean {
 *   @Required({RequiredMethod.READ, RequiredMethod.DELETE, RequiredMethod.UPDATE})
 *   private Long id;
 *
 *   @Required({RequiredMethod.DELETE, RequiredMethod.UPDATE})
 *   private String name;
 * }
 * </pre>
 * <p>row validation example</p>
 * <pre>
 * {@code
 *  @RestController
 *  class Controller {
 *   @GetMapping("/{id}")
 *   public ResponseEntity<String> getById(@Required @PathVariable Long id) {
 *     return ResponseEntity.ok("Hello World");
 *   }
 * }
 * }
 * </pre>
 *
 * <p>If validation fail, the aspect will throw a new {@link arch.exception.errors.InvalidRequestException} with HTTP code {@link org.springframework.http.HttpStatus#BAD_REQUEST}  }</p>
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    RequiredMethod[] value() default {};
}
