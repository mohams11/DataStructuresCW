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

	private Retrieve r;
	private HashMap<String, ArrayList<String>> stations;

	public Implementer() {
		r = new Retrieve();
		stations = new HashMap<>();
	}

	public String listAllTermini() {

		ArrayList<String> allTermini = new ArrayList<>();
		allTermini.addAll(r.getAllTermini());
		String terminiToString = "";
		for (String s : allTermini) {
			terminiToString = terminiToString + s + ", ";
		}

		return terminiToString;
	}

	public String listStationsInLine(String line) {

		stations = r.getStations();
		String stationsToString = "";

		for (String s : stations.get(line)) {
			stationsToString = stationsToString + s + ", ";
		}

		return stationsToString;
	}

	public String listAllDirectlyConnectedLines(String line) {
		String connections = "";
		try {
			stations = r.getStations();
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
			HashMap<String, ArrayList<String>> linkedStations = new HashMap<>();
			linkedStations = r.getLinkedStations();
			// store the stations we want to search in a stack
			Stack<String> stationsToBeSearched = new Stack<>();
			// store visited nodes which do not lead to the destination in a HashSet
			Set<String> irrelevantStations = new HashSet<>();
			// use hashmap to store the destination station as String key and
			// the path to reach that destination station as an ArrayList of String which is
			// the value
			HashMap<String, ArrayList<String>> pathToDestination = new HashMap<>();

			if (linkedStations.containsKey(stationA) && linkedStations.containsKey(stationB))
			// whichever start station (from the stations hashmap) the user enters, put that
			// into the destinationPath hashmap,
			// making stationA (the start station) a key in the hashmap with no path as of
			// yet
			{
				pathToDestination.put(stationA, new ArrayList<String>());
				// add the user's first station into the Stack so we can search its links, being
				// the obvious first station in the path
				stationsToBeSearched.add(stationA);
				while (stationsToBeSearched.isEmpty() == false)
				// whilst the stack stationsToBeSearched has stations in it
				{
					String currentBeingSearched = stationsToBeSearched.pop();
					if (currentBeingSearched.equals(stationB) == false) {
						for (String s : linkedStations.get(currentBeingSearched)) {
							if (irrelevantStations.contains(s)) {
								continue;
							}
							if (currentBeingSearched.contains(s) == false) {
								ArrayList<String> pathToNext = new ArrayList<>(
										pathToDestination.get(currentBeingSearched));
								pathToNext.add(currentBeingSearched);
								pathToDestination.put(s, pathToNext);
								stationsToBeSearched.add(s);
							}
						}
					} else {
						stationsToBeSearched.clear();

					}
					irrelevantStations.add(currentBeingSearched);
				}
				ArrayList<String> finalPath = pathToDestination.get(stationB);
				finalPath.add(stationB);
				return finalPath.toString();
			} else {
				throw new Exception("station entered does not exist");
			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}
}
