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

import com.B5015845.CSC8002.Coursework.Name;
import com.B5015845.CSC8002.Coursework.Student;
import com.B5015845.CSC8002.Coursework.StudentFactory;
import com.B5015845.CSC8002.Coursework.StudentID;
import com.B5015845.CSC8002.Coursework.StudentType;
import com.B5015845.CSC8002.Coursework.Module;

/**
 * This test class is UGTest, designed to test the ability to store and retrieve UGs.
 * @author Arianna Esposito
 */
public class UGTest {
	
	Name n;
	Calendar calendar;
	Calendar cal;
	Date dob;
	Date tooYoung;
	StudentType studentType;
	Student ug;
	Student badUG;
	
	@Before
	public void setUp()
	{
		n = new Name("Harry","Potter");
		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -18);
		dob = calendar.getTime();
		cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);
		tooYoung = cal.getTime();
		studentType = StudentType.UG;
		ug = StudentFactory.getInstance(n, dob, studentType);
		badUG = StudentFactory.getInstance(n, tooYoung, studentType);
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Harry Potter",ug.getName().toString());
	}
	
	@Test
	public void testGetDob()
	{
		assertEquals(calendar.getTime(),ug.getDob());
	}
	
	@Test
	public void testGetStudentID()
	{
		assertTrue(ug.getStudentID() instanceof StudentID);	
	}
	
	 @Test
	 public void testToString()
	 {
	    System.out.println(ug.toString());
	 }
	 
	@Test
	public void testGetStudentType()
	{
		assertEquals(StudentType.UG,ug.getStudentType());	
	}
	
	@Test
	public void testGetRequiredCredits()
	{
		assertEquals(120, ug.getRequiredCredits());
	}
	
	@Test
	public void testGetPassMark()
	{
		assertEquals(40, ug.getPassMark());
	}
	
	@Test
	public void testGetModuleList()
	{
		assertTrue(ug.getModuleList().isEmpty());
	}
	
	@Test
	public void testAddModuleToList() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("CSC5001");
		ug.addModuleToList(m);
		assertTrue(ug.getModuleList().contains(m));
	}
	
	@Test
	public void testHasEnoughCredits() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("TST0001");	//module with 120 credits
		ug.addModuleToList(m);
		assertTrue(ug.hasEnoughCredits());
	}
	
	/*
	 * Tests UG has not got too many credits; tests the rejection of adding a 300 credit module.
	 */
	@Test(expected = LimitExceededException.class)
	public void testHasEnoughCreditsLimitExceededOver() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("TST0003");	//module with 300 credits
		ug.addModuleToList(m);
		assertTrue(ug.hasEnoughCredits());
		assertTrue(ug.getModuleList().isEmpty());
	}
	
	/*
	 * Tests that UG is not allowed to have too few credits.
	 */
	@Test(expected = LimitExceededException.class)
	public void testHasEnoughCreditsLimitExceededUnder() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("CSC5001");	//module with 20 credits
		ug.addModuleToList(m);
		assertTrue(ug.hasEnoughCredits());
	}
	
	@Test
	public void testHowManyCredits() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("CSC5001");	//20 credits
		ug.addModuleToList(m);
		assertEquals(m.getCredits(), ug.howManyCredits());
	}
	
	@Test
	public void testIsOfAge() 
	{
		assertTrue(ug.isOfAge());
		assertTrue(!badUG.isOfAge());
	}
		
	@After
    public void tearDown() {
        StudentFactory.getUGs().clear();
        StudentFactory.getPGTs().clear();
        StudentFactory.getPGRs().clear();
    }
	
}


