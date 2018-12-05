// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

public class TeleportMove extends Move {
	IO print = new IO();
  private Character c;
  private Place teleportPlace;
  private String direction;
  
  TeleportMove(String type, String argument,Character x, Place teleportPlace) {
    super(type, argument);
    this.teleportPlace = teleportPlace; 
    c = x;  
  }

  @Override
  public boolean execute(){
    // player went to "exit"/"nowhere"
    if(teleportPlace.name().equalsIgnoreCase("exit") || teleportPlace.name().equalsIgnoreCase("nowhere")){
      print.display(c.name() + " is exiting the game...\n");
      c.curPlace = teleportPlace;
      return false;
    }
    
    // user moved to new location
    Place prevPlace = c.getCurPlace();
    prevPlace.removeCharacter(c); 
    teleportPlace.addCharacter(c);
    c.curPlace = teleportPlace;
    print.display(c.name() + " teleported from " + prevPlace.name() + " to " + teleportPlace.name() + "\n");
    print.display(c.name() + " teleported from " + prevPlace.name() + " to " + teleportPlace.name() + "\n");
    print.display(c.name() + " received 2 points Congrats ");
    c.addPoints(2);
    return true;
  }
}