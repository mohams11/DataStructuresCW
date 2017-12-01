package DataStructure;

public class InbetweenStation extends Station {

	public InbetweenStation(String name){
		super(name);
	}
	
	public String getName() {
		
		return name;
	}

	
	public boolean isTerminus() {
		
		return false;
	}

}
