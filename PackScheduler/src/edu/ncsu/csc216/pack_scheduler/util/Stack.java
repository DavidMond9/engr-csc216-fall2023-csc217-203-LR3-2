package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Interface for a Stack data structure.
 * 
 * @param <E> The type for the elements stored in the data structure.
 * @author Joey Hughes
 */
public interface Stack<E> {
	
	/**
	 * Pushes an element to the top of the stack.
	 * @param element The element to push onto the top of the stack.
	 * @throws IllegalArgumentException if the stack is full.
	 */
	void push(E element);
	
	/**
	 * Removes and returns the element from the top of the stack (the last element added).
	 * @return The element that was removed.
	 * @throws IllegalArgumentException if the stack is empty.
	 */
	E pop();
	
	/**
	 * Returns true if the stack is empty, false if it has at least one element.
	 * @return True if the stack is empty.
	 */
	boolean isEmpty();
	
	/**
	 * Returns how many elements are in the stack.
	 * @return The size of the stack.
	 */
	int size();
	
	/**
	 * Sets the max capacity of the stack. Cannot be 0 or less than the number
	 *     of elements in the stack.
	 * @param capacity The new capacity to give the stack.
	 * @throws IllegalArgumentException if the given capacity is negative or less than
	 *     the number of elements in the stack.
	 */
	void setCapacity(int capacity);
}
