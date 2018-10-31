// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

public class Go implements Move {
  private Character c;
  private Place p;
  private String dir;
  
  public Go(Place p, String dir, Character c) {
    this.p = p;
    this.dir = dir;
    this.c = c;
  }

  @Override
  public void execute() {
    //
    System.out.println("*** " + c.name() + " trying to go " + dir.toString() + "\n");
    Place newPlace = this.p.followDirection(this.dir);
    
    // same place
  	if (newPlace.equals(this.p)) {
      System.out.println(c.name() + " stayed in " + newPlace.name()+ "\n");
    }
    // different place
    else {
      // removing that character from the old place
   	  this.p.removeCharacter(c);
   	  System.out.println("*** " + c.name() + " moved from " + this.p.name() + " to " + newPlace.name()+ "\n");
    }

    // updating the character currentPlace Information
    newPlace.addCharacter(this.c);
    this.c.setCurrentPlace(newPlace);
  }  
}