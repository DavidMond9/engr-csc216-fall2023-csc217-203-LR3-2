package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Declares the InvalidTransitionException that extends Exception.  This exception is thrown when an invalid transition is done.
 * A message can be added to it, but the default message is "Invalid FSM Transition.".
 * @author - Sam McDonald
 */
public class InvalidTransitionException extends Exception {
	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for exception with default message "Invalid FSM Transition."
	 */
	public InvalidTransitionException() { 
		super("Invalid FSM Transition.");
	}
	/**
	 * Constructor for exception with custom message
	 * @param e custom message to use with exception
	 */
	public InvalidTransitionException(String e) { 
		super(e);
	}
}
