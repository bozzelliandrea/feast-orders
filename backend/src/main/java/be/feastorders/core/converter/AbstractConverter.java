package be.feastorders.core.converter;

public abstract class AbstractConverter<E, DTO> {

    public abstract DTO convertEntity(E entity);

    public abstract E convertDTO(DTO dto);
}
