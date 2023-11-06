package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import edu.ncsu.csc217.collections.list.SortedList;
import java.util.Scanner;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Deals with adding and removing Students from a file
 */
public class StudentRecordIO {
	
    /**
     * Reads student details from a file and generates a list of valid Students.  Any invalid
     * Students are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Student details from
     * @return a list of valid Students
     * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    SortedList<Student> students = new SortedList<Student>(); //Create an empty array of Student objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readStudent, and get the object
	            //If trying to construct a Student in readStudent() results in an exception, flow of control will transfer to the catch block, below
	        	Student student = readStudent(fileReader.nextLine()); 
	            //Create a flag to see if the newly created Student is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < students.size(); i++) {
	                //Get the course at index i
	            	User current = students.get(i);
	                //Check if the first and last names are the same
	                if (student.getId().equals(current.getId())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the student is NOT a duplicate
	            if (!duplicate) {
	            	students.add(student); //Add to the SortedList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	        	System.out.println("Exception is " + e.getMessage());
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the SortedList with all the courses we read!
	    return students;
	}
	
	/**
	 * Writes Students from studentDirectory to the file represented by fileName
	 * @param fileName The file to which Students will be written to
	 * @param studentDirectory A list of Students
	 * @throws IOException if the file cannot be found/read
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < studentDirectory.size(); i++) {
    	    fileWriter.println(studentDirectory.get(i).toString());
    	}
    	
    	fileWriter.close();      
		
	}
	
	/**
	 * Takes in a string with course details and constructs a Student based with them
	 * @param nextLine String to read student details from
	 * @return a Student instance with parameters given by nextLine
	 * @throws IllegalArgumentException if the given line is not formatted correctly and cannot be read.
	 */
    private static Student readStudent(String nextLine) {
    	//courseReader takes in the course parameters from nextLine
    	Scanner studentReader = new Scanner(nextLine);
    	//Set courseReader to use the delimiter ",", as nextLine is in csv format
    	studentReader.useDelimiter(",");
    	try {
    		//Students are read as CSVs with order:
    			//firstName,lastName,id,email,hashedPassword,maxCredits
    		String studentFirst = studentReader.next();
    		String studentLast = studentReader.next();
    		String studentId = studentReader.next();
    		String studentEmail = studentReader.next();
    		String studentHashedPassword = studentReader.next();
    		int studentMaxCredits = Integer.parseInt(studentReader.next());
    		//Close the scanner
    		//System.out.println("Adding: " + studentFirst + " " + studentLast);
    		studentReader.close();
    		return new Student(studentFirst, studentLast, studentId, studentEmail, studentHashedPassword, studentMaxCredits);
    	}
    	//Catches cases where nextLine ends unexpectedly
    	catch(Exception NoSuchElementException) {
    		studentReader.close();
    		throw new IllegalArgumentException();
    	} 	
	}
}
