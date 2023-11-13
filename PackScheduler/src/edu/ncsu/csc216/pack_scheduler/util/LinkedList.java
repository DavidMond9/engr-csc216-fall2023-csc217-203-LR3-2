package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Linked List based on AbstractSequentialList that utilizes an Iterator.
 * @param <E> The type to store in the list.
 * @author Joey Hughes, David Mond
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** The front node of the list. */
	private ListNode front;
	
	/** The back node of the list. */
	private ListNode back;
	
	/** The size of the list. */
	private int size;
	
	/** 
	 * Constructs a new LinkedList with 0 size and no elements.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	
	/**
	 * Returns size of list.
	 * @return returns size of list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds the given element to the given index. If it is a duplicate element or is null data,
	 *     an IllegalArgumentException is thrown.
	 * @param index The index to add the element to.
	 * @param element The data of the new element.
	 * @throws IllegalArgumentException If the element is a duplicate of another element in the list.
	 */
	@Override
	public void add(int index, E element) {
		if(this.contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		super.add(index, element);
	}
	
	/**
	 * Replaces the element at the given index to the given data.
	 * @param index The index to replace the data for.
	 * @param element The element to replace with.
	 * @throws IllegalArgumentException if the element is a duplicate element.
	 */
	@Override
	public E set(int index, E element) {
		if(this.contains(element)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		return super.set(index, element);
	}

	/**
	 * Returns a new ListIterator.
	 * @param index integer to represent the index.
	 * @return returns a new ListIterator.
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the list.
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		return new LinkedListIterator(index);
	}
	
	
	/**
	 * Class to represent a node in the list
	 * @author Joey Hughes, David Mond
	 */
	private class ListNode {
		
		/** The data at this node in the list. */
		public E data;
		
		/** The next node in the list. */
		public ListNode next;
		
		/** The previous node in the list. */
		public ListNode prev;
		
		/**
		 * Create a list node with null next and previous parameters.
		 * @param data The data to put in the node.
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * Create a list node with the given data and previous and next references.
		 * @param data The data to hold in this node.
		 * @param next The next node.
		 * @param prev The previous node.
		 */
		public ListNode(E data, ListNode next, ListNode prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	/**
	 * The iterator for this LinkedList.
	 * @author Joey Hughes, David Mond
	 */
	private class LinkedListIterator implements ListIterator<E> {
		
		/** The previous node in the list. */
		private ListNode previous;
		
		/** The next node in the list. */
		private ListNode next;
		
		/** The index of the previous node in the list. */
		private int previousIndex;
		
		/** The index of the next node in the list. */
		private int nextIndex;
		
		/** The node that was last retrieved by this iterator. */
		private ListNode lastRetrieved;

		/**
		 * Creates a new LinkedListIterator.
		 * @param index Index where the LinkedListIterator is.
		 * @throws IndexOutOfBoundsException if the given index is out of bounds for the list.
		 */
		public LinkedListIterator(int index) {
			if(index > size || index < 0) {
				throw new IndexOutOfBoundsException("Index out of bounds.");
			}
			ListNode curr = front;
			 for(int i = 0; i < index; i++) {
				 curr = curr.next;
			 }
			 this.previous = curr;
			 this.next = curr.next;
			 this.previousIndex = index - 1;
			 this.nextIndex = index;
			 this.lastRetrieved = null;
		}
		
		/**
		 * Returns true if next node, returns false if not.
		 * @return Returns a boolean if next or not.
		 */
		@Override
		public boolean hasNext() {
			if(next.data != null) {
				return true;
			}
			return false;
		}
		/**
		 * Returns the data in the next ListNode.
		 * @return Returns E for the data in the next ListNode.
		 * @throws NoSuchElementException if there is no next element.
		 */
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException("No such element.");
			}
			E nextData = next.data;
			this.lastRetrieved = next;
			this.previous = next;
			this.next = next.next;
			this.previousIndex++;
			this.nextIndex++;
			return nextData;
		}
		
		/**
		 * Returns true if previous node, returns false if not.
		 * @return Returns a boolean if previous or not.
		 */
		@Override
		public boolean hasPrevious() {
			if(previous.data != null) {
				return true;
			}
			return false;
		}
		
		/**
		 * Returns the data in the previous ListNode.
		 * @return Returns E for the data in the previous ListNode.
		 * @throws NoSuchElementException if there is no previous element.
		 */
		@Override
		public E previous() {
			if(!hasPrevious()) throw new NoSuchElementException("No such element.");
			E prevData = previous.data;
			this.lastRetrieved = previous;
			this.next = previous;
			this.previous = previous.prev;
			this.previousIndex--;
			this.nextIndex--;
			return prevData;
		}
		/**
		 * Returns the nextIndex field.
		 * @return Returns an integer for the nextIndex.
		 */
		@Override
		public int nextIndex() {
			return this.nextIndex;
		}
		
		/**
		 * Returns the previousIndex field.
		 * @return Returns an integer for the previousIndex.
		 */
		@Override
		public int previousIndex() {
			return this.previousIndex;
		}

		/**
		 * Removes the element that was last retrieved from the list using next() or previous()
		 * @throws IllegalStateException if there is no last retrieved element.
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) throw new IllegalStateException("No Last Retrieved element");
			if(lastRetrieved == previous) {
				previous = lastRetrieved.prev;
				previous.next = next;
			} else if(lastRetrieved == this.next) {
				next = lastRetrieved.next;
				next.prev = previous;
			}
			size--;
		}

		/**
		 * Sets the last retrieved element's data to the given data.
		 * @param e The data to replace the element with.
		 * @throws IllegalStateException if there is no last retrieved element.
		 * @throws NullPointerException if the given data is null
		 */
		@Override
		public void set(E e) {
			if(lastRetrieved == null) throw new IllegalStateException("No Last Retrieved element");
			if(e == null) throw new NullPointerException("Cannot add null element.");
			lastRetrieved.data = e;
		}

		/**
		 * Adds a new ListNode to the list at the position of the iterator between this
		 *     iterator's next and previous elements. After the addition, the next field
		 *     stays the same, previous become the new element. LastRetrieved is set to null.
		 * @param e The data to give the new node.
		 * @throws NullPointerException if the given data is null.
		 */
		@Override
		public void add(E e) {
			if(e == null) {
				throw new NullPointerException("Cannot add null objects."); 
			}
			ListNode curr = new ListNode(e, next, previous);
			previous.next = curr;
			next.prev = curr;
			nextIndex++;
			previous = previous.next;
			previousIndex++;
			this.lastRetrieved = null;
			size++;
		}
	}


}
