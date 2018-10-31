
public class quitMove extends Move {

	public quitMove(String command, String argument) {
		super(command, argument);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean execute(){
		Game.numPlayerCharacters--;
		return true;
	}
}
