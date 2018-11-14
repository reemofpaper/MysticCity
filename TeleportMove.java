public class TeleportMove extends Move {
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
      System.out.println(c.name() + " is exiting the game...\n");
      c.curPlace = teleportPlace;
      return false;
    }
    
    // user moved to new location
    Place prevPlace = c.getCurPlace();
    prevPlace.removeCharacter(c); 
    teleportPlace.addCharacter(c);
    c.curPlace = teleportPlace;
    System.out.println(c.name() + " teleported from " + prevPlace.name() + " to " + teleportPlace.name() + "\n");
    return true;
  }
}
