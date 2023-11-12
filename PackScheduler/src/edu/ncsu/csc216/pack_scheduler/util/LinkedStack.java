package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A Stack linear data structure with an underlying LinkedAbstractList.
 * @param <E> The type of element to use in the LinkedStack.
 * @author Joey Hughes
 */
public class LinkedStack<E> implements Stack<E> {
	
	/** The underlying LinkedAbstractList that will hold the elements. */
	private LinkedAbstractList<E> list;
	
	/**
	 * Constructs a new LinkedStack with the given max capacity.
	 * @param capacity The max capacity to give this stack.
	 * @throws IllegalArgumentException if the capacity is negative.
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Pushes an element to the top of the stack.
	 * @param element The element to push onto the top of the stack.
	 * @throws IllegalArgumentException if the stack is full or the element is a duplicate.
	 * @throws NullPointerException if the element is null.
	 */
	@Override
	public void push(E element) {
		list.add(element);
	}

	/**
	 * Removes and returns the element from the top of the LinkedStack (the last element added).
	 * @return The element that was removed.
	 * @throws IllegalArgumentException if the stack is empty.
	 */
	@Override
	public E pop() {
		if(list.size() == 0) throw new EmptyStackException();
		return list.remove(list.size() - 1);
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
		list.setCapacity(capacity);
	}
}
