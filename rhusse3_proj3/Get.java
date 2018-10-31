// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

public class Get implements Move {
  private Place p;
  private Character c;
  private String artifact_name;

  // constructor
  public Get(Place p, Character c, String artifact_name) {
    this.p = p;
    this.c = c;
    this.artifact_name = artifact_name;
  }

  @Override
  public void execute() {
    // checking if place has artifact
    Artifact a = this.p.getArtifact(this.artifact_name);
    if (a != null){
      // add to user inventory and remove from room
      this.c.addArtifact(a);
      this.p.removeArtifact(a);
      System.out.println(c.name() + " picked up " + this.artifact_name);
    }
    else {
      // artifact DNE or too heavy
      if (a == null)                System.out.println(this.artifact_name + " is not in" + this.p.name());
      else if (a.weight() > 0 );    System.out.println(this.artifact_name + " was too heavy to move");
    }
  }
}
