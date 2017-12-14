package DataStructure;
/**
 * Information center runs the main method creating instances of Implementer and TUI
 * 
 * @author Shayan Mohammadi
 * @author Lamisha Haque
 * @author Naeem Dogar
 *
 * @version 13/12/17
 */
public class InformationCenter 
{
	/**
	 * Runs the main method of the program
	 * @param args
	 */
	public static void main(String[] args)
	//main method which needs to be run
	//creates new instances of Implementer and TUI 
	{
		Implementer i = new Implementer();
		TUI t = new TUI(i);
	}

	
}
