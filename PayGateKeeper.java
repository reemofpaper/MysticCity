public class PayGateKeeper extends GoMove {
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
			System.out.println ("This is not a valid gatekeeper id...");
			return false;
		}

		// gives and artifact
		if(artifact.length() == 0){ 	
			return false;
		}

		// get all the direction in the users current place
		Vector<Direction> d = y.returnDirs();

		boolean roomMatches = false;

		for (Direction x : d){
			if (p.followDirection(x.toString()).equals(gk.returnGKPlace())){
				roomMatches = true;
				break;
			}
		}
		
		// room matches the gatekeeper current place
		if (roomMatches){
			
			for(Artifact a: c.playersArtifacts){ 

	      if(a.name().equalsIgnoreCase(artifact){
	       	if (a.value() >= gk.roomFee()){
	       		
	       		// removing the artifact from user inventoy
 						c.playersArtifacts.remove(a);

 						// throwing artifact in random room
						Place.getRandomPlace.addArtifact(a);
						System.out.println(c.name() + " paid GateKeeper #" + gk.keeperID() + " with " + a.name());

						// moving the character from their current room to their new room
						p.removeCharacter(c); 
						gk.returnGKPlace().addCharacter(c);
						c.curPlace = gk.returnGKPlace();
	       		
	       		//successfully traded with the user
	       		return true;
	       	}

	       	System.out.println ("The value of this artifact is too low... GateKeeper #" + gk.keeperID() + 
	       											" requires artifact of value : " + gk.roomFee());
	      }
    	}
    	return false;
		}

	}