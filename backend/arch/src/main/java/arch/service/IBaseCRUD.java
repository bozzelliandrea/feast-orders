package arch.service;

import arch.entity.BaseEntity;

import java.util.List;

public interface IBaseCRUD<E extends BaseEntity, ID> {

    E create(E entity);

    E update(E entity);

    E read(ID id);

    boolean delete(ID id);

    List<E> findAll();
}
