package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;


public class Retrieve {
	private MTRLine[] Lines;
	private ArrayList<Station> allTermini;
	private String[] cells;

	HashMap<String, ArrayList<String>> details;

	
	public Retrieve() {
		allTermini = new ArrayList<>();
		Lines = new MTRLine[12];
		cells = new String[20];
		details = new HashMap<>();
		readFile();
	}
	
	public void readFile() {
		ArrayList<String> tempStations = new ArrayList<>();
		
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
						tempStations.add(cells[celli]);
						allTermini.add(new Terminus(cells[celli]));
					}
					else {
						tempStations.add(cells[celli]);
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
	
	public void addToHashMap(String lineName, ArrayList<String> s) {
		
		details.put(lineName, new ArrayList<String>(s));
	}
	
	public MTRLine[] getLines() {
		return Lines;
	}
	
	public HashMap<String, ArrayList<String>> getHashMap(){
		return details;
	}
	
	public ArrayList<Station> getAllTermini(){
		return allTermini;
	}
	
}
