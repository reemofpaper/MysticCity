// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

public class UseMove extends Move {
	IO print = new IO();
  private Character c;
  private Place p;
  private String a;

  public UseMove(String command, String argument, Character x, Place y) {
    super(command, argument);
    c=x;
    p=y;
    a = argument;
  }
  
  @Override
  public boolean execute(){
    if(a.length()==0){
      return false;
    }

    for(Artifact a: c.playersArtifacts){ 
      if(a.name().equalsIgnoreCase(a.name())){
        a.use(c,p);
        print.display(c.name() + " is using " + a.name());
        return true;
      }
    }
    return false;
  }
}
