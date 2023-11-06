/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Deals with time conflicts
 */
public interface Conflict {
	/**
	 * Checks to see if an Activity conflicts with other known activities
	 * @param possibleConflictingActivity The activity to be checked
	 * @throws ConflictException When a conflict is detected
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
