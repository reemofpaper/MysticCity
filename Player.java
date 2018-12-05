// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;
public class Player extends Character {
	IO print = new IO();
	private static double version;
	Player(Scanner s) {
		super(s, version); 		
	}
	
	Player( int ID, String name, String desc){ //for creating the user
		super( ID,  name, desc);
	}

	@Override
	public void makeMove(){
		decision = new UI();
		Move result = decision.getMove(this, this.curPlace);
		while(result==null || result.execute()==false ){//if the move is null or encapsulated move is not properly executed
			if(this.curPlace.name().equalsIgnoreCase("exit")||this.curPlace.name().equalsIgnoreCase("nowhere")){
				print.display(this.name + " is exiting..");
				this.curPlace.removeCharacter(this);
				Game.numPlayerCharacters--;
				break;
			}
			else{
				result = decision.getMove(this, this.curPlace); //ask again
			}
		}
	}	
}