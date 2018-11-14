// My goal here was to prevent NPCs from using keys to unlock doors and only give that responsibility to playable characters

import java.util.*;

public class UseKey extends Artifact {
  private Character c;
  private Place p;

  //take in Artifact constructor
  public UseKey(Scanner infile, Character x, Place y){
        super(infile);
        c = x;
        p = y;
  }

  @Override
  public void use(Character c, Place p){

        
        while(keyPattern() > 1000){  //in the mysticcity.txt file, each key pattern was symbolized by an int over 1000
                                     //so this was my way of grabbing the three keys when the compiler parses the lines
          
          if(c.characterID() < 10){ //in the mysticcity.txt file, each NPC had a characterID less than 5
                                    //so this was my way of grabbing all of the NPCs when the compiler parses the lines
                
                System.out.println(c.name() + " is not allowed to use that!"); //print out message when they try to use the key
          }
          else{
                p.useKey(this); //if they're not a NPC, let them use the key
          }
        }
  }

}
