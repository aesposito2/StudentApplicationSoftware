package com.B5015845.CSC8002.Coursework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Supervisor class acts as a factory to create unique instances of supervisors that have been read in from a file containing all 
 * the supervisors available. It stores and retrieves information about supervisors.
 * Supervisor format is firstname + " " + second name e.g. "Albus Dumbledore" 
 * @author Arianna Esposito
 */
public final class Supervisor {
	
	private final Name name;
	private static final Map<String, Supervisor> supervisors = new HashMap<String, Supervisor>();
	
	/**
	 * Private constructor to ensure that supervisors are only created from the file containing supervisors with getInstance(). 
	 * This file can be modified to add or remove supervisors as necessary.
	 * @param name of supervisor
	 */
	private Supervisor(Name name) //immutable supervisor
	{	
		this.name = new Name(name.getFirstName(), name.getLastName());
	}
	
	/**
	 * This method acts as a factory to create unique instances of supervisors. It reads data from the file. It checks uniqueness by 
	 * adding supervisors to a hashmap, which does not store duplicates.
	 * This code is adapted from the CSC8002 lecture slides on object factories.
	 * @throws FileNotFoundException
	 */
	private static void readSupervisorNames() throws FileNotFoundException
	{
		Scanner inFile = new Scanner(new FileReader("Supervisors"));
		
		Supervisor s;
		while (inFile.hasNextLine())
		{
			String supervisor = inFile.nextLine();
			String [] splitSupervisor = supervisor.split(" ");
			Name n = new Name(splitSupervisor[0], splitSupervisor[1]);
			s = new Supervisor(n);
			supervisors.put(n.toString(), s);
		}
		inFile.close();
	} 
	
	/**
	 * This method returns supervisors from the supervisor hashmap based on the user's input of the name. If the name is non- 
	 * existent, an error message is passed to the user.
	 * @param name
	 * @return selected supervisor
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 */
	public static Supervisor getInstance(String name) throws FileNotFoundException, IllegalArgumentException
	{
		readSupervisorNames();
		if (supervisors.containsKey(name))
		{
		return supervisors.get(name);
		}
		else throw new IllegalArgumentException("This supervisor does not exist");
	}
	
	/**
	 * Retrieves the supervisor name
	 * @return supervisor name
	 */
	public Name getName() 
	{
		return new Name (name.getFirstName(), name.getLastName());
	}

	/**
	 * @Override
	 * @return the string representation of a supervisor
	 */
	@Override
	public String toString() {
		return name.getFirstName() + " " + name.getLastName(); 
	}
	
	/**
	 * Retrieves the list of supervisors available from the file "Supervisors".
	 * @return list of supervisors, read from file
	 * @throws FileNotFoundException
	 */
	public static Map<String, Supervisor> getListOfSupervisors() throws FileNotFoundException
	{
		readSupervisorNames();
		return supervisors;
	}	
}
