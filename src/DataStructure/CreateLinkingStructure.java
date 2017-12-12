package DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class CreateLinkingStructure {
	
	Retrieve r;
	HashMap<String, ArrayList<String>> stations;
	HashMap<String, ArrayList<String>> linkedStations;
	
	public CreateLinkingStructure() 
	{
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

}
