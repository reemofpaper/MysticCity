// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

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
				System.out.println(c.name() + " dropped " + a.name() + "and lost 2 points");
				c.removePoints(2);
				return true;
			}
		}

		return false;
	}
}