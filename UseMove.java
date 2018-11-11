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
    
  }
}
