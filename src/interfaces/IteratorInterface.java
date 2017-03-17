package interfaces;

/**
 * Created by Karol Pokomeda on 2017-03-08
 * Interface used to unify iteration across different data structures.
 * @param <T>
 */
public interface IteratorInterface<T> {
	/**
	 * Method sets the focus of the iterator to the firs element of data structure
	 */
	void first();

	/**
	 * Method sets the focus of the iterator to the last element of data structure
	 */
	void last();

	/**
	 * Method sets the focus of the iterator to next element of data structure
	 */
	void next();

	/**
	 * Method sets the focus of the iterator to previous element of data structure
	 */
	void previous();

	/**
	 * Method sets the focus of the iterator to the last element of data structure
	 * @return {boolean} true if focus is outside of dataStructure bounds,
	 * false if iterator focus is inside of data structure bounds
	 */
	boolean isDone();

	/**
	 * @return {T} Focused element of data structure
	 * @throws IndexOutOfBoundsException if method is called when focus is out of data structure bounds
	 */
	T current() throws IndexOutOfBoundsException;

	/**
	 * Adds new element to data structure shifting current element to the next position; sets focus to the new element
	 * @param {T} t
	 * @return {boolean} true if the new element was successfully added to the data structure
	 * @throws IndexOutOfBoundsException if method is called when focus is out of data structure bounds
	 */
	boolean addCurrent(T t) throws IndexOutOfBoundsException;

	/**
	 * Adds element to data structure at the position next to the focused one; sets focus to the new element
	 * @param {T} t
	 * @return {boolean} true if the new element was successfully added to the data structure
	 * @throws IndexOutOfBoundsException if method is called when focus is out of data structure bounds
	 */
	boolean addNext(T t) throws IndexOutOfBoundsException;

	/**
	 * Removes focused element from the data structure; leaves the focus at the same place(next element or out of bounds)
	 * @return {boolean} true if the new element was successfully removed from the data structure
	 * @throws IndexOutOfBoundsException if method is called when focus is out of data structure bounds
	 */
	boolean deleteCurrent() throws IndexOutOfBoundsException;
}