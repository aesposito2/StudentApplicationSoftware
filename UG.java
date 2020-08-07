package com.B5015845.CSC8002.Coursework;
import java.util.Date;

/**
 * The UG class creates extends AbstractStudent, and stores and retrieves information about UG students. It implements Student and 
 * TaughtStudent interfaces. UG students can only be created within the package, but cannot be added to the system through this class.
 * @author Arianna Esposito
 */
public final class UG extends AbstractStudent {
	
	/**
	 * Package private constructor, which can be accessed within the package (for example by the student factory), but means that 
	 * UG cannot be created outside of of the package. Inherits constructor from superclass (AbstractStudent). Even if UG can be created
	 * elsewhere in the package, it is only added to the system through the student factory method.
	 * @param name
	 * @param dob
	 */
	UG(Name name, Date dob) 
	{
		super(name, dob);
	} 

	/**
	 * @return student type: UG.
	 */
	@Override
	public StudentType getStudentType() 
	{
		return StudentType.UG;
	}

	/**
	 * @return required credits: 120.
	 */
	@Override
	public int getRequiredCredits() 
	{
		return 120;
	}

	/**
	 * @return pass mark: 40.
	 */
	@Override
	public int getPassMark() 
	{
		return 40;
	}
}
