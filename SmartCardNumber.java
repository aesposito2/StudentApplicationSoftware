package com.B5015845.CSC8002.Coursework;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The SmartCardNumber class acts as a factory to create unique smart card numbers. It stores smart card numbers in a hashmap to ensure
 * uniqueness, and can retrieve the individual components that make up the smart card number, such as name, year of issue and the random
 * numbers
 * The smart card number format is: "initials - year of issue - two arbitrary numbers" e.g. "AE-2020-12".
 * @author Arianna Esposito
 */
public final class SmartCardNumber {
	
	private final Name studentName;					
	private final int year;
	private final int randomNumbers;
	private final String smartCardNumberRep;
	private static final Map<String, SmartCardNumber> SMARTCARDNUMBERS = new HashMap<String, SmartCardNumber>();

	/**
     * Private constructor to ensure that the getInstance() method is the only way to create new smart card numbers.
     * @param student name
     * @param random numbers (2 digits)
     */
	private SmartCardNumber(Name studentName, int randomNumbers) 
	{
		this.studentName = studentName;
		this.year = Calendar.getInstance().get(Calendar.YEAR);
		this.randomNumbers = randomNumbers;
		smartCardNumberRep = studentName.getInitials() + "-" + year + "-" + String.format("%02d", randomNumbers);
	}
	
	/**
	 * This method creates a smart card number and checks uniqueness by checking if the smart card number exists in the hashmap
	 * before creating it and only returning a smart card number that hasn't previously been created. 
	 * This code is adapted from the CSC8002 lecture slides on object factories.
	 * @param student's name
	 * @return unique instance of smart card number
	 */
	public final static SmartCardNumber getInstance(Name studentName)
	{
		String initials = studentName.getInitials();
		
		Random r = new Random();
		final int randomNumbers = r.nextInt(99);
		final int year = 2018;
		
		String smartCardNumberRep = initials + "-" + year + "-" + String.format("%02d", randomNumbers); //stack for two digit format
		
		SmartCardNumber scn = SMARTCARDNUMBERS.get(smartCardNumberRep);
		if (scn == null)
		{
			scn = new SmartCardNumber(studentName, randomNumbers);
			SMARTCARDNUMBERS.put(smartCardNumberRep, scn);
		}
		return scn;
	}

	/**
	 * Retrieves the student's name on the smart card
	 * @return the student's name
	 */
	public Name getStudentName() 
	{
		return studentName;
	}

	/**
	 * Retrieves the year on the smart card
	 * @return the year off issue
	 */
	public int getYear() 
	{
		return year;
	}

	/**
	 * Retrieves the arbitrary two digit number on the smart card
	 * @return the random numbers
	 */
	public int getRandomNumbers() 
	{
		return randomNumbers;
	}

	/**
	 * @Override
	 * @return the string representation of the smart card number when printed
	 */
	@Override
	public String toString() {
		return smartCardNumberRep;
	}
	
}
