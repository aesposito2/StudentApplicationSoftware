package com.B5015845.CSC8002.Coursework.Testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;

import javax.naming.LimitExceededException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.StudentID;
import com.B5015845.CSC8002.Coursework.StudentType;
import com.B5015845.CSC8002.Coursework.Supervisor;
import com.B5015845.CSC8002.Coursework.Management.StudentManagementSystem;
import com.B5015845.CSC8002.Coursework.Module;
import com.B5015845.CSC8002.Coursework.Name;
import com.B5015845.CSC8002.Coursework.ResearchStudent;
import com.B5015845.CSC8002.Coursework.Student;
import com.B5015845.CSC8002.Coursework.StudentFactory;

/**
 * This test class is StudentManagementSystemTest, designed to test the ability of the methods in the Student Management System.
 * @author Arianna Esposito
 */
public class StudentManagementSystemTest {
	
	Name n;
	Name n2;
	Name n3;
	Name n4;
	Date dob;
	Date dob2;
	StudentType UGType;
	StudentType PGTType;
	StudentType PGRType;
	Student ug;
	Student ug2;
	Student pgt;
	ResearchStudent pgr;
	
	@Before
	public void setUp()
	{
		n = new Name("Frodo","Baggins");
		n2 = new Name("Samwise","Gamgee");
		n3 = new Name("Pippin","Took");
		n4 = new Name("Merry","Brandybuck");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -30);
		dob = calendar.getTime();
		Calendar cal = Calendar.getInstance();
		dob2 = cal.getTime();
		UGType = StudentType.UG;
		PGTType = StudentType.PGT;
		PGRType = StudentType.PGR;
		ug =  StudentFactory.getInstance(n, dob, UGType);
		ug2 = StudentFactory.getInstance(n2, dob2, UGType);
		pgt = StudentFactory.getInstance(n3, dob, PGTType);
		pgr = (ResearchStudent) StudentFactory.getInstance(n4, dob, PGRType);
	}

	@Test
	public void testNoOfStudents() 
	{
		assertEquals(2, StudentFactory.getUGs().size());
		assertEquals(1, StudentFactory.getPGTs().size());
		assertEquals(1, StudentFactory.getPGRs().size());
	}
	
	@Test
	public void testRegisterStudent() 
	{
		Student ug3 = StudentFactory.getInstance(n4, dob, UGType);
		StudentManagementSystem.registerStudent(ug3);
		assertTrue(StudentFactory.getUGs().containsKey(ug3.getStudentID()));
	}

	@Test
	public void testAmendStudentDataModules() throws FileNotFoundException, IllegalArgumentException, LimitExceededException 
	{
		Module m = Module.getInstance("CSC5002");
		StudentManagementSystem.amendStudentData(ug.getStudentID(), m);
		assertTrue(ug.getModuleList().contains(m));	
	}

	/*
	 * Tests that PGR students can't add modules to module list
	 */
	@Test(expected = LimitExceededException.class)
	public void testAmendStudentDataModulesLimitExceededPGR() throws FileNotFoundException, IllegalArgumentException, LimitExceededException 
	{
		Module m = Module.getInstance("CSC5002");
		StudentManagementSystem.amendStudentData(pgr.getStudentID(), m);
	}
	
	@Test
	public void testAmendStudentDataSupervisors() throws FileNotFoundException, IllegalArgumentException 
	{
		Supervisor s = Supervisor.getInstance("Peter Peterson");
		StudentManagementSystem.amendStudentData(pgr.getStudentID(), s);
		assertEquals(s, pgr.getSupervisorName());
	}
	
	/*
	 * Tests that supervisors can't be added to students who aren't PGR
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAmendStudentDataSupervisorsIllegalArgument() throws IllegalArgumentException, FileNotFoundException
	{
		Supervisor s = Supervisor.getInstance("Peter Peterson");
		StudentManagementSystem.amendStudentData(ug.getStudentID(), s);
	}
	
	@Test
	public void testTerminateStudent()
	{
		Student ug3 = StudentFactory.getInstance(n4, dob, UGType);
		StudentManagementSystem.terminateStudent(ug3.getStudentType(), ug3.getStudentID());
		assertTrue(!StudentFactory.getUGs().containsKey(ug3.getStudentID()));
	}
	
	/*
	 * Tests that non-existent students can't be removed from the system
	 */
	@Test(expected=IllegalArgumentException.class)				//throwing AssertionError
	public void testTerminateStudentIllegalArgumentStudentID() throws IllegalArgumentException
	{	
		StudentID sid = StudentID.getInstance();
		StudentManagementSystem.terminateStudent(UGType, sid);
	}
	
	/*
	 * Tests that the student type of a student must be UG, PGT or PGR for termination 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testTerminateStudentIllegalArgumentStudentType() throws IllegalArgumentException
	{	
		StudentType test = StudentType.doNOTUse;
		Student s = StudentFactory.getInstance(n4, dob, test);
		StudentManagementSystem.terminateStudent(test, s.getStudentID());	
	}
	
	@Test
	public void testGetSmartCard()
	{
		assertEquals(ug.getSmartCard(), StudentManagementSystem.getSmartCard(ug));
	}
	
	/*
	 * Tests that is student is not the correct age, a smart card will not be issued
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetSmartCardIllegalArgumentAge() throws IllegalArgumentException
	{
		StudentManagementSystem.getSmartCard(ug2);
	}
	
	@After
    public void tearDown() {
        StudentFactory.getUGs().clear();
        StudentFactory.getPGTs().clear();
        StudentFactory.getPGRs().clear();
    }
	
}
