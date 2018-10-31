// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

public class Look implements Move {
  private Place p;
  private Character c;

  public Look(Place p, Character c) {
    this.p = p;
    this.c = c;
  }

  @Override
  public void execute() {
    System.out.println("===========================================");
    System.out.println(c.name() + " is looking around in the room");
    System.out.println("===========================================");

    // displays the description, artifacts, and characters in the place
    p.display();
    System.out.println ("\n");
  }
}