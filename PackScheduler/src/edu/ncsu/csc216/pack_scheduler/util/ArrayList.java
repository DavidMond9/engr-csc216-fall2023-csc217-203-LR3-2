package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
import java.util.Collection;

/**
 * In house implementation of ArrayList.  This list does not allow adding null or duplicate elements.  Initial size is set to 10 elements
 * and is doubled once max size is reached.  
 * @param <E> Type of object stored in ArrayList
 * 
 * @author - Sam McDonald, Winston Cheaz, Joey Hughes
 */
public class ArrayList<E> extends AbstractList<E> {
	/** number of elements in the list */
	private int size;
	/** the list of elements */
	private E[] list;
	

	/** initial size of list when instantiated */
	private final static int INIT_SIZE = 10;
	
	/**
	 * Constructor for ArrayList.  Sets initial capacity to INIT_SIZE and size to 0.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	
	/**
	 * Adds an element to the list according to the index provided.  Fails if element is null, index is out of bounds, or
	 * element is a duplicate.
	 * @param index index to add element to
	 * @param element element to add at index
	 * @throws IndexOutOfBoundsException thrown if index is out of bounds
	 * @throws NullPointerException thrown if element is null
	 * @throws IllegalArgumentException thrown if element is a duplicate
	 */
	@Override
	public void add(int index, E element) {
		if(index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < size; i++) {
			if(element.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		if(size == list.length) {
			growArray();
		}

		for(int i = size; i > index; i--) {
			list[i] = list[i - 1];
		}
		list[index] = element;
		size++;
	}

	/**
	 * Removes the element at the provided index.
	 * @param index index to remove element from
	 * @throws IndexOutOfBoundsException thrown if index is out of bounds.
	 * @return E element that was removed
	 */
	@Override
	public E remove(int index) {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		E removedElement = list[index];
		for(int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		size--;
		list[size] = null;
		return removedElement;
	}

	/**
	 * Sets the index to the element.
	 * @param index index to replace element at
	 * @param element element to replace at index
	 * @throws IndexOutOfBoundsException thrown if index is out of bounds
	 * @throws NullPointerException thrown if element is null
	 * @throws IllegalArgumentException thrown if element is a duplicate
	 * @return E original element at index
	 */
	@Override
	public E set(int index, E element) {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < size; i++) {
			if(element.equals(list[i])) {
				throw new IllegalArgumentException();
			}
		}
		
		E originalElement = list[index];
		list[index] = element;
		return originalElement;
	}

	/**
	 * Returns the element at index.
	 * @param index index to get element from
	 * @return E element at index
	 */
	@Override
	public E get(int index) {
		if(index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}
	
	/**
	 * Increases the size of the list by double.  Used for when max size is reached.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] tempList = (E[]) new Object[list.length * 2];
		for(int i = 0; i < size; i++) {
			tempList[i] = list[i];
		}
		list = tempList;
	}

	/**
	 * Returns the size of the ArrayList
	 * @return size of ArrayList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds all elements from the collection into the ArrayList
	 * @return boolean if adding was successful or not.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		for(E element : c) {
			this.add(element);
		}
		return true;
	}
}
