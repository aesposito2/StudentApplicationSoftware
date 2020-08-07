package com.B5015845.CSC8002.Coursework.Testing;


import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.Name;
import com.B5015845.CSC8002.Coursework.Student;
import com.B5015845.CSC8002.Coursework.StudentFactory;
import com.B5015845.CSC8002.Coursework.StudentType;

/**
 * This test class is StudentFactoryTest, designed to test the ability of the factory to create Students.
 * @author Arianna Esposito
 */
public class StudentFactoryTest {

	Name n; 
	Name n2;
	Calendar calendar;  
	Date dob; 
	StudentType st;
	StudentType bad;
	Student s;
	Student ug;
	Student badStudent;

	@Before
	public void setUp()
	{
		n = new Name("Anne","Boleyn");
		n2 = new Name("Henry", "TheEighth");
		calendar = Calendar.getInstance();
		dob = calendar.getTime();
		st = StudentType.UG;
		bad = StudentType.doNOTUse;	
		ug = StudentFactory.getInstance(n2, dob, st);
	}
	
	@Test
	public void testGetInstance()
	{
		s = StudentFactory.getInstance(n, dob, st);
	}
	
	/*
	 * Tests that students must be either UG, PGT or PGR.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceIllegalArgsException() throws IllegalArgumentException 
	{ 
		badStudent = StudentFactory.getInstance(n, dob, bad);
	}
	
	@Test
	public void testGetUGs()
	{
		assertEquals(1, StudentFactory.getUGs().size());
	}
	
	@Test
	public void testGetPGTs()
	{
		assertEquals(0, StudentFactory.getPGTs().size());
	}
	
	@Test
	public void testGetPGRs()
	{
		assertEquals(0, StudentFactory.getPGRs().size());
	}
	
	@After
    public void tearDown() {
        StudentFactory.getUGs().clear();
        StudentFactory.getPGTs().clear();
        StudentFactory.getPGRs().clear();
    }
}
