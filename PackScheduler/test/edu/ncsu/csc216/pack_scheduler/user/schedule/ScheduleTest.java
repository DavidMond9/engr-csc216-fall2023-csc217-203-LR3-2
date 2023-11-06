/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test class for Schedule
 */
class ScheduleTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#Schedule()}.
	 */
	@Test
	void testSchedule() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
		assertEquals(0, s.getScheduledCourses().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#addCourseToSchedule(edu.ncsu.csc216.pack_scheduler.course.Course)}.
	 */
	@Test
	void testAddCourseToSchedule() {
		Schedule s = new Schedule();
		assertTrue(s.addCourseToSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertEquals(1, s.getScheduledCourses().length);
		assertEquals("AAA111", s.getScheduledCourses()[0][0]);
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "A")));
		assertEquals("You are already enrolled in " + "AAA111", e.getMessage(), "Incorrect message for duplicate course");

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertEquals("The course cannot be added due to a conflict.", e1.getMessage(), "Incorrect message for course conflict");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#removeCourseFromSchedule(edu.ncsu.csc216.pack_scheduler.course.Course)}.
	 */
	@Test
	void testRemoveCourseFromSchedule() {
		Schedule s = new Schedule();
		assertTrue(s.addCourseToSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertTrue(s.addCourseToSchedule(new Course("AAA112", "title", "001", 4, "instructorId", 10, "TH")));
		assertTrue(s.removeCourseFromSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertFalse(s.removeCourseFromSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertEquals("My Schedule", s.getTitle());
		assertEquals(1, s.getScheduledCourses().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#resetSchedule()}.
	 */
	@Test
	void testResetSchedule() {
		Schedule s = new Schedule();
		assertTrue(s.addCourseToSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertTrue(s.addCourseToSchedule(new Course("AAA112", "title", "001", 4, "instructorId", 10, "TH")));
		s.resetSchedule();
		assertEquals(0, s.getScheduledCourses().length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule#setTitle(java.lang.String)}.
	 */
	@Test
	void testSetTitle() {
		Schedule s = new Schedule();
		
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> s.setTitle(null));
		assertEquals("Title cannot be null.", e.getMessage(), "Incorrect message for setting title to null");
		
		s.setTitle("title");
		assertEquals("title", s.getTitle());

	}
	
	/**
	 * Test method for getScheduleCredits()
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule s = new Schedule();
		assertTrue(s.addCourseToSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertTrue(s.addCourseToSchedule(new Course("AAA112", "title", "001", 4, "instructorId", 10, "TH")));
		assertEquals(8, s.getScheduleCredits());
	}
	
	/**
	 * Test method for canAdd()
	 */
	@Test
	public void testCanAdd() {
		Schedule s = new Schedule();
		assertTrue(s.addCourseToSchedule(new Course("AAA111", "title", "001", 4, "instructorId", 10, "MWF")));
		assertTrue(s.addCourseToSchedule(new Course("AAA112", "title", "001", 4, "instructorId", 10, "T")));
		assertFalse(s.canAdd(new Course("AAA112", "title", "001", 4, "instructorId", 10, "TH")));
		assertTrue(s.canAdd(new Course("AAA113", "title1", "005", 4, "instructorId", 10, "H")));
	}
}
