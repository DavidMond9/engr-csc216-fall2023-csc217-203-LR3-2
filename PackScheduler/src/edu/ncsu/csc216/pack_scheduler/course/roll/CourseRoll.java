package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * Represents the roll of students in a Course. Has a minimum and maximum Course size,
 *     a list of Student objects, and a capacity of students for this specific Course.
 *     Students can be enrolled or dropped, and the number of open seats can be accessed.
 * 
 * @author Joey Hughes, Sam McDonald, Winston Cheaz
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
	
	/**
	 * Constructs a CourseRoll with the given enrollment capacity
	 * @param enrollmentCap The enrollment capacity to assign this roll.
	 * @throws IllegalArgumentException if the enrollment capacity is invalid.
	 */
	public CourseRoll(int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
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
	 * @param newStudent The Student to Enroll
	 * @throws IllegalArgumentException if the Student is invalid or cannot be enrolled.
	 */
	public void enroll(Student newStudent) {
		if(newStudent == null || !canEnroll(newStudent)) {
			throw new IllegalArgumentException("Student cannot be enrolled.");
		}
		roll.add(newStudent);
	}
	
	/**
	 * Removes the student from the roll.
	 * @param newStudent The student to remove from the roll.
	 * @throws IllegalArgumentException if the student is null or cannot be removed.
	 */
	public void drop(Student newStudent) {
		if(newStudent == null) {
			throw new IllegalArgumentException("Student is null");
		}
		if (roll.indexOf(newStudent) != -1) {
			roll.remove(roll.indexOf(newStudent));
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
		return !(roll.size() == enrollmentCap || roll.contains(newStudent));
	}
}

