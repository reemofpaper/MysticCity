// // Name : Reem Hussein
// // Netid: rhusse3
// // CS account : rhussein
// // CS342 Project 3

import java.io.*; 
import java.io.File; 
import java.util.Scanner; 

public class GameTester{
  //no access to anything declared outside of its function
  public static void main(String[] args) {
    System.out.println("Name: Reem Hussein");
    System.out.println("ACCC Account Name: rhussein");

    //Ask user for gdf file to read from
    System.out.print("Enter file name: ");
    Scanner sc = KeyboardScanner.getKeyboardScanner();
    File file = new File(sc.nextLine());

    // keep asking for a file until they get one that exists
    while (!file.exists()) {
      System.out.print("Invalid filename. Please try a different one: ");
      file = new File(sc.nextLine());
    }

    // checking if we can open the file with the scanner
    try {
      sc = new Scanner(file);
    } 
    catch (FileNotFoundException e) {
      System.out.println("This file does not exist..");
    }

    // open out game and play
    Game testGame = new Game(sc);
    testGame.play();
    sc.close();
  }
}