// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

public class Use implements Move {
  private Place p;
  private Character c;
  private String artifact_name;

  // constructor
  public Use(Place p, Character c, String artifact_name) {
    this.p = p;
    this.c = c;
    this.artifact_name = artifact_name;
  }

  @Override
  public void execute() {
    //Check to see if player even has artifact specified
    Artifact a = this.c.hasArtifact(this.artifact_name);
    
    //artifact exists
    if ( a != null){
      this.p.useKey(a);
      System.out.println(c.name() + " used " + this.artifact_name);

    }
    // artifacts DNE
    else {
      System.out.println(c.name() + " does not have " + this.artifact_name);
    }
  }
}
