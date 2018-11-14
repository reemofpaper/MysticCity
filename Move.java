import java.util.*;
public class Move{
	private String command;
	private String argument;
	
	public Move(String command, String argument){
		this.command = command;
		this.argument=argument;	 
	}
	
	public boolean execute() {
		return false;
	}
}
