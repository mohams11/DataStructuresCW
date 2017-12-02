package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;


public class Retrieve {
	private MTRLine[] Lines;
	private ArrayList<Station> allTermini;
	private String[] cells;
	
	HashMap<String, LinkedList<Station>> details;
	public Retrieve() {
		allTermini = new ArrayList<>();
		Lines = new MTRLine[12];
		cells = new String[20];
		details = new HashMap<>();
		readFile();
	}
	
	public void readFile() {
		LinkedList<Station> tempStations = new LinkedList<>();
		
		String mtrSystemFile = "MTRsystem_partial.csv";
		File file = new File(mtrSystemFile);
		
		try {
			Scanner inputStream = new Scanner(file);
			
			while(inputStream.hasNext()) 
			{
				String line = inputStream.nextLine();
				cells = line.split(",");
				int celli = 1;
				
				
				while(celli <= cells.length - 1)
				{
					if(celli == 1 || celli == cells.length - 1) {
						tempStations.add(new Terminus(cells[celli]));
						allTermini.add(new Terminus(cells[celli]));
					}
					else {
						tempStations.add(new InbetweenStation(cells[celli]));
					}
					
					celli++;
				}
				
				addToHashMap(cells[0] ,tempStations);
				tempStations.clear();
		
				
				
				cells=null;
			}
			 inputStream.close();
		    } catch (FileNotFoundException e) { 
		      System.out.println("file not found");
		    }
	}
	
	public void addToHashMap(String lineName, LinkedList<Station> s) {
		
		details.put(lineName, new LinkedList<Station>(s));
	}
	
	public MTRLine[] getLines() {
		return Lines;
	}
	
	public HashMap<String, LinkedList<Station>> getHashMap(){
		return details;
	}
	
	public ArrayList<Station> getAllTermini(){
		return allTermini;
	}
	
}
