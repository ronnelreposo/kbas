package abstractions;

/**
 * Serves as the abstraction for Setting a property.
 * @author Arya
 * @param <S> Return Type.
 * @param <T> Type to operate.
 */
public interface ControlPropertySetter<S, T>
{
    S set(S s, T t);
}
