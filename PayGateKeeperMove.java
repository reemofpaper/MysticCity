// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;

public class PayGateKeeperMove extends Move {
	IO print = new IO();
	private Character c;
	private Place p;
	private String artifact;
	private GateKeeper gk;
	
	PayGateKeeperMove(String type, String argument, int gk, Character x, Place y) {
		super(type, argument);
		this.gk = Place.allGateKeepers.get(gk);
		this.artifact = argument;
		this.p = y; 
		this.c = x; 	
	}

	@Override
	public boolean execute(){
		// if the character 
		if(gk == null) {
			print.display ("This is not a valid gatekeeper id... Please try again...");
			return false;
		}

		// gives and artifact
		if(artifact.length() == 0){ 	
			return false;
		}

		//checking if the user has the magic coin
		Boolean hasMagicCoin = false;
		Vector <Artifact> userInventory = c.returnUserInventory();

		for (Artifact a : userInventory){
			if (a.name().equalsIgnoreCase("magic coin"))  hasMagicCoin = true;
		}


		// get all the direction in the users current place
		Vector<Direction> d = p.returnDirs();
		boolean roomMatches = false;

		// making sure that the place in the given direction matches the gatekeeper's place
		for (Direction x : d){
			String possible = p.followDirection(x.name().toString(), hasMagicCoin).name() ;
			String gkRoomName = gk.returnGKPlace().name();
			if (possible.equalsIgnoreCase(gkRoomName)){
				roomMatches = true;
				print.display("there is a gatekeeper in the next room ");
				break;
			}
		}
		
		// room matches the gatekeeper current place 
		if (roomMatches){
			// checking every artifact in the users inventory

			for(Artifact a: c.playersArtifacts){ 
				// checks if the artifact we are trying to pay with is in the user inventory
				if(a.name().equalsIgnoreCase(artifact) || a.name().equalsIgnoreCase("magic coin")){
					// if the value of the artifact is equal or greater than the values required
					if (a.value() >= gk.roomFee() || a.name().equalsIgnoreCase("magic coin")){

						if (a.name().equalsIgnoreCase("magic coin")){
							print.display("GateKeeper #" + gk.keeperID() + "has seen your coin. Pass through at you lesiure...");
							// moving the character from their current room to their new room
							p.removeCharacter(c); 
							gk.returnGKPlace().addCharacter(c);
							c.curPlace = gk.returnGKPlace();
						}

						else {
							// removing the artifact from user inventoy
							c.playersArtifacts.remove(a);

							// throwing artifact in random room
							Place.getRandomPlace().addArtifact(a);
							print.display(c.name() + " paid GateKeeper #" + gk.keeperID() + " with " + a.name());

							// moving the character from their current room to their new room
							p.removeCharacter(c); 
							gk.returnGKPlace().addCharacter(c);
							c.curPlace = gk.returnGKPlace();
						}
						//successfully traded with the user
						return true;
					}
					
					// there is a valid inventory, but its not valuable enough
					else { 
						print.display ("The value of this artifact is too low... GateKeeper #" + gk.keeperID() + 
																" requires artifact of value : " + gk.roomFee());
					}
				}
    	}
    	return false;
		}
	else return false;
	}
}
