package be.feastorders.core.component;

public interface Validator<T> {

    void validate(T obj);
}
