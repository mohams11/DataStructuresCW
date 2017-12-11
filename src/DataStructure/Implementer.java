package DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Implementer implements Controller {

	Retrieve r;
	HashMap<String, LinkedList<String>> stations;

	public Implementer() {
		r = new Retrieve();
		stations = new HashMap<>();
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
			LinkedList<String> linkingLines = new LinkedList<>();
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

		return null;
	}

}
