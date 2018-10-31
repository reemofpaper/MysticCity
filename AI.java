
//TODO: create two different things AI can do:
//randomly move from room to room
//or pick up an artifact if there is one available in teh room and move it to a random room
//need to make child classes for NPC move "commands"
import java.util.*;
public class AI implements DecisionMaker {

	@Override
	public Move getMove(Character c, Place p) {
		
		Random randNum = new Random();
		//int n = randNum.nextInt(4)+1; 
		int n=1;
		
		if(n==1){
			Direction d =p.getRandDir(); // get a random direction
			c.curPlace =d.follow(); //follow that

		}
		else if(n ==2){
			
		}
		else if(n==3){
			
			
		}
		else{
			System.out.println(c.name()+ " has decided to do nothing");
		}
		

		return null;		
	}
	
	

}
