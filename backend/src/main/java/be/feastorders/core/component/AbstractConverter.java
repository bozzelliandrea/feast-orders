package be.feastorders.core.component;

public abstract class AbstractConverter<E, DTO> {

    public abstract DTO convertEntity(E entity);

    public abstract E convertDTO(DTO dto);
}
