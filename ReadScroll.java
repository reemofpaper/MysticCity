import java.util.*;

public class ReadScroll extends Move {
   private Artifact artifacts;

   public ReadScroll(String type, String argument, Artifact a){
        super(type, argument);
        artifacts = a;
   }

   public boolean execute(){

        if(artifacts.artifactID() == 21 || artifacts.artifactID() == 5){
           System.out.println("This text says: ");
           System.out.println(artifacts.description());
           return true;
        }
   }
}
