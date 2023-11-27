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
