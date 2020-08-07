package com.B5015845.CSC8002.Coursework;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * This class is a factory to create unique instances of different types of students.
 * This code is adapted from the CSC8002 lecture slides on object factories. 
 * @author Arianna Esposito
 *
 */
public final class StudentFactory {

	private static final Map<StudentID, Student> UG_STUDENTS = new HashMap<StudentID, Student>();
	private static final Map<StudentID, Student> PGT_STUDENTS = new HashMap<StudentID, Student>();
	private static final Map<StudentID, Student> PGR_STUDENTS = new HashMap<StudentID, Student>();
	
	/**
	 * This method is the only way to create students that are registered in the university system.
	 * @param studentName
	 * @param dob
	 * @param studentType
	 * @return unique instance of a Student
	 */
		public final static Student getInstance(Name studentName, Date dob, StudentType studentType)
		{	
			Student s;
			ResearchStudent rs;
			
			StudentType st = studentType;
			switch (st) {
			
			case UG: studentType = StudentType.UG;
			s = new UG(studentName, dob);
			UG_STUDENTS.put(s.getStudentID(), s);
			return s;
			
			case PGT: studentType = StudentType.PGT;
			s = new PGT(studentName, dob);
			PGT_STUDENTS.put(s.getStudentID(), s);
			return s;
			
			case PGR: studentType = StudentType.PGR;
				Supervisor sup = null;
				rs = new PGR(studentName, dob, sup);
			PGR_STUDENTS.put(rs.getStudentID(), rs);
			return rs;
			
			default:
				throw new IllegalArgumentException("Students can only be UG, PGT or PGR");
			}
		}	
		
		/**
		 * Retrieves all undergraduate students in the system
		 * @return all undergraduate students
		 */
		public static Map<StudentID, Student> getUGs()
		{
			return UG_STUDENTS;
		}
		
		/**
		 * Retrieves all postgraduate taught students in the system
		 * @return all postgraduate taught students
		 */
		public static Map<StudentID, Student> getPGTs()
		{
			return PGT_STUDENTS;
		}
		
		/**
		 * Retrieves all postgraduate research students in the system
		 * @return all postgraduate research students
		 */
		public static Map<StudentID, Student> getPGRs()
		{
			return PGR_STUDENTS;
		}
}
