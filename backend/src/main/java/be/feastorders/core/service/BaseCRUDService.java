package be.feastorders.core.service;

import be.feastorders.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BaseCRUDService<E extends BaseEntity, ID> implements IBaseCRUD<E, ID> {

    private final JpaRepository<E, ID> repository;

    public BaseCRUDService(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E create(E entity) {

        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E update(E entity) {

        if (Objects.isNull(entity.getID())) {
            throw new IllegalArgumentException("Cannot update entity with null ID");
        }

        if (Objects.isNull(entity.getVersion())) {
            throw new IllegalArgumentException(String.format("Version is required for entity with ID: %s", entity.getID()));
        }

        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public E read(ID ID) {

        Optional<E> entity = repository.findById(ID);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new EntityNotFoundException(String.format("Entity not found on read for ID: %s", ID));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(ID ID) {

        try {
            repository.deleteById(ID);
            return true;
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format("Entity not found on delete for ID: %s", ID));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> find() {
        return repository.findAll();
    }
}
