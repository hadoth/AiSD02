package utils.list;

import interfaces.IteratorInterface;
import interfaces.ListInterface;

/**
 * Created by Karol Pokomeda on 2017-03-24.
 */
public class MyListDouble<T> implements ListInterface<T> {
    private Element<T> head;
    private Element<T> tail;
    private int size;

    public MyListDouble() {
        this.clear();
    }

    @Override
    public IteratorInterface<T> iterator() {
        return new ListIterator(this);
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
            Element<T> nextElement = previousElement.getNext();
            Element<T> currentElement = new Element<>(t);
            currentElement.setPrevious(previousElement);
            currentElement.setNext(nextElement);
            previousElement.setNext(currentElement);
            nextElement.setPrevious(currentElement);
        } else {
            Element<T> second = this.head;
            Element<T> first = new Element<>(t);
            first.setNext(second);
            second.setPrevious(first);
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
        Element<T> newTail = new Element<>(t);
        if (!this.isEmpty()){
            newTail.setPrevious(this.tail);
            this.tail.setNext(newTail);
            this.tail = newTail;
        } else {
            this.head = newTail;
            this.tail = newTail;
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
            while(index-- >1) previousElement = previousElement.getNext();
            Element<T> nextElement = previousElement.getNext().getNext();
            previousElement.setNext(nextElement);
            nextElement.setPrevious(previousElement);
        } else {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        }
        this.size--;
        return result;
    }

    @Override
    public boolean contains(T t) {
        Element<T> currentElement = head;
        while(currentElement != null) {
            if (currentElement.getCurrent().equals(t)) return true;
            currentElement = currentElement.getNext();
        }
        return false;
    }

    @Override
    public int indexOf(T t) {
        Element<T> currentElement = head;
        int index = 0;
        while(currentElement != null){
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
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static final class Element<T> {
        private T current;
        private Element<T> previous;
        private Element<T> next;

        Element(T t){
            this.current = t;
            this.next = null;
            this.previous = null;
        }

        Element<T> getNext() {
            return next;
        }
        Element<T> getPrevious() {return previous;}

        T getCurrent() {
            return current;
        }

        void setNext(Element<T> next) {
            this.next = next;
        }

        void setPrevious(Element<T> previous){this.previous = previous;}

        void setCurrent(T current) {
            this.current = current;
        }
    }

    private final class ListIterator implements IteratorInterface<T>{
        private Element<T> focused;
        private int focusedIndex;
        private MyListDouble<T> internalList;

        ListIterator(MyListDouble<T> internalList){
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
            try{
                this.focused = this.focused.getPrevious();
            } catch (NullPointerException a) {
                this.focused = null;
            } finally {
                this.focusedIndex--;
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
            Element<T> newElement = new Element<>(t);
            newElement.setNext(this.focused);
            newElement.setPrevious(this.focused.getPrevious());
            newElement.getNext().setPrevious(newElement);
            newElement.getPrevious().setNext(newElement);
            this.focused = newElement;
            this.internalList.size++;
            return true;
        }

        @Override
        public boolean addNext(T t) throws IndexOutOfBoundsException {
            Element<T> newElement = new Element<>(t);
            Element<T> nextElement = this.focused.getNext();
            newElement.setNext(nextElement);
            if (nextElement != null) nextElement.setPrevious(newElement);
            newElement.setPrevious(this.focused);
            this.focused.setNext(newElement);
            this.focused = newElement;
            this.internalList.size++;
            return true;
        }

        @Override
        public boolean deleteCurrent() throws IndexOutOfBoundsException {
            Element<T> previous = this.focused.getPrevious();
            Element<T> next = this.focused.getNext();
            if (previous != null) previous.setNext(next);
            if (next != null) next.setPrevious(previous);
            this.internalList.size--;
            return true;
        }
    }
}