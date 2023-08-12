package spec;

@FunctionalInterface
public interface FunctionalProvider<E, R> {

    R getResult(E e);

}
