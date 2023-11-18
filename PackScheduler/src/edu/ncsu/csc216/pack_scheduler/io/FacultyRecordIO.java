package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;
/**
 * Class with static methods for reading and writing faculty records to files.
 * @author Warren Long
 */
public class FacultyRecordIO {
	
    /**
     * Reads faculty details from a file and generates a list of valid Faculty.  Any invalid
     * faculty are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Faculty details from
     * @return a list of valid Facultys
     * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
		LinkedList<Faculty> facultys = new LinkedList<Faculty>(); //Create an empty array of Faculty objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readFaculty, and get the object
	            //If trying to construct a Faculty in readFaculty() results in an exception, flow of control will transfer to the catch block, below
	        	Faculty faculty = readFaculty(fileReader.nextLine()); 
	            //Create a flag to see if the newly created Faculty is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < facultys.size(); i++) {
	                //Get the course at index i
	            	User current = facultys.get(i);
	                //Check if the first and last names are the same
	                if (faculty.getId().equals(current.getId())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the Faculty is NOT a duplicate
	            if (!duplicate) {
	            	facultys.add(faculty); //Add to the SortedList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the SortedList with all the courses we read!
	    return facultys;
	}
	
	/**
	 * Writes Facultys from FacultyDirectory to the file represented by fileName
	 * @param fileName The file to which Facultys will be written to
	 * @param facultyDirectory A list of Facultys
	 * @throws IOException if the file cannot be found/read
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < facultyDirectory.size(); i++) {
    	    fileWriter.println(facultyDirectory.get(i).toString());
    	}
    	
    	fileWriter.close();      
		
	}
	
	/**
	 * Takes in a string with course details and constructs a Faculty based with them
	 * @param nextLine String to read Faculty details from
	 * @return a Faculty instance with parameters given by nextLine
	 * @throws IllegalArgumentException if the given line is not formatted correctly and cannot be read.
	 */
    private static Faculty readFaculty(String nextLine) {
    	//courseReader takes in the course parameters from nextLine
    	Scanner facultyReader = new Scanner(nextLine);
    	//Set courseReader to use the delimiter ",", as nextLine is in csv format
    	facultyReader.useDelimiter(",");
    	try {
    		//Facultys are read as CSVs with order:
    			//firstName,lastName,id,email,hashedPassword,maxCredits
    		String facultyFirst = facultyReader.next();
    		String facultyLast = facultyReader.next();
    		String facultyId = facultyReader.next();
    		String facultyEmail = facultyReader.next();
    		String facultyHashedPassword = facultyReader.next();
    		int facultyMaxCredits = Integer.parseInt(facultyReader.next());
    		//Close the scanner
    		//System.out.println("Adding: " + FacultyFirst + " " + FacultyLast);
    		facultyReader.close();
    		return new Faculty(facultyFirst, facultyLast, facultyId, facultyEmail, facultyHashedPassword, facultyMaxCredits);
    	}
    	//Catches cases where nextLine ends unexpectedly
    	catch(Exception NoSuchElementException) {
    		facultyReader.close();
    		throw new IllegalArgumentException();
    	} 	
	}
}
