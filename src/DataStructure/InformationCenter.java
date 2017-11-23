package DataStructure;

import java.util.LinkedList;

public class InformationCenter {
	
	static LinkedList<String> stationsToDisplay = new LinkedList<>();
	
	public static void main(String[] args) {
		
		Retrieve r = new Retrieve();
		r.readFile();
		stationsToDisplay = r.getStations();
		
		for(String station : stationsToDisplay) {
			System.out.println(station);
		}
	}

	
}
