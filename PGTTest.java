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
import com.B5015845.CSC8002.Coursework.Student;
import com.B5015845.CSC8002.Coursework.StudentFactory;
import com.B5015845.CSC8002.Coursework.StudentID;
import com.B5015845.CSC8002.Coursework.StudentType;

/**
 * This test class is PGTTest, designed to test the ability to store and retrieve PGTs.
 * @author Arianna Esposito
 */
public class PGTTest {

	Name n;
	Calendar calendar;
	Calendar cal;
	Date dob;
	Date tooYoung;
	StudentType studentType;
	Student pgt ;
	Student badPGT;
	
	@Before
	public void setUp()
	{
		n = new Name("Hermione","Granger");
		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -20);
		dob = calendar.getTime();
		cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -5);
		tooYoung = cal.getTime();
		studentType = StudentType.PGT;
		pgt = StudentFactory.getInstance(n, dob, studentType);
		badPGT = StudentFactory.getInstance(n, tooYoung, studentType);
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Hermione Granger",pgt.getName().toString());
	}
	
	@Test
	public void testGetDob()
	{
		assertEquals(calendar.getTime(),pgt.getDob());
	}
	
	@Test
	public void testGetStudentID()
	{
		assertTrue(pgt.getStudentID() instanceof StudentID);	
	}
	
	 @Test
	 public void testToString()
	 {
	    System.out.println(pgt.toString());
	 }
	
	@Test
	public void testGetStudentType()
	{
		assertEquals(StudentType.PGT,pgt.getStudentType());	
	}
	
	@Test
	public void testGetRequiredCredits()
	{
		assertEquals(180, pgt.getRequiredCredits());
	}
	
	@Test
	public void testGetPassMark()
	{
		assertEquals(50, pgt.getPassMark());
	}
	
	@Test
	public void testGetModuleList()
	{
		assertTrue(pgt.getModuleList().isEmpty());
	}
	
	@Test
	public void testAddModuleToList() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("CSC5001");
		pgt.addModuleToList(m);
		assertTrue(pgt.getModuleList().contains(m));
	}
	
	@Test
	public void testHasEnoughCredits() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("TST0002");	//module with 180 credits
		pgt.addModuleToList(m);
		assertTrue(pgt.hasEnoughCredits());
	}
	
	/*
	 * Tests PGT does not add module with too many credits to sustain; tests their rejection of this module into their list.
	 */
	@Test(expected = LimitExceededException.class)
	public void testHasEnoughCreditsLimitExceededOver() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("TST0003");	//module with 300 credits
		pgt.addModuleToList(m);
		assertTrue(pgt.hasEnoughCredits());
		assertTrue(pgt.getModuleList().isEmpty());
	}
	
	/*
	 * Tests that PGT cannot have too few credits.
	 */
	@Test(expected = LimitExceededException.class)
	public void testHasEnoughCreditsLimitExceededUnder() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("CSC5003");	//module with 20 credits
		pgt.addModuleToList(m);
		assertTrue(pgt.hasEnoughCredits());
	}
	
	@Test
	public void testHowManyCredits() throws FileNotFoundException, IllegalArgumentException, LimitExceededException
	{
		Module m = Module.getInstance("CSC5009");	//20 credits
		pgt.addModuleToList(m);
		assertEquals(m.getCredits(), pgt.howManyCredits());
	}
	
	@Test
	public void testIsOfAge() 
	{
		assertTrue(pgt.isOfAge());
		assertTrue(!badPGT.isOfAge());
	}
	
	@After
    public void tearDown() {
        StudentFactory.getUGs().clear();
        StudentFactory.getPGTs().clear();
        StudentFactory.getPGRs().clear();
    }
}

