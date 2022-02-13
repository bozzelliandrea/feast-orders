package arch.validation;

import org.springframework.web.bind.annotation.*;

public enum RequiredMethod {
    READ(GetMapping.class),
    CREATE(PostMapping.class),
    UPDATE(PutMapping.class),
    DELETE(DeleteMapping.class),
    PATCH(PatchMapping.class);

    private final Class<?> annotation;

    RequiredMethod(Class<?> annotation) {
        this.annotation = annotation;
    }

    public static RequiredMethod getByAnnotation(Class<?> clazz) {
        for (RequiredMethod m : RequiredMethod.values()) {
            if (m.getAnnotation().equals(clazz)) {
                return m;
            }
        }
        return null;
    }

    public Class<?> getAnnotation() {
        return annotation;
    }
}
