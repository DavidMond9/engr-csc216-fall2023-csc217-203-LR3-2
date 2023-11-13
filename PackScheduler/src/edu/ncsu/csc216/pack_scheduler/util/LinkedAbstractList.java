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
	/** A reference to the last node in the list. */
	private ListNode back;
	
	/**
	 * Creates a new LinkedAbstractList with a set capacity
	 * @param capacity The desired capacity of the list
	 * @throws IllegalArgumentException Throws when capacity is less than 0
	 */
	public LinkedAbstractList(int capacity) throws IllegalArgumentException{
		front = null;
		back = null;
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
	public void add(int index, E data) {
		if (index < 0 || index > size()) { // If the index is out of the bounds of the list
		    throw new IndexOutOfBoundsException();
		}
		if (size == capacity) { // If the list is full
		    throw new IllegalArgumentException();
		}
		if (data == null) { // If trying to add null data
		    throw new NullPointerException();
		}
		
		// If the list is currently empty
		if(size == 0) {
			front = new ListNode(data);
			back = front;
			size++;
			return; // return, we shouldn't do anything else
		}
		
		// Loop through the list to see if this data is duplicate
		ListNode currentNode = front;
		for (int x = 0; x < size(); x++) {
			if (currentNode.data.equals(data)) {
				throw new IllegalArgumentException();
			}
			currentNode = currentNode.next;
		}
		
		// If adding to the back, don't traverse, just go to the back and add a new node, which becomes the new back.
		if(index == size) {
			ListNode newNode = new ListNode(data);
			back.next = newNode;
			back = newNode;
		} else if(index == 0) { // If adding to the front, set front to a new node that points to what front used to be
			front = new ListNode(data, front);
		} else { // If adding to the middle, set the next to be a new node that points to what used to be next
		
			// Traverse to the node right before the add
			currentNode = front;
			for (int x = 0; x < index - 1; x++) {
				currentNode = currentNode.next;
			}
			
			ListNode nextNode = currentNode.next;
			currentNode.next = new ListNode(data, nextNode);
		}
		
		// Always increment size
		size++;
	}
	
	
	/**
	 * Removes the node at the given index
	 * @param index Index of the node that's going to be removed
	 * @throws IndexOutOfBoundsException When the index of the node to be removed is less than 0 or greater than or equal to size
	 * @return The data of the node that was removed
	 */
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E removedData = null;
		if (index == 0) {
			removedData = front.data;
			front = front.next;
		}
		else {
			ListNode currentNode = front;
			for (int x = 0; x < index - 1; x++) {
				currentNode = currentNode.next;
			}
			removedData = currentNode.next.data;
			currentNode.next = currentNode.next.next;
			if(index == size - 1) back = currentNode;
		}
		size--;
		return removedData;
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
