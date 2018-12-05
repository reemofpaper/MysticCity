// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

public class GetMove extends Move{
	private Character c;
	private Place p;
  private String artifact;
	
	
	public GetMove(String command, String argument,Character x, Place y) {
		super(command, argument);
		artifact = argument; //name of the artifact to get
		p = y; //current place for the character
		c=x; //character
	}
	
	@Override
	public boolean execute(){
		if(artifact.length()==0){ //if the character isnt valid or the argument \
			System.out.println ("invalid artifact name");
			return false;
		}

		Artifact a = p.getArtifact(artifact);

		if (a != null){
			this.c.addArtifact(a);
			this.p.removeArtifact(a);
			System.out.println(c.name() + " picked up " + this.artifact);
			if (a.name().equalsIgnoreCase("magic coin")){
				System.out.println("5 points have been awarded to " +c.name() + "for picking up the magic coin!" );
				c.addPoints(5);
			}
			else{
				System.out.println("3 points have been awarded to " +c.name() + "for picking up an artifact!");
				c.addPoints(3);
			}
			return true;
		}
		else {
			// artifact DNE or too heavy
			if (a == null)                System.out.println(this.artifact + " is not in" + this.p.name());
			else if (a.weight() > 0 );    System.out.println(this.artifact + " was too heavy to move");
			return false;
		}
	}
}