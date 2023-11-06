package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Test class for the LinkedQueue class.
 * 
 * @author Warren Long
 */
class LinkedQueueTest {
	/**
	 * tests inserting a single element
	 */
	@Test
	void testInsertSingleElement() {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>(5);
		q.enqueue(1);
		assertEquals(1, q.dequeue());
	}
	/*
	 * tests inserting multiple elements
	 */
	@Test
	void testInsertMultipleElements() {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>(11);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9);
		q.enqueue(10);
		q.enqueue(11);

		assertEquals(1, q.dequeue());
		assertEquals(2, q.dequeue());
		assertEquals(3, q.dequeue());
		assertEquals(4, q.dequeue());
		assertEquals(5, q.dequeue());
		assertEquals(6, q.dequeue());
		assertEquals(7, q.dequeue());
		assertEquals(8, q.dequeue());
		assertEquals(9, q.dequeue());
		assertEquals(10, q.dequeue());
		assertEquals(11, q.dequeue());

	}
	/**
	 * test removing a single element
	 */
	@Test
	void testRemoveSingleElement() {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>(11);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9);
		q.enqueue(10);
		q.enqueue(11);
		
		assertEquals(1, q.dequeue());
		assertEquals(2, q.dequeue());
		assertEquals(3, q.dequeue());
		assertEquals(4, q.dequeue());
		assertEquals(5, q.dequeue());
		assertEquals(6, q.dequeue());
		assertEquals(7, q.dequeue());
		assertEquals(8, q.dequeue());
		assertEquals(9, q.dequeue());
		assertEquals(10, q.dequeue());
		assertEquals(11, q.dequeue());
	}
	/**
	 * Testing interleaved insertions and removals
	 */
	@Test
	void testInterleavedInsertAndRemove() {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>(11);
		
		

		assertTrue(q.isEmpty());

	
		q.enqueue(1);
		q.enqueue(2);
		assertEquals(1, q.dequeue());
		q.enqueue(3);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		assertEquals(2, q.dequeue());
		q.enqueue(8);
		q.enqueue(9);
		q.enqueue(10);
		q.enqueue(11);
		
		assertEquals(9, q.size());
		assertFalse(q.isEmpty());

		assertEquals(3, q.dequeue());
		assertEquals(4, q.dequeue());
		assertEquals(5, q.dequeue());
		assertEquals(6, q.dequeue());
		assertEquals(7, q.dequeue());
		assertEquals(8, q.dequeue());
		assertEquals(9, q.dequeue());
		assertEquals(10, q.dequeue());
		assertEquals(11, q.dequeue());
		
		
	}
	/**
	 * testing removing from an empty queue
	 */
	@Test
	void testRemoveEmptyQueue() {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>(5);
		assertThrows(NoSuchElementException.class, () -> q.dequeue());
	}
	/**
	 * testing setting the capacity
	 */
	@Test
	void testSetCapacity() {
		LinkedQueue<Integer> q = new LinkedQueue<Integer>(5);
		q.enqueue(1);
		assertThrows(IllegalArgumentException.class, () -> q.setCapacity(0));
		assertThrows(IllegalArgumentException.class, () -> q.setCapacity(-1));
		q.enqueue(2);
		q.enqueue(3);
		assertThrows(IllegalArgumentException.class, () -> q.setCapacity(2));
		assertThrows(IllegalArgumentException.class, () -> q.setCapacity(-1));
		q.setCapacity(10);
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.enqueue(9);
		q.enqueue(10);
		assertThrows(IllegalArgumentException.class, () -> 	q.enqueue(10));

	}

}
