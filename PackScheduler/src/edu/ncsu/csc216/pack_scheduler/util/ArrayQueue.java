package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * ArrayQueue should have a field of type ArrayList. ArrayQueue will construct
 * an ArrayList and delegate to the ArrayList.
 * 
 * ArrayQueue has a constructor with the capacity of the stack. Note that since
 * the current implementation of ArrayList does not have a capacity, the
 * capacity functionality will be part of ArrayQueue. DO NOT add capacity to
 * ArrayList!
 * 
 * @author Warren Long
 * @param <E> the type of the queue

 */
public class ArrayQueue<E> implements Queue<E> {
	/**
	 * the capacity of the queue
	 */
	private int capacity;
	/**
	 * the ArrayList that ArrayQueue delegates to
	 */
	private ArrayList<E> list;
	/**
	 * The constructor for ArrayQueue
	 * 
	 * ArrayQueue should have a field of type ArrayList. ArrayQueue will construct
	 * an ArrayList and delegate to the ArrayList.
	 * 
	 * ArrayQueue has a constructor with the capacity of the stack. Note that since
	 * the current implementation of ArrayList does not have a capacity, the
	 * capacity functionality will be part of ArrayQueue. DO NOT add capacity to
	 * ArrayList!
	 * 
	 * @param capacity the capacity of the list
	 */
	public ArrayQueue(int capacity) {
		 this.list = new ArrayList<E>();
		 this.capacity = capacity;
	}
	/**
	 * Adds the element to the back of the Queue If there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 * 
	 * @param element the element to add to the back of the Queue
	 * @throws IllegalArgumentException if there is no room (capacity has
	 * been reached), an IllegalArgumentException is thrown
	 */
	@Override
	public void enqueue(E element) {
		if (!(list.size() + 1 > capacity)) {
			list.add(list.size(), element);
		} else {
			throw new IllegalArgumentException("Capacity has been reached.");
		}
	}
	/**
	 * Removes and returns the element at the front of the Queue If the Queue is
	 * empty, an NoSuchElementException is thrown
	 * 
	 * @return the element at the front
	 * @throws NoSuchElementException if the Queue is
	 * empty, an NoSuchElementException is thrown
	 */
	@Override
	public E dequeue() {
		if (list.size() > 0) {
			return list.remove(0);
		} else {
			throw new NoSuchElementException("No element to remove");
		}
	}
	/**
	 * Returns true if the Queue is empty
	 * @return true if the Queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.size() == 0;
		
	}
	/**
	 * Returns the number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {
		return list.size();
	}
	/**
	 * Sets the Queue’s capacity If the actual parameter is negative or if it is
	 * less than the number of elements in the Queue, an 1IllegalArgumentException
	 * is thrown
	 * 
	 * @param capacity the capacity to set
	 * @throws IllegalArgumentException if the capacity is set to be less than the size or less than 0
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < list.size() || capacity < 0) {
			throw new IllegalArgumentException("Illegal capacity.");
		} 
		this.capacity = capacity;
	}

}
