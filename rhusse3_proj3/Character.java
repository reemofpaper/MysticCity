// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

import java.lang.Object;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Vector;

public class Character{
  protected int characterId;
  protected String name;
  protected String description;
  protected Place currentPlace;
  protected DecisionMaker decision;
  protected Vector <Artifact> userInventory = new Vector<Artifact>();
  protected static Map<Integer, Character> allCharacters = new HashMap<>();
  
  public static Character getCharacterByID (int id){
    return allCharacters.get(id);
  }

  // constructors
  public Character (int id, String name, String description, int placeID){
    this.characterId = id;
    this.name = name;
    this.description = description;
    this.currentPlace = Place.getPlaceById(placeID);
    allCharacters.put(this.characterId, this);
  }

  public Character (int id, String name, String description){
    this.characterId = id;
    this.name = name;
    this.description = description;
    allCharacters.put(this.characterId, this);
  }
  
  public Character(Scanner scan, float version){
    while(scan.hasNextLine()) {      
      // next line of input
      String input = CleanLineScanner.getCleanLine(scan.nextLine());
      if(input == null || input.isEmpty()) continue; 

      // splitting the input based on whitespace
      String[] splitInput = input.split("\\s+");
      int placeId = Integer.parseInt(splitInput[0]);

      input = CleanLineScanner.getCleanLine(scan.nextLine());
      if(input == null || input.isEmpty()) continue; 
      
      splitInput = input.split("\\s+");
      
      this.characterId = Integer.parseInt(splitInput[0]);
      this.name = String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length));

      // next line of input
      input = CleanLineScanner.getCleanLine(scan.nextLine());
      if(input == null || input.isEmpty()) continue; 
      
      // storing the description
      int numDesc = Integer.parseInt(input);
      this.description = "";
      for (int i = 0; i <numDesc; i++){
        this.description = this.description + CleanLineScanner.getCleanLine(scan.nextLine()) + "\n";
      }
      
      this.currentPlace = Place.getPlaceById(placeId);

      // adding the character to a place
      if (placeId > 0 ){
        this.currentPlace.addCharacter(this);
      }

      // add to a random spot
      else if (placeId == 0){
        Place randomPlace = Place.getRandomPlace();
        randomPlace.addCharacter(this);
        this.currentPlace = randomPlace;  
      }

      // adding to hashmap
      if (!allCharacters.containsKey(this.characterId)){
        allCharacters.put(this.characterId, this);
      }
      // only want to read in what we expect for each character
      break;
    }
  }

  // return the name
  public String name(){
    return name;
  }
  
  // return the charid
  public int id(){
    return characterId;
  }
  
  // return the decsription
  public String description(){
    return description;
  }

  // chaning the current place
  public void setCurrentPlace(Place p ){
    this.currentPlace = p;
  }

  // get artifact to character inventory
  public void addArtifact(Artifact a){
    userInventory.add(a);
  }

  // drop artifact from character inventory
  public void removeArtifact(Artifact a){
    if (userInventory.contains(a)) userInventory.add(a);
  }
  
  // envoking the get move function
  public Move makeMove(){
    Move move =  decision.getMove(this, this.currentPlace);
    return move;
  }

  // initalizing out decision
  public void setDecision(DecisionMaker decision) {
        this.decision = decision;
  }

  // return artifacts with the userinventory
  public Vector <Artifact> returnUserInventory(){
    return userInventory;
  }

  // check if the use has a specific artifact
  public Artifact hasArtifact(String name){
    for (Artifact a: userInventory){
      if (name.equalsIgnoreCase(a.name())){
        return a;
      }
    }
    return null;
  }


  public void printArtifacts(){
    System.out.println("=========================================");
    System.out.println(this.name + "\'s Inventory: "); 
    int totalValue = 0;
    int totalMobility = 0;
    for (Artifact a: userInventory){
      a.display();
      totalValue += a.value() ;
      totalMobility += a.size(); 
    }

    System.out.println("\nTotals: "); 
    System.out.println(">>> Total Items : " + userInventory.size() + " artifacts");
    System.out.println(">>> Total Value : " + totalValue + " value points");
    System.out.println(">>> Total Mobility : " + totalMobility + " mobility points");
  }

  public void print(){
    System.out.println("\n=======================");
    System.out.println("Character Information");
    System.out.println("=======================");
    System.out.println(">> Name: " + name);
    System.out.println(">> Description: " + description);
    System.out.println(">> Current Place: " + currentPlace.name());
    System.out.println(">> Character ID: " + characterId);
  }

  public void display(){
    System.out.println("======================");
    System.out.println("Character Information");
    System.out.println("======================");
    System.out.println(">> Name: " + name);
    System.out.println(">> Description: " + description);
  }

  public void printAll(){
    for(Character c : allCharacters.values()){
      c.print();
    }
  }
}