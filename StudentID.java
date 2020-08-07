package com.B5015845.CSC8002.Coursework;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * The StudentID class acts as a factory to create unique student IDs. It stores the student IDs in a hashmap to ensure uniqueness, 
 * and can retrieve the individual components that make up the student ID: four digits and a letter.
 * The student ID format is a single letter and four arbitrary numbers e.g. "a1234".
 * @author Arianna Esposito
 */
public final class StudentID {

	private final char letter;
	private final int fourDigit;
	private final String studentIDRep;
	private static final Map<String, StudentID> STUDENT_IDS = new HashMap<String, StudentID>();
	
	/**
     * Private constructor to ensure that the getInstance() method is the only way to create new student IDs.
     * @param a single letter
     * @param four random digits
     */
	private StudentID(char letter, int fourDigit) 
	{ 
		this.letter = letter;
		this.fourDigit = fourDigit;
		studentIDRep = letter + String.format("%04d", fourDigit);	
	}

	/**
	 * This method creates a student ID and checks uniqueness by checking if the studentID exists in the hashmap before creating it 
	 * and only returning a student ID that hasn't previously been created. 
	 * This code is adapted from the CSC8002 lecture slides on object factories.
	 * @return unique instance of a student ID.
	 */
	public final static StudentID getInstance()
	{
		Random r = new Random();
		int fourDigit = r.nextInt(10000);	//stack overflow again
		
		//https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java/14021385#14021385
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char letter = alphabet.charAt(r.nextInt(7));
		
		String studentIDRep = letter + String.format("%04d", fourDigit);  //https://stackoverflow.com/questions/51322750/generate-6-digit-random-number
		StudentID s = STUDENT_IDS.get(studentIDRep);
		if (s == null)
		{
			s = new StudentID(letter, fourDigit);
			STUDENT_IDS.put(studentIDRep, s);
		}
		return s;
	}
	
	/**
     * Retrieves the letter of the student ID.
     * @return this student ID's letter
     */
	public char getLetter() 
	{
		return letter;
	}
	
	/**
     * Retrieves the four digits of the student ID.
     * @return this student ID's four digit number
     */
	public int getFourDigit() 
	{
		return fourDigit;
	}
	
	/**
     * @Override 
     * @return the string representation of student IDs when printed.
     */	
	@Override
	public String toString() 
	{
		return "Student ID: " + studentIDRep;
	}
}
	