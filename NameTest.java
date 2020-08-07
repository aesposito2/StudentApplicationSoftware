package com.B5015845.CSC8002.Coursework.Testing;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.B5015845.CSC8002.Coursework.Name;

/**
 * This test class is NameTest, designed to test the ability to store and retrieve name objects.
 * @author Arianna Esposito
 */

public class NameTest {
	
	
	Name n = new Name("Charles","Dance");

	@Test
	public void testGetFirstName()
	{
		assertEquals("Charles",n.getFirstName());
	}
	
	@Test
	public void testGetLastName()
	{
		assertEquals("Dance",n.getLastName());
	}
	
	/*
	 * Tests that a null pointer exception is thrown when name parameters are null
	 */
	@Test(expected = NullPointerException.class) 
	public void testNullNameParameters() throws NullPointerException
	{
		Name badFirstName = new Name(null, "Stark");
		Name badLastName = new Name("Eddard", null);
		badFirstName.getFirstName();
		badLastName.getLastName();
	}

	 @Test
	 public void testToString()
	 {
	    System.out.println(n.toString());
	 }
	 
	 @Test
	 public void testGetInitials()
	 {
	    assertEquals("CD", n.getInitials());
	 }

}
