package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
/**
 * A linked list that doesn't allow for Null or duplicate elements as defined by .equals()
 * Also has a capacity
 * @param <E> Element type that the Linked List holds
 * @author Sam McDonald, Joey Hughes, Winston Cheaz
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	/** The first node in the list */
	private ListNode front;
	/** The size of the list */
	private int size;
	/** The capacity of the list */
	private int capacity;
	/** The back of the list. */
	private ListNode back;
	
	/**
	 * Creates a new LinkedAbstractList with a set capacity
	 * @param capacity The desired capacity of the list
	 * @throws IllegalArgumentException Throws when capacity is less than 0
	 */
	public LinkedAbstractList(int capacity) throws IllegalArgumentException{
		front = null;
		size = 0;
		setCapacity(capacity);
	}
	
	/**
	 * Attempts to set the capacity of the list
	 * @param capacity The desired capacity of the list
	 * @throws IllegalArgumentException Throws when capacity is less than 0, or when capacity is less than size
	 */
	public void setCapacity(int capacity) throws IllegalArgumentException{
		if (capacity < 0 || capacity < this.size) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	/**
	 * Returns the element of the node in the list at the given index
	 * @param index The index of the node that returns its element
	 * @throws IndexOutOfBoundsException When index is less than 0 or greater than or equal to size
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException{
		ListNode currentNode = front;
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		else {
			for (int x = 0; x < index; x++) {
				currentNode = currentNode.next;
			}
		}
		return currentNode.data;
	}
	
	/** 
	 * Replaces the element at the given index with the given data
	 * @param index Index of the node that should be modified
	 * @param data Data that the node should now have
	 * @throws IndexOutOfBoundsException When index less than 0 or index greater than or equal to size
	 * @throws NullPointerException When the element is null
	 * @throws IllegalArgumentException If the element already exists in the list
	 * @return The overwritten element
	 */
	public E set(int index, E data) throws IndexOutOfBoundsException, 
	NullPointerException, IllegalArgumentException{
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		if (data == null) {
			throw new NullPointerException();
		}
		ListNode currentNode = front;
		for (int x = 0; x < size(); x++) {
			if (currentNode.data.equals(data)) {
				throw new IllegalArgumentException();
			}
			currentNode = currentNode.next;
		}
		currentNode = front;
		for (int x = 0; x < index; x++) {
			currentNode = currentNode.next;
		}
		E overwrittenElement = currentNode.data;
		currentNode.data = data;
		return overwrittenElement;
	}
	
	/** 
	 * Adds a new node to the list at the current index.
	 * Elements cannot be added over capacity
	 * @param index Index of the node that should be modified
	 * @param data Data that the node should now have
	 * @throws IndexOutOfBoundsException When index less than 0 or index greater than or equal to size
	 * @throws NullPointerException When the element is null
	 * @throws IllegalArgumentException If the element already exists in the list. 
	 * 	Also throws when the size is equal to capacity
	 */
	public void add(int index, E data) throws IndexOutOfBoundsException,
    NullPointerException, IllegalArgumentException {
		if (index < 0 || index > size()) {
		    throw new IndexOutOfBoundsException();
		}
		if (size == capacity) {
		    throw new IllegalArgumentException();
		}
		if (data == null) {
		    throw new NullPointerException();
		}
		

		// Check if the data already exists in the list
		ListNode currentNode = front;
		for (int x = 0; x < size(); x++) {
			if (currentNode.data.equals(data)) {
				throw new IllegalArgumentException();
			}
			currentNode = currentNode.next;
		}
		// Adding to the back of the list constant-time
		if (index == size()) {
		    if (size == 0) {
		        front = new ListNode(data);
		        back = new ListNode(data);
		    } else {
		        back.next = new ListNode(data);
		        back = back.next;
		    }
		    size++;
		    return;
		}
		
		// Inserting at the specified index
		currentNode = front;
		for (int x = 0; x < index - 1; x++) {
		    currentNode = currentNode.next;
		}
		if (index == 0) {
		    // Adding at the beginning
		    front = new ListNode(data, front);
		    if(size == 0) {
		    	back = front;
		    }
		}
		else {
			ListNode nextNode = currentNode.next;
		    currentNode.next = new ListNode(data, nextNode);
		}
		size++;
	}
	
	/**
	 * Removes the node at the given index
	 * @param index Index of the node that's going to be removed
	 * @throws IndexOutOfBoundsException When the index of the node to be removed is less than 0 or greater than or equal to size
	 * @return The data of the node that was removed
	 */
	public E remove(int index) throws IndexOutOfBoundsException {
	    E removedElement = null;

	    if (index < 0 || index >= size()) {
	        throw new IndexOutOfBoundsException();
	    }

	    if (index == 0) {
	        removedElement = front.data;
	        front = front.next;
	        if (size == 1) {
	            back = null;
	        }
	    } else {
	        ListNode currentNode = front;
	        for (int x = 0; x < index - 1; x++) {
	            currentNode = currentNode.next;
	        }

	        removedElement = currentNode.next.data;
	        currentNode.next = currentNode.next.next;

	        if (index == size - 1) {
	            back = currentNode;
	        }
	    }

	    size--;
	    return removedElement;
	}
	/**
	 * Returns the size of the LinkedAbstractList
	 * @return The size of the LinkedAbstractList
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Class representing a single element in this linked list. Holds data and a single reference to the next element.
	 * 
	 * @author Sam McDonald, Joey Hughes, Winston Cheaz
	 */
	private class ListNode {
		/** The data the node holds */
		private E data;
		/** The next node in the list */
		private ListNode next;
		
		/**
		 * Creates a new ListNode with only data
		 * @param data Data to put in the listNode
		 */
		public ListNode(E data) {
			this.data = data;
			next = null;
		}
		
		/**
		 * Inserts a ListNode into a list given data and the next node in the list
		 * @param data Data to put in the listNode
		 * @param next The next node in the list
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
	}
}
