// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 2

import java.util.Vector;
import java.util.Scanner;
import java.util.Arrays;

public class Game {
  //private members
  private String name;
  private static Vector<Character> allCharacters = new Vector<Character>();
  private static int totalInUsePlayers = 0;
  private static int firstPlaceAdded = 0;

  public static void changeFirstPlaceAdded(int x){
    firstPlaceAdded = x;
  }
  public static int returnFirstPlaceAdded(){
    return firstPlaceAdded;
  }
  public static Vector<Character> returnAllCharacters(){
    return allCharacters;
  }

  public static void setPlayers(int x){
    totalInUsePlayers = x;
  }
  
  // creating instances of the place with the scanner
  private void createPlaces(Scanner scan, int numPlaces, float version){
    // read the input from scanner for numPlaces places.
    for(int i =0; i<numPlaces; i++){
      new Place(scan, version);
    }
    // adding the exit and the now where place using the old constructor
    new Place(1, "Exit", "this is the exit");
    new Place(0, "No Where", "this is nowhere");
  }

  // creating instances of the direction with the scanner
  private void createDirections(Scanner scan, int numDirections, float version){
    // read the input from scanner for numDirections places.
    for(int i =0; i<numDirections; i++){
      new Direction(scan, version);
    }        
  }

  // creating instances of the character with the scanner
  private void createCharacters(Scanner scan, int numCharacters, float version){
    // read the input from scanner for numCharacters characters.
    for(int i =0; i<numCharacters; i++){
      String type = scan.next().toUpperCase();

      if (type.equalsIgnoreCase("PLAYER")){
        allCharacters.add(new Player(scan, version));
        totalInUsePlayers++;
      }
      else if (type.equalsIgnoreCase("NPC")){
        allCharacters.add(new NPC(scan, version));   
      }
      else {
        Character c = new Character(scan, version);
        allCharacters.add(new Character(scan, version));
      }
    }
    if (totalInUsePlayers == 0){
      System.out.print("There are no players. Please enter number of players for this game: ");
      Scanner line = KeyboardScanner.getKeyboardScanner();
      int players = Integer.parseInt(line.nextLine());
      for (int i = 1; i <= players; i++) {
        System.out.print("Enter Player " + i + " name: ");
        String name = line.nextLine();
        System.out.print("Enter Player " + i + " Description: ");
        String description = line.nextLine();

        int charID = 0;
        // checking to see if the character id is not being used
        while(true){
          for (Character c : allCharacters){
            if (c.id() == charID){
              charID++;
            }
          }
          break;
        }
        // public Player (int id, String name, String description, int placeID) 
        Player p = new Player(charID, name, description, firstPlaceAdded);
        allCharacters.add(p);
      }
    }
  }

  // creating instances of the artifacts with the scanner
  private void createArtifacts(Scanner scan, int numArtifacts, float version){
    // read the input from scanner for numArtifacts places.
    for(int i =0; i<numArtifacts; i++){
      int charOrPlaceId = scan.nextInt();
      Artifact a = new Artifact(scan,version);
      // = 0 to put the artifact in a random Place
      if (charOrPlaceId == 0){
        Place.getRandomPlace().addArtifact(a);
      }
      // < 0 for a character’s possessions. ( Character ID is the positive value. )
      else if (charOrPlaceId < 0){
        charOrPlaceId *= -1;
        Character.getCharacterByID(charOrPlaceId).addArtifact(a);
      }
      // > 0 to put the artifact in a specified Place
      else if  (charOrPlaceId > 0){
        Place.getPlaceById(charOrPlaceId).addArtifact(a);
      }
    }  
  }

  //constructor to make an instance of the game
  public Game(Scanner scan){
    // gets the name from the first line in the input
    name = CleanLineScanner.getCleanLine(scan.nextLine());
    String[] splits = name.split(" ");
    float version = Float.parseFloat(splits[1]);

    System.out.println("Game Version : " + version);
    // ignoring the "GDF 3.1" to get the actual name of the game
    name = String.join(" ", Arrays.copyOfRange(splits, 2, splits.length));

    // iterating the the input to add places
    while(scan.hasNextLine()) {
      // the first line should be either PLACES # or DIRECTIONS # OR ARTIFACTS #
      String input = CleanLineScanner.getCleanLine(scan.nextLine());
      if(input == null || input.isEmpty()) { 
          continue; 
      }

      // splitting the input based on whitespace
      String[] splitInput = input.split("\\s+");

      // we parse the input based on what kind of object it is.
      // parsing places
      if (splitInput[0].equals("PLACES")){
          int numPlaces = Integer.parseInt(splitInput[1].trim());
          createPlaces(scan, numPlaces, version);
          System.out.println("FINISHED PARSING PLACES");
      } 
      //parsing directions
      else if (splitInput[0].equals("DIRECTIONS")){
          int numDirections = Integer.parseInt(splitInput[1].trim());
          createDirections(scan, numDirections, version);
          System.out.println("FINISHED PARSING DIRECTIONS");

      } 
      else if (splitInput[0].equals("CHARACTERS")){
          int numCharacters = Integer.parseInt(splitInput[1].trim());
          createCharacters (scan, numCharacters, version);
          System.out.println("FINISHED PARSING CHARACTERS");
      } 
      // parsing artifacts
      else if(splitInput[0].equals("ARTIFACTS")){
          int numArtifacts = Integer.parseInt(splitInput[1].trim());
          createArtifacts(scan, numArtifacts, version);
          System.out.println("FINISHED PARSING ARTIFACTS");
      } 
      // bad line in the input
      else {
          System.out.println("got bad input line. Expected ARTIFACTS, PLACES, or DIRECTIONS, instead got: " + splitInput[0]);
      }
    }
  } 

  //prints out all the information about the game instance
  public void print(){
      System.out.println("Game Information");
      System.out.println("=============================");
      System.out.println("Name: " + this.name);
  }

  //plays the game
  public void play() {
    Scanner scan = KeyboardScanner.getKeyboardScanner();
    Vector<Move> allMoves = new Vector<Move>();

    while (totalInUsePlayers > 0) {
      for (Character c : allCharacters) {
        allMoves.add(c.makeMove());
      }
      for (Move m : allMoves){
        m.execute();
      }
    }
    
    System.out.println("\n\n\nNo more players in the game. Exiting program...");
  }
}