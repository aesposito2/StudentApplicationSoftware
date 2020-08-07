package com.B5015845.CSC8002.Coursework.Testing;



import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.StudentFactory;
import com.B5015845.CSC8002.Coursework.StudentID;

/**
 * This test class is StudentIDTest, designed to test the ability to store and retrieve studentIDs.
 * @author Arianna Esposito
 */
public class StudentIDTest {

	StudentID sid = StudentID.getInstance();
	
	/*
	 * As the letter generated is random, tests that it is not null.
	 */
	@Test
	public void testGetLetter()
	{
		assertNotNull(sid.getLetter());
	}
	
	/*
	 * As the numbers generated are random, tests that random number is within limits of method (4 digit integer).
	 */
	@Test
	public void testGetFourDigit() 
	{
		assertNotNull(sid.getFourDigit());
		assertTrue(sid.getFourDigit() > 0000 && sid.getFourDigit() < 9999 );
	}
	
	 @Test
	 public void testToString()
	 {
	    System.out.println(sid.toString());
	 }
	
	@Test
	public void testGetInstance()
	{
		assertTrue(sid instanceof StudentID);
	}
	
	@After
    public void tearDown() {
        StudentFactory.getUGs().clear();
        StudentFactory.getPGTs().clear();
        StudentFactory.getPGRs().clear();
    }

}
