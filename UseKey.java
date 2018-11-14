//Only allows playable characters to use keys

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

        //getting each key by keyPattern
        while(keyPattern() > 1000){
          //check if it's a NPC
          if(c.characterID() < 10){
                System.out.println(c.name() + " is not allowed to use that!");
          }
          else{
                p.useKey(this);
          }
        }
  }

}
