package arch.validation;

import arch.exception.errors.HttpFeastServerException;
import arch.exception.errors.InvalidRequestException;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;

import javax.el.PropertyNotFoundException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public final class RequiredSupport {

    public static final Logger LOGGER = LoggerFactory.getLogger(RequiredSupport.class);

    private static final Set<Class<?>> _WRAPPER_TYPES = _getWrapperTypes();

    public static void validate(Object primitive, Method method) {
        Annotation[][] paramsAnnotations = method.getParameterAnnotations();
        String paramName = "";
        String errorMessage = null;
        for (Annotation[] annotationRow : paramsAnnotations) {
            for (Annotation a : annotationRow) {
                if (a.annotationType().equals(PathVariable.class)) {
                    paramName = ((PathVariable) a).value();
                }
                if (a.annotationType().equals(Required.class) && (primitive == null
                        || primitive.equals("''")
                        || (primitive instanceof String && Strings.isBlank((String) primitive)))) {
                    errorMessage = "Missing required path parameter";
                }
            }
        }
        if (errorMessage != null) {
            throw new InvalidRequestException(errorMessage + ": " + paramName);
        }
    }

    public static void validate(Object o, RequiredMethod method) {

        final List<String> errors = new ArrayList<>();

        try {
            for (PropertyDescriptor pd : Introspector.getBeanInfo(o.getClass()).getPropertyDescriptors()) {
                if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {

                    List<Field> classFields = getFullDeclaredFields(new ArrayList<>(), o.getClass());
                    Field field = getFieldByName(classFields, pd.getName());
                    if (field == null) {
                        LOGGER.error("Field with name {} not found in class {}", pd.getName(), o.getClass().getName());
                        throw new PropertyNotFoundException("Field not found or not exist");
                    }

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
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            exception.printStackTrace();
            throw new HttpFeastServerException("Failed request validation: Internal error process");
        }

        if (errors.size() > 0) {
            throw new InvalidRequestException(String.format("Missing required parameters: %s",
                    String.join(",", errors)));
        }
    }

    public static boolean isPrimitiveWrapper(Class<?> clazz) {
        return _WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> _getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }

    private static Field getFieldByName(List<Field> fields, String fieldName) {
        for (Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    private static List<Field> getFullDeclaredFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getFullDeclaredFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
