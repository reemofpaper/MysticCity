import java.util.*;
public class GateKeeper extends NPC {
	private static double version;
	private int roomFee;
	private Place currPlace;
	private int keeperID;


  public GateKeeper(int id, Place currPlace) {
    super(id, ("GateKeeper #" + String.valueOf(id)), ("GateKeeper #" + id));
    // gets a random number from 0 to 100
    this.currPlace = currPlace;
  	this.roomFee = 10;
  	this.keeperID = id;
  }

  public Place returnGKPlace(){
  	return currPlace;
  }

  public int roomFee(){
  	return roomFee;
  }

  public int keeperID(){
  	return keeperID;
  }
  
  @Override
  // prompt user for artifact 
  // move trade? 
  public void makeMove(){
  }

}