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
            Element<T> previousElement = head;
            while(index-- > 1) previousElement = previousElement.getNext();
            Element<T> currentElement = new Element<>(t);
            currentElement.setNext(previousElement.getNext());
            previousElement.setNext(currentElement);
        } else {
            Element<T> second = this.head;
            Element<T> first = new Element<>(t);
            first.setNext(second);
            this.head = first;
        }
        this.size++;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        Element<T> currentElement = head;
        while (index-- > 0) currentElement = currentElement.getNext();
        return currentElement.getCurrent();
    }

    @Override
    public T set(T t, int index) throws IndexOutOfBoundsException {
        Element<T> currentElement = head;
        while (index-- > 0) currentElement = currentElement.getNext();
        T result = currentElement.getCurrent();
        currentElement.setCurrent(t);
        return result;
    }

    @Override
    public void add(T t) {
        if (this.tail != null){
            this.tail.setNext(new Element<>(t));
            this.tail = tail.getNext();
        } else {
            this.head = new Element<>(t);
            this.tail = this.head;
        }
        this.size++;
    }

    @Override
    public T delete(T t) throws IndexOutOfBoundsException {
        return this.delete(this.indexOf(t));
    }

    @Override
    public T delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException("Object not found or index exceeds data structure bounds");
        T result = this.get(index);
        if (index > 0){
            Element<T> previousElement = head;
            while(index-- > 1) previousElement = previousElement.getNext();
            Element<T> nextElement = previousElement.getNext().getNext();
            previousElement.setNext(nextElement);
        } else {
            this.head = this.head.getNext();
        }
        this.size--;
        return result;
    }

    @Override
    public boolean contains(T t) {
        Element<T> currentElement = head;
        while (currentElement != null) {
            if (currentElement.getCurrent().equals(t)) return true;
            currentElement = currentElement.getNext();
        }
        return false;
    }

    @Override
    public int indexOf(T t){
        Element<T> currentElement = head;
        int index = 0;
        while (currentElement != null) {
            if (currentElement.getCurrent().equals(t)) return index;
            currentElement = currentElement.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public IteratorInterface<T> iterator() {
        return new ListIterator(this);
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private final class Element<T> {
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

    private final class ListIterator implements IteratorInterface<T>{
        private Element<T> focused;
        private int focusedIndex;
        private MyList<T> internalList;

        ListIterator(MyList<T> internalList){
            this.internalList = internalList;
        }

        @Override
        public void first() {
            this.focused = this.internalList.head;
            this.focusedIndex = 0;
        }

        @Override
        public void last() {
            this.focused = this.internalList.tail;
            this.focusedIndex = this.internalList.size - 1;
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
            return !(this.focusedIndex < this.internalList.size && this.focusedIndex >= 0);
        }

        @Override
        public T current() throws IndexOutOfBoundsException {
            if (this.isDone()) throw new IndexOutOfBoundsException("Iterator focus is out of bounds of the data structure");
            return this.focused.getCurrent();
        }

        @Override
        public boolean addCurrent(T t) throws IndexOutOfBoundsException {
            this.internalList.insert(t, this.focusedIndex);
            Element<T> nextElement = this.focused.getNext();
            Element<T> newElement = new Element<>(t);
            this.previous();
            this.focused.setNext(newElement);
            newElement.setNext(nextElement);
            this.focused = newElement;
            this.focusedIndex++;
            this.internalList.size++;
            return true;
        }

        @Override
        public boolean addNext(T t) throws IndexOutOfBoundsException {
            Element<T> nextElement = this.focused.getNext();
            this.focused.setNext(new Element<>(t));
            this.focused.getNext().setNext(nextElement);
            this.next();
            this.internalList.size++;
            return true;
        }

        @Override
        public boolean deleteCurrent() throws IndexOutOfBoundsException {
            Element<T> nextElement = this.focused.getNext();
            this.previous();
            this.focused.setNext(nextElement);
            this.next();
            this.internalList.size--;
            return true;
        }
    }
}