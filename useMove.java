
public class useMove extends Move {
	private Character c;
	private Place p;
	private String artifact;

	public useMove(String command, String argument, Character x, Place y) {
		super(command, argument);
		c=x;
		p=y;
		artifact = argument;
	}
	
	@Override
	public boolean execute(){
		
		if(c ==null || artifact.length()==0){ //if the character isnt valid or the argument 
			return false;
		}
		
		for(Artifact a: c.playersArtifacts){ //go through all of the players artifacts
			if(a.name().equalsIgnoreCase(artifact)){ //if the name matches
				a.use(c,p); //use the artifact 
				
				
				return true;
			}
		}
		return false;
	}

}
