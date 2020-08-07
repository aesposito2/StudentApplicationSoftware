package com.B5015845.CSC8002.Coursework;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.naming.LimitExceededException;

/**
 * The AbstractStudent class creates abstract students, and stores and retrieves information about them. It is extended by UG, PGT 
 * and PGR, and implements the Student interface.
 * @author Arianna Esposito
 */

public abstract class AbstractStudent implements Student{
	
	private final Name studentName;
	private final Date dob;
	private final StudentID sid;
	private ArrayList<Module> modules = new ArrayList<Module>();
	private final SmartCard smartCard;
	
	/**
	 * Constructs and initialises objects of the abstract student class. This constructor is protected so that only subclasses may 
	 * access it and use it to initialise their own objects. The parameters are not permitted to be null. The abstract student
	 * objects are immutable.
	 * @param studentName
	 * @param dob
	 */
	protected AbstractStudent(Name studentName, Date dob)
	{
		if (studentName == null)
			throw new IllegalArgumentException("null student name not permitted");
		if (dob == null)
			throw new IllegalArgumentException("null date of birth not permitted");
		
		this.studentName = new Name(studentName.getFirstName(), studentName.getLastName());
		this.dob = new Date(dob.getTime());
		this.sid = StudentID.getInstance();
		this.smartCard = new SmartCard(this.studentName, this.dob, sid, getStudentType());
	}
	
	/**
     * Retrieves a defensive copy of the student's name.
     * @return the student's name.
     */
	public Name getName() 
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
     * Retrieves the student ID generated in the constructor.
     * @return the student's ID.
     */
	public StudentID getStudentID() 
	{
		return sid;
	}

	/**
     * Retrieves the list of modules for the student.
     * @return the student's module list.
     */
	public ArrayList<Module> getModuleList() 
	{
		return modules;
	}
	
	/**
     * Method to add modules to student's list of modules. Checks that student has not exceeded their credit allowance.
     * Returns true if modules can be added.
     */
	public boolean addModuleToList(Module m) throws LimitExceededException
	{
		if (getRequiredCredits() == 0)
		{
			throw new LimitExceededException("Postgraduate research students should not register for modules");
		}
		else if (hasEnoughCredits() == true)
		{
			throw new LimitExceededException("This student is already registered for the required amount of credits");
		}
		else
		{
			modules.add(m);
			return true;
		}
	}
	
	/**
     * Returns true if student has enough credits.
	 * @throws LimitExceededException 
     */
	public boolean hasEnoughCredits() throws LimitExceededException 
	{
		int totalCredits = 0;
		if (getStudentType() == StudentType.PGR)
		{
			return true;
		}
		for (Module m: modules)
		{
			
			totalCredits =+ m.getCredits();
			
			if (totalCredits == getRequiredCredits())
			{
				return true;
			}
			else if (totalCredits < getRequiredCredits())	
			{
				throw new LimitExceededException("This student is not registered for enough credits!");
			}
			else if (totalCredits > getRequiredCredits())	//this needs sorting
			{
				throw new LimitExceededException("This student is registered for too many credits!");
			}
		}
		return false;
	}
	
	/**
	 * Method adds up how many credits a student has and returns the value.
	 * @return number of credits.
	 */
	public int howManyCredits()
	{
		int totalCredits = 0;
		for (Module m: modules)
		{
			totalCredits=+ m.getCredits();
		}
		return totalCredits;
	}

	/**
	 * @Override 
	 * @return the string representation of students when printed.
	 */
	@Override
	public String toString() 
	{
		return getStudentType() + " Student: " + studentName + ", " + sid + ", " + getStudentType();
	}
	
	public boolean isOfAge()
	{
		Calendar cal = Calendar.getInstance();				
		
		if (getStudentType() == StudentType.UG) 
		{
			cal.add(Calendar.YEAR, -17);
			Date minDate = cal.getTime();
			if (dob.compareTo(minDate) <= 0)
			{
				return true;
			}
			return false;
		}
		else if (getStudentType() == StudentType.PGT || getStudentType() == StudentType.PGR )
			{
				cal.add(Calendar.YEAR, -20);
				Date minDate = cal.getTime();
				if (dob.compareTo(minDate) <= 0)
				{
					return true;
				}
				return false;
		}
		return false;
	}

	/**
	 * Issues a smart card, based on student type and the student being 
	 * @return the correct smart card, based on the type of student.
	 */
	public SmartCard getSmartCard()		
	{
		if (getStudentType() == StudentType.UG && isOfAge() == true) //&& dob is correct
		{
			return smartCard;
		}
		else if (getStudentType() == StudentType.PGT && isOfAge() == true)
		{
			return smartCard;
		}
		else if (getStudentType() == StudentType.PGR && isOfAge() == true)
		{
			return smartCard;
		}
		else
		{
			throw new IllegalArgumentException("UGs must be at least 17y/o. PGs must be at least 20y/o. This student is not correct");
		}
	}
	
	public abstract StudentType getStudentType();
	
	public abstract int getRequiredCredits();
	
	public abstract int getPassMark();
	
}

