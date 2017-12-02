package DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Implementer implements Controller {
	
	Retrieve r;
	
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
		
		HashMap<String, LinkedList<Station>> stations = new HashMap<>();
		stations = r.getHashMap();
		String stationsToString = "";
	
		for(Station s: stations.get(line)) {
			stationsToString = stationsToString + s.getName() + ", ";
		}
		
		return stationsToString;
	}

	
	public String listAllDirectlyConnectedLines(String line) {
		
		return null;
	}

	
	public String showPathBetween(String stationA, String stationB) {
		
		return null;
	}

	
}
