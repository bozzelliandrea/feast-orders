package arch.validation;

public abstract class AbstractRESTValidator<T> {

    protected abstract void create(T request);

    protected abstract void update(T request);

    protected abstract void get(Long id);

    protected abstract void delete(Long id);
}
