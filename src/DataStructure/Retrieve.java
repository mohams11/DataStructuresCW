package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Retrieve {
	private MTRLine[] Lines;
	private LinkedList<String> temp;
	
	public Retrieve() {
		temp = new LinkedList<>();
		Lines = new MTRLine[11];
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
				String[] cells = line.split(",");
				int celli = 1;
				
				Lines[linei] = new MTRLine(cells[0]);
				while(celli <= cells.length) {
					
					temp.add(cells[celli]);
					celli++;
				}
				linei++;
				Lines[linei].addStations(temp);
			}
			 inputStream.close();
		    } catch (FileNotFoundException e) { 
		      System.out.println("file not found");
		    }
	}
	
	public LinkedList<String> getStations() {
		temp=null;
		
			temp = Lines[0].getStations();
		
		return temp;
	}

}
