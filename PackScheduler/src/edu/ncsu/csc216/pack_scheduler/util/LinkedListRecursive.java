package edu.ncsu.csc216.pack_scheduler.util;


/**
 * This is a custom implementation of a recursive linked list that doesn’t allow
 * for null elements or duplicate elements as defined by the equals() method.
 * It is used for Faculty objects.
 * @param <E> the type of the list
 * @author Warren Long, David Mond
 */
public class LinkedListRecursive<E> {
	/**
	 * the size of the list
	 */
	private int size;
	/**
	 * the front list node of the list
	 */
	private ListNode front;
	
	/**
	 * Constructor for LinkedListRecursive, initializes the state to represent an empty list.
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * Getter for getting the size of the list.
	 * @return returns an integer, size of the list.
	 */
	public int size() {
		return size;
	}
	/**
	 * Method to check if the size of the list is zero, returns true if empty, false if not.
	 * @return Returns a boolean to determine if size of list is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Gets the data in the list node at a specific index.
	 * @param idx index to get the data from.
	 * @return Returns an element of the data in the list node.
	 */
	public E get(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		E returnVal = front.get(idx);
		return returnVal;
	}
	/**
	 * This implementation will have a public/private pair of methods. The public
	 * method is LinkedListRecursive.contains(E element) and returns a boolean. The
	 * private method is ListNode.contains(E element). The public method handles the
	 * special case of an empty list. If the list is not empty, then the public
	 * method transfers the flow of control to the private ListNode.contains(E
	 * element) method, which completes the recursion to check the elements in the
	 * list.
	 * 
	 * @param value the value for the list to see if it contains
	 * @return true if it contains the value
	 */
	public boolean contains(E value) {
		if (front == null) {
			return false;
		}
		return front.contains(value);
	}
	

	/**
	 * This implementation will have a public/private pair of methods. The public
	 * method is LinkedListRecursive.add(E element) and returns a boolean. 
	 * 
	 * The
	 * private method is ListNode.add(E element). The public method checks that the
	 * element isn’t already in the list (an IllegalArgumentException is thrown if
	 * the element already exists) and handles the special case of adding a node to
	 * an empty list. 
	 * 
	 * If the list is not empty, then the public method transfers the
	 * flow of control to the private ListNode.add(E element) method, which
	 * completes the recursion to add to element to the end of the list.
	 * 
	 * Don’t forget to increment size on all paths where the element is added!
	 * 
	 * @param value the value to add to the list
	 * @return true if it was added, false otherwise
	 */
	public boolean add(E value) {
		if (isEmpty()) {
			front = new ListNode(value);
			size++;
			return true;
		} else if (contains(value)) {
			throw new IllegalArgumentException();
		}
		
		return front.recursiveAdd(value);
	}

	/**
	 * This implementation will have a public/private pair of methods. The public
	 * method is LinkedListRecursive.add(int idx, E element). The private method is
	 * ListNode.add(int idx, E element). 
	 * 
	 * The public method checks that the element
	 * isn’t already in the list (an IllegalArgumentException is thrown if the
	 * element already exists), handles bounds checking on the index (an
	 * IndexOutOfBoundsException is thrown for an invalid index), checks that the
	 * element isn’t null (a NullPointerException is thrown if element is null), and
	 * the special case of adding a node to the front of the list. 
	 * 
	 * If the element is
	 * added to the middle or end of the list, then the public method transfers the
	 * flow of control to the private ListNode.add(int idx, E element) method, which
	 * completes the recursion to add to element at the appropriate location.
	 * 
	 * Don’t forget to increment size on all paths where the element is added!
	 * 
	 * @param idx the index to add the value to
	 * @param value the value to add at the index
	 * @return true if the value was added, false otherwise
	 */
	public boolean add(int idx, E value) {
		if (contains(value)) {
			throw new IllegalArgumentException();
		}
		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (value == null) {
			throw new NullPointerException();
		}
		if (idx == 0) {
			if (isEmpty()) {
				front = new ListNode(value);
				size++;
				return true;
			} else {
				ListNode temp = front;
				front = new ListNode(value);
				front.next = temp;
				size++;
				return true;
			}
			
		} 		
		return front.recursiveAdd(idx, value);
	}

	/**
	 * Sets the given index in the list to be the given element.
	 * 
	 * This implementation will have a public/private pair of methods. The public
	 * method is LinkedListRecursive.set(int idx, E element). The private method is
	 * ListNode.set(int idx, E element). Both methods return the data previously in
	 * the ListNode. The public method handles bounds checking on the index. Then
	 * the public method transfers the flow of control to the private
	 * ListNode.set(int idx, E element) method, which completes the recursion to set
	 * to element at the appropriate location.
	 * 
	 * @param idx   The index to change.
	 * @param value The value to give the index.
	 * @return The element value that was replaced.
	 */
	public E set(int idx, E value) {
		if(idx < 0 || idx > size - 1) throw new IndexOutOfBoundsException();
		if(value == null) throw new NullPointerException();
		if(contains(value)) throw new IllegalArgumentException();

		return front.set(idx, value);
	}
	
