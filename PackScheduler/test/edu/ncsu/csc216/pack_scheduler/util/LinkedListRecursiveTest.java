package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListRecursiveTest {
	
	/**
	 * Tests the construction of an LinkedListRecursive
	 */
	@Test
	void testLinkedListRecursive() {
		assertDoesNotThrow(() -> {new LinkedListRecursive<String>(); });
	}

	
	/**
	 * Tests to see if add adds things to the list.
	 * Checks to see if correct exceptions are thrown given incorrect arguments
	 * Also tests to see if it adds them to the right places
	 */
	@Test
	void testAddIntE() {
		LinkedListRecursive<Integer> intList = new LinkedListRecursive<Integer>();
		LinkedListRecursive<String> stringList = new LinkedListRecursive<String>();
		assertDoesNotThrow(() -> intList.add(15));
		assertDoesNotThrow(() -> stringList.add("Whatever"));
		assertEquals(intList.get(0), 15);
		assertEquals(stringList.get(0), "Whatever");
		assertThrows(IllegalArgumentException.class, 
				() -> intList.add(15));
		assertDoesNotThrow(() -> intList.add(5));
		assertDoesNotThrow(() -> intList.add(3));
		assertDoesNotThrow(() -> intList.add(7));
		assertDoesNotThrow(() -> intList.add(14));
		assertDoesNotThrow(() -> intList.add(13));
		assertDoesNotThrow(() -> intList.add(300));
		assertDoesNotThrow(() -> intList.add(400));
		assertDoesNotThrow(() -> intList.add(200));
		assertDoesNotThrow(() -> intList.add(301));
		assertDoesNotThrow(() -> intList.add(999));
		assertEquals(intList.size(), 11);
				assertEquals(intList.get(0), 15);
				assertEquals(intList.get(1), 5);
				 assertEquals(intList.get(2), 3);
				assertEquals(intList.get(3), 7);
				 assertEquals(intList.get(4), 14);
				assertEquals(intList.get(5), 13);
				 assertEquals(intList.get(6), 300);
				 assertEquals(intList.get(7), 400);
				assertEquals(intList.get(8), 200);
				assertEquals(intList.get(9), 301);
				assertEquals(intList.get(10), 999);
				
			
		
	}

	/**
	 * Tests to see if removal of elements is correct
	 * Also tests for correct thrown exceptions when giving invalid input
	 * Also tests to see if remove correctly returns the removed element
	 */
	@Test
	void testRemoveInt() {
		LinkedListRecursive<Integer> intList = new LinkedListRecursive<Integer>();
		LinkedListRecursive<String> stringList = new LinkedListRecursive<String>();
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
		LinkedListRecursive<Integer> intList = new LinkedListRecursive<Integer>();
		LinkedListRecursive<String> stringList = new LinkedListRecursive<String>();
		assertDoesNotThrow(() -> intList.add(0, 15));
		assertDoesNotThrow(() -> stringList.add(0, "Whatever"));
		assertThrows(IndexOutOfBoundsException.class, 
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
		LinkedListRecursive<Integer> intList = new LinkedListRecursive<Integer>();
		LinkedListRecursive<String> stringList = new LinkedListRecursive<String>();
		assertDoesNotThrow(() -> intList.add(0, 15));
		assertDoesNotThrow(() -> stringList.add(0, "Whatever"));
		assertEquals(intList.get(0), 15);
		assertEquals(stringList.get(0), "Whatever");
		assertDoesNotThrow(() -> intList.add(1, 20));
		assertDoesNotThrow(() -> stringList.add(1, "Whenever"));
		assertEquals(intList.get(0), 15);
		assertEquals(intList.get(1), 20);
		
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
		LinkedListRecursive<Integer> intList = new LinkedListRecursive<Integer>();
		LinkedListRecursive<String> stringList = new LinkedListRecursive<String>();
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
