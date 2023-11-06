package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Class for testing CourseRoll
 * 
 * @author - Sam McDonald, Joey Hughes
 */
public class CourseRollTest {
	
	/**
	 * Tests the CourseRoll constructor.
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll cr = new CourseRoll(250);
		assertEquals(cr.getOpenSeats(), 250);
		assertEquals(cr.getEnrollmentCap(), 250);
		
		CourseRoll cr2 = new CourseRoll(10);
		assertEquals(cr2.getOpenSeats(), 10);
		assertEquals(cr2.getEnrollmentCap(), 10);
		
		CourseRoll cr3 = new CourseRoll(140);
		assertEquals(cr3.getOpenSeats(), 140);
		assertEquals(cr3.getEnrollmentCap(), 140);
		
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(9); });
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(251); });
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(-10); });
		assertThrows(IllegalArgumentException.class, () -> {new CourseRoll(900); });
	}
	
	/**
	 * Tests the setEnrollmentCap method.
	 */
	@Test
	public void testSetEnrollmentCap() {
		CourseRoll cr = new CourseRoll(10);
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
		CourseRoll cr = new CourseRoll(10);
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
		assertFalse(cr.canEnroll(s11));
		assertThrows(IllegalArgumentException.class, () -> {cr.enroll(s11); });
		cr.drop(s2);
		assertEquals(cr.getOpenSeats(), 1);
		assertEquals(cr.getEnrollmentCap(), 10);
	}
}
