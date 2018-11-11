/*
name: Joshua Horton
netID: jhorto5
*/

public class DropMove extends Move {
  private Character c;
  private Place p;
  private String s; //artifact name
  
  public DropMove(Place p, Character c, String s){
    this.p = p;
    this.c = c;
    this.s = s;
  }
  
  @Override
  public void execute(){
    Artifact a = c.has_Artifact(s);
    
    if (a != null){
      p.addArtifact(a);
      c.removeArtifact(a);
      System.out.println(c.name() + " dropped the artifact " + this.s);
    }
    else {
      System.out.println(c.name() + " does not have the artifact " + this.s);
    }
  }
  
}
