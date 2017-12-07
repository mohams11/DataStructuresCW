package DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Implementer implements Controller {

	Retrieve r;
	HashMap<String, ArrayList<String>> stations;

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

		HashMap<String, ArrayList<String>> stations = new HashMap<>();
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
			connections = "The following lines are connected: ";
			boolean isLinked;
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
