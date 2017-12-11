package DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;


public class Implementer implements Controller {

	Retrieve r;


	HashMap<String, ArrayList<String>> stations;
	HashMap<String, ArrayList<String>> linkedStations;


	public Implementer() {
		r = new Retrieve();
		stations = new HashMap<>();
		linkedStations = new HashMap<>();
	}
	
	public void listStationLinks() 
	{
		//already have a hashmap of line with stations in a line
		//to find station links you need to get a station and get the previous and next,
		//considering that there is a previous and next and store that into an empty arraylist (links)
		//then go through the next line and if that station is there do the previous next and add to links
		//if there isn't go to the next line
		
		HashMap<String, ArrayList<String>> stations = new HashMap<>();
		//new hashmap stations
		stations = r.getHashMap();
		//assign new hashmap to the hashmap in Retrieve class (line is key, stations are value)
		for (Entry<String, ArrayList<String>> entry : stations.entrySet()) 
			//use entrySet to get a Set view of the mappings contained in the stations hashmap
			//for every key value pair in the stations hashmap...
		{
			ArrayList<String> stationsInLine = entry.getValue();
			//get the arraylist of stations value and put it into a new arraylist stationsInLine 
			
			for(int i = 0; i < stationsInLine.size(); i++) 
				//go through the arraylist stationsInLine containing the stations from the value of the hashmap
			{
				String currentStation = stationsInLine.get(i);
				//get current station at position i 
				
				ArrayList<String> links;
				//create empty arraylist links for storing relevant stations 
				
				if(linkedStations.containsKey(currentStation)) 
				//if another line contains the current station
				//lamisha: if the linkedStations hashmap has the current station as the key
				{
					links = linkedStations.get(currentStation);
					//get their links list and add the new link from the other line that links to this station
					//lamisha: assign the current station to the links array list
				} else 
				{
					links = new ArrayList<>();
					//if it is the very first link then create create an arraylist
				}
				if(i!=0)
				{
					links.add(stationsInLine.get(i-1));
					//if it is not the first item add the station previous to the one you are at - this will form a line of stations
				}
				if(i + 1 != stationsInLine.size()) 
				{
					links.add(stationsInLine.get(i + 1));
					//if it is not the last item, add the next station to the current one
				}
				//populate the hashmap with station as key and the relevant links arraylist created
				linkedStations.put(currentStation, links);
			}
			
		}
	} 

	public String listAllTermini() {

		ArrayList<Station> allTermini = new ArrayList<>();
		allTermini.addAll(r.getAllTermini());
		String terminiToString = "";
		for (Station s : allTermini) {
			terminiToString = terminiToString + s.getName() + ", ";
		}

		return terminiToString;
	}
	

	public String listStationsInLine(String line) {

		
		stations = r.getHashMap();
		String stationsToString = "";

		for (String s : stations.get(line)) {
			stationsToString = stationsToString + s + ", ";
		}

		return stationsToString;
	}
	

	public String listAllDirectlyConnectedLines(String line) {
		String connections = "";
		try {
			stations = r.getHashMap();
			ArrayList<String> linkingLines = new ArrayList<>();
			boolean isLinked;
			connections = "The following lines are connected: ";
			linkingLines = stations.get(line);
			
			for (String key : stations.keySet()) {

				isLinked = !Collections.disjoint(linkingLines, stations.get(key));
				if (isLinked == true && linkingLines != stations.get(key)) {
					connections = connections + key + ", ";
				}
			}
		} catch (Exception e) {
			System.out.println("Please enter a valid line");
		}

		return connections;
	}

	public String showPathBetween(String stationA, String stationB) {
		try {
		listStationLinks();
		//store the stations we want to search in a stack 
		Stack<String> stationsToBeSearched = new Stack<>();
		//store visited nodes which do not lead to the destination in a HashSet
		Set<String> irrelevantStations = new HashSet<>();
		//use hashmap to store the destination station as String key and
		//the path to reach that destination station as an ArrayList of String which is the value
		HashMap<String, ArrayList<String>> pathToDestination = new HashMap<>();
		
		if(linkedStations.containsKey(stationA) && linkedStations.containsKey(stationB))
		//whichever start station (from the stations hashmap) the user enters, put that into the destinationPath hashmap,
		//making stationA (the start station) a key in the hashmap with no path as of yet
		{
			pathToDestination.put(stationA, new ArrayList<String>());
			//add the user's first station into the Stack so we can search its links, being the obvious first station in the path
			stationsToBeSearched.add(stationA);
			while (stationsToBeSearched.isEmpty()==false)
			//whilst the stack stationsToBeSearched  has stations in it
			{
				String currentBeingSearched = stationsToBeSearched.pop();
				if(currentBeingSearched.equals(stationB)==false) 
				{
					for(String s : linkedStations.get(currentBeingSearched))
					{
						if(irrelevantStations.contains(s)) 
						{
							continue;
						}
						if(currentBeingSearched.contains(s)==false)
						{
							ArrayList<String> pathToNext = new ArrayList<>(pathToDestination.get(currentBeingSearched));
							pathToNext.add(currentBeingSearched);
							pathToDestination.put(s, pathToNext);
							stationsToBeSearched.add(s);
						}
					}
				}
				else 
				{
					stationsToBeSearched.clear();
					
				}
				irrelevantStations.add(currentBeingSearched);
			}
			ArrayList<String> finalPath = pathToDestination.get(stationB);
			finalPath.add(stationB);
			return finalPath.toString();
		} else 
		{
			throw new Exception("station entered does not exist");
		}
		
	}
	catch(Exception e) 
	{
		return e.getMessage();
	}

	}
}
