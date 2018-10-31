// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

public class Drop implements Move {
  private Place p;
  private Character c;
  private String artifact_name;

  // constructor
  public Drop(Place p, Character c, String artifact_name) {
    this.p = p;
    this.c = c;
    this.artifact_name = artifact_name;
  }

  @Override
  public void execute() {
    // printing the artifacts with each player
    Artifact a = this.c.hasArtifact(this.artifact_name);
    // player has the artifact
    if ( a != null){
      this.p.addArtifact(a);
      this.c.removeArtifact(a);
      System.out.println(c.name() + " dropped " + this.artifact_name);

    }
    // player doesnt have the artifact
    else {
      System.out.println(c.name() + " does not have " + this.artifact_name);
    }
  }
}
