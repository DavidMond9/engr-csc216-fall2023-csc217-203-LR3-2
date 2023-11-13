package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A extension of User with a field for how many credit hours they can have and
 *     a Schedule of Courses. Can be constructed with a custom max credit hours (3-18)
 *     or constructed without credit hours, in which case it will be assigned the default
 *     of 18.
 *     
 * @author Joey Hughes, Winston Cheaz, Sam McDonald
 */
public class Student extends User implements Comparable<Student> {
	
	/** Default maxCredits value. */
	public static final int MAX_CREDITS = 18;
	
	/** Change the maxCredits default value to store into this state. */
	private int maxCredits;
	
	/** The Student's schedule of Courses */
	private Schedule schedule;
	
	
	/**
	 * Creates a student object. Uses encapsulation and setter methods to define all attributes.
	 * Can alter the maxCredits value.
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Student's id number.
	 * @param email Student's email address.
	 * @param password Student's password.
	 * @param maxCredits Student's max credits.
	 * @throws IllegalArgumentException if any parameters are invalid.
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
		schedule = new Schedule();
	}
	
	/**
	 * Creates a Student object. Same as previous Student constructor except this uses the
	 * default value for maxCredits of 18.
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Student's id number.
	 * @param email Student's email address.
	 * @param password Student's password.
	 * @throws IllegalArgumentException if any parameters are invalid.
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}
	
	
	/**
	 * Get's student's max credits.
	 * @return Student's max credits.
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	
	
	/**
	 * Set's the student's credits.
	 * @param credits Represents the student's credits.
	 * @throws IllegalArgumentException if the parameter is invalid (under 3 or more than 18).
	 */
	public void setMaxCredits(int credits) {
		if(credits < 3 || credits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		maxCredits = credits;
	}
	
	/**
	 * Returns the Student's Schedule.
	 * @return The schedule.
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Converts Student to a string, with variables in order of:
	 * firstName,lastName,id,email,hashedPassword,maxCredits
	 * @return String of all student variables.
	 */
	@Override
	public String toString() {
		return this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," 
				+ this.getEmail() + "," + this.getPassword() + "," + this.maxCredits;
	}
	
	/**
	 * Implements the compareTo method for Comparable for use with the sortedList
	 * @return integer, 1 if compared object is greater, 0 if equal, and -1 if lesser.
	 */
	@Override
	public int compareTo(Student o) {
		if (this.getLastName().compareTo(o.getLastName()) != 0) {
			return this.getLastName().compareTo(o.getLastName());
		}
		if (this.getFirstName().compareTo(o.getFirstName()) != 0) {
			return this.getFirstName().compareTo(o.getFirstName());
		}
		if (this.getId().compareTo(o.getId()) != 0) {
			return this.getId().compareTo(o.getId());
		}
		return 0;
	}
	
	/**
	 * Generates hash code for the object.
	 * @return Integer, hash code for object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}
	
	/**
	 * Determines whether objects are equal
	 * @param obj object to check if they are equal.
	 * @return boolean, true if objects are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return !(maxCredits != other.maxCredits);
	}
	
	/**
	 * Returns true of false depending on whether the course can be added to the student's schedule.
	 * @param c course to check
	 * @return boolean, true if course can be added and false if not
	 */
	public boolean canAdd(Course c) {
		return !(c.getCredits() + schedule.getScheduleCredits() > this.maxCredits) && schedule.canAdd(c);
	}
}
