// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

public class Inventory implements Move {
  private Character c;

  public Inventory(Character c) {
    this.c = c;
  }
  
  @Override
  public void execute() {
    // printing the artifacts with each player
    c.printArtifacts();
  }
}
