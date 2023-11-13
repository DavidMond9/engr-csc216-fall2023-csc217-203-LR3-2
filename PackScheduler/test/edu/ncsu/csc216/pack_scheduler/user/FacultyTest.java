package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * tests faculty class
 * @author Warren Long
 */
class FacultyTest {
	
	/**
	 * Test First Name
	 */
	private static final String FIRST_NAME = "Bob";
	/**
	 * Test Last Name
	 */
	private static final String LAST_NAME = "Builder";
	/**
	 * Test ID
	 */
	private static final String ID = "bbuilder";
	/**
	 * Test email
	 */
	private static final String EMAIL = "bbuilder@ncsu.edu";
	/**
	 * Test Password
	 */
	private static final String PASSWORD = "building";
	/**
	 * Test max credits
	 */
	private static final int MAX_COURSES = 2;
	
	/**
	 * Tests constructing a valid Faculty with a specific credit amount given
	 */
	@Test
	void testFaculty() {
		//Tests a valid construction
		Faculty s = assertDoesNotThrow(
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES),
				"Should not throw exception");
		
		assertAll("Course", 
				() -> assertEquals(FIRST_NAME, s.getFirstName(), "incorrect name"), 
				() -> assertEquals(LAST_NAME, s.getLastName(), "incorrect title"),
				() -> assertEquals(ID, s.getId(), "incorrect section"), 
				() -> assertEquals(EMAIL, s.getEmail(), "incorrect credits"),
				() -> assertEquals(PASSWORD, s.getPassword(), "incorrect instructor id"),
				() -> assertEquals(MAX_COURSES, s.getMaxCourses(), "incorrect meeting days"));		
	}
	/**
	 * Tests constructing an invalid Faculty with specific credit amount given
	 */
	@Test
	void testInvalidFaculty() {
		//Checks for invalid First Name
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("", LAST_NAME, ID, EMAIL, PASSWORD, MAX_COURSES));
		assertEquals("Invalid first name", e1.getMessage());
		//Checks for invalid Last Name
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, MAX_COURSES));
		assertEquals("Invalid last name", e2.getMessage());
		//Checks for invalid ID
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, MAX_COURSES));
		assertEquals("Invalid id", e3.getMessage());
		//Checks for invalid Email
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, "Icandoanything@here", PASSWORD, MAX_COURSES));
		assertEquals("Invalid email", e4.getMessage());
		//Checks for invalid Password
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, MAX_COURSES));
		assertEquals("Invalid password", e5.getMessage());
		//Checks for invalid Max Credits
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 500));
		assertEquals("Invalid max courses", e6.getMessage());
}
	/**
	 * Tests constructing a Faculty with credits
	 */
	@Test
	void testFacultyWithCredits() {
		//Tests a valid construction
				Faculty s = assertDoesNotThrow(
						() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1),
						"Should not throw exception");
				
				assertAll("Course", 
						() -> assertEquals(FIRST_NAME, s.getFirstName(), "incorrect name"), 
						() -> assertEquals(LAST_NAME, s.getLastName(), "incorrect title"),
						() -> assertEquals(ID, s.getId(), "incorrect section"), 
						() -> assertEquals(EMAIL, s.getEmail(), "incorrect credits"),
						() -> assertEquals(PASSWORD, s.getPassword(), "incorrect instructor id"),
						() -> assertEquals(1, s.getMaxCourses(), "incorrect meeting days"));	
	}

	/**
	 * Tests constructing an invalid Faculty with specific credit amount given
	 */
	@Test
	void testInvalidFacultyWithCredits() {
		//Checks for invalid First Name
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty("", LAST_NAME, ID, EMAIL, PASSWORD, 1));
		assertEquals("Invalid first name", e1.getMessage());
		//Checks for invalid Last Name
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, null, ID, EMAIL, PASSWORD, 1));
		assertEquals("Invalid last name", e2.getMessage());
		//Checks for invalid ID
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD, 1));
		assertEquals("Invalid id", e3.getMessage());
		//Checks for invalid ID (null)
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, 1));
		assertEquals("Invalid id", e4.getMessage());
		//Checks for invalid Email
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, "Icandoanything@here", PASSWORD, 1));
		assertEquals("Invalid email", e5.getMessage());
		//Checks for invalid Password
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, 1));
		assertEquals("Invalid password", e6.getMessage());
		
	}
	/**
	 * Checks to see if setFirstName accepts a valid name, and rejects invalid names
	 */
	@Test
	void testSetFirstName() {
		//Construct a valid Faculty
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setFirstName(null));
		assertEquals("Invalid first name", e1.getMessage()); //Check correct exception message
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setFirstName(""));
		assertEquals("Invalid first name", e2.getMessage()); //Check correct exception message
		assertEquals("first", s.getFirstName()); //Check that first name didn't change
	}
	/**
	 * Checks to see if setLastName accepts a valid name, and rejects invalid names
	 */
	@Test
	void testSetLastName() {
		//Construct a valid Faculty
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setLastName(null));
		assertEquals("Invalid last name", e1.getMessage()); //Check correct exception message
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setLastName(""));
		assertEquals("Invalid last name", e2.getMessage()); //Check correct exception message
		assertEquals("last", s.getLastName()); //Check that last name didn't change
	}

	/**
	 * Checks to see if a valid email is accepted and invalid emails aren't
	 * Tests:
	 * 	the parameter is null or an empty string
	 *	email doesn’t contain an ‘@’ character
	 *	email doesn’t contain a ‘.’ character
	 *	the index of the last ‘.’ character in the email string is earlier than the index of the first ‘@’ character (for example, first.last@address would be invalid)
	 */
	@Test
	void testSetEmail() {
		//Construct a valid Faculty
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		//Checks for catch null
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setEmail(null));
		assertEquals("Invalid email", e1.getMessage()); //Check correct exception message
		//Checks for catch empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail(""));
		assertEquals("Invalid email", e2.getMessage()); //Check correct exception message
		//Checks for catch no "@"
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("noatsign."));
		assertEquals("Invalid email", e3.getMessage()); //Check correct exception message
		//Checks for catch no "."
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("noperiod@here"));
		assertEquals("Invalid email", e4.getMessage()); //Check correct exception message
		//Check for no "." after the first "@"
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> s.setEmail("strange.order@here"));
		assertEquals("Invalid email", e5.getMessage()); //Check correct exception message
		assertEquals("email@ncsu.edu", s.getEmail()); //Check that email didn't change
	}

	/**
	 * Checks to see if valid passwords are accepted and invalid are rejected
	 */
	@Test
	void testSetPassword() {
		//Construct a valid Faculty
		User s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		//Checks for catch null
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setPassword(null));
		assertEquals("Invalid password", e1.getMessage()); //Check correct exception message
		//Checks for catch empty string
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.setPassword(""));
		assertEquals("Invalid password", e2.getMessage()); //Check correct exception message
		assertEquals("hashedpassword", s.getPassword()); //Check that the password didn't change
	}
	/**
	 * Checks to see if valid max credits are accepted and invalid are rejected
	 */
	@Test
	void testSetMaxCourses() {
		//Construct a valid Faculty
		Faculty s = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		//Checks for catch under minimum
		Exception e1 = assertThrows(IllegalArgumentException.class,
						() -> s.setMaxCourses(0));
		assertEquals("Invalid max courses", e1.getMessage()); //Check correct exception message
		//Checks for catch over maximum
		Exception e2 = assertThrows(IllegalArgumentException.class,
						() -> s.setMaxCourses(-1));
		assertEquals("Invalid max courses", e2.getMessage()); //Check correct exception message
	}

	/**
	 * Checks to see if Equals works as expected
	 */
	@Test
	void testEqualsObject() {
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		User s3 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
		User s4 = new Faculty("first?", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		User s5 = new Faculty("first?", "last", "id", "email@ncsu.edu", "differentPassword", 1);
		User s6 = new Faculty("first?", "last?", "id", "email@ncsu.edu", "differentPassword", 1);
		assertEquals(s1, s2);
		assertEquals(s1, s1);
		assertEquals(s2, s2);
		assertNotEquals(s1, null);
		assertNotEquals(s1, "What");
		assertNotEquals(s4, s5);
		assertNotEquals(s1, s3);
		assertNotEquals(s1, s4);
		assertNotEquals(s5, s6);
	}
	
	/**
	 * Checks to see if Hashcode works as expected
	 */
	@Test
	void testHashCode() {
		User s1 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		User s2 = new Faculty("first", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		User s3 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
		User s4 = new Faculty("first?", "last", "id", "email@ncsu.edu", "hashedpassword", 1);
		assertEquals(s1.hashCode(), s2.hashCode());
		assertNotEquals(s2.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
	}
	
	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User s1 = new Faculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 1);
		assertEquals("Bob,Builder,bbuilder,bbuilder@ncsu.edu,building,1", s1.toString());
		assertNotEquals("what,am,I,even,doing,here", s1.toString());
	}

	/**
	 * Test compareTo() method.
	 */
	@Test
	public void testCompareTo() {
		Faculty s1 = new Faculty("Anna", "Barbara", "ab1", EMAIL, PASSWORD, 1);
		Faculty s2 = new Faculty("Charles", "Anthony", "notid1", EMAIL, PASSWORD, 1);
		Faculty s3 = new Faculty("Banna", "Barbara", "notid2", EMAIL, PASSWORD, 1);
		Faculty s4 = new Faculty("Anna", "Barbara", "ab2", EMAIL, PASSWORD, 1);
		assertTrue(s1.compareTo(s2) > 0);
		assertTrue(s3.compareTo(s1) > 0);
		assertTrue(s4.compareTo(s1) > 0);
		assertEquals(0, s1.compareTo(s1));
	}

}
