package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * Test class for the ArrayStack class.
 * @author Joey Hughes
 */
class LinkedStackTest {

	/** The LinkedStack to be used in the tests */
	private LinkedStack<String> list;

	/**
	 * Tests the constructor.
	 */
	@Test
	void testArrayStack() {
		list = new LinkedStack<String>(0);
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		assertThrows(IllegalArgumentException.class, () -> {list.push("element 1"); });
		list = new LinkedStack<String>(2);
		assertEquals(0, list.size());
		list.push("element 1");
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
		list.push("element 2");
		assertEquals(2, list.size());
		assertThrows(IllegalArgumentException.class, () -> {list.push("element 3"); });
		assertEquals(2, list.size());
		assertThrows(IllegalArgumentException.class, () -> {new LinkedStack<String>(-1); });
	}

	/**
	 * Tests the push() and pop() methods.
	 */
	@Test
	void testPushAndPop() {
		list = new LinkedStack<String>(3);
		assertEquals(0, list.size());
		assertThrows(NullPointerException.class, () -> {list.push(null); });
		assertEquals(0, list.size());
		list.push("element 1");
		assertEquals(1, list.size());
		assertThrows(IllegalArgumentException.class, () -> {list.push("element 1"); });
		assertEquals(1, list.size());
		list.push("element 2");
		assertEquals(2, list.size());
		list.push("element 3");
		assertEquals(3, list.size());
		assertThrows(IllegalArgumentException.class, () -> {list.push("element 4"); });
		assertEquals("element 3", list.pop());
		assertEquals(2, list.size());
		assertEquals("element 2", list.pop());
		assertEquals(1, list.size());
		System.out.println("OMYGOMYOMGOYMTROIURMTOIGMTRIOG THIS IS WHEN I CARE ABOUT -------------------");
		list.push("element 4");
		assertEquals(2, list.size());
		assertEquals("element 4", list.pop());
		assertEquals(1, list.size());
		assertEquals("element 1", list.pop());
		assertEquals(0, list.size());
		assertThrows(EmptyStackException.class, () -> {list.pop(); });
		assertEquals(0, list.size());
	}

	/**
	 * Tests the setCapacity() method.
	 */
	@Test
	void testSetCapacity() {
		list = new LinkedStack<String>(3);
		assertEquals(0, list.size());
		assertThrows(IllegalArgumentException.class, () -> {list.setCapacity(-1); });
		list.setCapacity(0);
		assertEquals(0, list.size());
		assertThrows(IllegalArgumentException.class, () -> {list.push("element 1"); });
		list.setCapacity(1);
		assertEquals(0, list.size());
		list.push("element 1");
		assertEquals(1, list.size());
		assertThrows(IllegalArgumentException.class, () -> {list.push("element 2"); });
		list.setCapacity(5);
		assertEquals(1, list.size());
		list.push("element 2");
		assertEquals(2, list.size());
		assertThrows(IllegalArgumentException.class, () -> {list.setCapacity(1); });
		assertEquals(2, list.size());
		list.setCapacity(2);
		assertEquals(2, list.size());
	}

}
