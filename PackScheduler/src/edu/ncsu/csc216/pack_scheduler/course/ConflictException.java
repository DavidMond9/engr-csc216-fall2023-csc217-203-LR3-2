/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An exception made for Conflict
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/** Parameterized constructor to give message when making new 
	 * @param message The message given when an exception is thrown
	*/
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * The default constructor used when a conflict exception is made
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
	
}


