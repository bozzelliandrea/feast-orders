package be.feastorders.core.component;

public abstract class AbstractRESTValidator<T> {

    abstract void create(T request);

    abstract void update(T request);

    abstract void get(Long id);

    abstract void delete(Long id);
}
