package com.B5015845.CSC8002.Coursework.Testing;



import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.Supervisor;

/**
 * This test class is SupervisorTest, designed to test the ability to store and retrieve supervisors.
 * @author Arianna Esposito
 */
public class SupervisorTest {

	Supervisor s;
	
	@Before
	public void setUp() throws FileNotFoundException, IllegalArgumentException
	{
		s = Supervisor.getInstance("Peter Peterson");
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void setUpIllegalArgumentException() throws FileNotFoundException, IllegalArgumentException
	{
		s = Supervisor.getInstance("Gandalf The Grey");
	}
	
	@Test
	public void testReadSupervisorNames() throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new FileReader("Supervisors"));
		inFile.close();
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testReadSupervisorNamesFileNotFound() throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new FileReader("Test"));
		inFile.close();
	}

	@Test
	public void testGetName()
	{
		assertEquals("Peter Peterson", s.getName().toString());	//tests against toString so it's the same object, as the defensive copying creates reference to new object so fails test
	}
		
	 @Test
	 public void testToString()
	 {
	    System.out.println(s.toString());
	 }

}

//works!