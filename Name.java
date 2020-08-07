package com.B5015845.CSC8002.Coursework;

/**
 * The Name class creates names, and can store and retrieve individual components of the name, such as first name, last name and 
 * initials.
 * @author Arianna Esposito
 */

public final class Name {

	private final String firstName;
	private final String lastName;
	
	/**
     * Constructs and initialises the objects of the name (first name and last name).
     * @param first name
     * @param last name
     */	
	public Name (String firstName, String lastName)
	{
		if (firstName == null || lastName == null)
		{
			throw new NullPointerException("Student must have both a first name and a last name");
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	 /**
     * Retrieves the first name.
     * @return this name's first name.
     */
	public String getFirstName() {		//String is immutable.
		return firstName;
	}
	
	 /**
     * Retrieves the last name.
     * @return this name's last name.
     */
	public String getLastName() {		//String is immutable.
		return lastName;
	}

	/**
     * @Override 
     * @return the string representation of names when printed.
     */
	@Override
	public String toString()		//String is immutable. 
	{
		return firstName + " " + lastName;
	}
	
	 /**
     * Retrieves the name's initials.
     * Code adapted from: //https://stackoverflow.com/questions/328249/how-to-concatenate-characters-in-java
     * @return this name's initials.
     */
	public String getInitials()		//String is immutable.
	{
		char firstInitials = getFirstName().charAt(0);
		char secondInitials = getLastName().charAt(0);
		String initials = new StringBuilder().append(firstInitials).append(secondInitials).toString(); 
		return initials;
	}

}