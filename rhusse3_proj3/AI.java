// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

import java.util.Random;
import java.util.Vector;

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
				int current = 0;
				// for loop to get the artifact in the index of random
				for (Artifact a : userArtifacts){
					// get to that random index in the list of artifacts and make a new move
					if (current == random){
						return new Use(p, c, a.name()); 
					}
					// go to the next artifact
					else current++;
				}
			}
			// does nothing
			else return new Halt();
  	}

  	// going in a random direction
  	else if (randomValue == 1){
			// getting all the directions in that place
			Vector<Direction> dirs = p.returnDirs();
			
			// getting a random numbers between 0 and size - 1
			int random = (new Random()).nextInt(dirs.size());
        Direction dir = dirs.get(random);
        return new Go(p, dir.toString(), c);
  	}

  	// dropping artifact 
  	else if (randomValue == 2){
			// have local user inventory
  		Vector<Artifact> userArtifacts = c.returnUserInventory();
			if (userArtifacts.size() > 0){
				// random int in range of the inventory size
				int random = (new Random()).nextInt(userArtifacts.size());
				int current = 0;
				// loop until we find the random index of artifact
				for (Artifact a : userArtifacts){
					if (current == random){
						return new Drop(p, c, a.name()); 
					}
					else current++;
				}
			}
			// do nothing
			else return new Halt();
		}
  	// only other value it can be is 3
  	// getting an artifact from the current room p
  	else {
			// getting the artifacts in place
  		Vector<Artifact> placeArtifacts = p.returnArtifacts();
			if (placeArtifacts.size() > 0){
				// random in range of the artifact size
				int random = (new Random()).nextInt(placeArtifacts.size());
				int current = 0;
				//iterate until we find that random artifact with the random index
				for (Artifact a : placeArtifacts){
					if (current == random){
						return new Get(p, c, a.name()); 
					}
					else current++;
				}
			}
			// do nothing
			else return new Halt();
  	}
  	// should never execute, but will also do nothing
  	return new Halt();
  } 
}
