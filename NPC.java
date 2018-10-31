import java.util.*;
public class NPC extends Character {

	private static double version;

	NPC(Scanner s) {
		super(s, version);
		
	}
	
	@Override
	public void makeMove(){
		Place prevPlace = this.curPlace;
		decision = new AI();
		Move result = decision.getMove(this, this.curPlace);
			if(this.curPlace.name().equalsIgnoreCase("exit")||this.curPlace.name().equalsIgnoreCase("nowhere")){ //if trying to go to an exist
				this.curPlace = prevPlace; //put npc back to the original place
			}
			else{
				System.out.println(this.name +" is in "+this.curPlace.name());
			}
			
			
		
	}
}
