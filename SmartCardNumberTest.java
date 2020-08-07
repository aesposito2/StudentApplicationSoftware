package com.B5015845.CSC8002.Coursework.Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.After;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.Name;
import com.B5015845.CSC8002.Coursework.SmartCardNumber;
import com.B5015845.CSC8002.Coursework.StudentFactory;

/**
 * This test class is SmartCardNumberTest, designed to test the ability to store and retrieve smart card numbers.
 * @author Arianna Esposito
 */
public class SmartCardNumberTest {

	Name n = new Name("Stevie","Wonder");
	SmartCardNumber scn = SmartCardNumber.getInstance(n);
	int cal = Calendar.getInstance().get(Calendar.YEAR);
	
	
	@Test
	public void testGetStudentName()
	{
		assertEquals("Stevie Wonder",scn.getStudentName().toString());
	}
	
	@Test
	public void testGetYear()
	{
		assertEquals(cal,scn.getYear());
	}
	
	/*
	 * As the numbers generated are random, tests that random number is within limits of method (2 digit integer).
	 */
	@Test
	public void testGetRandomNumbers()
	{
		assertTrue(scn.getRandomNumbers() > 00 && scn.getRandomNumbers() < 99 );
	}
	
	@Test
	public void testGetInstance()
	{
		assertTrue(scn instanceof SmartCardNumber);
	}
	
	@After
    public void tearDown() {
        StudentFactory.getUGs().clear();
        StudentFactory.getPGTs().clear();
        StudentFactory.getPGRs().clear();
    }

}
