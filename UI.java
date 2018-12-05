// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;
public class UI implements DecisionMaker {
	//to print using IO object instead of system
	IO print = new IO();
	
	//to getline from user using IO object instead of using KeyboardScanner
	IO getInput = new IO();
	@Override
	public Move getMove(Character c, Place p) {
		print.display("==============================");
		print.display("Current Player: " + c.name());
		print.display("Current Location: " + p.name());
		print.display("Total Points: " + c.returnPoints());
		print.display("------------------------------");
		p.display();
		print.display("Enter a command for " +c.name() + ":  "); 
		//Scanner s = KeyboardScanner.getInstance();
		//String text = s.nextLine();
		String text = getInput.getLine();
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
				else return null; 
				//if correct argument isnt given then return null, this will reprompt the user
			}


			else if (inputCommand.equalsIgnoreCase("pay")){
				print.display("got to pay function");
				if (line.hasNext()){
					int gateKeeperNum = line.nextInt();
					if (gateKeeperNum <= 0 ) return null;

					String arg = "";
					while(line.hasNext()){
						arg = arg + " " +line.next();
					}

					arg = arg.trim();

					// nothing, artifact name, GK id, c, p

					print.display("right before to pay return");
					return new PayGateKeeperMove("", arg , gateKeeperNum, c, p);
				} else return null;
			}

			else if(inputCommand.equalsIgnoreCase("get")){
				if(line.hasNext()){ //if the get command has an argument 
					String arg = line.next(); 
					while(line.hasNext()){
						arg = arg + " " +line.next();
					}
					arg = arg.trim();
					return new GetMove("get", arg,c,p);
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("drop")){ 
				if(line.hasNext()){
					String arg = ""; //check for the argument
					while(line.hasNext()){
						arg += line.next() + " ";
					}
					arg = arg.trim();
					return new DropMove("drop",arg,c,p);
				}
				else return null;
				
			}
			else if(inputCommand.equalsIgnoreCase("use")){
				if(line.hasNext()){
					String arg = ""; //check for the argument
					while(line.hasNext()){
						arg += line.next() + " ";
					}
					arg = arg.trim();
					return new UseMove("use",arg,c,p);
				}
				else return null;	
			}
			else if(inputCommand.equalsIgnoreCase("inve") || inputCommand.equalsIgnoreCase("inventory")){
				return new InventoryMove("inventory","",c); 
				
			}
			else if(inputCommand.equalsIgnoreCase("exit")|| inputCommand.equalsIgnoreCase("quit")){//**NEED TO DO THIS
				return new QuitMove("exit","");
			}
			else if(inputCommand.equalsIgnoreCase("look")){
				return new LookMove("LOOK","",p,c);
			}
			else if (inputCommand.equalsIgnoreCase("teleport")){
				//the instance of doesnt work
				if (p.canTeleport()){
					String arg = "";
					while(line.hasNext()){
						arg += line.next() + " ";
					}
					arg = arg.trim();
					TeleportationPlace temp = (TeleportationPlace)p;
					Place teleportPlace = temp.returnTeleportationRoom();
					if (arg.equalsIgnoreCase(teleportPlace.name())){
						return new TeleportMove("", "", c, teleportPlace);
					}
					else{
						print.display("There is no portal to " + arg + "...");
					}
				}
				else return null;
			}
			else {
				print.display("invalid move...");
			}
		}
		return null; 
	}
}