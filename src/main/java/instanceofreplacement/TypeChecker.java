package instanceofreplacement;


import java.util.HashMap;

public class TypeChecker {
    public static <T, K> Is<T, K, Object> whenTypeOf(T object) {
        return Is.from(object, Object.class, new HashMap<>());
    }
}
