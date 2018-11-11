/*
name: Joshua Horton
netID: jhorto5
*/

public class LookMove extends Move {
  private Character c;
  private Place p;
  
  public LookMove(Place p, Character c){
    this.p = p;
    this.c = c;
  }
  
  @Override
  public void execute(){
    System.out.println(c.name() + " is looking around.\n");
    
    p.display();
    System.out.println("\n");
  }
}
