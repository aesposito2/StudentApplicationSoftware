package com.B5015845.CSC8002.Coursework.Management;


import javax.naming.LimitExceededException;

import com.B5015845.CSC8002.Coursework.Student;
import com.B5015845.CSC8002.Coursework.StudentFactory;
import com.B5015845.CSC8002.Coursework.StudentID;
import com.B5015845.CSC8002.Coursework.StudentType;
import com.B5015845.CSC8002.Coursework.Supervisor;
import com.B5015845.CSC8002.Coursework.Module;
import com.B5015845.CSC8002.Coursework.ResearchStudent;
import com.B5015845.CSC8002.Coursework.SmartCard;

public class StudentManagementSystem {
	
	/**
	 * Retrieves the number of students enrolled for the specified type of student.
	 * @param student type
	 * @return number of students of the specified type
	 */
	public static int noOfStudents(StudentType studentType)
	{
		if (studentType == StudentType.UG)
		{
			int noOfStudents = StudentFactory.getUGs().size();
			return noOfStudents;
		}
		else if (studentType == StudentType.PGT)
		{
			int noOfStudents = StudentFactory.getUGs().size();
			return noOfStudents;
		}
		else if (studentType == StudentType.PGR)
		{
			int noOfStudents = StudentFactory.getUGs().size();
			return noOfStudents;
		}
		else
		{
			throw new IllegalArgumentException("Students can only be UG, PGT or PGR");
		}
	}
	
	/**
	 * Registers a new student onto the system and allocates them a student ID. 
	 * @param s - a Student
	 */
	public static StudentID registerStudent(Student s)
	{
		StudentFactory.getInstance(s.getName(), s.getDob(), s.getStudentType());
		return s.getStudentID();
	}
	
	/**
	 * Changes the modules chosen. This method is overloaded for different data.
	 * @param student id
	 * @param module
	 * @throws LimitExceededException 
	 */
	public static void amendStudentData(StudentID sid, Module m) throws LimitExceededException //studentData
	{
		if (StudentFactory.getUGs().containsKey(sid) || StudentFactory.getPGTs().containsKey(sid))
		{
			StudentFactory.getUGs().get(sid).addModuleToList(m);
		}
		else
			throw new LimitExceededException("Only UGs or PGTs have modules. PGR credits = 0");
	}
	
	/**
	 * Changes the supervisor chosen. This method is overloaded for different data.
	 * @param student id
	 * @param supervisor
	 */
	public static void amendStudentData(StudentID sid, Supervisor s) //studentData
	{
		if (StudentFactory.getPGRs().containsKey(sid))
		{
			((ResearchStudent) StudentFactory.getPGRs().get(sid)).setSupervisorName(s);
		}
		else
			throw new IllegalArgumentException("Only PGR students can have a supervisor.");
	}
	
	/**
	 * Removes a student record associated with the given student ID. Removes the student from the appropriate hashmap which stores 
	 * their data.
	 * @param student type
	 * @param student ID
	 */
	public static void terminateStudent(StudentType studentType, StudentID sid) throws IllegalArgumentException 
	{
		if (!StudentFactory.getUGs().containsKey(sid) || StudentFactory.getPGTs().containsKey(sid) || StudentFactory.getPGRs().containsKey(sid))
		{
			throw new IllegalArgumentException("This student ID is not valid");
		}
		else
		{
		if (studentType == StudentType.UG)
		{
			StudentFactory.getUGs().remove(sid);
		}
		else if (studentType == StudentType.PGT)
		{
			StudentFactory.getPGTs().remove(sid);
		}
		else if (studentType == StudentType.PGR)
		{
			StudentFactory.getPGRs().remove(sid);
		}
		else
			throw new IllegalArgumentException("Students must be UG, PGT or PGR");
		}
	}
	
	/**
	 * Issues a smart card to students
	 * @return smart card 
	 * @param s
	 */
	public static SmartCard getSmartCard(Student s)		
	{
		return s.getSmartCard();
	}
}