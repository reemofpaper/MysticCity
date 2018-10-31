
public class lookMove extends Move{
	private Place p;
	public lookMove(String command, String argument,Place y) {
		super(command, argument);
		p=y;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean execute(){
		p.display();
		return true;
	}

}
