package business.order;

import arch.search.SearchCriteria;
import atomic.entity.V2Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecificationBuilder {

    private final List<SearchCriteria> params;

    public OrderSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public final OrderSpecificationBuilder with(
            final String key,
            final String operation,
            final Object value,
            final String prefix,
            final String suffix
    ) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final OrderSpecificationBuilder with(SearchCriteria criteria) {
        params.add(criteria);
        return this;
    }

    public final OrderSpecificationBuilder with(
            final String orPredicate,
            final String key,
            final String operation,
            Object value,
            final String prefix,
            final String suffix
    ) {
        params.add(new SearchCriteria(orPredicate, key, operation, prefix, value, suffix));
        return this;
    }

    public Specification<V2Order> build() {
        if (params.size() == 0) return null;
        Specification<V2Order> result = new OrderSpecification(params.get(0));
        for (int i = 1; i < params.size(); i++) {
            result =
                    params.get(i).isOrPredicate()
                            ? Specification.where(result).or(new OrderSpecification(params.get(i)))
                            : Specification.where(result).and(new OrderSpecification(params.get(i)));
        }
        return result;
    }
}