	/**
	 * Removes an element at a specific index in the list.
	 * 
	 * This implementation will have a public/private pair of methods. The public
	 * method is LinkedListRecursive.remove(int idx). The private method is
	 * ListNode.remove(int idx). Both methods return the data of the removed
	 * ListNode. The public method handles bounds checking on the index and the
	 * special case of removing the first node in the list. Then the public method
	 * transfers the flow of control to the private ListNode.remove(int idx) method,
	 * which completes the recursion to remove to element at the appropriate
	 * location. You’ll need to look ahead.
	 * 
	 * Don’t forget to decrement size on all paths where the element is removed!
	 * 
	 * @param idx the index to be removing the element at.
	 * @return returns the data of the removed element.
	 */
	public E remove(int idx) {
		if(idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		if(idx == 0) {
			E removedEle = front.data;
			front = front.next;
			size--;
			return removedEle;
		}
		return front.remove(idx);
	}
	/**
	 * Removes a specific element in the list.
	 * 
	 * This implementation will have a public/private pair of methods. The public
	 * method is LinkedListRecursive.remove(E element). 
	 * 
	 * The private method is
	 * ListNode.remove(E element). Both methods return the true if the element is
	 * removed. 
	 * 
	 * The public method checks if the element is null, if the list is
	 * empty, and handles the special case of removing the first node in the list.
	 * Then the public method transfers the flow of control to the private
	 * ListNode.remove(E element) method, which completes the recursion to remove to
	 * element at the appropriate location. You’ll need to look ahead.
	 * 
	 * Don’t forget to decrement size on all paths where the element is removed!
	 * @param ele element to be removed in the list.
	 * @return returns true if element was removed, false if element was not removed.
	 */
	public boolean remove(E ele) {
		if(ele == null || isEmpty()) {
			// I am not sure I should throw this, it does not say.
			// It just says to check.
			return false;
		}
		if (front.data.equals(ele)) {
			front = front.next;
			size--;
			return true;
		} 
		return front.remove(ele);
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
		 * Gets the data in the list node at a specific index.
		 * @param idx index to get the data from.
		 * @return Returns an element of the data in the list node.
		 */
		private E get(int idx) {
			if(idx == 0) {
				return data;
			}
			return next.get(idx - 1);
		}
		/**
		 * Checks if something is contained
		 * 
		 * @param value the value to check
		 * @return true if it is contained
		 */
		private boolean contains(E value) {
			if (data.equals(value)) {
				return true;
			} 
			if (next == null) {
				return false;
			} else {
				return next.contains(value);
			}
		}
		/**
		 * adds a value to the list
		 * 
		 * @param value the value to add
		 * @return true if the value is added
		 */
		private boolean recursiveAdd(E value) {
			// return false if is same
			if (next == null) {
				next = new ListNode(value);
				size++;
				return true;
			} else {
				return next.recursiveAdd(value);
			}
		}
		/**
		 * adds a value to the list
		 * @param idx the index to add the value at
		 * @param value the value to add
		 * @return true if the value is added
		 */
		private boolean recursiveAdd(int idx, E value) {
			if(idx == 1) {
				ListNode temp = next;
				next = new ListNode(value);
				next.next = temp;
				size++;
				return true;
			}
			return next.recursiveAdd(idx - 1, value);
		}
		/**
		 * Removes an element at a specific index in the list.
		 * @param idx the index to be removing the element at.
		 * @return returns the data of the removed element.
		 */
		private E remove(int idx) {
			if(idx == 1) {
				E removedEle = this.next.data;
				this.next = this.next.next;
				size--;
				return removedEle;
			}
			return next.remove(idx - 1);
		}
		/**
		 * Removes a specific element in the list.
		 * @param ele element to be removed in the list.
		 * @return returns true if element was removed, false if element was not removed.
		 */
		private boolean remove(E ele) {
			if(this.next == null) {
				return false;
			}
			if(this.next.data == ele) {
				this.next = this.next.next;
				size--;
				return true;
			}
	
			return next.remove(ele);
		}
		/**
		 * Recursive method to set the element at the given index to the given value.
		 * @param idx The index. This being 0 is a base case.
		 * @param value The value to set.
		 * @return The value that was replaced.
		 */
		private E set(int idx, E value) {
			if(idx == 0) {
				E oldValue = data;
				data = value;
				return oldValue;
			}
			return next.set(idx - 1, value);
		}
		
	}
}
