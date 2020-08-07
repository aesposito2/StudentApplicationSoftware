package com.B5015845.CSC8002.Coursework;

import java.util.Calendar;
//import java.util.Calendar;
import java.util.Date;

/**
 * The SmartCard class create unique smart cards. It stores and can retrieve components of the smart card, for example student name,
 * date of birth, the smart card number, the student's ID, the issue date and the expiry date of the card.
 * This class does not implement the factory design pattern because the smart card numbers are all unique, therefore each card created
 * will also be unique as a result of this.
 * @author Arianna Esposito
 */
public final class SmartCard {								

	private final Name studentName;
	private final Date dob;
	private final SmartCardNumber smartCardNumber;
	private final StudentID sid;
	private final Date issueDate;
	private Date expiryDate;
	//private static final Map<String, SmartCard> SMARTCARDS = new HashMap<String, SmartCard>();
	
	/**
	 * Constructs and initialises the objects of the smart card.
	 * @param studentName
	 * @param dob
	 * @param sid
	 * @param studentType
	 */
	public SmartCard (Name studentName, Date dob, StudentID sid, StudentType studentType) 
	{
		this.studentName = new Name(studentName.getFirstName(), studentName.getLastName());
		this.dob = new Date(dob.getTime());
		this.smartCardNumber = SmartCardNumber.getInstance(studentName);
		this.issueDate = new Date(); 
		this.sid = sid;
		this.expiryDate = setExpiryDate(studentType); 
	}
	
	/**
     * Retrieves a defensive copy of the student's name.
     * @return the student's name.
     */
	public Name getStudentName()
	{
		return new Name(studentName.getFirstName(), studentName.getLastName());
	}

	/**
     * Retrieves a defensive copy of the student's date of birth.
     * @return the student's date of birth.
     */
	public Date getDob() 
	{
		return new Date(dob.getTime());
	}

	/**
     * Retrieves the smart card number generated in the constructor.
     * @return the smart card number.
     */ 
	public SmartCardNumber getSmartCardNumber() 
	{	
		return smartCardNumber;
	}
	
	/**
     * Retrieves the unique student ID generated in the constructor.
     * @return the smart card number.
     */
	public StudentID getStudentID()
	{
		return sid;
	}

	/**
     * Retrieves a defensive copy of the issue date of the smart card.
     * @return the smart card issue date.
     */
	public Date getIssueDate() 
	{
		return new Date(issueDate.getTime());	
	}
		
	/**
     * Private method to set and retrieve defensive copy of the expiry date for the smart card based on the student type.
     * Code adapted from: https://mkyong.com/java/java-how-to-add-days-to-current-date/
     * @return the smart card expiry date (privately).
     */
	private Date setExpiryDate(StudentType studentType) throws IllegalArgumentException
	{
		Calendar cal = Calendar.getInstance();				
		cal.setTime(issueDate);
		
		if (studentType == StudentType.UG)
			{
				cal.add(Calendar.YEAR, 4);
				this.expiryDate = cal.getTime();
				return new Date(expiryDate.getTime());
			}
		else if (studentType == StudentType.PGT)
			{
				cal.add(Calendar.YEAR, 2);
				this.expiryDate = cal.getTime();	
				return new Date(expiryDate.getTime());
			}
		else if (studentType == StudentType.PGR)
			{	
				cal.add(Calendar.YEAR, 5);
				this.expiryDate = cal.getTime();
				return new Date(expiryDate.getTime());
			}
		else
		{
			throw new IllegalArgumentException("This student's student type is not valid.");
		}
	}
	
	/**
	 * Retrieves a defensive copy of the smart card expiry date that's accessible outside the smart card class.
	 * @return the smart card expiry date
	 */
	public Date getExpiryDate()
	{	
		return new Date(expiryDate.getTime());
	}

	/**
	 * @Override
	 * @return the string representation of the smart card when printed.
	 */
	@Override
	public String toString() 
	{
		return "SmartCard: " +  studentName.toString() + ", " + dob.toString() + ", " + smartCardNumber.toString() + ", " + issueDate.toString();
	}

}
