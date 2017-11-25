package abstractions;

/**
 * Adds property to the target control.
 * @author Arya
 *
 * @param <S> The target control.
 * @param <T> Parameter to be added.
 */
public interface ControlPropertyAdder<S, T> { S add(S p, T c); }
