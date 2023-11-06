package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Describes a course that can be taken at NC State. Extends Activity behavior and state as well as a name, section, instructor ID,
 *     and a number of Credit Hours. Name cannot be set except in the Constructor.
 *     Can be constructed without start or end times if the course is arranged. This is comparable and can be sorted.
 * @author Joey Hughes, Sam McDonald
 */
public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**Minimum length of a course's name (EX: CSC 216 is 7 characters)*/
	final static int MIN_NAME_LENGTH = 5;
	/**Maximum length of a course's name*/
	final static int MAX_NAME_LENGTH = 8;
	/**Minimum length of a course's prefix (EX: CSC is 3 characters)*/
	final static int MIN_LETTER_COUNT = 1;
	/**Maximum length of a course's prefix*/
	final static int MAX_LETTER_COUNT = 4;
	/**Exact amount of digits each course must have (EX: CSC 216 has 3 digits)*/
	final static int DIGIT_COUNT = 3;
	/**Exact amount of digits the section string can be*/
	final static int SECTION_LENGTH = 3;
	/**Minimum amount of credits a course can be*/
	final static int MIN_CREDITS = 1;
	/** Maximum amount of credits a course can be*/
	final static int MAX_CREDITS = 5;
	/** Object to validate course names */
	private CourseNameValidator validator;
	
	/** Object that stores the course roll **/
	private CourseRoll roll;
	
	/**
	 * Constructs a Course object with all fields
	 * @param name name of a course
	 * @param title title of a course
	 * @param section section of a course
	 * @param credits credit hrs of a course
	 * @param instructorId instructor's Unity ID
	 * @param meetingDays days course meets
	 * @param startTime When the course starts
	 * @param endTime When the course ends
	 * @param enrollmentCap the max number of students that can enroll
	 * @throws IllegalArgumentException if any of the parameters are invalid.
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
	    super(title, meetingDays, startTime, endTime);
	    roll = new CourseRoll(enrollmentCap);
	    setName(name);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
	}

	
	/**
	 * Constructs a Course object with unknown startTime and unknown endTime
	 * @param name name of a course
	 * @param title title of a course
	 * @param section the section of a course
	 * @param credits the credit hours of a course
	 * @param instructorId instructor's Unity ID
	 * @param meetingDays days course meets
	 * @param enrollmentCap the max number of students that can enroll
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}


	/**
	 * Returns the Course's name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) throws IllegalArgumentException{
		
		validator = new CourseNameValidator();
		
		if(name == null || "".equals(name)) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		try {
			if(validator.isValid(name)) {
				this.name = name;
			}
			else {
				throw new IllegalArgumentException("Invalid course name.");
			}
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
	}
	/**
	 * Returns the Course's section.
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section.
	 * Fails if:
	 * 	> the section number is NOT exactly three digits
	 * @param section the section to set
	 * @throws IllegalArgumentException if the parameter is invalid.
	 */
	
	public void setSection(String section) {
		//Check if section is null
		if (section == null) {
			throw new IllegalArgumentException("Invalid section.");
		}
		//If the section String doesn't have SECTION_LENGTH characters
		if (section.length() != SECTION_LENGTH) {
			//The section is invalid
			throw new IllegalArgumentException("Invalid section.");
		}
		//Check if the section String is only digits
		for (int sectionIterator = 0; sectionIterator < SECTION_LENGTH; sectionIterator++) {
			//Check if current position in section isn't a digit
			if (!Character.isDigit(section.charAt(sectionIterator))){
				//If it isn't a digit, then the section is invalid
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		// Assume that section is valid, accept the section 
		this.section = section;
	}

	/**
	 * Returns the Course's credits.
	 * @return the credits
	 */
	
	public int getCredits() {
		return credits;
	}
	
	/**
	 * Sets the Course's credits.
	 * @param credits The credits to set.
	 * @throws IllegalArgumentException if the parameter is invalid.
	 */
	public void setCredits(int credits) {
		//Check if credits is in between MIN and MAX credits allowed
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		//Assume credits is valid, accept it
		this.credits = credits;
	}

	/**
	 * Returns the Course's instructorID.
	 * @return the instructorID
	 */
	
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course's instructor ID.
	 * @param instructorId The instructorId to set.
	 * @throws IllegalArgumentException if the parameter is invalid.
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Sets the meetingDays, the startTime, and the endTime of the Course all at the same time.
	 * @param meetingDays The meetingDays to set.
	 * @param startTime The startTime to set.
	 * @param endTime The endTime to set.
	 * @throws IllegalArgumentException If a parameter is invalid (days are not either a combo of MTWHF or just A, military times are invalid).
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || "".equals(meetingDays)) {
			//If meetingDays doesn't have days, it's invalid
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		//Check if A is the only character
		if ("A".equals(meetingDays)) {
			//Check if there's either a start or end time given 
			if (startTime != 0 || endTime != 0) {
				//Fails, as Arranged classes don't have a start or end time
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			// meetingDays is correct, and startTime and endTime should be set as 0
			// The method should end after this
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
		else {
			//weekCounter counts the number times each day is referenced. 
			//0 is Monday, 1, is Tuesday... 4 is Friday
			int[] weekCounter = new int[5];
			//Iterate through meetingDays, and add to weekCounter depending on the day. 
			for(int meetingDaysIterator = 0; meetingDaysIterator < meetingDays.length(); meetingDaysIterator++) {
				//Switch case here
				switch(meetingDays.charAt(meetingDaysIterator)) {
					//M for Monday
					case 'M':
						weekCounter[0]++;
						break;
					//T for Tuesday
					case 'T':
						weekCounter[1]++;
						break;
					//W for Wednesday
					case 'W':
						weekCounter[2]++;
						break;
					//H for tHursday
					case 'H':
						weekCounter[3]++;
						break;
					//F for Friday
					case 'F':
						weekCounter[4]++;
						break;
					//The default case is for when something is wrong
					//If the char at the current position isn't one of the allowed ones (Ex: 'A' or 'Z')
					//Then something is wrong, and the argument is thrown.
					default:
						throw new IllegalArgumentException("Invalid meeting days and times.");
					
				}
			}
			//Iterates through weekCounter, dayCount stores the number of times a single day was submitted
			for(int dayCount : weekCounter) {
				//If the dayCount > 1, then the argument is thrown.
				if (dayCount > 1) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			//Everything should be valid, accept the inputs
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Returns true if the given Activity is a Course with the same name
	 * Returns false if not a Course, or if it is a Course, the name isn't the same
	 * @param activity the Activity to compare to current Course
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course confirmedCourse = (Course) activity;
			return this.getName().equals(confirmedCourse.getName()) && this.getSection().equals(confirmedCourse.getSection());
		}
		return false;
	}


	/**
	 * Generates a hashCode for Course using all fields.
	 * @return The hashCode for Course.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(credits, instructorId, name, section);
		return result;
	}


	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj The Object to compare.
	 * @return True if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return credits == other.credits && Objects.equals(instructorId, other.instructorId)
				&& Objects.equals(name, other.name) && Objects.equals(section, other.section);
	}



	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of the Course.
	 */
	@Override
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," 
	        			+ roll.getEnrollmentCap() + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," 
	    		+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}

	/**
	 * Returns this Course's name, section, title, and meeting times in a String array to be used in the UI.
	 * @return An array of this Course's name, section, title, and meeting string.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = new String[5];
		shortArray[0] = name;
		shortArray[1] = section;
		shortArray[2] = getTitle();
		shortArray[3] = getMeetingString();
		shortArray[4] = String.valueOf(roll.getOpenSeats());
		return shortArray;
	}

	/**
	 * Returns this Course's name, section, title, credits, instructor, and meeting times in a String array to be used in the UI.
	 * @return This Course's name, section, title, credits, instructor, and meeting string.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = {getName(), getSection(), getTitle(), String.valueOf(getCredits()), getInstructorId(), getMeetingString(), ""};
		return longArray;
	}
	
	/**
	 * Returns the course roll.
	 * @return The course roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}
	
	/**
	 * Compares two Courses together 
	 * @return 0 if names are equal, 1 if this.name is greater, -1 if less
	 */
	@Override
	public int compareTo(Course o) {
		if (this.getName().compareTo(o.getName()) != 0) {
			return this.getName().compareTo(o.getName());
		}
		else if (this.getSection().compareTo(o.getSection()) != 0) {
			return this.getSection().compareTo(o.getSection());
		}
		return 0;
	}

}

