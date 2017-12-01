package DataStructure;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class Retrieve {
	private MTRLine[] Lines;
	private ArrayList<Station> temp;
	private String[] cells;
	public Retrieve() {
		temp = new ArrayList<>();
		Lines = new MTRLine[20];
		cells = new String[20];
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
				if(linei == 11) {
					System.out.println("hi");
				}
				String line = inputStream.nextLine();
				cells = line.split(",");
				int celli = 1;
				
				Lines[linei] = new MTRLine(cells[0]);
				while(celli <= cells.length - 1) {
					String j = cells[celli];
					temp.add(new Station(j));
					celli++;
				}
				linei++;
				cells=null;
			}
			 inputStream.close();
		    } catch (FileNotFoundException e) { 
		      System.out.println("file not found");
		    }
	}
	
	public ArrayList<Station> getStations(){
		return temp;
	}
	
}
