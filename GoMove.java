public class GoMove extends Move {
	private Character c;
	private Place p;
	private String direction;
	
	GoMove(String type, String argument,Character x, Place y) {
		super(type, argument);
		direction = argument;
		p = y; 
		c = x; 	
	}
	
	@Override
	public boolean execute(){
		if(direction.length() == 0){ 	
			return false;
		}
		
		Place newPlace = p.followDirection(direction);

		// player went to "exit"/"nowhere"
		if(newPlace.name().equalsIgnoreCase("exit") || newPlace.name().equalsIgnoreCase("nowhere")){
      		System.out.println(c.name() + " is exiting the game...\n");
			c.curPlace = newPlace;
			return false;
		}

		// stayed in same place
		if(newPlace.equals(p)){
      		System.out.println(c.name() + " stayed in " + newPlace.name()+ "\n"); 	
			return false;
		}
		else {
			// has a gate keeper
			if (newPlace.hasGateKeeper() == true){
				System.out.println("This room has a gate keeper... GateKeeper # " + newPlace.returnGateKeeper().keeperID() +
				" requires an artifact of at least value of : " 
									+ newPlace.returnGateKeeper().roomFee());
				return false;
			}	
			// no gatekeeper. free to go into the room
			else{
				// user moved to new location
				p.removeCharacter(c); 
				newPlace.addCharacter(c);
				c.curPlace = newPlace;
				System.out.println(c.name() + " moved from " + p.name() + " to " + newPlace.name() + "\n");
				return true;
			}
		}
	}

}
