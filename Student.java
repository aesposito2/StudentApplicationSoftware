package com.B5015845.CSC8002.Coursework;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.LimitExceededException;

/*
 * Interface class for Students. Contains methods that all student classes must implement.
 * @author Arianna Esposito
 */
public interface Student {
	public Name getName();
	public Date getDob(); 
	public StudentID getStudentID();
	public StudentType getStudentType(); //different for different students
	public ArrayList<Module> getModuleList(); 
	public boolean addModuleToList(Module m) throws LimitExceededException;
	public int getRequiredCredits(); //different for different students
	public boolean hasEnoughCredits() throws LimitExceededException;
	public String toString();
	public SmartCard getSmartCard();
	public int getPassMark(); //different for different students
	public boolean isOfAge();
	public int howManyCredits();
}
