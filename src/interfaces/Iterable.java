package interfaces;

/**
 * Has Method which returns iterator for class which implements this interface
 * Created by Karol Pokomeda on 2017-03-17.
 */
public interface Iterable<T> {
    IteratorInterface<T> iterator();
}
