package DataStructure;

import java.util.ArrayList;

public class Implementer implements Controller {
	
	public String listAllTermini()
	{
		Retrieve r = new Retrieve();
		ArrayList<String> retrieve = r.getStations();
		ArrayList<Station> termini = new ArrayList<>();
		String names = "";
		int i = 0;
		while(i <= retrieve.size()){
			
			if(i == 0 || i == retrieve.size()){
				termini.add(new Station(retrieve.get(i)));
				names = names + termini.get(i).getName(); 
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
