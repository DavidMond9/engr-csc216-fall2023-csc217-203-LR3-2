package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the CourseNameValidatorFSM with the while switch implementation.
 */
class CourseNameValidatorFSMTest {


	/**
	 * Test method for non state items.
	 */
	@Test
	void testOther() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("@"));
		assertEquals("Course name can only contain letters and digits.", e.getMessage());
		try {
			throw new InvalidTransitionException();
		} catch(InvalidTransitionException exception){
			assertEquals("Invalid FSM Transition.", exception.getMessage());
		}
	}
	
	/**
	 * Test method for stateInitial.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateInitial() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("1"));
		assertEquals("Course name must start with a letter.", e.getMessage());
		
		try {
			assertFalse(c.isValid("C"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertTrue(c.isValid("A111"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertTrue(c.isValid("AA111"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertTrue(c.isValid("AAA111"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertTrue(c.isValid("AAAA111"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertTrue(c.isValid("AAAA111A"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}

	/**
	 * Test method for stateL.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateL() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		try {
			assertFalse(c.isValid("AA"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertFalse(c.isValid("A1"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}
	
	/**
	 * Test method for stateLL.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateLL() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		try {
			assertFalse(c.isValid("AAA"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertFalse(c.isValid("AA1"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}
	
	/**
	 * Test method for stateLLL.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateLLL() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		try {
			assertFalse(c.isValid("AAAA"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
		
		try {
			assertFalse(c.isValid("AAA1"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}
	
	/**
	 * Test method for stateLLLL.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateLLLL() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("AAAAA"));
		assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		
		try {
			assertFalse(c.isValid("AAAA1"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}
	
	/**
	 * Test method for stateD.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateD() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("A1A"));
		assertEquals("Course name must have 3 digits.", e.getMessage());
		
		try {
			assertFalse(c.isValid("A11"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}
	
	/**
	 * Test method for stateDD.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateDD() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("A11A"));
		assertEquals("Course name must have 3 digits.", e.getMessage());
		
		try {
			assertTrue(c.isValid("A111"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}
	
	/**
	 * Test method for stateDDD.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateDDD() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("A1111"));
		assertEquals("Course name can only have 3 digits.", e.getMessage());
		
		try {
			assertTrue(c.isValid("A111A"));
		} catch (InvalidTransitionException e1) {
			fail();
		}
	}
	
	/**
	 * Test method for stateSuffix.  Tests the letter, digit, and other transitions.
	 */
	@Test
	void testStateSuffix() {
		CourseNameValidatorFSM c = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("A111AA"));
		assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());

		Exception e1 = assertThrows(InvalidTransitionException.class,
				() -> c.isValid("A111A1"));
		assertEquals("Course name cannot contain digits after the suffix.", e1.getMessage());
		
	}
	
	
}
