package DataStructure;

import java.util.LinkedList;

public class InformationCenter {
	
	static LinkedList<String> stationsToDisplay = new LinkedList<>();
	
	public static void main(String[] args) {
		Implementer i = new Implementer();
		System.out.println(i.listAllTermini());
		
	}

	
}
