package com.B5015845.CSC8002.Coursework;

/*
 * Interface class for research students (PGR). Contains methods that all research student classes must implement.
 * @author Arianna Esposito
 */
public interface ResearchStudent extends Student 
{
	public Supervisor getSupervisorName();
	public void setSupervisorName(Supervisor s);
}
