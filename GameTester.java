import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameTester {
  public static void main(String arg[]){
    System.out.println("Maleeha Ahmed, netid: mahmed58");
    System.out.println("Johsua Horton, netid: jhorto5");
    System.out.println("Reem Hussein,  netid: rhusse3");

    String filename = "mysticcity.txt";
    File file = new File(filename);
    
    try {
      Scanner sc = new Scanner(file);
      // open out game and play
      Game testGame = new Game(sc);
      System.out.println("Starting Game...");
      testGame.play();
      sc.close();
    } 
    catch (FileNotFoundException e) {
      System.out.println("This file does not exist..");
    }

  }
}