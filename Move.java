import java.util.*;
public class Move{

	 private String command;
	 private String argument;

//-------------------------------------------------------------------------------------------------------------------
	 public Move(String command, String argument){
		this.command = command;
		this.argument=argument;
		 
	 }
	 
	public boolean execute() {
		
		return false;
	}
	 
//-------------------------------------------------------------------------------------------------------------------
	
	
	
	 
	
	 
	 //move has an execute method in it
	 //it has child classes of each type of move aka go, use, quit... 

}
