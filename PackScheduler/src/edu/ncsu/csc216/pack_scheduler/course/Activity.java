package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Objects;

/**
 * An activity that can be added to a schedule with a title, meeting days, a start time and end time.
 *     The start and end times must be in military time of the form [HH][MM]. The meeting days is a string of the days the
 *     activity meets using letters to represent days of the week. "A" indicates this activity is Arranged and has no determined
 *     meeting days or times yet.
 *     Can return a short or long String array with information about the array, and can be checked as a duplicate Activity.
 *     
 * @author Joey Hughes
 */
public abstract class Activity implements Conflict {

	/** The maximum hour */
	private static final int UPPER_HOUR = 24;
	/** The maximum minute */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Constructs an Activity with the given parameters.
	 * @param title The title of the Activity.
	 * @param meetingDays The meeting days of the Activity.
	 * @param startTime The start time of the Activity.
	 * @param endTime The end time of the Activity.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Should return an array of some basic information about the Activity. Used in the UI to fill a table.
	 * @return An array of basic information about the Activity.
	 */
	public abstract String[] getShortDisplayArray();
	
	
	/**
	 * Should return an array with in-depth information about the Activity. Used in the UI to fill a table.
	 * @return An array containing lots of information about the Activity.
	 */
	public abstract String[] getLongDisplayArray();
	
	/**
	 * Should check if this Activity is equal the given Activity in terms of being able to be added to a schedule without issues.
	 * @param activity The Activity to compare to.
	 * @return Whether the given Activity is a duplicate or not.
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Returns the Activity's title.
	 * @return The title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title.
	 * @param title The title to set.
	 * @throws IllegalArgumentException if the parameter is invalid.
	 */
	public void setTitle(String title) {
		if(title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Activity's meeting days.
	 * @return The meetingDays.
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Activity's start time.
	 * @return The startTime.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's end time.
	 * @return The endTime.
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the meetingDays, the startTime, and the endTime all at the same time.
	 * @param meetingDays The meetingDays to set.
	 * @param startTime The startTime to set.
	 * @param endTime The endTime to set.
	 * @throws IllegalArgumentException If a parameter is invalid (not valid military times, null or empty meeting days).
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		if(endTime < startTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		int startHour = startTime / 100;
		if(startHour < 0 || startHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		int startMin = startTime % 100;
		if(startMin < 0 || startMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		int endHour = endTime / 100;
		if(endHour < 0 || endHour >= UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		int endMin = endTime % 100;
		if(endMin < 0 || endMin >= UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns a String describing the days and times this Activity meets.
	 * @return A String of the form 
	 * "[meetingDays] [startTime in standard]-[endTime in standard]", such as "MW 11:30AM-12:15PM".
	 */
	public String getMeetingString() {
		if("A".equals(meetingDays)) {
			return "Arranged";
		}
		return meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
	}

	/**
	 * Takes an int of military time and returns a String of the standard time it corresponds to
	 * @param militaryTime An int of military time.
	 * @return A string of standard time of the form "11:32AM".
	 */
	private String getTimeString(int militaryTime) {
		int hour = militaryTime / 100;
		int minute = militaryTime % 100;
		boolean pm = false;
		String hourString = "";
		String minuteString = "";
		if(hour == 0) {
			hourString = "12";
		} else if(hour == 12) {
			hourString = Integer.toString(hour);
			pm = true;
		} else if(hour > 12) {
			hourString = Integer.toString(hour - 12);
			pm = true;
		} else {
			hourString = Integer.toString(hour);
		}
		if(minute < 10) {
			minuteString = "0" + Integer.toString(minute);
		} else {
			minuteString = Integer.toString(minute);
		}
		return hourString + ":" + minuteString + (pm ? "PM" : "AM");
	}

	/**
	 * Checks if the other Activity and this Activity take place at the same time on the same day at least once during the week.
	 *     If they do, this throws a ConflictException.
	 * @param possibleConflictingActivity The activity to check for conflicts against.
	 * @throws ConflictException if this Activity and the other Activity take place at the same time on the same day at least once.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		
		// If either of them are arranged, there can't be a conflict, just return.
		if("A".equals(meetingDays) || "A".equals(possibleConflictingActivity.meetingDays)) return;
		
		// If they never share a day, then they are never going to overlap, return.
		boolean dayOverlap = false;
		for(int i = 0; i < meetingDays.length(); i++) {
			char day = meetingDays.charAt(i);
			if(possibleConflictingActivity.meetingDays.contains("" + day)) {
				dayOverlap = true;
				break;
			}
		}
		if(!dayOverlap) return;
		
		// If both the start and end time for this Activity are less than the start time for the other, then it's not overlapping, just return.
		if(startTime < possibleConflictingActivity.startTime && endTime < possibleConflictingActivity.startTime) return;
		
		// If both the start and end time for this Activity are greater than the end time for the other, then it's not overlapping, just return.
		if(startTime > possibleConflictingActivity.endTime && endTime > possibleConflictingActivity.endTime) return;
		
		// Otherwise, it is overlapping somewhere, throw the exception.
		throw new ConflictException();
		
	}

	/**
	 * Generates a hash code for the Activity.
	 * @return The hash code for the Activity.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(endTime, meetingDays, startTime, title);
	}

	
	/**
	 * Returns if this Activity and the object parameter are the same, as in they are the
	 *    same type and have all the same fields.
	 * @param obj The object to compare this Activity to.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		return endTime == other.endTime && Objects.equals(meetingDays, other.meetingDays)
				&& startTime == other.startTime && Objects.equals(title, other.title);
	}
	
	

}