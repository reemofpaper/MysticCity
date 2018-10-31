
//TODO: need to figure out exit for character
//problem: infinite loop when using the go command
//have a static counter that keeps track of the characters
//if a character exits decrement the counter...if character counter is zero then exit the game completely
import java.util.*;
public class UI implements DecisionMaker {

	@Override
	public Move getMove(Character c, Place p) {
		System.out.println(c.name+" is in "+ c.curPlace.name());
		System.out.println("Enter a command for "+c.name()); //ask character to enter a command
		Scanner s = getUserInput.getInstance(); //get the (singleton) scanner
		String text = s.nextLine();
		Scanner line = new Scanner(text); 
		if(line.hasNext()){ //if something was typed in
			
			String inputCommand = line.next();
			
			if(inputCommand.equalsIgnoreCase("go")){ //if the command is go
				if(line.hasNext()){ //if the go command has an argument 
					String arg = line.next(); 
					goMove charMove = new goMove("GO", arg,c ,p); //encapsulate the info in a goMove object
					return charMove;  //return the object
				}
				else return null; //if correct argument isnt given then return null, this will reprompt the user
			}
			
			
			else if(inputCommand.equalsIgnoreCase("get")){
				if(line.hasNext()){ //if the get command has an argument 
					String arg = line.next(); 
					while(line.hasNext()){
						arg = arg +" "+line.next();
					}
					arg = arg.trim();
					System.out.println("artifact user requestd:"+arg);
					getMove charMove = new getMove("get", arg,c,p); //encapsulate the info in a goMove object
					return charMove;  //return the object
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("drop")){ //if the drop command is given
				if(line.hasNext()){
					String arg = line.next(); //check for the argument
					dropMove charMove = new dropMove("drop",arg,c,p); //encapsulate the move
					return charMove; //return the move
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("use")){
				if(line.hasNext()){
					String arg = line.next(); //check for the argument
					useMove charMove = new useMove("use",arg,c,p); //encapsulate the move
					return charMove; //return the move
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("INVE") || inputCommand.equalsIgnoreCase("inventory")){
				inveMove charMove = new inveMove("use","",c); //encapsulate the move
				return charMove; //return the move
				
			}
			else if(inputCommand.equalsIgnoreCase("exit")|| inputCommand.equalsIgnoreCase("quit")){//**NEED TO DO THIS
				quitMove charMove = new quitMove("exit","");
				return charMove;
				
				
			}
			else if(inputCommand.equalsIgnoreCase("look")){
				lookMove charMove = new lookMove("LOOK","",p); //encapsulate the move
				return charMove; //return the move
				
			}
		}
	
		return null; //if a command and an argument is not given then return null
		
		
	}

	
	
	

}
