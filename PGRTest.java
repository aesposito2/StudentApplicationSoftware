package com.B5015845.CSC8002.Coursework.Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Date;

import javax.naming.LimitExceededException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.Module;
import com.B5015845.CSC8002.Coursework.Name;
import com.B5015845.CSC8002.Coursework.ResearchStudent;
import com.B5015845.CSC8002.Coursework.Student;
import com.B5015845.CSC8002.Coursework.StudentFactory;
import com.B5015845.CSC8002.Coursework.StudentID;
import com.B5015845.CSC8002.Coursework.StudentType;
import com.B5015845.CSC8002.Coursework.Supervisor;

/**
 * This test class is PGRTest, designed to test the ability to store and retrieve PGRs.
 * @author Arianna Esposito
 */
public class PGRTest {

	Name n;
	Calendar calendar;
	Calendar cal;
	Date dob;
	Date tooYoung;
	StudentType studentType;
	Student pgr ;
	ResearchStudent badPGR;

	@Before
	public void setUp()
	{
		n = new Name("Ron","Weasley");
		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -20);
		dob = calendar.getTime();
		cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);
		tooYoung = cal.getTime();
		studentType = StudentType.PGR;
		pgr = ((ResearchStudent)StudentFactory.getInstance(n, dob, studentType));
		badPGR = ((ResearchStudent) StudentFactory.getInstance(n, tooYoung, studentType));
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Ron Weasley",pgr.getName().toString());
	}
	
	@Test
	public void testGetDob()
	{
		assertEquals(calendar.getTime(),pgr.getDob());
	}
	
	@Test
	public void testGetStudentID()
	{
		assertTrue(pgr.getStudentID() instanceof StudentID);
	}
	
	 @Test
	 public void testToString()
	 {
	    System.out.println(pgr.toString());
	 }
	
	@Test
	public void testGetStudentType()
	{
		assertEquals(StudentType.PGR,pgr.getStudentType());	
	}
	
	@Test
	public void testGetRequiredCredits()
	{
		assertEquals(0, pgr.getRequiredCredits());
	}
	
	@Test
	public void testGetPassMark()
	{
		assertEquals(0, pgr.getPassMark());
	}
	
	@Test
	public void testGetSupervisorName() throws FileNotFoundException, IllegalArgumentException
	{
		Supervisor s = Supervisor.getInstance("Andy Anderson");
		((ResearchStudent) pgr).setSupervisorName(s);
		assertEquals(s, (((ResearchStudent) pgr).getSupervisorName())); 
	}
	
	@Test
	public void testGetModuleList()
	{
		assertTrue(pgr.getModuleList().isEmpty());
	}
	
	/*
	 * PGR students should not be able to add modules to list.
	 */
	@Test(expected = LimitExceededException.class)
	public void testAddModuleToList() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("CSC5001");
		pgr.addModuleToList(m);
		assertTrue(pgr.getModuleList().contains(m));
	}
	
	@Test
	public void testHasEnoughCredits() throws IllegalArgumentException, LimitExceededException
	{
		assertTrue(pgr.hasEnoughCredits());
	}
	
	/*
	 * PGR students should not take modules. Tests module hasn't been added but PGR still has 'sufficient' credits
	 */
	@Test(expected = LimitExceededException.class)
	public void testHasEnoughCreditsLimitExceededOver() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("TST0003");	//module with 300 credits
		pgr.addModuleToList(m);
		assertTrue(pgr.hasEnoughCredits());
		assertTrue(pgr.getModuleList().isEmpty());
	}
		
	@Test
	public void testHowManyCredits() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		assertEquals(0, pgr.howManyCredits());
	}
	
	@Test
	public void testIsOfAge() 
	{
		assertTrue(pgr.isOfAge());
		assertTrue(!badPGR.isOfAge());
	}
	
	@After
    public void tearDown() {
        StudentFactory.getUGs().clear();
        StudentFactory.getPGTs().clear();
        StudentFactory.getPGRs().clear();
    }
	
}

