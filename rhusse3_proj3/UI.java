// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

import java.util.Scanner;
import java.util.Arrays;

public class UI implements DecisionMaker {
	@Override
    public Move getMove(Character c, Place p) {
    	// in exit or nowhere
    	if (p.placeId() == 0 || p.placeId() == 1) {
            return new Quit(c);
        }

    	System.out.println("==============================");
			System.out.println("Current Player: " + c.name());
			System.out.println("Current Location: " + p.name());
			System.out.println("------------------------------");
			p.display();
			System.out.println("==============================");

			// opening up user input
			Scanner scan = KeyboardScanner.getKeyboardScanner();
			System.out.println("\nEnter a command: ");
			// spitting the input up
	    String[] command = scan.nextLine().toUpperCase().split(" ");

			// POSSIBLE COMMANDS FOR THE GAME
	    if (command[0].equals("QUIT") || command[0].equals("EXIT")){
	      return new Quit(c);
			}
			// displaying romo information
	    else if (command[0].equals("LOOK")){
				return new Look(p, c);
			}
			// expecting direction after
			// going to a different room
	    else if(command[0].equals("GO")){
	      String dir = command[1];
	      if (dir == "")	return new Invalid(c);
	      else 						return new Go(p, dir, c);
	    }
			// expecting artifact name after get
			// grabbing an artifact in the room
	    else if (command[0].equals("GET")){
	      String artifact_name =  String.join(" ", Arrays.copyOfRange(command, 1, command.length));
	      return new Get(p, c, artifact_name);
	    }
			// expecting artifact name after drop
			// drop artifact in current place
	    else if (command[0].equals("DROP")){
      		String artifact_name =  String.join(" ", Arrays.copyOfRange(command, 1, command.length));
      		return new Drop(p, c, artifact_name);
    	}
			// expecting artifact name after drop
			// try to use the keys
    	else if (command[0].equals("USE")){
	    String artifact_name =  String.join(" ", Arrays.copyOfRange(command, 1, command.length));
	      return new Use(p, c, artifact_name);
			}
			// display artifacts with user
	    else if (command[0].equals("INVENTORY") || command[0].equals("INVE") ){
	      return new Inventory(c);
			}
			// entered a direction instead of Go XXX
	    else if (command.length == 1){
	      String dir = command[0];
	      return new Go(p, dir, c);
			}
			// unknown command
	    else return new Invalid(c);
	}
}