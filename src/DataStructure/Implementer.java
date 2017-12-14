package DataStructure;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * This implementer provides concrete implementations to the methods in the Controller interface
 * 
 * @author Shayan Mohammadi
 * @author Lamisha Haque
 * @author Naeem Dogar
 * 
 * @version 13/12/17
 */

public class Implementer implements Controller {

	private Retrieve r;
	//r variable of type Retrieve class, will be used later to call methods from Retrieve class
	private HashMap<String, ArrayList<String>> stations;
	//stations variable which is a HashMap

	public Implementer() 
	{
		r = new Retrieve();
		//instantiate r variable
		stations = new HashMap<>();
		//instantiate stations HashMap
	}
	
	/**
	 * Lists all termini stations in the MTR network
	 * @return names of all termini stations, i.e. first and last station in a line
	 */
	public String listAllTermini() 
	{
		ArrayList<String> allTermini = new ArrayList<>();
		//create arraylist allTermini to store all termini stations  
		allTermini.addAll(r.getAllTermini());
		//retrieve all of the termini stations from Retrieve class method and add to the arraylist
		String terminiToString = "";
		//create empty string 
		for (String s : allTermini)
		//for every station in allTermini arraylist
		{
			terminiToString = terminiToString + s + ", ";
			//concatenate the stations to the empty string created above
		}
		return terminiToString;
		//print out all termini
	}

	/**
	 * Lists all of the stations in a line in the MTR network
	 * @param line a line in the MTR network
	 * @return names of all stations in a line
	 */
	public String listStationsInLine(String line) 
	{
		stations = r.getStations();
		//assign stations HashMap to the HashMap created in Retrieve class 
		String stationsToString = "";
		//create empty string stationsToString
		for (String s : stations.get(line))
		//for every station get the line
		{
			//concatenate the stations to the empty string created above
			stationsToString = stationsToString + s + ", ";
		}
		return stationsToString;
		//print out stations in a line
	}

	/**
	 * Lists all directly connected lines in the MTR network
	 * @param line a line in the MTR network
	 * @return list of all directly connected lines in the MTR network
	 */
	public String listAllDirectlyConnectedLines(String line) 
	{
		String connections = "";
		//create empty string connections
		try {
			stations = r.getStations();
			//assign stations HashMap to the HashMap created in Retrieve class
			ArrayList<String> linkingLines = new ArrayList<>();
			//create arraylist linkingLines to store linking lines
			boolean isLinked;
			//boolean condition determining if lines are linked
			connections = "The following lines are connected: ";
			//add a message to the empty string created above
			linkingLines = stations.get(line);
			//assign the arraylist to store the lines

			for (String key : stations.keySet())
			//for every key in the stations HashMap store it in a set of keys
			{
				isLinked = !Collections.disjoint(linkingLines, stations.get(key));
				//boolean variable isLinked returns true or false depending on if linking lines array list and the stations HashMap keys (storing lines) contain common stations
				if (isLinked == true && linkingLines != stations.get(key))
				//if there are common stations between the arraylist (storing lines) and HashMap (keys storing lines)
				{
					//concatenate the key to connections
					connections = connections + key + ", ";
				}
			}
		} catch (Exception e) 
		{
			System.out.println("Please enter a valid line");
		}

		return connections;
	}

	/**
	 * Lists a path between two stations in the MTR network
	 * @param stationA a starting point station
	 * @param stationB a destination station
	 * @return a path of stations between the two stations entered by the user
	 */
	public String showPathBetween(String stationA, String stationB) {
		try {
			//create HashMap linkedStations and assign it to the HashMap from Retrieve class, so key is station line and value is arraylist of stations
			HashMap<String, ArrayList<String>> linkedStations = new HashMap<>();
			linkedStations = r.getLinkedStations();
			//create a stack to store the stations we want to perform a bfs type of search on
			Stack<String> stationsToBeSearched = new Stack<>();
			//create a HashSet to store visited nodes which are not on the path to the destination
			Set<String> irrelevantStations = new HashSet<>();
			//create a HashMap pathToDestination which contains the destination station as the key and path of stations as arraylist
			HashMap<String, ArrayList<String>> pathToDestination = new HashMap<>();

			if (linkedStations.containsKey(stationA) && linkedStations.containsKey(stationB))
			//user should enter start and destination stations stored in the linkedStations HashMap 
			{
				pathToDestination.put(stationA, new ArrayList<String>());
				//store the start station as the key of the pathToDestination HashMap and create an empty array list to store the path
				stationsToBeSearched.add(stationA);
				//the first station to be added to the stack should be the start station
				while (stationsToBeSearched.isEmpty() == false)
				//whilst the stack is not empty
				{
					String currentBeingSearched = stationsToBeSearched.pop();
					//pop the station being searched off the stack - essentially means station has been searched
					if (currentBeingSearched.equals(stationB) == false)
					//if the station that has been popped off the stack is not the destination
					{
						for (String s : linkedStations.get(currentBeingSearched))
						//assign the station being searched to s variable 
						{
							if (!irrelevantStations.contains(s))
							//if the current station being searched is relevant to the path
							{
							ArrayList<String> pathToNext = new ArrayList<>(pathToDestination.get(currentBeingSearched));
							//create a new arraylist pathToNext for storing each searched relevant station on the path  
							pathToNext.add(currentBeingSearched);
							//add the station to the arraylist
							pathToDestination.put(s, pathToNext);
							//put the station being searched as the key and the pathToNext arraylist consisting of the searched stations as the value 
							stationsToBeSearched.add(s);
							//add the station the stack
							}
						}
					} else 
					{
						stationsToBeSearched.clear();
						//clear the stack if the destination station has been reached
					}
					irrelevantStations.add(currentBeingSearched);
					//add stations not on the path to the destination station, to the HashSet
				}
				ArrayList<String> finalPath = pathToDestination.get(stationB);
				//assign finalPath arraylist to the destination station from HashMap
				finalPath.add(stationB);
				//add the destination station to the arraylist
				return finalPath.toString();
				//print out the final path
			} else 
			{
				throw new Exception("station entered does not exist or path does not exist");
			}

		} catch (Exception e) 
		{
			return e.getMessage();
		}

	}
}
