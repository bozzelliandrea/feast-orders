package be.feastorders;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;

import java.util.*;

@DataJpaTest
@SuppressWarnings({"all"})
public abstract class AbstractJpaDataProvider {

    @Autowired
    private BeanFactory _beanFactory;

    private final Set<CrudRepository> _repositories = new HashSet<>();

    protected <T> T load(JpaRepository<T, ?> bean, T entity) {
        this._registerRepository(bean);
        return bean.saveAndFlush(entity);
    }

    protected <T> T load(Class<? extends JpaRepository<T, ?>> repository, T entity) {
        JpaRepository<T, ?> bean = _beanFactory.getBean(repository);
        this._registerRepository(bean);
        return bean.saveAndFlush(entity);
    }

    protected <T> T load(T entity) {
        JpaRepository<T, ?> bean = (JpaRepository) getRepository(entity.getClass());
        return bean.saveAndFlush(entity);
    }

    protected <T> List<T> load(List<T> entities) {
        Objects.requireNonNull(entities);
        if (entities.isEmpty())
            return new ArrayList<>();

        List<T> result = new ArrayList<>();
        JpaRepository<T, ?> repository = (JpaRepository) getRepository(entities.get(0).getClass());

        for (T entity : entities) {
            result.add(load(repository, entity));
        }
        return result;
    }

    protected void clean() {
        for (CrudRepository repository : _repositories) {
            repository.deleteAll();
        }
    }

    protected  <T> JpaRepository<?, ?> getRepository(Class<T> clazz) {
        Repositories repositories = new Repositories((ListableBeanFactory) _beanFactory);
        Optional<Object> opt = repositories.getRepositoryFor(clazz);
        if (opt.isPresent()) {
            JpaRepository<?, ?> bean = (JpaRepository<?, ?>) opt.get();
            this._registerRepository(bean);
            return bean;
        } else {
            throw new NoSuchBeanDefinitionException(String
                    .format("Repository for entity type %s does not exist!", clazz));
        }
    }

    private void _registerRepository(JpaRepository repository) {
        this._repositories.add(repository);
    }
}
