//TODO: need to take care of exit, if a CHARACTER exits dont close the program
//what to do when a character exits the game?
public class goMove extends Move {

	private Character c;
	private Place p;
	private String direction;
	goMove(String type, String argument,Character x, Place y) {
		super(type, argument); //super class constructor to initialize the type of command and the argument of the command
		direction = argument; //initialize the direction
		p = y; //current place for the character
		c=x; //character
		
	}
	
	
	@Override
	public boolean execute(){
		
		if(c ==null || direction.length()==0){ //if the character isnt valid or the argument 
			
			return false;
		}
		
		Place newPlace = p.followDirection(direction); //go to the new place //**if newPlace == oldPlace then reprompt the user
		
		
			if(newPlace.name().equalsIgnoreCase("exit") || newPlace.name().equalsIgnoreCase("nowhere")){
				
				c.curPlace = newPlace;
				return false;
			}
		
		
		
	
		
		
		if(newPlace.equals(p)){ //if the newPlace is the same as the old place return fasle
			System.out.println("here");
			return false;
		}
		p.removeCharacter(c); //remove character from the old place
		newPlace.addCharacter(c); //add character to the new place
		c.curPlace = newPlace;
		return true; //retrun true for execute
	}

}
