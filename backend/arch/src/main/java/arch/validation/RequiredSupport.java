package arch.validation;

import arch.exception.errors.HttpFeastServerException;
import arch.exception.errors.InvalidRequestException;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class RequiredSupport {

    public static void validate(Object[] request, RequiredMethod method) {

        for (Object o : request) {

            final List<String> errors = new ArrayList<>();

            try {
                for (PropertyDescriptor pd : Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors()) {
                    if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {

                        Field field = o.getClass().getDeclaredField(pd.getName());
                        if (field.isAnnotationPresent(Required.class)) {
                            Required annotation = field.getAnnotation(Required.class);
                            Object value = pd.getReadMethod().invoke(o);

                            for (RequiredMethod rm : annotation.value()) {
                                if (rm.equals(method) && value == null) {
                                    errors.add(field.getName());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new HttpFeastServerException("Failed request validation: Internal error process");
            }

            if (errors.size() > 0) {
                throw new InvalidRequestException(String.format("Missing required parameters: %s", String.join(",", errors)));
            }
        }
    }
}
