package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * LinkedQueue should have a field of type LinkedAbstractList. LinkedQueue will
 * construct a LinkedAbstractList and delegate to the LinkedAbstractList.
 * 
 * LinkedQueue has a constructor with the capacity of the stack.Since
 * LinkedAbstractList does have a capacity, LinkedQueue can delegate to
 * LinkedAbstractList for the setCapacity() implementation.
 * 
 * @author Warren Long
 * @param <E> the type of the linked queue
 */
public class LinkedQueue<E> implements Queue<E> {
	
	/**
	 * the ArrayList that ArrayQueue delegates to
	 */
	private LinkedAbstractList<E> list;
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
	public LinkedQueue(int capacity) {
		 this.list = new LinkedAbstractList<E>(capacity);
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
		try {
			list.add(list.size(), element);
		} catch (Exception e) {
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
	 * @throws IllegalArgumentException if setCapacity throws an exception
	 * (if the capacity is less than 0 or less than the current size)
	 */
	@Override
	public void setCapacity(int capacity) {

		try {
			list.setCapacity(capacity); 
		} catch (Exception e) {
			throw new IllegalArgumentException("Illegal capacity.");
		}
	}

}
