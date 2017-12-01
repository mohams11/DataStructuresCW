package DataStructure;

import java.util.ArrayList;

public class Implementer implements Controller {
	private Retrieve r = new Retrieve();
	private ArrayList<Station> retrieve;
	
	public Implementer() {
		retrieve = new ArrayList<>();
		retrieve = r.getStations();
	}
	
	public String listAllTermini()
	{
		ArrayList<Station> termini = new ArrayList<>();
		String names = "";
		boolean isTerminus;
		int i = 0;
		
		while(i <= retrieve.size()-1){
			isTerminus = retrieve.get(i).isTerminus();
			if(isTerminus == true) {
				termini.add(retrieve.get(i));
				names = names + retrieve.get(i).getName() + ", ";
			}
			i++;
		}
			
		
		if(names != null){
			return names;
		}
		return null;
	}

	
	public String listStationsInLine(String line) {
		
		return null;
	}

	
	public String listAllDirectlyConnectedLines(String line) {
		
		return null;
	}

	
	public String showPathBetween(String stationA, String stationB) {
		
		return null;
	}

	
}
