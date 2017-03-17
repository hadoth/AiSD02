package interfaces;

/**
 * Created by Karol Pokomeda on 2017-03-15.
 * Interface is an interface for List implementations specifying the most important
 * methods which are required
 */
public interface ListInterface<T> extends Iterable {
    /**
     * Method returns number of elements in the list
     * @return {int} size of the list; positive integer
     */
    int size();

    /**
     * Parametrized method for insertion of element into the list
     * @param {T} t inserted element
     * @param {int} index; positive integer
     * @throws IndexOutOfBoundsException when index >= size() || index < 0
     */
    void insert(T t, int index) throws IndexOutOfBoundsException;

    /**
     * Method gives reference to the specified object in a list
     * @param {int} index; positive integer
     * @return {T} reference to the object on the specified location in list
     * @throws IndexOutOfBoundsException when index >= size() || index < 0
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Method replaces element at the index with new provided element and returns
     * the original element at the specified indes
     * @param {T} t element to be inserted into index location
     * @param {int} index; positive integer
     * @return {T} element which previously occupied index location
     * @throws IndexOutOfBoundsException when index >= size() || index < 0
     */
    T set(T t, int index) throws IndexOutOfBoundsException;

    /**
     * Method adds provided element at the end of the list
     * @param {T} t element to be added to the list
     */
    void add(T t);

    /**
     * Method removes first occurrence of specified element from the and returns it as a result
     * @param {T} t element to be deleted
     * @return {T} deleted element
     * @throws IndexOutOfBoundsException when t is not an element of the list
     */
    T delete(T t) throws IndexOutOfBoundsException;

    /**
     * Method removes element at the specified index in the list
     * @param {int} index; positive integer
     * @return {T} deleted element
     * @throws IndexOutOfBoundsException when index >= size() || index < 0
     */
    T delete(int index) throws IndexOutOfBoundsException;

    /**
     * Tests if the list contains specified element and returns the result of this test
     * @param {T} t
     * @return {boolean} true if list contains questioned element, ffalse if not
     */
    boolean contains(T t);

    /**
     * Method searches in the list for said element and returns its location in the list
     * @param {T} t searched element
     * @return {int} index of element in the list or -1 if element is not in the list
     */
    int indexOf(T t);

    /**
     * Checks if list is empty and returns result of this test
     * @return {boolean} true if list is empty, false if not
     */
    boolean isEmpty();

    /**
     * Method generates basic iterator for ListInterface object
     * @return {IteratorInterface<T>} iterator for this list
     */
    IteratorInterface<T> iterator();

    /**
     * Method clears the content of the list
     */
    void clear();
}
