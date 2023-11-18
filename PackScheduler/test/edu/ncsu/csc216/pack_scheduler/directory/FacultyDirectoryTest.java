package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * Tests the faculty directory
 * @author Warren Long
 */
class FacultyDirectoryTest {
	
	/** Valid course records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "Stu";
	/** Test last name */
	private static final String LAST_NAME = "Dent";
	/** Test id */
	private static final String ID = "sdent";
	/** Test email */
	private static final String EMAIL = "sdent@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 3;
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		//Reset faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests FacultyDirectory().
	 */
	@Test
	public void testFacultyDirectory() {
		//Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory sd = new FacultyDirectory();
		assertFalse(sd.removeFaculty("sesmith5"));
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.testNewFacultyDirectory().
	 */
	@Test
	public void testNewFacultyDirectory() {
		//Test that if there are facultys in the directory, they 
		//are removed after calling newFacultyDirectory().
		FacultyDirectory sd = new FacultyDirectory();
		
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		sd.newFacultyDirectory();
		assertEquals(0, sd.getFacultyDirectory().length);
	}

	/**
	 * Tests FacultyDirectory.loadFacultysFromFile().
	 */
	@Test
	public void testLoadFacultysFromFile() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Test valid file
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		
		//Test invalid file
		FacultyDirectory sd2 = new FacultyDirectory();
		String invalidPath = "Invalid file path";
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> sd2.loadFacultyFromFile(invalidPath));
		assertEquals("Unable to read file " + invalidPath, exception.getMessage(), "Incorrect exception thrown with invalid input - " + invalidPath);
	}

	/**
	 * Tests FacultyDirectory.addFaculty().
	 */
	@Test
	public void testAddFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Test valid Faculty
		sd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);
		
		//Test invalid Faculty Passwords
		Exception exception1 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, "newID1", EMAIL, "", "", MAX_CREDITS));
		assertEquals("Invalid password", exception1.getMessage());
		
		//Test non-matching Faculty Passwords
		Exception exception2 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty(FIRST_NAME, LAST_NAME, "newID2", EMAIL, "pass1", "passone", MAX_CREDITS));
		assertEquals("Passwords do not match", exception2.getMessage());
		
	
		//Test repeating faculty IDs
		Exception exception4 = assertThrows(IllegalArgumentException.class,
				() -> sd.addFaculty("New First", "New Last", ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals("Invalid id", exception4.getMessage());
	}

	/**
	 * Tests FacultyDirectory.removeFaculty().
	 */
	@Test
	public void testRemoveFaculty() {
		FacultyDirectory sd = new FacultyDirectory();
				
		//Add facultys and remove
		sd.loadFacultyFromFile(validTestFile);
		assertEquals(8, sd.getFacultyDirectory().length);
		assertTrue(sd.removeFaculty("fmeadow"));
		String [][] facultyDirectory = sd.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Brent", facultyDirectory[1][0]);
		assertEquals("Brewer", facultyDirectory[1][1]);
		assertEquals("bbrewer", facultyDirectory[1][2]);
	}

	/**
	 * Tests FacultyDirectory.saveFacultyDirectory().
	 */
	@Test
	public void testSaveFacultyDirectory() {
		FacultyDirectory sd = new FacultyDirectory();
		
		//Add a faculty
		sd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		sd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		sd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
		assertEquals(3, sd.getFacultyDirectory().length);
		sd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
		
		//Attempt to save to an invalid file
		String invalidFilePath = "////?!?!?/??Invalid File Path goes here!";
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> sd.saveFacultyDirectory(invalidFilePath));
		assertEquals("Unable to write to file " + invalidFilePath, exception.getMessage());
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
