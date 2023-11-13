package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ListIterator;

import org.junit.jupiter.api.Test;

/**
 * Tests the in house implementation of LinkedList
 * @author Joey Hughes, David Mond
 */
class LinkedListTest {
	
	/**
	 * Tests the construction of an LinkedList
	 */
	@Test
	void testLinkedList() {
		assertDoesNotThrow(() -> {new LinkedList<String>(); });
	}

	
	/**
	 * Tests to see if add adds things to the list.
	 * Checks to see if correct exceptions are thrown given incorrect arguments
	 * Also tests to see if it adds them to the right places
	 */
	@Test
	void testAddIntE() {
		LinkedList<Integer> intList = new LinkedList<Integer>();
		LinkedList<String> stringList = new LinkedList<String>();
		assertDoesNotThrow(() -> intList.add(0, 15));
		assertDoesNotThrow(() -> stringList.add(0, "Whatever"));
		assertEquals(intList.get(0), 15);
		assertEquals(stringList.get(0), "Whatever");
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intList.add(-1, 10));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intList.add(5, 10));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intList.add(-1, 10));
		assertThrows(NullPointerException.class, 
				() -> intList.add(0, null));
		assertThrows(IllegalArgumentException.class, 
				() -> intList.add(0, 15));
		assertDoesNotThrow(() -> intList.add(1, 5));
		assertDoesNotThrow(() -> intList.add(1, 3));
		assertDoesNotThrow(() -> intList.add(1, 7));
		assertDoesNotThrow(() -> intList.add(1, 14));
		assertDoesNotThrow(() -> intList.add(1, 13));
		assertDoesNotThrow(() -> intList.add(1, 300));
		assertDoesNotThrow(() -> intList.add(1, 400));
		assertDoesNotThrow(() -> intList.add(1, 200));
		assertDoesNotThrow(() -> intList.add(1, 301));
		assertDoesNotThrow(() -> intList.add(1, 999));
		assertAll(
				() -> assertEquals(intList.get(0), 15),
				() -> assertEquals(intList.get(1), 999),
				() -> assertEquals(intList.get(2), 301),
				() -> assertEquals(intList.get(3), 200),
				() -> assertEquals(intList.get(4), 400),
				() -> assertEquals(intList.get(5), 300),
				() -> assertEquals(intList.get(6), 13),
				() -> assertEquals(intList.get(7), 14),
				() -> assertEquals(intList.get(8), 7),
				() -> assertEquals(intList.get(9), 3),
				() -> assertEquals(intList.get(10), 5)
				);
		
	}

	/**
	 * Tests to see if removal of elements is correct
	 * Also tests for correct thrown exceptions when giving invalid input
	 * Also tests to see if remove correctly returns the removed element
	 */
	@Test
	void testRemoveInt() {
		LinkedList<Integer> intList = new LinkedList<Integer>();
		LinkedList<String> stringList = new LinkedList<String>();
		assertDoesNotThrow(() -> intList.add(0, 15));
		assertDoesNotThrow(() -> stringList.add(0, "Whatever"));
		assertEquals("Whatever", stringList.remove(0));
		intList.add(1, 10);
		intList.add(2, 11);
		intList.add(3, 12);
		intList.add(4, 13);
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intList.remove(10));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intList.remove(-1));
		assertEquals(10, intList.remove(1));
		assertEquals(intList.get(1), 11);
		assertEquals(13, intList.remove(3));
		assertEquals(15, intList.remove(0));
	}

	/**
	 * Tests to see if you can set an element in the list to something else
	 * Also checks to see if what set returns is correct
	 */
	@Test
	void testSetIntE() {
		LinkedList<Integer> intList = new LinkedList<Integer>();
		LinkedList<String> stringList = new LinkedList<String>();
		assertDoesNotThrow(() -> intList.add(0, 15));
		assertDoesNotThrow(() -> stringList.add(0, "Whatever"));
		assertThrows(IllegalArgumentException.class, 
				() -> intList.set(-20, 15));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> stringList.set(500, "Now"));
		assertThrows(NullPointerException.class, 
				() -> stringList.set(0, null));
		assertDoesNotThrow(() -> intList.add(1, 30));
		assertDoesNotThrow(() -> intList.add(2, 45));
		assertThrows(IllegalArgumentException.class, 
				() -> intList.set(1, 30));
		assertEquals(30, intList.set(1, 100));
		assertEquals(3, intList.size());
		assertEquals(100, intList.get(1));
	}

	/**
	 * Tests to see if get returns the expected element
	 */
	@Test
	void testGetInt() {
		LinkedList<Integer> intList = new LinkedList<Integer>();
		LinkedList<String> stringList = new LinkedList<String>();
		assertDoesNotThrow(() -> intList.add(0, 15));
		assertDoesNotThrow(() -> stringList.add(0, "Whatever"));
		assertEquals(intList.get(0), 15);
		assertEquals(stringList.get(0), "Whatever");
		assertDoesNotThrow(() -> intList.add(1, 20));
		assertDoesNotThrow(() -> stringList.add(1, "Whenever"));
		assertEquals(intList.get(0), 15);
		assertEquals(intList.get(1), 20);
		
		// Test the iterator for the linked list using the int list
		ListIterator<Integer> intIterator = intList.listIterator(0);
		assertEquals(-1, intIterator.previousIndex());
		assertEquals(0, intIterator.nextIndex());
		assertEquals(15, intIterator.next());
		assertTrue(intIterator.hasPrevious());
		assertEquals(0, intIterator.previousIndex());
		assertEquals(1, intIterator.nextIndex());
		assertEquals(15, intIterator.previous());
		
		
		
		assertEquals(stringList.get(0), "Whatever");
		assertEquals(stringList.get(1), "Whenever");
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intList.get(-500));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intList.get(500));
	}
	

	/**
	 * Tests to see whether returned size is correct
	 */
	@Test
	void testSize() {
		LinkedList<Integer> intList = new LinkedList<Integer>();
		LinkedList<String> stringList = new LinkedList<String>();
		assertEquals(0, intList.size());
		intList.add(0, 1);
		stringList.add(0, "Whatever");
		assertEquals(1, intList.size());
		intList.add(1, 2);
		intList.add(2, 3);
		assertEquals(3, intList.size());
		intList.remove(0);
		assertEquals(2, intList.size());
	}

}
