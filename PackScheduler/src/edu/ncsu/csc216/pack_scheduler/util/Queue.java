package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Queue has a generic type and 5 methods which are described below:
 * 
 * void enqueue(E element) Adds the element to the back of the Queue If there is
 * no room (capacity has been reached), an IllegalArgumentException is thrown E
 * dequeue() Removes and returns the element at the front of the Queue If the
 * Queue is empty, an NoSuchElementException is thrown boolean isEmpty():
 * Returns true if the Queue is empty int size(): Returns the number of elements
 * in the Queue void setCapacity(int capacity) Sets the Queue’s capacity If the
 * actual parameter is negative or if it is less than the number of elements in
 * the Queue, an 1IllegalArgumentException is thrown Javadoc Queue’s methods.
 * 
 * @author Warren Long
 * @param <E> the type of the queue
 */
public interface Queue<E> {
	/**
	 * Adds the element to the back of the Queue If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element the element to add to the back of the Queue
	 * @throws IllegalArgumentException if there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 */
	void enqueue(E element);

	/**
	 * Removes and returns the element at the front of the Queue If the Queue is
	 * empty, an NoSuchElementException is thrown
	 * 
	 * @return the element at the front
	 * @throws NoSuchElementException if the Queue is
	 * empty, an NoSuchElementException is thrown
	 */
	E dequeue();
	/**
	 * Returns true if the Queue is empty
	 * @return true if the Queue is empty
	 */
	boolean isEmpty();
	/**
	 * Returns the number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	int size();

	/**
	 * Sets the Queue’s capacity If the actual parameter is negative or if it is
	 * less than the number of elements in the Queue, an 1IllegalArgumentException
	 * is thrown
	 * 
	 * @param capacity the capacity to set
	 */
	void setCapacity(int capacity);
}
