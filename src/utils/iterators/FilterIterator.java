package utils.iterators;

import interfaces.IteratorInterface;
import interfaces.Predicate;

/**
 * Created by Karol Pokomeda on 2017-03-09.
 */
public class FilterIterator<T> implements IteratorInterface<T> {
    private IteratorInterface<T> internalIterator;
    private Predicate filteringPredicate;

    /**
     * @param {IteratorInterface} internalIterator for filtering
     * @param {Predicate} filteringPredicate
     */
    public FilterIterator(IteratorInterface<T> internalIterator, Predicate filteringPredicate){
        this.internalIterator = internalIterator;
        this.filteringPredicate = filteringPredicate;
    }

    @Override
    public void first() {
        this.internalIterator.first();
        this.filterForward();
    }

    @Override
    public void last() {
        this.internalIterator.last();
        this.filterBackward();
    }

    @Override
    public void next() {
        this.internalIterator.next();
        this.filterForward();
    }

    @Override
    public void previous() {
        this.internalIterator.previous();
        this.filterBackward();
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
        if (this.filteringPredicate.accept(t)) return this.internalIterator.addCurrent(t);
        return false;
    }

    @Override
    public boolean addNext(T t) throws IndexOutOfBoundsException {
        if (this.filteringPredicate.accept(t)) return this.internalIterator.addNext(t);
        return false;
    }

    @Override
    public boolean deleteCurrent() throws IndexOutOfBoundsException {
        if (this.internalIterator.deleteCurrent()) {
            if (!this.isDone() && !this.filteringPredicate.accept(this.current())) this.next();
            return true;
        }
        return false;
    }

    /**
     * Private method which checks if current element passes predicate test and changes iterator focus to the next
     * element until accepted element is found or iterator focus is out of bounds of data structure
     */
    private void filterForward(){
        while(!this.internalIterator.isDone() && !this.filteringPredicate.accept(this.internalIterator.current()))
            this.internalIterator.next();
    }

    /**
     * Private method which checks if current element passes predicate test and changes iterator focus to the previous
     * element until accepted element is found or iterator focus is out of bounds of data structure
     */
    private void filterBackward(){
        while(!this.internalIterator.isDone() && this.filteringPredicate.accept(this.internalIterator.current()))
            this.internalIterator.previous();
    }
}
