package DataStructure;

import java.util.LinkedList;


public class MTRLine {
	private String name;
	LinkedList<Station> stations;
	
	public MTRLine(String name) {
		this.name = name;
		stations = new LinkedList<>();
	}
	
	public void addStation(Station s) {
		stations.add(s);
	}
	
	public String getName() {
		return name;
	}
	
	public LinkedList<Station> getStations(){
		return stations;
	}
	
}
