package be.feastorders.core.service;

import be.feastorders.core.entity.BaseEntity;

public interface IBaseCRUD<E extends BaseEntity, ID> {

    E create(E entity);

    E update(E entity);

    E read(ID ID);

    boolean delete(ID ID);
}
