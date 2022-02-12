package arch.validation;

import arch.exception.errors.InvalidRequestException;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public final class RequiredSupport {

    public static void validate(Object o, RequiredMethod method) throws Exception {

        for (PropertyDescriptor pd : Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors()) {
            if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {

                Field field = o.getClass().getDeclaredField(pd.getName());
                if (field.isAnnotationPresent(Required.class)) {
                    Required annotation = field.getAnnotation(Required.class);
                    Object value = pd.getReadMethod().invoke(o);

                    for (RequiredMethod rm : annotation.value()) {
                        if (rm.equals(method) && value == null) {
                            throw new InvalidRequestException(String.format("Missing required parameter: %s", field));
                        }
                    }
                }
            }
        }
    }
}
