package utils.iterators;

import interfaces.IteratorInterface;

public class MyTableIterator<T> implements IteratorInterface<T> {
	private T[] internalList;
	private int i;

	/**
	 * @param {T} t parametrized table of elements
	 */
	public MyTableIterator(T[] t){
		this.internalList = t;
	}
	
	@Override
	public void first() {
		this.i = 0;
	}

	@Override
	public void last() {
		this.i = this.internalList.length-1;
	}

	@Override
	public void next() {
		i++;
	}

	@Override
	public void previous() {
		i--;
	}

	@Override
	public boolean isDone() {
		if (i < 0 || i >= this.internalList.length) return true;
		return false;
	}

	@Override
	public T current() throws IndexOutOfBoundsException {
		if (i < 0 || i >= this.internalList.length) throw new IndexOutOfBoundsException("Current object is out of list boundaries");
		return this.internalList[i];
	}

	@Override
	public boolean addNext(T t) {
		try{
			T[] tempList = (T[]) new Object[this.internalList.length + 1];
			for (int j = 0; j <= this.i; j++){
				tempList[j] = this.internalList[j];
			}
			tempList[this.i+1] = t;
			for (int j = i+2; j < tempList.length; j++){
				tempList[j] = internalList[j-1];
			}
			this.internalList = tempList;
			this.i++;
		} catch (ClassCastException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCurrent() throws IndexOutOfBoundsException {
		try{
			T[] tempList = (T[]) new Object[this.internalList.length - 1];
			for (int j = 0; j < this.i; j++) tempList[j] = this.internalList[j];
			for (int j=this.i; j < tempList.length; j++) tempList[j] = this.internalList[j+1];
			this.internalList = tempList;
		} catch (ClassCastException e){
			return false;
		}
		return true;
	}

	@Override
	public boolean addCurrent(T t) {
		try{
			T[] tempList = (T[]) new Object[this.internalList.length + 1];
			for (int j = 0; j < this.i; j++){
				tempList[j] = this.internalList[j];
			}
			tempList[this.i] = t;
			for (int j =i+1; j < tempList.length; j++){
				tempList[j] = internalList[j-1];
			}
			this.internalList = tempList;
		} catch (ClassCastException e){
			return false;
		}
		return true;
	}
}