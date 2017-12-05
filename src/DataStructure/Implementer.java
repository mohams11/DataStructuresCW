package DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;


public class Implementer implements Controller {
	
	Retrieve r;
	HashMap<String, ArrayList<String>> stations = new HashMap<>();
	
	public Implementer() {
		r = new Retrieve();
	}
	
	public String listAllTermini()
	{
		
 		ArrayList<Station> allTermini = new ArrayList<>();
 		allTermini.addAll(r.getAllTermini());
 		String terminiToString = "";
 		for(Station s: allTermini) {
			terminiToString = terminiToString + s.getName() + ", ";
		}
 		
 		return terminiToString;
	}

	
	public String listStationsInLine(String line) {
		
		HashMap<String, ArrayList<String>> stations = new HashMap<>();
		stations = r.getHashMap();
		String stationsToString = "";
	
		for(String s: stations.get(line)) {
			stationsToString = stationsToString + s + ", ";
		}
		
		return stationsToString;
	}

	
	public String listAllDirectlyConnectedLines(String line) {
		stations = r.getHashMap();
		ArrayList<String> linkingLines = new ArrayList<>(); 
		ArrayList<String> buffer = new ArrayList<>();
		 String connections = "";
		
		
		
		for(String key : stations.keySet()) {
			
			
			buffer = stations.get(key);
			linkingLines = stations.get(line);
			
			linkingLines.retainAll(buffer);
			
			if(!linkingLines.isEmpty()) {
				connections = connections + key + ", ";  
				
				
			}
		}
		
		
		return connections;
	}

	
	public String showPathBetween(String stationA, String stationB) {
		
		return null;
	}

	
}
