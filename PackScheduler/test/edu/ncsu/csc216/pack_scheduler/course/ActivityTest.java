/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the CheckConflict method
 */
class ActivityTest {

	/**
	 * Tests for valid Activities
	 */
	@Test
	void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1330, 1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330, 1445);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "F", 1330, 1445);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "MW", 1500, 1600);
	    assertDoesNotThrow(() -> a1.checkConflict(a2));
	    assertDoesNotThrow(() -> a2.checkConflict(a1));
	    assertDoesNotThrow(() -> a2.checkConflict(a3));
	    assertDoesNotThrow(() -> a3.checkConflict(a2));
	    assertDoesNotThrow(() -> a1.checkConflict(a4));
	    assertDoesNotThrow(() -> a4.checkConflict(a1));
	}
	
	/**
	 * Tests for invalid Activities
	 */
	@Test
	void testConflictCheckConflict(){
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1330, 1445);
	    Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1100, 1330);
	    Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "TH", 1000, 1400);
	    Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 100, "T", 1445, 1600);
	    //When end and start intersect
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());
		//When start and end intersect
		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
		//When the start and end are in between another's start and end
		Exception e3 = assertThrows(ConflictException.class, () -> a2.checkConflict(a3));
		assertEquals("Schedule conflict.", e3.getMessage());
		//When the start and end are around another's start and end
		Exception e4 = assertThrows(ConflictException.class, () -> a3.checkConflict(a2));
		assertEquals("Schedule conflict.", e4.getMessage());
		//When one's start hits the other's end on only one day
		Exception e5 = assertThrows(ConflictException.class, () -> a1.checkConflict(a4));
		assertEquals("Schedule conflict.", e5.getMessage());
		//When the start is before the other's end, and the end is after the other's end
		Exception e6 = assertThrows(ConflictException.class, () -> a1.checkConflict(a3));
		assertEquals("Schedule conflict.", e6.getMessage());
		//When the start is before the other's start, and the end is after the other's start
		Exception e7 = assertThrows(ConflictException.class, () -> a3.checkConflict(a1));
		assertEquals("Schedule conflict.", e7.getMessage());
	}

}
