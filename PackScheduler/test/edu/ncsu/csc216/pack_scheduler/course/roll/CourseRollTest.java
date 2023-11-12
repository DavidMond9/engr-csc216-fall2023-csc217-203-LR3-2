package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Class for testing CourseRoll
 * 
 * @author - Sam McDonald, Joey Hughes
 */
public class CourseRollTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course enrollment cap */
	private static final int ENR_CAP = 100;
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Tests the CourseRoll constructor.
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENR_CAP, MEETING_DAYS, START_TIME, END_TIME);

		CourseRoll cr = new CourseRoll(250, c);
		assertEquals(cr.getOpenSeats(), 250);
		assertEquals(cr.getEnrollmentCap(), 250);
		
		CourseRoll cr2 = new CourseRoll(10, c);
		assertEquals(cr2.getOpenSeats(), 10);
		assertEquals(cr2.getEnrollmentCap(), 10);
		
		CourseRoll cr3 = new CourseRoll(140, c);
		assertEquals(cr3.getOpenSeats(), 140);
		assertEquals(cr3.getEnrollmentCap(), 140);
		
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(9, c); });
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(251, c); });
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(-10, c); });
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(900, c); });
	}
	
	/**
	 * Tests the setEnrollmentCap method.
	 */
	@Test
	public void testSetEnrollmentCap() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENR_CAP, MEETING_DAYS, START_TIME, END_TIME);

		CourseRoll cr = new CourseRoll(10, c);
		assertEquals(cr.getOpenSeats(), 10);
		assertEquals(cr.getEnrollmentCap(), 10);
		cr.setEnrollmentCap(15);
		assertEquals(cr.getOpenSeats(), 15);
		assertEquals(cr.getEnrollmentCap(), 15);
		cr.enroll(new Student("a", "Hughes", "aHughes", "email@website.com", "pw"));
		assertEquals(cr.getOpenSeats(), 14);
		assertEquals(cr.getEnrollmentCap(), 15);
		cr.enroll(new Student("b", "Hughes", "bHughes", "email@website.com", "pw"));
		cr.enroll(new Student("c", "Hughes", "cHughes", "email@website.com", "pw"));
		cr.enroll(new Student("d", "Hughes", "dHughes", "email@website.com", "pw"));
		cr.enroll(new Student("e", "Hughes", "eHughes", "email@website.com", "pw"));
		cr.enroll(new Student("f", "Hughes", "fHughes", "email@website.com", "pw"));
		cr.enroll(new Student("g", "Hughes", "gHughes", "email@website.com", "pw"));
		cr.enroll(new Student("h", "Hughes", "hHughes", "email@website.com", "pw"));
		cr.enroll(new Student("i", "Hughes", "iHughes", "email@website.com", "pw"));
		cr.enroll(new Student("j", "Hughes", "jHughes", "email@website.com", "pw"));
		cr.enroll(new Student("k", "Hughes", "kHughes", "email@website.com", "pw")); // 11 Students
		assertEquals(cr.getOpenSeats(), 4);
		assertEquals(cr.getEnrollmentCap(), 15);
		assertThrows(IllegalArgumentException.class, () -> {cr.setEnrollmentCap(10); });
	}
	
	/**
	 * Tests the CourseRoll constructor.
	 */
	@Test
	public void testEnrollAndDrop() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENR_CAP, MEETING_DAYS, START_TIME, END_TIME);

		CourseRoll cr = new CourseRoll(10, c);
		assertEquals(cr.getOpenSeats(), 10);
		assertEquals(cr.getEnrollmentCap(), 10);
		
		assertThrows(IllegalArgumentException.class, () -> {cr.enroll(null); });
		assertThrows(IllegalArgumentException.class, () -> {cr.drop(null); });
		
		cr.enroll(new Student("a", "Hughes", "aHughes", "email@website.com", "pw"));
		assertEquals(cr.getOpenSeats(), 9);
		assertEquals(cr.getEnrollmentCap(), 10);
		Student s2 = new Student("b", "Hughes", "bHughes", "email@website.com", "pw");
		assertTrue(cr.canEnroll(s2));
		cr.enroll(s2);
		assertEquals(cr.getOpenSeats(), 8);
		assertEquals(cr.getEnrollmentCap(), 10);
		assertFalse(cr.canEnroll(s2));
		
		cr.enroll(new Student("c", "Hughes", "cHughes", "email@website.com", "pw"));
		cr.enroll(new Student("d", "Hughes", "dHughes", "email@website.com", "pw"));
		cr.enroll(new Student("e", "Hughes", "eHughes", "email@website.com", "pw"));
		cr.enroll(new Student("f", "Hughes", "fHughes", "email@website.com", "pw"));
		cr.enroll(new Student("g", "Hughes", "gHughes", "email@website.com", "pw"));
		cr.enroll(new Student("h", "Hughes", "hHughes", "email@website.com", "pw"));
		cr.enroll(new Student("i", "Hughes", "iHughes", "email@website.com", "pw"));
		cr.enroll(new Student("j", "Hughes", "jHughes", "email@website.com", "pw"));
		assertEquals(cr.getOpenSeats(), 0);
		assertEquals(cr.getEnrollmentCap(), 10);
		Student s11 = new Student("k", "Hughes", "kHughes", "email@website.com", "pw");
		assertTrue(cr.canEnroll(s11));
		cr.drop(s2);
		assertEquals(cr.getOpenSeats(), 1);
		assertEquals(cr.getEnrollmentCap(), 10);
	}
	/**
	 * Tests the new functionality 
	 */
	@Test
	public void testNewFunctionality() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENR_CAP, MEETING_DAYS, START_TIME, END_TIME);

		CourseRoll cr = new CourseRoll(10, c);
		assertEquals(cr.getOpenSeats(), 10);
		assertEquals(cr.getEnrollmentCap(), 10);
		
		
		cr.enroll(new Student("a", "Hughes", "aHughes", "email@website.com", "pw"));
		
		Student s2 = new Student("b", "Hughes", "bHughes", "email@website.com", "pw");
		cr.enroll(s2);
		cr.enroll(new Student("c", "Hughes", "cHughes", "email@website.com", "pw"));
		cr.enroll(new Student("d", "Hughes", "dHughes", "email@website.com", "pw"));
		cr.enroll(new Student("e", "Hughes", "eHughes", "email@website.com", "pw"));
		cr.enroll(new Student("f", "Hughes", "fHughes", "email@website.com", "pw"));
		cr.enroll(new Student("g", "Hughes", "gHughes", "email@website.com", "pw"));
		cr.enroll(new Student("h", "Hughes", "hHughes", "email@website.com", "pw"));
		cr.enroll(new Student("i", "Hughes", "iHughes", "email@website.com", "pw"));
		cr.enroll(new Student("j", "Hughes", "jHughes", "email@website.com", "pw"));
		assertEquals(cr.getNumberOnWaitlist(), 0);

		cr.enroll(new Student("k", "Hughes", "jHughes", "email@website.com", "pw"));

		assertEquals(cr.getOpenSeats(), 0);
		assertEquals(cr.getEnrollmentCap(), 10);
		assertEquals(cr.getNumberOnWaitlist(), 1);
		for (int i = 0; i < 9; i++) {
			cr.enroll(new Student(i + "", "Hughes", "jHughes", "email@website.com", "pw"));
		}
		assertEquals(cr.getNumberOnWaitlist(), 10);
		assertThrows(IllegalArgumentException.class, () -> {cr.enroll(new Student("l", "Hughes", "jHughes", "email@website.com", "pw")); });
		cr.drop(s2);
		assertEquals(cr.getNumberOnWaitlist(), 9);
		cr.drop(new Student("c", "Hughes", "cHughes", "email@website.com", "pw"));
		assertEquals(cr.getNumberOnWaitlist(), 8);
		cr.drop(new Student("a", "Hughes", "aHughes", "email@website.com", "pw"));
		assertEquals(cr.getNumberOnWaitlist(), 7);
		cr.enroll(new Student("a", "Hughes", "aHughes", "email@website.com", "pw"));
		assertEquals(cr.getNumberOnWaitlist(), 8);
		assertTrue(cr.canEnroll(new Student("m", "Hughes", "aHughes", "email@website.com", "pw")));
		assertEquals(cr.getNumberOnWaitlist(), 8);

	}
	/**
	 * Tests specific failure with system test
	 */
	@Test
	public void testSpecificFailureWithWaitlist() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENR_CAP, MEETING_DAYS, START_TIME, END_TIME);

		CourseRoll cr = new CourseRoll(10, c);
		
		for (int i = 0; i < 12; i++) {
			cr.enroll(new Student(i + "", "Hughes", "jHughes", "email@website.com", "pw"));
		}
		assertEquals(cr.getOpenSeats(), 0);
		assertEquals(cr.getEnrollmentCap(), 10);
		assertEquals(cr.getNumberOnWaitlist(), 2);
		
		cr.drop(new Student("10", "Hughes", "jHughes", "email@website.com", "pw"));
		cr.drop(new Student("-1", "Hughes", "jHughes", "email@website.com", "pw"));

		assertEquals(cr.getNumberOnWaitlist(), 1);

		cr.drop(new Student("11", "Hughes", "jHughes", "email@website.com", "pw"));
		assertEquals(cr.getNumberOnWaitlist(), 0);
		cr.drop(new Student("-1", "Hughes", "jHughes", "email@website.com", "pw"));

		assertEquals(cr.getNumberOnWaitlist(), 0);
	}
}
