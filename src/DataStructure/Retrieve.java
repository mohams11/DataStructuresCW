package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Retrieve information from the MTR csv file, creates a linked structure of stations and provides useful methods which can be called by methods in implementer
 * 
 * @author Shayan Mohammadi
 * @author Lamisha Haque
 * @author Naeem Dogar
 * 
 * @version 13/12/17
 */

public class Retrieve {

	private ArrayList<String> allTermini;
	//create arraylist variable allTermini which will hold all the termini from the from the file

	private String[] cells;
	//create array variable cells which will hold all the elements in the line after they are split

	private HashMap<String, ArrayList<String>> stations;
	//create hashMap variable stations which will hold the line name as the key and a list of the stations as the value
	
	private HashMap<String, ArrayList<String>> linkedStations;
	//create hashMap variable linkedStations which will hold a station as the key and a list of all the stations that connect to it as the value

	public Retrieve() 
	{
		allTermini = new ArrayList<>();
		//instantiate allTermini arraylist
		cells = new String[20];
		//instantiate cells array to hold 20 elements
		stations = new HashMap<>();
		//instantiate stations hashMap
		linkedStations = new HashMap<>();
		//instantiate linkedStations hashMap
		readFile();
		//call method readFile everytime Retrieve objects are constructed
		createLinkedStructure();
		//call method createLinkedStructure everytime Retrieve objects are constructed
	}

	/**
	 * reads information from csv file, splits information into array for simpler processing
	 */
	public void readFile() 
	{
		ArrayList<String> tempStations = new ArrayList<>();
		//create arraylist local variable tempStations which will hold the list of stations temporarily for a specific line to be added to the stations hashmap 

		String mtrSystemFile = "MTRsystem_partial.csv";
		//store the csv file name in  a string
		File file = new File(mtrSystemFile);
		//create a file object for the MTRSystems csv file

		try 
		{
			Scanner inputStream = new Scanner(file);
			//pass the file through to new scanner object to be scanned

			while (inputStream.hasNext())
			//so long as the scanner object has another token to read from the csv file
			{
				String line = inputStream.nextLine();
				//store the next line in the string variable
				cells = line.split(",");
				//split each line into cells separated by a comma
				int celli = 1;
				//assign int index to 1, so that only stations are read excluding line name stored at position 0

				while (celli <= cells.length - 1)
				//whilst the index is less than or equal the last position of the array
				{
					if (celli == 1 || celli == cells.length - 1)
					//if the index is at the second position in the array or at the last
					{
						allTermini.add((cells[celli]));
						//add termini to the ArrayList
					}
					tempStations.add(cells[celli]);
					//add station to the ArrayList
					celli++;
				}

				addToStations(cells[0], tempStations);
				//add the line name and the list of stations for that line to the hashmap
				tempStations.clear();
				//Clear the temporary list for the next line

				cells = null;
			}
			inputStream.close();
		} catch (FileNotFoundException e) 
		{
			System.out.println("file not found");
		}
	}

	/**
	 * creates hashMap linkedStations consisting of stations and their links
	 */
	public void createLinkedStructure() {
		
		for (ArrayList<String> s : stations.values())
		//Check every ArrayList of stations in the stations hashmap
		{
			ArrayList<String> stationsInLine = s;
			// get the arraylist of stations value and put it into a new arraylist stationsInLine
			int i = 0;
			while(i < stationsInLine.size())
			//loop through the list of stations 1 by 1
			{
				String currentStation = stationsInLine.get(i);
				//get current station at position i in the arraylist and assign it to String variable currentStation

				ArrayList<String> links;
				//create empty arraylist links for storing relevant stations

				if (linkedStations.containsKey(currentStation))
				//if the linkedStations hashmap has the current station as the key
				{
					links = linkedStations.get(currentStation);
					//assign the current station to the links array list
				} else 
				{
					links = new ArrayList<>();
					//if it is the very first link then create an new arraylist
				}
				if (i + 1 < stationsInLine.size()) {
					links.add(stationsInLine.get(i + 1));
					//if it is not the last station, add the next station to the current one
				}
				if (i > 0) {
					links.add(stationsInLine.get(i - 1));
					//if it is not the first item add the station previous to the one you are at - this will form a line of stations
				}
				//populate the HashMap with station as key and the relevant links arraylist created
				linkedStations.put(currentStation, links);
				i++;
			}
		}
	}

	/**
	 * Populates stations HashMap by adding line name and relevant stations to each line
	 * @param lineName name of a line in the MTR network
	 * @param s an ArrayList of type String which holds all the station names
	 */
	public void addToStations(String lineName, ArrayList<String> s) 
	{
		//put line name as key and arraylist as value to stations hashMap 
		stations.put(lineName, new ArrayList<String>(s));
	}

	/**
	 * Method for getting the stations HashMap
	 * @return stations HashMap
	 */
	public HashMap<String, ArrayList<String>> getStations() 
	{
		//return stations hashMap
		return stations;
	}
	
	/**
	 * Method for getting the linkedStations HashMap
	 * @return linkedStations HashMap
	 */
	public HashMap<String, ArrayList<String>> getLinkedStations()
	{
		//return linkedStations hashMap
		return linkedStations;
	}

	/**
	 * Method for getting all termini in the MTR network
	 * @return allTermini arraylist
	 */
	public ArrayList<String> getAllTermini() 
	{
		//return allTermini arraylist
		return allTermini;
	}

}
