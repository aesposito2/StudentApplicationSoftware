package com.B5015845.CSC8002.Coursework;
import java.util.Date;

/**
 * The PGT class creates extends AbstractStudent, and stores and retrieves information about PGT students. It implements Student and 
 * TaughtStudent interfaces. PGT students can only be created within the package, but cannot be added to the system through this class.
 * @author Arianna Esposito
 */
public final class PGT extends AbstractStudent {
	
	/**
	 * Package private constructor, which can be accessed within the package (for example by the student factory), but means that 
	 * PGT cannot be created outside of of the package. Inherits constructor from superclass (AbstractStudent). Even if PGT can be created
	 * elsewhere in the package, it is only added to the system through the student factory method.
	 * @param name
	 * @param dob
	 */
	PGT(Name name, Date dob) 
	{
		super(name, dob);
	}

	/**
	 * @return student type: PGT.
	 */
	@Override
	public StudentType getStudentType() 
	{
		return StudentType.PGT;
	}

	/**
	 * @return required credits: 180.
	 */
	@Override
	public int getRequiredCredits() 
	{
		return 180;
	}

	/**
	 * @return pass mark: 50.
	 */
	@Override
	public int getPassMark() {
		return 50;
	}
}
