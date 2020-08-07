package com.B5015845.CSC8002.Coursework.Testing;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.B5015845.CSC8002.Coursework.Module;

/**
 * This test class is ModuleTest, designed to test the ability to store and retrieve Modules.
 * @author Arianna Esposito
 */
public class ModuleTest {
	
	Module m;
	
	@Before
	public void setUp() throws FileNotFoundException
	{
		m = Module.getInstance("CSC5001");
	}
	
	/*
	 * Tests an illegal argument is passed through parameter
	 */
	@Test(expected=IllegalArgumentException.class)
	public void setUpIllegalArgumentException() throws FileNotFoundException, IllegalArgumentException
	{
		m = Module.getInstance("NonExistentModuleCode");
	}
	
	@Test
	public void testReadModuleList() throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new FileReader("Modules"));
		inFile.close();
	}
	
	/*
	 * Tests the file not found exception
	 */
	@Test(expected=FileNotFoundException.class)
	public void testReadModuleListFileNotFound() throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new FileReader("Test"));
		inFile.close();
	}
	
	@Test
	public void testGetModuleCode() 
	{
		assertEquals("CSC5001", m.getModuleCode());
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("How to: Java",m.getName());
	}
	
	@Test
	public void testGetCredits()
	{
		assertEquals(20,m.getCredits());	
	}
	
	@Test
	public void testGetInstance()
	{
		assertTrue(m instanceof Module);
	}

	@Test
	public void testGetModules() throws FileNotFoundException
	{
		assertTrue(Module.getListedModules().containsKey(m.getModuleCode())); 
	}
}
