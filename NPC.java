// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;
public class NPC extends Character {
	IO print = new IO();
  private static double version;

  public NPC(Scanner s) {
    super(s, version);
  }
  
  public NPC(int id, String name, String desc ){
    super(id, name, desc);
  }
  
  @Override
  public void makeMove(){
    Place prevPlace = this.curPlace;
    decision = new AI();
    Move result = decision.getMove(this, this.curPlace);

    if (result == null){
      print.display ("This move is not valid...");
    }
    else if(this.curPlace.name().equalsIgnoreCase("exit")||this.curPlace.name().equalsIgnoreCase("nowhere")){       
      this.curPlace = prevPlace; //put npc back to the original place
    }
    else{
      result.execute();
    }
  }
}