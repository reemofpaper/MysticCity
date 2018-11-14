import java.util.*;
public class GateKeeper extends NPC {
	private static double version;
	private int roomFee;
	private Place currPlace;
	private int keeperID;

  public GateKeeper(int id, Place currPlace) {
  	String desc = "GateKeeper #" + id;
    super(id, String.valueOf(id), desc);
    // gets a random number from 0 to 100
    this.currPlace = currPlace;
  	this.roomFee = new Random().nextInt(100);
  	this.keeperID = id;
  	System.out.println("Room Fee for GateKeeper #" + id + "  is " + this.roomFee );
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