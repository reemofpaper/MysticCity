public class InventoryMove extends Move {
	private Character c;
	public InventoryMove(String command, String argument,Character x) {
		super(command, argument);
		c = x;
	}
	
	@Override
	public boolean execute(){
		for(Artifact a: c.playersArtifacts){
			a.print();
		}
		return true;
	}
}
