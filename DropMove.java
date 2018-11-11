public class DropMove extends Move {
	private Character c;
	private Place p;
	private String artifact;

	public DropMove(String command, String argument, Character x, Place y) {
		super(command, argument);
		c=x;
		p=y;
		artifact = argument;
	}
	
	@Override
	public boolean execute(){
		if(artifact.length()==0){ 
			return false;
		}
		for(Artifact a: c.playersArtifacts){
			if(a.name().equalsIgnoreCase(artifact)){ 
				c.playersArtifacts.remove(a);
				p.addArtifact(a);
				return true;
			}
		}
		return false;
	}
}
