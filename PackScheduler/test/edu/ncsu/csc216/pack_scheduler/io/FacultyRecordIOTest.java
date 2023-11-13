package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

class FacultyRecordIOTest {

	/** Valid faculty records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Invalid faculty records */
	private final String invalidTestFile = "test-files/invalid_faculty_records.txt";
	/** Valid Faculty Test Example 0 */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	/** Valid Faculty Test Example 1 */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** Valid Faculty Test Example 2 */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** Valid Faculty Test Example 3 */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** Valid Faculty Test Example 4 */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** Valid Faculty Test Example 5 */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,3";
	/** Valid Faculty Test Example 6 */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,1";
	/** Valid Faculty Test Example 7 */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,MMlS+rEiw/l1nwKm2Vw3WLJGtP7iOZV7LU/uRuJhcMQ=,2";
	
	/** Valid Facultys string*/
	private String [] validFacultys = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5, 
			validFaculty6, validFaculty7};
	/** String containing hashed password */
	private String hashPW;
	/** Hash Algorithm Type */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Method to set up exception testing, and run the password hashing algorithm.
	 * @throws java.lang.Exception throw exception for FileNotFoundException
	 */
	@BeforeEach
	void setUp() throws Exception {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validFacultys.length; i++) {
	            validFacultys[i] = validFacultys[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	/**
	 * Helper method to compare two files for the same contents. Catches IOException error.
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO#readFacultyRecords(java.lang.String)}.
	 */
	@Test
	void testReadFacultyRecords() {
		try {
			ArrayList<Faculty> s = FacultyRecordIO.readFacultyRecords(validTestFile);
			assertEquals(8, s.size());
			
			for (int i = 0; i < validFacultys.length; i++) {
				assertEquals(validFacultys[i], s.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + validTestFile);
		}
	}

	/**
	 * Tests readFacultyRecords() with invalid test file
	 */
	@Test
	public void testReadInvalidFacultyRecords() {
		ArrayList<Faculty> s;
		try {
			s = FacultyRecordIO.readFacultyRecords(invalidTestFile);
			assertEquals(0, s.size());
		} catch (FileNotFoundException e) {
			fail("Unexpected FileNotFoundException");
		}
	}
	
	/**
	 * Tests readFacultyRecords() for a file that does not exist
	 */
	@Test
	public void testReadRecordsFileDoesNotExist() {
		assertThrows(FileNotFoundException.class,
					() -> FacultyRecordIO.readFacultyRecords("test-files/not_a_real_file.txt"));
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO#writeFacultyRecords(java.lang.String, )}.
	 */
	@Test
	void testWriteFacultyRecords() {
		ArrayList<Faculty> facultys = new ArrayList<Faculty>();
		facultys.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		facultys.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		facultys.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
		facultys.add(new Faculty("Halla", "Aguirre", "haguirr", "Fusce.dolor.quam@amalesuadaid.net", hashPW, 3));
		facultys.add(new Faculty("Kevyn", "Patel", "kpatel", "risus@pellentesque.ca", hashPW, 1));
		facultys.add(new Faculty("Elton", "Briggs", "ebriggs", "arcu.ac@ipsumsodalespurus.edu", hashPW, 3));
		facultys.add(new Faculty("Norman", "Brady", "nbrady", "pede.nonummy@elitfermentum.co.uk", hashPW, 1));
		facultys.add(new Faculty("Lacey", "Walls", "lwalls", "nascetur.ridiculus.mus@fermentum.net", hashPW, 2));
		
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", facultys);
		} catch (IOException e) {
			fail("Cannot write to faculty records file");
		}
		checkFiles("test-files/expected_full_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}
	
	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO#writeFacultyRecords(java.lang.String, )}.
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		ArrayList<Faculty> facultys = new ArrayList<Faculty>();
		facultys.add(new Faculty("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 2));

		
		Exception exception = assertThrows(IOException.class, 
				() -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_faculty_records.txt", facultys));
		assertTrue("/home/sesmith5/actual_faculty_records.txt (No such file or directory)".equals(exception.getMessage())
				|| "\\home\\sesmith5\\actual_faculty_records.txt (The system cannot find the path specified)".equals(exception.getMessage()));
	}
}
