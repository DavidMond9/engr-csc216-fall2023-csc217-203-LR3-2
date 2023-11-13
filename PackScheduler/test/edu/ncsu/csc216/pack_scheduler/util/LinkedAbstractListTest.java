/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for testing LinkedAbstractListTest
 * 
 * @author - Sam McDonald
 */
public class LinkedAbstractListTest {
	
	/**
	 * Tests add() method.
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(3);
		assertEquals(list.size(), 0);

		assertThrows(NullPointerException.class,
				() -> list.add(0, null));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(5, "element"));
		
		list.add(0, "element");
		assertEquals(1, list.size());
		assertEquals(list.get(0), "element");
		
		assertThrows(IllegalArgumentException.class,
				() -> list.add(0, "element"));
		
		list.add(0, "element0");
		assertEquals(list.size(), 2);
		assertEquals("element0", list.get(0));
		assertEquals("element", list.get(1));
		
		list.add(2, "element1");
		assertEquals(list.size(), 3);
		assertEquals("element0", list.get(0));
		assertEquals("element", list.get(1));
		assertEquals("element1", list.get(2));
		
		assertThrows(IllegalArgumentException.class,
				() -> list.add(0, "element4"));
	}
	
	/**
	 * Tests remove() method.
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(4);
		list.add("element0");
		list.add("element1");
		list.add("element2");
		list.add("element3");
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(10));
		
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "element0");
		assertEquals(list.get(1), "element1");
		assertEquals(list.get(2), "element2");
		assertEquals(list.get(3), "element3");
		
		list.remove(0);
		assertEquals(list.size(), 3);
		assertEquals(list.get(0), "element1");
		assertEquals(list.get(1), "element2");
		assertEquals(list.get(2), "element3");
		
		list.remove(1);
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), "element1");
		assertEquals(list.get(1), "element3");
		
		list.remove(1);
		assertEquals(list.size(), 1);
		assertEquals(list.get(0), "element1");
		
		list.remove(0);
		assertEquals(list.size(), 0);
	}
	
	/**
	 * Tests set() method.
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(4);
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		assertThrows(NullPointerException.class,
				() -> list.set(0, null));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.add(5, "element"));
		
		list.set(0, "element0");
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "element0");
		assertEquals(list.get(1), "b");
		assertEquals(list.get(2), "c");
		assertEquals(list.get(3), "d");
		
		list.set(0, "element1");
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "element1");
		assertEquals(list.get(1), "b");
		assertEquals(list.get(2), "c");
		assertEquals(list.get(3), "d");
		
		list.set(1, "element2");
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "element1");
		assertEquals(list.get(1), "element2");
		assertEquals(list.get(2), "c");
		assertEquals(list.get(3), "d");
		
		list.set(3, "element3");
		assertEquals(list.size(), 4);
		assertEquals(list.get(0), "element1");
		assertEquals(list.get(1), "element2");
		assertEquals(list.get(2), "c");
		assertEquals(list.get(3), "element3");
		
		assertThrows(IllegalArgumentException.class,
				() -> list.set(0, "element1"));
	}
}
