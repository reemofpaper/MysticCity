import java.util.*;

public class DrinkPotion extends Move {
    private String artifact;
    private Character c;

    public DrinkPotion(String type, String argument, Character x, Artifact y){
        super(type, argument);
        c = x;
        artifact = argument;
    }

    public boolean execute(){
        if(artifact.length()==0){
                return false;
        }

        for(Artifact a: c.playersArtifacts){
                if(a.artifactID() == 3){
                        c.playersArtifacts.remove(a);
                        //c.description() += "can i do this?";
                        System.out.println(c.name() + " drunk the potion " + a.name());
                        System.out.println("\n***c.name() is feeling a lot happier!***\n");
                        return true;
                }
        }
        return false;
    }
}
