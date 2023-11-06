package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the in house implementation of ArrayList
 */
class ArrayListTest {
	/**
	 * Tests the construction of an ArrayList
	 */
	@Test
	void testArrayList() {
		assertDoesNotThrow(() -> {new ArrayList<String>(); });
	}

	
	/**
	 * Tests to see if add adds things to the array.
	 * Checks to see if correct exceptions are thrown given incorrect arguments
	 * Also tests to see if it adds them to the right places
	 */
	@Test
	void testAddIntE() {
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		ArrayList<String> stringArray = new ArrayList<String>();
		assertDoesNotThrow(() -> intArray.add(0, 15));
		assertDoesNotThrow(() -> stringArray.add(0, "Whatever"));
		assertEquals(intArray.get(0), 15);
		assertEquals(stringArray.get(0), "Whatever");
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.add(-1, 10));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.add(5, 10));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.add(-1, 10));
		assertThrows(NullPointerException.class, 
				() -> intArray.add(0, null));
		assertThrows(IllegalArgumentException.class, 
				() -> intArray.add(0, 15));
		assertDoesNotThrow(() -> intArray.add(1, 5));
		assertDoesNotThrow(() -> intArray.add(1, 3));
		assertDoesNotThrow(() -> intArray.add(1, 7));
		assertDoesNotThrow(() -> intArray.add(1, 14));
		assertDoesNotThrow(() -> intArray.add(1, 13));
		assertDoesNotThrow(() -> intArray.add(1, 300));
		assertDoesNotThrow(() -> intArray.add(1, 400));
		assertDoesNotThrow(() -> intArray.add(1, 200));
		assertDoesNotThrow(() -> intArray.add(1, 301));
		assertDoesNotThrow(() -> intArray.add(1, 999));
		assertAll(
				() -> assertEquals(intArray.get(0), 15),
				() -> assertEquals(intArray.get(1), 999),
				() -> assertEquals(intArray.get(2), 301),
				() -> assertEquals(intArray.get(3), 200),
				() -> assertEquals(intArray.get(4), 400),
				() -> assertEquals(intArray.get(5), 300),
				() -> assertEquals(intArray.get(6), 13),
				() -> assertEquals(intArray.get(7), 14),
				() -> assertEquals(intArray.get(8), 7),
				() -> assertEquals(intArray.get(9), 3),
				() -> assertEquals(intArray.get(10), 5)
				);
		
	}

	/**
	 * Tests to see if removal of elements is correct
	 * Also tests for correct thrown exceptions when giving invalid input
	 * Also tests to see if remove correctly returns the removed element
	 */
	@Test
	void testRemoveInt() {
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		ArrayList<String> stringArray = new ArrayList<String>();
		assertDoesNotThrow(() -> intArray.add(0, 15));
		assertDoesNotThrow(() -> stringArray.add(0, "Whatever"));
		assertEquals("Whatever", stringArray.remove(0));
		intArray.add(1, 10);
		intArray.add(2, 11);
		intArray.add(3, 12);
		intArray.add(4, 13);
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.remove(10));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.remove(-1));
		assertEquals(10, intArray.remove(1));
		assertEquals(intArray.get(1), 11);
		assertEquals(13, intArray.remove(3));
		assertEquals(15, intArray.remove(0));
	}

	/**
	 * Tests to see if you can set an element in the list to something else
	 * Also checks to see if what set returns is correct
	 */
	@Test
	void testSetIntE() {
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		ArrayList<String> stringArray = new ArrayList<String>();
		assertDoesNotThrow(() -> intArray.add(0, 15));
		assertDoesNotThrow(() -> stringArray.add(0, "Whatever"));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.set(-20, 15));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> stringArray.set(500, "Now"));
		assertThrows(NullPointerException.class, 
				() -> stringArray.set(0, null));
		assertDoesNotThrow(() -> intArray.add(1, 30));
		assertDoesNotThrow(() -> intArray.add(2, 45));
		assertThrows(IllegalArgumentException.class, 
				() -> intArray.set(1, 30));
		assertEquals(30, intArray.set(1, 100));
		assertEquals(3, intArray.size());
		assertEquals(100, intArray.get(1));
	}

	/**
	 * Tests to see if get returns the expected element
	 */
	@Test
	void testGetInt() {
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		ArrayList<String> stringArray = new ArrayList<String>();
		assertDoesNotThrow(() -> intArray.add(0, 15));
		assertDoesNotThrow(() -> stringArray.add(0, "Whatever"));
		assertEquals(intArray.get(0), 15);
		assertEquals(stringArray.get(0), "Whatever");
		assertDoesNotThrow(() -> intArray.add(1, 20));
		assertDoesNotThrow(() -> stringArray.add(1, "Whenever"));
		assertEquals(intArray.get(0), 15);
		assertEquals(intArray.get(1), 20);
		assertEquals(stringArray.get(0), "Whatever");
		assertEquals(stringArray.get(1), "Whenever");
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.get(-500));
		assertThrows(IndexOutOfBoundsException.class, 
				() -> intArray.get(500));
	}
	

	/**
	 * Tests to see whether returned size is correct
	 */
	@Test
	void testSize() {
		ArrayList<Integer> intArray = new ArrayList<Integer>();
		ArrayList<String> stringArray = new ArrayList<String>();
		assertEquals(0, intArray.size());
		intArray.add(0, 1);
		stringArray.add(0, "Whatever");
		assertEquals(1, intArray.size());
		intArray.add(1, 2);
		intArray.add(2, 3);
		assertEquals(3, intArray.size());
		intArray.remove(0);
		assertEquals(2, intArray.size());
	}

}
