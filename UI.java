import java.util.*;
public class UI implements DecisionMaker {

	@Override
	public Move getMove(Character c, Place p) {
		System.out.println(c.name + " is in " + c.curPlace.name());
		System.out.println("Enter a command for " +c.name()); 
		Scanner s = KeyboardScanner.getInstance();
		String text = s.nextLine();
		Scanner line = new Scanner(text); 

		//if something was typed in
		if(line.hasNext()){
			String inputCommand = line.next();
			
			if(inputCommand.equalsIgnoreCase("go")){ 
				if(line.hasNext()){ 
					String arg = line.next(); 
					//encapsulate the info in a goMove object
					return new GoMove("GO", arg,c ,p);
				}
				else return null; //if correct argument isnt given then return null, this will reprompt the user
			}
			
			else if(inputCommand.equalsIgnoreCase("get")){
				if(line.hasNext()){ //if the get command has an argument 
					String arg = line.next(); 
					while(line.hasNext()){
						arg = arg + " " +line.next();
					}
					arg = arg.trim();
					System.out.println("artifact user requestd:"+arg);
					return new GetMove("get", arg,c,p);
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("drop")){ 
				if(line.hasNext()){
					String arg = line.next();
					return new DropMove("drop",arg,c,p);
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("use")){
				if(line.hasNext()){
					String arg = line.next(); //check for the argument
					return new UseMove("use",arg,c,p);
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("INVE") || inputCommand.equalsIgnoreCase("inventory")){
				return new InventoryMove("inventory","",c); 
				
			}
			else if(inputCommand.equalsIgnoreCase("exit")|| inputCommand.equalsIgnoreCase("quit")){//**NEED TO DO THIS
				return new QuitMove("exit","");
				
				
			}
			else if(inputCommand.equalsIgnoreCase("look")){
				return new LookMove("LOOK","",p,c);
			}
		}
		return null; 
	}
}
