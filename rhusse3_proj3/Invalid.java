// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

public class Invalid implements Move {
  private Character c;

  public Invalid(Character c) {
    this.c = c;
  }

  @Override
  public void execute() {
    System.out.println("*** " +c.name() + " inactive, this command does not exist..."+ "\n");
  }
}