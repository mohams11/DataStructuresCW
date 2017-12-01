package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Retrieve {
	private MTRLine[] Lines;
	private ArrayList<String> temp;
	private String[] cells;
	public Retrieve() {
		temp = new ArrayList<>();
		Lines = new MTRLine[11];
		readFile();
	}
	
	public void readFile() {
		String mtrSystemFile = "MTRsystem_partial.csv";
		File file = new File(mtrSystemFile);
		
		try {
			Scanner inputStream = new Scanner(file);
			int linei = 0;
			
			while(inputStream.hasNext()) 
			{
				temp = null;
				String line = inputStream.nextLine();
				cells = line.split(",");
				int celli = 1;
				
				Lines[linei] = new MTRLine(cells[0]);
				while(celli <= cells.length) {
					
					temp.add(cells[celli]);
					celli++;
				}
				linei++;
			}
			 inputStream.close();
		    } catch (FileNotFoundException e) { 
		      System.out.println("file not found");
		    }
	}
	
	public ArrayList<String> getStations(){
		return temp;
	}
	
}
