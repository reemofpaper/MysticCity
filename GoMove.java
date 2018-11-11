/*
name: Joshua Horton
netID; jhorto5
*/

public class GoMove extends Move {
   private Character c;
   private Place p;
   private String direction;
   
   public GoMove(Place p, String direction, Character c){
    this.p = p;
    this.direction = direction;
    this.c = c;
   }
   
   @Override
   public void execute(){
    Place newplace = p.followDirection(direction);
    
  if (newplace.equals(this.p)) {
      System.out.println(c.name() + " is in " + newplace.name()+ "\n");
    }
    else {
   	  p.removeCharacter(c);
   	  System.out.println(c.name() + " moved from " + p.name() + " to " + newPlace.name() + "\n");
    }
    
    newPlace.addCharacter(c);
    c.setCurrentPlace(newPlace);
   }
}
