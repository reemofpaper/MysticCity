// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4 

public class InventoryMove extends Move {
	IO print = new IO();
	private Character c;
	public InventoryMove(String command, String argument,Character x) {
		super(command, argument);
		c = x;
	}

	
	@Override
	public boolean execute(){
		if (c.playersArtifacts.size() == 0 ){
			print.display("\n\n" + c.name() + " does not have any artifacts in their inventory...");
		}
		else{
			for(Artifact a: c.playersArtifacts){
				a.print();
			}
		}
		return true;
	}
}