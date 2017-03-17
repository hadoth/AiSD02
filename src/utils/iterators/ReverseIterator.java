package utils.iterators;

import interfaces.IteratorInterface;

/**
 * Created by Karol Pokomeda on 2017-03-12.
 */
public class ReverseIterator<T> implements IteratorInterface<T> {
    private IteratorInterface<T> internalIterator;

    public ReverseIterator(IteratorInterface<T> internalIterator) {
        this.internalIterator = internalIterator;
    }

    @Override
    public void first() {
        this.internalIterator.last();
    }

    @Override
    public void last() {
        this.internalIterator.first();
    }

    @Override
    public void next() {
        this.internalIterator.previous();
    }

    @Override
    public void previous() {
        this.internalIterator.next();
    }

    @Override
    public boolean isDone() {
        return this.internalIterator.isDone();
    }

    @Override
    public T current() throws IndexOutOfBoundsException {
        return this.internalIterator.current();
    }

    @Override
    public boolean addCurrent(T t) throws IndexOutOfBoundsException {
        return this.internalIterator.addNext(t);
    }

    @Override
    public boolean addNext(T t) throws IndexOutOfBoundsException {
        return this.internalIterator.addCurrent(t);
    }

    @Override
    public boolean deleteCurrent() throws IndexOutOfBoundsException {
        boolean result = this.internalIterator.deleteCurrent();
        this.internalIterator.previous();
        return result;
    }
}
