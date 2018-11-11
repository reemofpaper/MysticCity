// public class GetMove extends Move {
//   private Place p;
//   private Character c;
//   private String artifact_name;

//   // constructor
//   public GetMove(Place p, Character c, String artifact_name) {
//     this.p = p;
//     this.c = c;
//     this.artifact_name = artifact_name;
//   }

//   @Override
//   public boolean execute() {
//     // checking if place has artifact
//     if (artifact_name.length() == 0){
//       System.out.println("Artifact Name not provided...\n");
//       return false;
//     }
//     Artifact a = this.p.getArtifact(this.artifact_name);
//     if (a != null){
//       this.c.addArtifact(a);
//       this.p.removeArtifact(a);
//       System.out.println(c.name() + " picked up " + this.artifact_name);
//       return true;
//     }
//     else {
//       // artifact DNE or too heavy
//       if (a == null)                System.out.println(this.artifact_name + " is not in" + this.p.name());
//       else if (a.weight() > 0 );    System.out.println(this.artifact_name + " was too heavy to move");
//       return false;
//     }
//   }
// }



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
		if(artifact.length()==0){ //if the character isnt valid or the argument 
			return false;
		}
		Artifact a = p.getArtifact(artifact);

    if (a != null){
      this.c.addArtifact(a);
      this.p.removeArtifact(a);
      System.out.println(c.name() + " picked up " + this.artifact);
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