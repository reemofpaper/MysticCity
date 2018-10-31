// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

import java.util.Vector;

public class Quit implements Move {
    private Character c;

    public Quit(Character c) {
        this.c = c;
    }

    @Override
    public void execute() {
      
      // removing character from game
      Vector<Character> characters = Game.returnAllCharacters();    
      characters.remove(this.c);
      System.out.println("*** " + c.name() + " has quit the game");   

      // counting number of players left in the game
      int numPlayers = 0;
      for (Character c : characters) {
        if (c instanceof Player) {
            numPlayers++;
        }
      }
      // updating num players in the Game
      Game.setPlayers(numPlayers);
    }
  }