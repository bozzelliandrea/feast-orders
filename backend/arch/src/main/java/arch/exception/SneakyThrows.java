package arch.exception;

public final class SneakyThrows {

    @SuppressWarnings("unchecked")
    public static <E extends Throwable> void execute(Throwable e) throws E {
        throw (E) e;
    }

}
