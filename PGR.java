package com.B5015845.CSC8002.Coursework;
import java.util.Date;


/**
 * The PGR class creates extends AbstractStudent, and stores and retrieves information about UG students. It implements Student and 
 * ResearchStudent interfaces. PGR students can only be created within the package, but cannot be added to the system through this class.
 * @author Arianna Esposito
 */
public final class PGR extends AbstractStudent implements ResearchStudent {
	
	private Supervisor supervisor;
	
	/**
	 * Package private constructor, which can be accessed within the package (for example by the student factory), but means that 
	 * PGR cannot be created outside of of the package. Inherits constructor from superclass (AbstractStudent). Even if PGR can be created
	 * elsewhere in the package, it is only added to the system through the student factory method.
	 * @param name
	 * @param dob
	 * @param supervisor
	 */
	PGR(Name name, Date dob, Supervisor supervisor) 
	{
		super(name, dob);
		this.supervisor = supervisor;
	}
	
	/**
	 * @return student type: PGR.
	 */
	@Override
	public StudentType getStudentType() 
	{
		return StudentType.PGR;
	}

	/**
	 * @return required credits: 0.
	 */
	@Override
	public int getRequiredCredits() 
	{
		return 0;
	}

	/**
	 * @return supervisor.
	 */
	public Supervisor getSupervisorName() 
	{
		return supervisor;
	}
	
	/**
	 * Set supervisor for PGR
	 */
	public void setSupervisorName(Supervisor s)
	{
		this.supervisor = s;
	}

	/**
	 * @return pass mark: 0
	 */
	@Override
	public int getPassMark() {
		return 0;
	}
}
