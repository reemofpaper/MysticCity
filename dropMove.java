
public class dropMove extends Move {
	private Character c;
	private Place p;
	private String artifact;
	public dropMove(String command, String argument, Character x, Place y) {
		super(command, argument);
		c=x;
		p=y;
		artifact = argument;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean execute(){
		if(c ==null || artifact.length()==0){ //if the character isnt valid or the argument 
			return false;
		}
		for(Artifact a: c.playersArtifacts){
			if(a.name().equalsIgnoreCase(artifact)){ //if artifact with that name is found in the players possesion
				c.playersArtifacts.remove(a); //remove the artifact from the players posesssion
				p.addArtifact(a); //add to the current place
				return true;
				
			}
		}
		return false;
		
	}

}
