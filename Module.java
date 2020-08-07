package com.B5015845.CSC8002.Coursework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Module class acts as a factory to create unique instances of modules that have been read in from a file containing all the 
 * modules available. It stores and retrieves information about modules.
 * A module format is module code, module name and credits, all separated by a comma e.g. "CSC8001, Advanced Programming, 20". 
 * @author Arianna Esposito
 */
public final class Module {

	private final String moduleCode;
	private final String name;
	private final int credits;
	private static final Map<String, Module> modules = new HashMap<String, Module>();
	
	/**
	 * Private constructor to ensure that modules are only created from the file containing modules with getInstance(). 
	 * This file can be modified to add or remove modules as necessary.
	 * @param moduleCode
	 * @param name of module
	 * @param credits
	 */
	private Module (String moduleCode, String name, int credits)
	{
		this.moduleCode=moduleCode;
		this.name=name;
		this.credits=credits;
	}

	/**
	 * This method acts as a factory to create unique instances of modules. It reads data from the file, separating the parameters
	 * of a module and storing them separately. It checks uniqueness by adding modules to a hashmap, which does not store duplicates.
	 * This code is adapted from the CSC8002 lecture slides on object factories.
	 * @throws FileNotFoundException
	 */
	private static void readModuleList() throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new FileReader("Modules"));
		
		Module m;
		while (inFile.hasNextLine()) 
		{
			String module = inFile.nextLine();
			String [] separateModule = module.split(", ");
			m = new Module(separateModule[0], separateModule[1], Integer.parseInt(separateModule[2]));
			modules.put(separateModule[0], m);
		}
		inFile.close();
	}

	/**
	 * This method returns modules from the module hashmap based on the user's input of the module code. If the module code is non- 
	 * existent, an error message is passed to the user.
	 * @param moduleCode
	 * @return selected module
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	public static Module getInstance(String moduleCode) throws FileNotFoundException, IllegalArgumentException
	{
		readModuleList();
		if (modules.containsKey(moduleCode))
		{
		return modules.get(moduleCode);
		}
		else throw new IllegalArgumentException("This module does not exist");
	}
	
	/**
	 * Retrieves the module code
	 * @return module code
	 */
	public String getModuleCode() 
	{
		return moduleCode;
	}

	/**
	 * Retrieves the module name
	 * @return module name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Retrieves the module credits
	 * @return credits
	 */
	public int getCredits() 
	{
		return credits;
	}

	/**
	 * @Override
	 * @return string representation of modules when printed
	 */
	@Override
	public String toString() 
	{
		return moduleCode + ", " + name + ", " + credits;
	}
				
	/**
	 * Retrieves the list of modules available from the file "Modules".
	 * @return list of modules, read from file
	 * @throws FileNotFoundException
	 */
	public static Map<String, Module> getListedModules() throws FileNotFoundException
	{
		readModuleList();
		return modules;
	}
}

