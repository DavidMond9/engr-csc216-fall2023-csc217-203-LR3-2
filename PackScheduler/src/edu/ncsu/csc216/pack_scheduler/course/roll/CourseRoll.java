package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Represents the roll of students in a Course. Has a minimum and maximum Course size,
 *     a list of Student objects, and a capacity of students for this specific Course.
 *     Students can be enrolled or dropped, and the number of open seats can be accessed.
 * 
 * @author Joey Hughes, Sam McDonald, Winston Cheaz, Warren Long
 */
public class CourseRoll {

	/** The smallest class size for a Course */
	private static final int MIN_ENROLLMENT = 10;
	
	/** The largest class size for a Course */
	private static final int MAX_ENROLLMENT = 250;
	
	/** The Linked List of Students in the Course */
	private LinkedAbstractList<Student> roll;
	
	/** The enrollment capacity of the Course */
	private int enrollmentCap;
	
	/** The waitlist LinkedQueue of Students. If a student tries to enroll into a full roll, they are placed in the waitlist queue. */
	private LinkedQueue<Student> waitlist;
	
	/** The course that this is a roll for. */
	private Course course;
	
	/**
	 * Constructs a CourseRoll with the given enrollment capacity
	 * @param enrollmentCap The enrollment capacity to assign this roll.
	 * @param c the course to set
	 * @throws IllegalArgumentException if the enrollment capacity is invalid.
	 */
	public CourseRoll(int enrollmentCap, Course c) {
		if (c == null) {
			throw new IllegalArgumentException("Invalid course");
		}
		this.waitlist = new LinkedQueue<Student>(10);
		this.course = c;
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
	}
	/**
	 * gets the number of students on the waitlist
	 * @return the number of students on the waitlist
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	/**
	 * Returns the enrollment capacity of this CourseRoll.
	 * @return The enrollment capacity.
	 */
	public int getEnrollmentCap() {
		return this.enrollmentCap;
	}
	
	/**
	 * Sets the enrollment cap to the given number. If the number is above or below the
	 *     allowed bounds of a Course, an IllegalArgumentException is thrown.
	 * @param newEnrollmentCap the enrollment capacity to set.
	 */
	public void setEnrollmentCap(int newEnrollmentCap) {
		if(newEnrollmentCap < MIN_ENROLLMENT || newEnrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException("Invalid enrollment capacity.");
		}
		if(roll != null) {
			if(newEnrollmentCap < roll.size()) {
				throw new IllegalArgumentException("Enrollment capacity cannot be less than roll size.");
			}
			this.enrollmentCap = newEnrollmentCap;
			roll.setCapacity(enrollmentCap);
		} else {
			this.enrollmentCap = newEnrollmentCap;
		}
	}
	
	/**
	 * Enrolls the given Student into the CourseRoll and adds them to the list.
	 * 
	 * If the CourseRoll has reached capacity, the Student should be added to the
	 * waitlist. That is one reason why roll.add(s) (which is really a call to
	 * LinkedAbstractList.add()) might throw an IllegalArgumentException. If the
	 * size of the roll is the same as enrollmentCap attempt to add the student to
	 * waitlist.
	 * 
	 * If the waitlist is full, then the Student cannot enroll and an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param newStudent The Student to Enroll
	 * @throws IllegalArgumentException if the Student is invalid or cannot be
	 *                                  enrolled.
	 */
	public void enroll(Student newStudent) {
		if(newStudent == null || !canEnroll(newStudent)) {
			throw new IllegalArgumentException("Student cannot be enrolled.");
		}
		// this might not work
		try {
			roll.add(newStudent);
		} catch (Exception e) {
			waitlist.enqueue(newStudent);
		}
	}
	
	/**
	 * Removes the student from the roll.
	 * 
	 * If the Student is in the main roll, remove the Student and add the first
	 * eligible Student in the waitlist to the main roll.
	 * 
	 * If the Student is in the waitlist, remove the Student from the waitlist while
	 * maintaining the order of the waitlist and working with the specialized data
	 * structure methods.
	 * 
	 * @param newStudent The student to remove from the roll.
	 * @throws IllegalArgumentException if the student is null or cannot be removed.
	 */
	public void drop(Student newStudent) {
		if(newStudent == null) {
			throw new IllegalArgumentException("Student is null");
		}
		
		boolean inWaitlist = false;
		for (int i = 0; i < waitlist.size(); i++) {
			// need replace element that was removed
			Student replace = waitlist.dequeue();
			waitlist.enqueue(replace);
			if (newStudent.equals(replace)) {
				inWaitlist = true;
			}
		}
		// if not in waitlist or roll, it does not drop
		if (roll.indexOf(newStudent) == -1 && !inWaitlist) {
			return;
		}
		if (roll.indexOf(newStudent) != -1) {
			roll.remove(roll.indexOf(newStudent));
			// adds first student in waitlist 
			Student wait = null;
			if (waitlist.size() > 0) {
				wait = waitlist.dequeue();
				try {
					roll.add(wait);
					wait.getSchedule().addCourseToSchedule(course);
				} catch (Exception e) {
					// do nothing;
				}
			}
		}
		if (inWaitlist) {
			// remove from the waitlist 
			for (int i = 0; i < waitlist.size(); i++) {
				// need replace element that was removed
				// unless it is element we want removed
				Student replace = waitlist.dequeue();
				if (!newStudent.equals(replace)) {
					waitlist.enqueue(replace);
				}
			}
		}
	}
	
	/**
	 * Returns the number of open seats in the class.
	 * @return The number of open seats in the class.
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}
	
	/**
	 * Returns false if the student is already in the class or the class is full.
	 * @param newStudent The Student to check for enrollment with.
	 * @return False if the student cannot enroll, true if the student can.  
	 */
	public boolean canEnroll(Student newStudent) {
		if (newStudent == null) {
			return false;
		}
		for (int i = 0; i < waitlist.size(); i++) {
			// need replace element that was removed
			Student replace = waitlist.dequeue();
			waitlist.enqueue(replace);
			if (newStudent.equals(replace)) {
				return false;
			}
		}
		return !(roll.size() + waitlist.size() >= enrollmentCap + 10) && !roll.contains(newStudent);
	}
}

