package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * Retrieve reads information from the MTR csv file and provides useful methods which can be called by methods in implementer
 * 
 * @author Shayan Mohammadi
 * @author Lamisha Haque
 * @author Naeem Dogar
 * 
 * @version 13/12/17
 */

public class Retrieve {

	private ArrayList<String> allTermini;
	//create arraylist variable allTermini

	private String[] cells;
	//create array variable cells

	private HashMap<String, ArrayList<String>> stations;
	//create hashMap variable stations
	
	private HashMap<String, ArrayList<String>> linkedStations;
	//create hashMap variable linkedStations

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
	 * reads information from csv file, splits information into array and array lists data structures for simpler processing
	 */
	public void readFile() 
	{
		ArrayList<String> tempStations = new ArrayList<>();
		//create arraylist local variable tempStations  

		String mtrSystemFile = "MTRsystem_partial.csv";
		//store the csv file provided in a String variable mtrSystemFile
		File file = new File(mtrSystemFile);
		//create file local variable which passes the variable containing the csv file to it

		try 
		{
			Scanner inputStream = new Scanner(file);
			//pass the file through to new scanner object

			while (inputStream.hasNext())
			//so long as the scanner object has another token to read from the csv file
			{
				String line = inputStream.nextLine();
				//store each line from the file into a String variable line
				cells = line.split(",");
				//split each line into cells separated by a comma
				int celli = 1;
				//assign int index to 1, so that only stations are read excluding line name stored at position 0

				while (celli <= cells.length - 1)
				//whilst the index is at the last position of the array
				{
					if (celli == 1 || celli == cells.length - 1)
					//if the index is at the second position in the array or at the last
					{
						allTermini.add((cells[celli]));
						//it is a termini station and should be added to the allTermini arraylist
					}
					tempStations.add(cells[celli]);
					//add everything else to the tempStations arraylist
					celli++;
				}

				addToStations(cells[0], tempStations);
				//add to HashMap 
				tempStations.clear();

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
	 * @return populated linkedStations HashMap
	 */
	public void createLinkedStructure() {
		
		for (ArrayList<String> s : stations.values())
		//for every arraylist s get the arraylist value from the stations HashMap
		{
			ArrayList<String> stationsInLine = s;
			// get the arraylist of stations value and put it into a new arraylist stationsInLine
			int i = 0;
			while(i < stationsInLine.size())
			// go through the arraylist stationsInLine containing the stations from the value of the hashmap
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
	 * @param s an ArrayList of type String
	 * @return populated stations HashMap 
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
	 * Method for getting all termini stations in the MTR network
	 * @return allTermini arraylist
	 */
	public ArrayList<String> getAllTermini() 
	{
		//return allTermini arraylist
		return allTermini;
	}

}
