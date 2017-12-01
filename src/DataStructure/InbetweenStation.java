package DataStructure;

public class InbetweenStation extends Station {

	public InbetweenStation(String name){
		super(name);
		isTerminus = false;
	}
	
	public String getName() {
		
		return name;
	}

	
	public boolean isTerminus() {
		
		return isTerminus;
	}

}
