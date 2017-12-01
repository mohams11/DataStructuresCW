package DataStructure;

public class Station {
	protected String name;
	protected boolean isTerminus;
	
	public Station(String name) 
	{
		this.name = name;
		isTerminus = false;
	}
	
	public String getName(){
		return name;
	}
	
	
	public boolean isTerminus(){
		return isTerminus;
	}
	
	

}
