package arch.service;

import arch.entity.BaseEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Primary
public class BaseCRUDService<E extends BaseEntity, ID> implements IBaseCRUD<E, ID> {

    private final JpaRepository<E, ID> repository;

    public BaseCRUDService(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E create(E entity) {

        Objects.requireNonNull(entity, "Entity cannot be null!");

        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public E update(E entity) {
        Objects.requireNonNull(entity, "Entity cannot be null!");

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

        Objects.requireNonNull(ID, "Entity ID cannot be null!");

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

        Objects.requireNonNull(ID, "Entity ID cannot be null!");

        try {
            repository.deleteById(ID);
            return true;
        } catch (Exception e) {
            throw new EntityNotFoundException(String.format("Entity not found on delete for ID: %s", ID));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<E> findAll() {
        return repository.count() > 0 ? repository.findAll() : new ArrayList<>();
    }
}
