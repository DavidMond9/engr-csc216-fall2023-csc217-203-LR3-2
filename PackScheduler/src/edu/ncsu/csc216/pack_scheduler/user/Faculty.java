package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The Faculty class represents an individual faculty record. The Faculty class
 * is a “plain old java object” (POJO) consisting mostly of getters and setters.
 * Faculty extends User and its implementation is similar to the implementation
 * of Student. The main difference is that Faculty objects have a number of
 * courses they can teach in a given semester (between 1 and 3 inclusive) rather
 * keeping track of credits as Students do.
 * 
 * @author Warren Long
 */

public class Faculty extends User implements Comparable<Faculty> {
	
	/** max maxCourses value. */
	public static final int MAX_COURSES = 3;
	
	/** the maximum number of courses*/
	private int maxCourses;
	
	/** The minimum number of courses*/
	public static final int MIN_COURSES = 1;
	
	
	/**
	 * Creates a faculty object. Uses encapsulation and setter methods to define all attributes.
	 * Can alter the maxCredits value.
	 * @param firstName faculty's first name.
	 * @param lastName faculty's last name.
	 * @param id faculty's id number.
	 * @param email faculty's email address.
	 * @param password faculty's password.
	 * @param maxCourses faculty's max courses.
	 * @throws IllegalArgumentException if any parameters are invalid.
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
	}
	
	/**
	 * gets's the faculty's courses.
	 * @return maxCourses the max courses of the faculty
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	
	
	/**
	 * generates the hash code for the faculty.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}
	/**
	 * checks fields to make sure that they are the same
	 * to determine if a faculty is equal to another
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return !(maxCourses != other.maxCourses);
	}

	/**
	 * Set's the faculty's courses.
	 * @param maxCourses Represents the faculty's courses.
	 * @throws IllegalArgumentException if the parameter is invalid (under 1 or more than 3).
	 */
	public void setMaxCourses(int maxCourses) {
		if(maxCourses < MIN_COURSES || maxCourses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		this.maxCourses = maxCourses;
	}
	
	
	/**
	 * Converts Student to a string, with variables in order of:
	 * firstName,lastName,id,email,hashedPassword,maxCredits
	 * @return String of all student variables.
	 */
	@Override
	public String toString() {
		return this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," 
				+ this.getEmail() + "," + this.getPassword() + "," + this.maxCourses;
	}
	
	/**
	 * Implements the compareTo method for Comparable for use with the sortedList
	 * @return integer, 1 if compared object is greater, 0 if equal, and -1 if lesser.
	 */
	@Override
	public int compareTo(Faculty o) {
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
	
	
}
