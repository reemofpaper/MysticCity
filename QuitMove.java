public class QuitMove extends Move {
	public QuitMove(String command, String argument) {
		super(command, argument);
	}
	@Override
	public boolean execute(){
		Game.numPlayerCharacters--;
		return true;
	}
}