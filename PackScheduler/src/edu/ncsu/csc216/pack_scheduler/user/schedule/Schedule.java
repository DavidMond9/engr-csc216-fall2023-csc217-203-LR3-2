package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Represents a Student's Schedule, with a list of Courses and a title. Course objects can
 *     be added and removed from the schedule, and a String[][] array of all Course's info
 *     can be generated.
 * @author Joey Hughes, Sam McDonald, Winston Cheaz
 */
public class Schedule {

	/** The schedule. An ArrayList of Courses. */
	private ArrayList<Course> schedule;
	
	/** The title of the schedule. */
	private String title;

	/**
	 * Constructs a new Schedule with an empty ArrayList of Courses and a default title "My Schedule".
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Adds the given course object to the schedule, as long as it 
	 *      is not a duplicate or conflicting with another Course.
	 * @param course The course to add to the schedule.
	 * @return true if the schedule was successfully added.
	 * @throws IllegalArgumentException if the course is a duplicate or conflicting with another Course.
	 * @throws NullPointerException if the Course that is supposed to be added is null.
	 */
	public boolean addCourseToSchedule(Course course) {
		
		// Checks for conflicts and duplicates
		for(int i = 0; i < schedule.size(); i++) {
			try {
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
			if(course.getName().equals(schedule.get(i).getName())) {
				throw new IllegalArgumentException("You are already enrolled in " + course.getName());
			}
		}
		
		schedule.add(course);
		return true;
	}

	/**
	 * Removes the given course from the schedule, if it is in the schedule.
	 *      Checks for the course in the schedule via the name and section.
	 * @param course The course to try to remove from the schedule.
	 * @return True if the course was found and removed, false if it wasn't found.
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(course)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule to a blank list and resets the title to the default "My Schedule".
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Returns a 2D String array of all the Courses in the schedule's names, sections, and titles.
	 * @return The 2D String array with all the schedule's Courses' names, sections, and titles.
	 */
	public String[][] getScheduledCourses() {
		if(schedule.size() == 0) {
			return new String[0][0];
		}
		String[][] scheduleArray = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			scheduleArray[i] = schedule.get(i).getShortDisplayArray();
		}
		return scheduleArray;
	}


	/**
	 * Sets a new title for the schedule.
	 * @param newTitle The new title.
	 * @throws IllegalArgumentException if the new title is null.
	 */
	public void setTitle(String newTitle) {
		if(newTitle == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		title = newTitle;
	}

	/**
	 * Returns the schedule's title.
	 * @return The schedule's title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Adds up all credits within the schedule then returns the final sum
	 * @return sum of all credits in schedule
	 */
	public int getScheduleCredits() {
		int credits = 0;
		for(int i = 0; i < schedule.size(); i++) {
			credits += schedule.get(i).getCredits();
		}
		return credits;
	}
	
	/**
	 * Returns true of false depending on whether the course can be added to the schedule.
	 * @param c course to check
	 * @return boolean, true if course can be added and false if not
	 */
	public boolean canAdd(Course c) {
		if(c == null) {
			return false;
		}
		for(int i = 0; i < schedule.size(); i++) {
//			if(c.isDuplicate(schedule.get(i))) {
//				return false;
//			}
			if (c.getName().equals(schedule.get(i).getName())){
				return false;
			}
			try {
				c.checkConflict(schedule.get(i));
			} catch(ConflictException e) {
				return false;
			}
		}
		return true;
	}
}
