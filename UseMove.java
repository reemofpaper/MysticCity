/*
name: Joshua Horton
netID: jhorto5
*/

public class UseMove extends Move {
  private Character c;
  private Place p;
  private String s; //artifact name
  
  public UseMove(Place p, Character c, String s){
    this.c = c;
    this.p = p;
    this.s = s;
  }
  
  @Override 
  public void execute(){
    Artifact a = c.has_Artifact(s);
      
    if (a != null){
      p.useKey(a);
      System.out.println(c.name() + " used artifact " + this.s);
    }
    else {
      System.out.println(c.name() + " does not have artifact " + this.s + ". Sorry");
    }
    
  }
  
}
