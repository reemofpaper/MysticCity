import java.util.*;
public class NPC extends Character {

  private static double version;

  public NPC(Scanner s) {
    super(s, version);
  }
  
  @Override
  public void makeMove(){
    Place prevPlace = this.curPlace;
    decision = new AI();
    Move result = decision.getMove(this, this.curPlace);

    if (result == null){
      System.out.println ("This move is not valid...");
    }
    else if(this.curPlace.name().equalsIgnoreCase("exit")||this.curPlace.name().equalsIgnoreCase("nowhere")){       
      this.curPlace = prevPlace; //put npc back to the original place
    }
    else{
      result.execute();
    }
  }
}
