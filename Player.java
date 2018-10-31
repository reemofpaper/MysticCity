//TODO: decision maker from charater is used to make a  new UI() object?
//need to fix all the constructor to include version number
//also need to add another constructor here **look at the UML diagram
import java.util.*;
public class Player extends Character {
	
	private static double version;

	Player(Scanner s) {
		super(s, version); 
		
	}
	
	Player( int ID, String name, String desc){ //for creating the user
		super( ID,  name, desc);
	}

	@Override
	public void makeMove(){
		//decision = new UI(); --is this what we are suppose to do with teh decision maker?
		/*UI move = new UI(); 
		Move result =move.getMove(this, this.curPlace); //get the move user wants to make ** note this is calling the function getMove no the function
		
		while(result==null ||result.execute()==false ){//if the move is null or encapsulated move is not properly executed
			result =move.getMove(this, this.curPlace); //ask again
		} 
		*/
		
		
		decision = new UI();
		Move result = decision.getMove(this, this.curPlace);
		while(result==null ||result.execute()==false ){//if the move is null or encapsulated move is not properly executed
			
			
			if(this.curPlace.name().equalsIgnoreCase("exit")||this.curPlace.name().equalsIgnoreCase("nowhere")){
				System.out.println(this.name + " is exiting..");
				this.curPlace.removeCharacter(this);
				System.out.println("decrement");
				Game.numPlayerCharacters--; //decrement number of players
				break;
				
			}
			else{
			
			result =decision.getMove(this, this.curPlace); //ask again
			}
			
		}
	}
	
	
}
