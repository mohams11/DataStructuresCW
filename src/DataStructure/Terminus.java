package DataStructure;

public class Terminus extends Station {

	public Terminus(String name){
		super(name);
		isTerminus = true;
	}
	
	public String getName() {
		
		return name;
	}

	
	public boolean isTerminus() {
		
		return isTerminus;
	}

}
