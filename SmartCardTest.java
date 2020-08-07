package com.B5015845.CSC8002.Coursework.Testing;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.Name;
import com.B5015845.CSC8002.Coursework.SmartCard;
import com.B5015845.CSC8002.Coursework.SmartCardNumber;
import com.B5015845.CSC8002.Coursework.Student;
import com.B5015845.CSC8002.Coursework.StudentFactory;
import com.B5015845.CSC8002.Coursework.StudentID;
import com.B5015845.CSC8002.Coursework.StudentType;

/**
 * This test class is SmartCardTest, designed to test the ability to store and retrieve smart cards.
 * @author Arianna Esposito
 */
public class SmartCardTest {

	Name n;
	
	Calendar calendar; 
	Date dob;
	
	SmartCardNumber scn;
	
	Calendar cal;
	Date issueDate;
	
	StudentID sid; 
	StudentType st;
	StudentType badST;
	Student s; 
	Student badStudent;
	SmartCard sc; 

	@Before
	public void setUp()
	{
		n = new Name("James","Blunt");
		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -20);
		dob = calendar.getTime();
		scn = SmartCardNumber.getInstance(n);
		cal = Calendar.getInstance();
		issueDate = cal.getTime();
		sid = StudentID.getInstance();
		st = StudentType.UG;
		s = StudentFactory.getInstance(n, dob, st);
		sc = new SmartCard(n, dob, sid, s.getStudentType());		
	}

	@Test
	public void testGetInstance()
	{
		assertTrue(sc instanceof SmartCard);
	}

	@Test
	public void testGetStudentName()
	{
		assertEquals("James Blunt",sc.getStudentName().toString());
	}
	
	@Test
	public void testGetDob() 
	{
		assertEquals(dob.toString(),sc.getDob().toString());
	}
	
	/*
	 * Smart card number has random 2 digit which cannot be tested. Therefore, tests individual components that can be compared 
	 * (initials and year) and tests 2 digit is in fact a two digit number.
	 */
	@Test
	public void testGetSmartCardNumber()
	{
		assertEquals("JB", scn.getStudentName().getInitials());
		assertEquals(2020, scn.getYear());	
		assertTrue(scn.getRandomNumbers() > 00 && scn.getRandomNumbers() < 99 );
	}
	
	@Test
	public void testGetStudentID() 
	{
		assertEquals(sid.toString(),sc.getStudentID().toString());	
	}
	
	@Test
	public void testGetIssueDate() 
	{
		Calendar c = Calendar.getInstance();
		Date testIssue = c.getTime();
		assertEquals(testIssue.toString(),sc.getIssueDate().toString());	
	}
	
	/*
	 * s is an undergraduate student. Tests that expiry date is 4 years from today
	 */
	@Test
	public void testSetExpiryDate()		
	{
		Calendar addCal = Calendar.getInstance();
		addCal.add(Calendar.YEAR, 4);
		Date expiryDate = addCal.getTime();
		assertEquals(expiryDate.toString(), s.getSmartCard().getExpiryDate().toString());
	}
	
	/*
	 * Tests that illegal argument exception is thrown when an non-existent student type is inputted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetExpiryDateIllegalArgumentException()	throws IllegalArgumentException	
	{
		badST = StudentType.doNOTUse;
		badStudent = StudentFactory.getInstance(n, dob, badST);
	}
		
	@Test
	public void testGetExpiryDate() 
	{
		Calendar addCal = Calendar.getInstance();
		addCal.add(Calendar.YEAR, 4);
		Date expiryDate = addCal.getTime();
		assertEquals(expiryDate.toString(), sc.getExpiryDate().toString());
	}
	
	 @Test
	 public void testToString()
	 {
	    System.out.println(sc.toString());
	 }
	 
	 @After
	 public void tearDown() {
	    StudentFactory.getUGs().clear();
	    StudentFactory.getPGTs().clear();
	    StudentFactory.getPGRs().clear();
	 }
}
