package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A Stack linear data structure with an underlying ArrayList.
 * @param <E> The type of element to use in the ArrayStack.
 * @author Joey Hughes
 */
public class ArrayStack<E> implements Stack<E> {

	/** The max capacity of this stack. */
	private int capacity;
	
	/** The underlying ArrayList that will hold the elements. */
	private ArrayList<E> list;
	
	/**
	 * Constructs a new ArrayStack with the given max capacity.
	 * @param capacity The max capacity to give this stack.
	 * @throws IllegalArgumentException if the capacity is negative.
	 */
	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	/**
	 * Pushes an element to the top of the stack.
	 * @param element The element to push onto the top of the stack.
	 * @throws IllegalArgumentException if the stack is full or the element is a duplicate.
	 * @throws NullPointerException if the element is null.
	 */
	@Override
	public void push(E element) {
		if(list.size() == capacity) throw new IllegalArgumentException("The stack is full.");
		list.add(element);
	}

	/**
	 * Removes and returns the element from the top of the ArrayStack (the last element added).
	 * @return The element that was removed.
	 * @throws IllegalArgumentException if the stack is empty.
	 */
	@Override
	public E pop() {
		if(list.size() == 0) throw new EmptyStackException();
		E data = list.remove(list.size() - 1);
		return data;
	}

	/**
	 * Returns true if the stack is empty, false if it has at least one element.
	 * @return True if the stack is empty.
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns how many elements are in the stack.
	 * @return The size of the stack.
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * Sets the max capacity of the stack. Cannot be 0 or less than the number
	 *     of elements in the stack.
	 * @param capacity The new capacity to give the stack.
	 * @throws IllegalArgumentException if the given capacity is negative or less than
	 *     the number of elements in the stack.
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException("Invalid capacity.");
		}
		this.capacity = capacity;
	}

}
