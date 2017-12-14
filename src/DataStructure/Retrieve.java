package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Retrieve {

	private ArrayList<String> allTermini;

	private String[] cells;

	private HashMap<String, ArrayList<String>> stations;
	private HashMap<String, ArrayList<String>> linkedStations;

	public Retrieve() {
		allTermini = new ArrayList<>();
		cells = new String[20];
		stations = new HashMap<>();
		linkedStations = new HashMap<>();
		readFile();
		createLinkedStructure();
	}

	public void readFile() {
		ArrayList<String> tempStations = new ArrayList<>();

		String mtrSystemFile = "MTRsystem_partial.csv";
		File file = new File(mtrSystemFile);

		try {
			Scanner inputStream = new Scanner(file);

			while (inputStream.hasNext()) {
				String line = inputStream.nextLine();
				cells = line.split(",");
				int celli = 1;

				while (celli <= cells.length - 1) {
					if (celli == 1 || celli == cells.length - 1) {
						allTermini.add((cells[celli]));
					}

					tempStations.add(cells[celli]);
					celli++;
				}

				addToStations(cells[0], tempStations);
				tempStations.clear();

				cells = null;
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
	}

	public void createLinkedStructure() {
		// already have a hashmap of line with stations in a line
		// to find station links you need to get a station and get the previous and
		// next,
		// considering that there is a previous and next and store that into an empty
		// arraylist (links)
		// then go through the next line and if that station is there do the previous
		// next and add to links
		// if there isn't go to the next line

		// assign new hashmap to the hashmap in Retrieve class (line is key, stations
		// are value)
		for (ArrayList<String> s : stations.values())
		// use entrySet to get a Set view of the mappings contained in the stations
		// hashmap
		// for every key value pair in the stations hashmap...
		{
			ArrayList<String> stationsInLine = s;
			// get the arraylist of stations value and put it into a new arraylist
			// stationsInLine
			int i = 0;
			while(i < stationsInLine.size())
			// go through the arraylist stationsInLine containing the stations from the
			// value of the hashmap
			{
				String currentStation = stationsInLine.get(i);
				// get current station at position i

				ArrayList<String> links;
				// create empty arraylist links for storing relevant stations

				if (linkedStations.containsKey(currentStation))
				// if another line contains the current station
				// lamisha: if the linkedStations hashmap has the current station as the key
				{
					links = linkedStations.get(currentStation);
					// get their links list and add the new link from the other line that links to
					// this station
					// lamisha: assign the current station to the links array list
				} else {
					links = new ArrayList<>();
					// if it is the very first link then create create an arraylist
				}
				
				if (i + 1 < stationsInLine.size()) {
					links.add(stationsInLine.get(i + 1));
					// if it is not the last item, add the next station to the current one
				}
				
				if (i > 0) {
					links.add(stationsInLine.get(i - 1));
					// if it is not the first item add the station previous to the one you are at -
					// this will form a line of stations
				}
				
				// populate the hashmap with station as key and the relevant links arraylist
				// created
				linkedStations.put(currentStation, links);
				i++;
			}

		}
	}

	public void addToStations(String lineName, ArrayList<String> s) {

		stations.put(lineName, new ArrayList<String>(s));
	}

	public HashMap<String, ArrayList<String>> getStations() {
		return stations;
	}
	
	public HashMap<String, ArrayList<String>> getLinkedStations(){
		return linkedStations;
	}

	public ArrayList<String> getAllTermini() {
		return allTermini;
	}

}
