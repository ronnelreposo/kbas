package abstractions;

/**
 * Source streamer.
 * @author Arya
 *
 * @param <S> Return Type.
 * @param <T> Parameter Type.
 */
public interface SourceStreamer<S, T> { S stream(S s, T t); }