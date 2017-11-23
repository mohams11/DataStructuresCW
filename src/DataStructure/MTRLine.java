package DataStructure;

import java.util.LinkedList;

public class MTRLine {
	private LinkedList<String> stations;
	private String name;
	
	public MTRLine(String name) {
		stations = new LinkedList<String>();
		this.name = name;
	}
	
	public void addStations(LinkedList<String> stationsParam) {
		stations = stationsParam;
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedList<String> getStations(){
		return stations;
	}
}
