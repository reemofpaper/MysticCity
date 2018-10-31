
public class getMove extends Move{
	private Character c;
	private Place p;
	private String artifact;
	public getMove(String command, String argument,Character x, Place y) {
		super(command, argument);
		artifact = argument; //name of the artifact to get
		p = y; //current place for the character
		c=x; //character
	}
	
	@Override
	public boolean execute(){
		if(c ==null || artifact.length()==0){ //if the character isnt valid or the argument 
			return false;
		}
		Artifact a = p.removeArtifactByName(artifact);
		//Artifact a = p.containsArtifact(artifact);
		if(a!=null){//if the artifact is in the place
			c.addArtifact(a); //add it to the players collection
			System.out.println("succesfully picked up the artifact!");
			//Note: containsArtifact(artifact) removes the artifact from the place if it is in the place
			return true;
			
		}
		else{
			System.out.print("could not find the artifact");
			return false;
		}
		
		
	}

}
