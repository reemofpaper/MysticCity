// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;

public class AI implements DecisionMaker {
	@Override
	
  public Move getMove(Character c, Place p) {
		// randomizing the move we do each time
  	int randomValue = (new Random()).nextInt(4);

  	// using a random artifact in user inventory
  	if (randomValue == 0){
			Vector<Artifact> userArtifacts = c.returnUserInventory();
			if (userArtifacts.size() > 0){
				// random int in range of the inventory size
				int random = (new Random()).nextInt(userArtifacts.size());
				return new UseMove("", userArtifacts.get(random).name(), c, p); 
			}
			else return null;
  	}

  	// going in a random direction
  	else if (randomValue == 1){
			// getting all the directions in that place
			Vector<Direction> dirs = p.returnDirs();
			if (dirs.isEmpty()){
				return null;
			}
			// getting a random numbers between 0 and size - 1
			int random = (new Random()).nextInt(dirs.size());
      Direction dir = dirs.get(random);
      return new GoMove("", dir.toString(), c, p);
  	}

  	// dropping artifact 
  	else if (randomValue == 2){
			// have local user inventory
  		Vector<Artifact> userArtifacts = c.returnUserInventory();
			if (userArtifacts.size() > 0){
				int random = (new Random()).nextInt(userArtifacts.size());
				return new DropMove("", userArtifacts.get(random).name(), c , p); 
			}
			else return null;
		}

  	// getting an artifact from the current room p
  	else {
			// getting the artifacts in place
  		Vector<Artifact> placeArtifacts = p.returnArtifacts();
			if (placeArtifacts.size() > 0){
				int random = (new Random()).nextInt(placeArtifacts.size());	
				return new GetMove("", placeArtifacts.get(random).name(), c ,p ); 
			}
			// do nothing
			else return null;
  	}
  }  
}