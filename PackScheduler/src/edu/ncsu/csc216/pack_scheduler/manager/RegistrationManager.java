package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Singleton class that manages a Course catalog and Student directory. Contains a registrar User that can
 * use the manager
 * @author Warren Long
 * 
 */
public class RegistrationManager {
	/**
	 * A single instance of RegistrationManager
	 */
	private static RegistrationManager instance;
	/**
	 * Catalog of the available courses
	 */
	private CourseCatalog courseCatalog;
	/**
	 * A directory of the students
	 */
	private StudentDirectory studentDirectory;
	/**
	 * Holds the preset registrar
	 */
	private User registrar;
	/**
	 * Holds the actual currentUser, either the registrar or a Student.  
	 */
	private User currentUser;
	/**
	 * the directory of the faculty
	 */
	private FacultyDirectory facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** Properties File containing the registrar login information */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Constructs a new Registration Manager. This is a private constructor to allow the RegistrationManager class to act as a singleton.
	 *     Constructs a new course catalog and new student directory, and initializes a new registrar using the local registrar.properties file.
	 * @throws IllegalArgumentException if the registrar inner class cannot be constructed with the given properties.
	 */
	private RegistrationManager() {
		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		facultyDirectory = new FacultyDirectory();
		createRegistrar();
		logout();
		
	}
	/**
	 * gets the faculty directory
	 * @return the faculty directory
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	/**
	 * Creates a new instance of the Registrar inner class with information imported from the local registrar.properties file.
	 * @throws IllegalArgumentException if the file cannot be found or if it's details are invalid.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();
		
		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);
			
			String hashPW = hashPW(prop.getProperty("pw"));
			
			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}
	
	/**
	 * Returns a hashed version of the given password using the HASH_ALGORITHM variable algorithm.
	 * @param pw The password to hash.
	 * @return The hashed version of the password.
	 * @throws IllegalArgumentException if the password cannot be hashed.
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
	
	/**
	 * Returns a static, singleton instance of RegistrationManager.
	 * @return a new RegistrationManager if there isn't one already, the old one if there was.
	 * @throws IllegalArgumentException if constructing a new RegistrationManager and a registrar cannot be constructed with the local registrar properties.
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}
	
	/**
	 * Returns the course catalog.
	 * @return The courseCatalog.
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}
	/**
	 * Returns the student directory.
	 * @return The studentDirectory.
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}
	
	/**
	 * Allows the user to login. Sets the currentUser as the type of user the ID/Pass are associated with.
	 * @param id id of the User.
	 * @param password password of the User.
	 * @return true if the ID and Password are linked to an account, false if there isn't any.
	 * @throws IllegalArgumentException if there is no user associated with the given information, or somebody is already logged in.
	 */
	public boolean login(String id, String password) {
		if (currentUser != null) {
			return false;
		}
		Student s = studentDirectory.getStudentById(id);
		Faculty f = facultyDirectory.getFacultyById(id);
		String localHashPW = hashPW(password);
		if (s != null) {
			if (s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}
			else {
				return false;
			}
		}
		if (f != null) {
			if (f.getPassword().equals(localHashPW)) {
				currentUser = f;
				return true;
			}
			else {
				return false;
			}
		}
		if (registrar.getId().equals(id)){
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}
			else {
				return false;
			}
		}	
		throw new IllegalArgumentException("User doesn't exist.");
	}
	
	/**
	 * Logs the user out. CurrentUser becomes null.
	 */
	public void logout() {
		currentUser = null; 
	}
	
	/**
	 * Returns the current user.
	 * @return the current user.
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	/**
	 * Resets the courseCatalog and studentDirectory.
	 * Also clears currentUser.
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		logout();
	}
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * A class to represent the registrar user of the system.
	 * @author CSC 217 team.
	 */
	private static class Registrar extends User {
		/**
		 * Creates a Registrar, a type of User. 
		 * @param firstName First name of the Registrar.
		 * @param lastName Last name of the Resgistrar.
		 * @param id id of the Registrar.
		 * @param email email of the Registrar.
		 * @param hashPW hashed password of the Registrar.
		 * @throws IllegalArgumentException if any of the arguments are null or ampty strings,
		 *     or if the email is not the valid format for an email address.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}