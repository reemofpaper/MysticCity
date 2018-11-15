
// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;
public class Character {
  
  //data members
  protected int ID; //char id
  protected String name; //char name
  protected String description; //char description
  protected Place curPlace; //current place of the character
  protected Vector<Artifact> playersArtifacts; //a vector of all the artifacts the character has
  protected String type; //ADDED THIS***
  protected static HashMap<Integer,Character> allCharacters = new HashMap<Integer, Character>();
  protected DecisionMaker decision;
  protected static int charNum =0;
  
  
  Character(Scanner s,double version){
    this.name="";
    this.description="";
    this.playersArtifacts = new Vector<Artifact>();
    
    String line = "";
    // getting a clean line with info
    while(s.hasNextLine()) {      
        line = CleanLineScanner.getCleanLine(s.nextLine());
        if(line == null || line.isEmpty()) continue; 
        else break;
    }

    // splitting the line based on whitespace
    String[] input = line.split("\\s+");
    int placeID = Integer.parseInt(input[0]);
    
    //set the players current place as that place 
    if(placeID > 0){
      this.curPlace=Place.getPlaceById(placeID); 
      this.curPlace.addCharacter(this);
    }
    //set character to a random place
    else{
      this.curPlace = Place.getRandomPlace();
      // random place that is not exit or no where
      while(curPlace.name().equalsIgnoreCase("exit")||curPlace.name().equalsIgnoreCase("nowhere")){
        this.curPlace = Place.getRandomPlace();
      }
      this.curPlace.addCharacter(this);
    }
    

    while(s.hasNextLine()){
      line = CleanLineScanner.getCleanLine(s.nextLine());
      if(line == null || line.isEmpty()) continue;
      else break; 
    }
    input = null;
    input = line.split("\\s+");

  
    this.ID = Integer.parseInt(input[0]);
    this.name = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
    
    
    
    while(s.hasNextLine()){
      line = CleanLineScanner.getCleanLine(s.nextLine());
      if(line == null || line.isEmpty()) continue;
      else break; 
    }
    input = null;
    input = line.split("\\s+");

    int numDescription = Integer.parseInt(input[0]); 

    for(int i=0; i<numDescription; i++){
        this.description = this.description + CleanLineScanner.getCleanLine(s.nextLine()) + "\n";
    }

    if(!allCharacters.containsKey(this.ID)){
      allCharacters.put(this.ID, this);
      Character.charNum++;
    }   
  }
  
  Character(int id, String name, String desc){
    this.ID =id;
    this.name = name;
    this.description=desc;
    this.playersArtifacts = new Vector<Artifact>();
   
    
  }

  public static Character getCharacterByID(int id){
    return allCharacters.get(id);
  }

  public void addArtifact(Artifact a){ 
    this.playersArtifacts.addElement(a);
  }
  
  public void makeMove(){ 
  }

  public void print(){
    System.out.println(this.name);
  }

  public Vector <Artifact> returnUserInventory(){
    return playersArtifacts;
  }

  public void display(){
    System.out.println("======================");
    System.out.println("Character Information");
    System.out.println("======================");
    System.out.println(">> Name: " + name);
    System.out.println(">> Description: " + description);
  }

  public String name(){
    return this.name;
  }

  public Place getCurPlace(){
    return this.curPlace;
  }
}