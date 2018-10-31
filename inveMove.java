
public class inveMove extends Move {
	private Character c;
	public inveMove(String command, String argument,Character x) {
		super(command, argument);
		c = x;
	}
	
	@Override
	public boolean execute(){
		if(c==null){
			return false;
		}
		for(Artifact a: c.playersArtifacts){
			a.print();
		}
		
		return true;
		
	}

}
