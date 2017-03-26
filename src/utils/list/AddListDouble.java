package utils.list;

import interfaces.IteratorInterface;
import interfaces.ListInterface;

/**
 * Created by Karol Pokomeda on 2017-03-25.
 */
public class AddListDouble<T> extends MyListDouble<T> {
    public void insert(ListInterface<T> list, int index){
        IteratorInterface<T> internalIterator = this.iterator();
        internalIterator.first();
        while(--index > 0) internalIterator.next();
        IteratorInterface<T> externalIterator = list.iterator();
        externalIterator.last();
        while (!externalIterator.isDone()){
            internalIterator.addCurrent(externalIterator.current());
            externalIterator.previous();
        }
    }

    public void add(ListInterface<T> list){
        IteratorInterface<T> internalIterator = this.iterator();
        internalIterator.last();
        IteratorInterface<T> externalIterator = list.iterator();
        externalIterator.first();
        while(!externalIterator.isDone()){
            internalIterator.addNext(externalIterator.current());
            externalIterator.next();
        }
    }

    public void insertBefore(ListInterface<T> list, T t){
        IteratorInterface<T> internalIterator = this.iterator();
        internalIterator.first();
        IteratorInterface<T> externalIterator = list.iterator();
        externalIterator.last();
        while (!internalIterator.current().equals(t)) internalIterator.next();
        while (!externalIterator.isDone()){
            internalIterator.addCurrent(externalIterator.current());
            externalIterator.previous();
        }
    }
}
