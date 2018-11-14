//My goal here was to have an option to read out (basically print out the description) for the scroll and the hitchhiker's guide
import java.util.*;

public class ReadScroll extends Move {
   private Artifact artifacts;

   //take in the Move constructor with super
   public ReadScroll(String type, String argument, Artifact a){
        super(type, argument);
        artifacts = a;
   }

   public boolean execute(){

        if(artifacts.artifactID() == 21 || artifacts.artifactID() == 5){//the hitchhiker's guide had an artifact ID of 21
                                                                        //and the scroll was 5. My way of trying to grab each artifact
                                                                        //in particular by listing out the ID when the compiler is 
                                                                        //parsing each line
           System.out.println("This text says: "); 
           System.out.println(artifacts.description()); //prints out description of either the guide or scroll
           return true;
        }

	      return false;//if "if statement" isn't met then return false to leave?
   }
}
