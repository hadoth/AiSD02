package utils.list;

import interfaces.ListInterface;
import interfaces.IteratorInterface;

/**
 * Created by Karol Pokomeda on 2017-03-15.
 */
public class MyList<T> implements ListInterface<T> {
    private Element<T> head;
    private Element<T> tail;
    private int size;
    private Element<T> focused;
    private int focusedIndex;

    public MyList () {
        this.clear();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void insert(T t, int index) throws IndexOutOfBoundsException {
        if (index > 0){
            this.first();
            while(index-- > 1) this.next();
            Element<T> previousElement = this.focused;
            Element<T> currentElement = new Element<T>(t);
            currentElement.setNext(previousElement.getNext());
            previousElement.setNext(currentElement);
        } else {
            Element<T> second = this.head.getNext();
            Element<T> first = new Element<T>(t);
            first.setNext(second);
            this.head = first;
        }
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        this.first();
        while (index-- > 0) this.next();
        return this.current();
    }

    @Override
    public T set(T t, int index) throws IndexOutOfBoundsException {
        this.first();
        while (index-- > 0) this.next();
        T result = this.current();
        this.focused.setCurrent(t);
        return result;
    }

    @Override
    public void add(T t) {
        if (this.tail != null){
            this.tail.setNext(new Element<T>(t));
            this.tail = tail.getNext();
        } else {
            this.head = new Element<T>(t);
            this.tail = this.head;
        }
        this.size++;
    }

    @Override
    public T delete(T t) throws IndexOutOfBoundsException {
        return null; //TODO: implement
    }

    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        return null; //TODO: implement
    }

    @Override
    public boolean contains(T t) {
        this.first();
        while(!this.isDone()){
            if (this.current().equals(t)) return true;
            this.next();
        }
        return false;
    }

    @Override
    public int indexOf(T t){
        this.first();
        while(!this.isDone()){
            if (this.current().equals(t)) return this.focusedIndex;
            this.next();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (this.head != null) return false;
        return true;
    }

    @Override
    public IteratorInterface<T> iterator() {
        //TODO: delete from interface
        return null;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.focused = null;
        this.focusedIndex = 0;
    }

    @Override
    public void first() {
        this.focused = this.head;
        this.focusedIndex = 0;
    }

    @Override
    public void last() {
        this.focused = this.tail;
        this.focusedIndex = this.size - 1;
    }

    @Override
    public void next() {
        try {
            this.focused = this.focused.getNext();
        } catch (NullPointerException e){
            this.focused = null;
        } finally {
            this.focusedIndex++;
        }
    }

    @Override
    public void previous() {
        int indexToSet = --this.focusedIndex;
        if (indexToSet >= 0){
            this.first();
            for (int i = 0; i < indexToSet; i++){
                this.next();
            }
        } else{
            this.focused = null;
        }
    }

    @Override
    public boolean isDone() {
        if (this.focusedIndex < this.size && this.focusedIndex >= 0) return false;
        return true;
    }

    @Override
    public T current() throws IndexOutOfBoundsException {
        if (this.isDone()) throw new IndexOutOfBoundsException("Iterator focus is out of bounds of the data structure");
        return this.focused.getCurrent();
    }

    @Override //TODO: implement
    public boolean addCurrent(T t) throws IndexOutOfBoundsException {
        return false;
    }

    @Override //TODO: implement
    public boolean addNext(T t) throws IndexOutOfBoundsException {
        return false;
    }

    @Override //TODO: implement
    public boolean deleteCurrent() throws IndexOutOfBoundsException {
        return false;
    }

    private class Element<T> {
        private T current;
        private Element<T> next;

        Element(T t){
            this.current = t;
            this.next = null;
        }

        Element<T> getNext() {
            return next;
        }

        T getCurrent() {
            return current;
        }

        void setNext(Element<T> next) {
            this.next = next;
        }

        void setCurrent(T current) {
            this.current = current;
        }
    }
}
