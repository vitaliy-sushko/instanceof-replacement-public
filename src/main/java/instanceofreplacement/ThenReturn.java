package instanceofreplacement;

import java.util.Map;
import java.util.function.Function;

public class ThenReturn<T, K, V> {
    private final T obj;
    private final Class<V> clazz;
    private final Map<Class, Function<Object, K>> mappings;

    private ThenReturn(T obj, Class<V> clazz, Map<Class, Function<Object, K>> mappings) {
        this.obj = obj;
        this.clazz = clazz;
        this.mappings = mappings;
    }

    public Is<T, K, V> thenReturn(Function<T, K> mapping) {
        mappings.putIfAbsent(clazz, (Function<Object, K>) mapping);
        return Is.from(obj, clazz, mappings);
    }

    static <U, I, N> ThenReturn<U, I, N> from(U object, Class<N> clazz, Map<Class, Function<Object, I>> mappings) {
        return new ThenReturn<>(object, clazz, mappings);
    }
}
