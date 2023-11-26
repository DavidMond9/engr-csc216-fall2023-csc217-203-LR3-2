package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Objected oriented implementation of CourseNameValidatorFSM
 * Provides and implements four different states
 * @author Winston Cheaz, Joey Hughes, Sam McDonald
 */
public class CourseNameValidator {
	
	/** Whether or not the current state of the instance is valid*/ 
	private boolean validEndState;
	
	/** The amount of letters already read */
	private int letterCount;
	
	/** The amount of digits already read */
	private int digitCount;
	
	/** Holds InitialState for state transitions */
	private final State stateInitial = new InitialState();
	/** Holds SuffixState for state transitions */
	private final State stateSuffix = new SuffixState();
	/** Holds LetterState for state transitions */
	private final State stateLetter = new LetterState();
	/** Holds NumberState for state transitions */
	private final State stateNumber = new NumberState();
	
	/** The current state of the program */
	private State currentState;
	
	/**
	 * Creates a CourseNameValidator
	 * Sets validEndState to false, as it's only true when reaching the end of the string anyways
	 */
	public CourseNameValidator() {
		validEndState = false;
	}
	
	/**
	 * Determines if a inputed string is a valid course name.
	 * @param courseName Course name to be validated
	 * @return true if course name is valid, false otherwise
	 * @throws InvalidTransitionException When the string is invalid
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		currentState = stateInitial;
		digitCount = 0;
		validEndState = false;
		letterCount = 0;
		for (int x = 0; x < courseName.length(); x++) {
			if (Character.isLetter(courseName.charAt(x))) {
				currentState.onLetter();
			}
			else if (Character.isDigit(courseName.charAt(x))) {
				currentState.onDigit();
			}
			else {
				currentState.onOther();
			}
		}
		return validEndState;
	}
	
	/**
	 * Abstraction for states in the Validator FSM.
	 */
	private abstract class State {
		
		/** Empty constructor */
		public State() {
			//Intentionally left empty
		}
		
		/** Method for what to do on a letter transition. 
		 * 
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public abstract void onLetter() throws InvalidTransitionException;
		
		/** Method for what to do on a digit transition. 
		 * @throws InvalidTransitionException if the transition is invalid
		 */
		public abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * What happens when a non-digit or letter character is in the string
		 * @throws InvalidTransitionException When a non-digit or number character is in the strings
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}
	
	/**
	 * The starting state for the Course Name FSM. Moves to letter state on a letter, throws on a digit.
	 * 
	 * @author Sam McDonald, Joey Hughes, Winston Cheaz
	 */
	private class InitialState extends State {
		
		/** Empty private constructor, there is no special function to construct this, and the constructor must be private per the design. */
		private InitialState() {
			//Intentionally left empty
		}
		
		/**
		 * What happens when a letter is detected
		 */
		public void onLetter() {
			currentState = stateLetter;
			letterCount++;
		}
		
		/**
		 * What happens when a digit is detected
		 * @throws InvalidTransitionException if it is on a digit
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 * The letter state for the Course Name FSM. Counts letters until the max, any more and it throws. On a digit, moves to number state.
	 * 
	 * @author Sam McDonald, Joey Hughes, Winston Cheaz
	 */
	private class LetterState extends State {
		
		/** The maximum amount of letters in the prefix */
		private static final int MAX_PREFIX_LETTERS = 4;
		
		/** Empty private constructor, there is no special function to construct this, and the constructor must be private per the design. */
		private LetterState() {
			//Intentionally left empty
		}
		
		/**
		 * What happens when a letter is detected
		 * @throws InvalidTransitionException if the letter count is greater than the max number of letters
		 */
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				letterCount++;
			}
			else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		
		/**
		 * What happens when a digit is detected
		 */
		public void onDigit() {
			digitCount++;
			currentState = stateNumber;
		}
	}

	/**
	 * The number state for the Course Name FSM. On a letter, if the digit count is right, moves to suffix state, otherwise throws. 
	 *     On digits, counts the digit count until the max, after which it throws.
	 * 
	 * @author Sam McDonald, Joey Hughes, Winston Cheaz
	 */
	private class NumberState extends State {
		
		/** The exact amount of numbers required */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		/** Empty private constructor, there is no special function to construct this, and the constructor must be private per the design. */
		private NumberState() {
			//Intentionally left empty
		}
		
		/**
		 * What happens when a letter is detected
		 * @throws InvalidTransitionException if the digit count is not equal to 3
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCount != COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
			else {
				currentState = stateSuffix;
			}
		}
		
		/**
		 * What happens when a digit is detected
		 * @throws InvalidTransitionException if the course name does not have 3 digi9ts
		 */
		public void onDigit() throws InvalidTransitionException {
			if (digitCount < COURSE_NUMBER_LENGTH) {
				digitCount++;
				if (digitCount == COURSE_NUMBER_LENGTH) {
					validEndState = true;
				}
			}
			else {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			}
		}
	}
	
	/**
	 * The suffix state for the Course Name FSM. Throws on any attempted transitions.
	 * 
	 * @author Sam McDonald, Joey Hughes, Winston Cheaz
	 */
	private class SuffixState extends State {
		
		/** Empty private constructor, there is no special function to construct this, and the constructor must be private per the design. */
		private SuffixState() {
			//Intentionally left empty
		}
		
		/**
		 * What happens when a letter is detected
		 * @throws InvalidTransitionException if it is on a letter
		 */
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		
		/**
		 * What happens when a digit is detected
		 * @throws InvalidTransitionException if it is on a digit
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
}
