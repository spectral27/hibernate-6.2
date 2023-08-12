package spec;

@FunctionalInterface
public interface FunctionalExecutor<T> {

    void executeTask(T t);

}
