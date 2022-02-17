package arch.search;

public class SearchCriteria {

    private String key;
    private QueryOperator operation;
    private Object value;
    private boolean orPredicate;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, QueryOperator operation, Object value, boolean orPredicate) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.orPredicate = orPredicate;
    }

    public SearchCriteria(final String orPredicate, final String key, final QueryOperator operation, final Object value) {
        super();
        this.orPredicate = orPredicate != null && orPredicate.equals(QueryOperator.OR_PREDICATE_FLAG);
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(final String orPredicate, String key, String operation, String prefix, Object value, String suffix) {
        QueryOperator op = QueryOperator.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == QueryOperator.EQUALITY) {
                final boolean startWithAsterisk = prefix != null && prefix.contains(QueryOperator.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(QueryOperator.ZERO_OR_MORE_REGEX);
                if (startWithAsterisk && endWithAsterisk) {
                    op = QueryOperator.CONTAINS;
                } else if (startWithAsterisk) {
                    op = QueryOperator.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = QueryOperator.STARTS_WITH;
                } else {
                    if ("true".equalsIgnoreCase(value.toString()) || "false".equalsIgnoreCase(value.toString())) {
                        value = Boolean.valueOf(value.toString());
                    }
                }
            }
        }
        this.orPredicate = orPredicate != null && orPredicate.equals(QueryOperator.OR_PREDICATE_FLAG);
        this.key = key;
        this.operation = op;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public QueryOperator getOperation() {
        return operation;
    }

    public void setOperation(QueryOperator operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isOrPredicate() {
        return orPredicate;
    }

    public void setOrPredicate(boolean orPredicate) {
        this.orPredicate = orPredicate;
    }
}
