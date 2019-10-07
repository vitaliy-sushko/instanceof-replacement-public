package instanceofreplacement;

import java.util.Map;
import java.util.function.Function;

public class Is<T, K, V> {
    private final T obj;
    private final Class<V> clazz;
    private final Map<Class, Function<Object, K>> mappings;

    private Is(T obj, Class<V> clazz, Map<Class, Function<Object, K>> mappings) {
        this.obj = obj;
        this.clazz = clazz;
        this.mappings = mappings;
    }

    public ThenReturn<T, K, V> is(Class<V> clazz) {
        mappings.putIfAbsent(clazz, null);
        return ThenReturn.from(obj, clazz, mappings);
    }

    public K execute() {
        Class key = mappings.keySet().stream()
                .filter(clz -> clz.isAssignableFrom(obj.getClass()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No mapping have been found"));

        return mappings.get(key).apply(obj);
    }

    static <U, I, N> Is<U, I, N> from(U object, Class<N> clazz, Map<Class, Function<Object, I>> mappings) {
        return new Is<>(object, clazz, mappings);
    }
}
