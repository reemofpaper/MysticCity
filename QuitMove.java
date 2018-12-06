// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

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
