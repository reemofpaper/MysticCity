// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

public class LookMove extends Move {
	IO print = new IO();
  private Character c;
  private Place p;
  
  public LookMove(String command, String argument, Place y, Character x) {
    super(command, argument);
    p = y;
    c = x;
  }
  
  @Override
  public boolean execute(){
    print.display(c.name() + " is looking around.\n");
    p.display();
    print.display("\n");
    return true;
  }
}
