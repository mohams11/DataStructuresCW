package DataStructure;

public class Terminus extends Station {

	public Terminus(String name){
		super(name);
	}
	
	public String getName() {
		
		return name;
	}

	
	public boolean isTerminus() {
		
		return true;
	}

}
