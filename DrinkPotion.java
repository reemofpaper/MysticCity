//My goal here was to simulate a character drinking a potion and what happens to the character. The potion would make the character feel 
//happier.

import java.util.*;

public class DrinkPotion extends Move {
    private String artifact;
    private Character c;
    
    //take in the Move constructor with super
    public DrinkPotion(String type, String argument, Character x, Artifact y){
        super(type, argument);
        c = x;
        artifact = argument;
    }

    public boolean execute(){
        if(artifact.length()==0){ //if no artifacts are present, return false and we're done
                return false;
        }

        for(Artifact a: c.playersArtifacts){ // loop to look through a characters artifacts
                //the potions ID was 3. If it is present then continue
                if(a.artifactID() == 3){ 
                        //remove the artifact from the character (since he drunk it)
                        c.playersArtifacts.remove(a); 
                        //print out statement saying the character is feeling happier
                        System.out.println(c.name() + " drunk the potion " + a.name());
                        System.out.println("\n***c.name() is feeling a lot happier!***\n"); 
                        //return successfully
                        return true;
                }
        }
        //if characters have artifacts and potions are not in there, then return false
        return false;
    }
}
