// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;
import java.lang.reflect.Array;

public class Game {
  private  boolean createChar = false; 
  private String gameName;
  private static Vector<Character> allCharacters; 
  public static int numPlayerCharacters =0; 
  private float gameVersion; 
  private Place first;

  // public constructor
  public Game(Scanner s){
    allCharacters = new Vector<Character>();
    int numPlaces = 0;

    //start by reading the first line which gives the name
    String line = CleanLineScanner.getCleanLine(s.nextLine());
    
    // parsing game features
    gameName = ""; 
    String[] input = line.split(" ");
    gameVersion = Float.parseFloat(input[1]);
    gameName = String.join(" ", Arrays.copyOfRange(input, 2, input.length));

    System.out.println("Game Name : " + gameName);
    System.out.println("Game Version : " +gameVersion);
    
    if(gameVersion >= 4.0){ //if the game version is not 4.0 
      createChar  = true; //ask user to input player info
    }
        

    while(s.hasNextLine()) {
      line = CleanLineScanner.getCleanLine(s.nextLine());
      if(line == null || line.isEmpty()) { 
        continue; 
      }
      else {
        break;
      }
    }

    input = null;
    input = line.split("\\s+");

    //look for the places keyword
    if(input[0].equalsIgnoreCase("places")){ 
      numPlaces = Integer.parseInt(input[1].trim());
    }
    
    //create places here:
    for(int i=0; i<numPlaces; i++){ 
      while(s.hasNextLine()) {
        line = CleanLineScanner.getCleanLine(s.nextLine());
        if(line == null || line.isEmpty()) continue;
        else break;
      }
      input = null;
      input = line.split("\\s+");

      int placeId = Integer.parseInt(input[0]);
      String name = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
      
      //call the place constructor 
      //then add the Place to the places vector
      Place temp = null;
      if (placeId % 6 == 0){
        temp = new TeleportationPlace(s, gameVersion , placeId , name); //create a new place
      }
      else{
        temp = new Place(s, gameVersion, placeId , name); //create a new place
      }
      if(i==0){
        first = temp; //set the current place as the first place created
      }
    }
    Place Exit = new Place(1,"Exit","");
    Place Nowhere = new Place(0,"Nowhere","");
    
    int numGateKeepers = new Random().nextInt(numPlaces);
    
    for (int i=1; i <= numGateKeepers ; i++){
      Place temp = Place.getRandomPlace();
      
      // we want a place that does not already have a gatekeeper
      while(temp.hasGateKeeper()){
        temp = Place.getRandomPlace();
      }

      // add a new gatekeeper to the random room
      GateKeeper g = new GateKeeper(i, temp);
      temp.addCharacter(g);
      temp.setGateKeeper(g);
      // add it to the place collection
      Place.allGateKeepers.put(i, g);
    }

    while(s.hasNextLine()) {
      line = CleanLineScanner.getCleanLine(s.nextLine());
      if(line == null || line.isEmpty()) { 
        continue; 
      }
      else {
        break;
      }
    }

    input = null;
    input = line.split("\\s+");

    //if keyword directions is found
    int numDirections = 0;
    if(input[0].equalsIgnoreCase("DIRECTIONS")){
      numDirections = Integer.parseInt(input[1].trim());
    
    }

    //create directions here
    for(int i=0; i<numDirections;i++){
      Direction d = new Direction(s, gameVersion);
    }
    
    // do we need to add players?
    if(gameVersion >= 4.0){ 
      // creating characters here  
      while(s.hasNextLine()) {
        line = CleanLineScanner.getCleanLine(s.nextLine());
        if(line == null || line.isEmpty()) { 
          continue; 
        }
        else {
          break;
        }
      }

      input = null;
      input = line.split("\\s+");
      int numCharacters=0;
      
      // looking for keyword characters
      if(input[0].equalsIgnoreCase("characters")){ 
        numCharacters = Integer.parseInt(input[1].trim());
      }
      if(numCharacters == 0){  //if no characters are specified in the file
        createChar = true; //set create character to true;
      }
      for(int i=0; i<numCharacters;i++){
        String type = s.next();        
        if(type.equalsIgnoreCase("player")){
          Player p = new Player(s);
          this.allCharacters.addElement(p);
          Game.numPlayerCharacters++;
        }
        else if(type.equalsIgnoreCase("npc")){ 
          NPC nonP = new NPC(s);
          this.allCharacters.addElement(nonP); 
        }
        else{
          Character c = new Character(s,gameVersion);
          this.allCharacters.addElement(c);
        }    
      }
    }

    //no players added
    if(Game.numPlayerCharacters == 0){
      createChar =true; 
    }
    
    //parsing artifacts
    while(s.hasNextLine()) {
      line = CleanLineScanner.getCleanLine(s.nextLine());
      if(line == null || line.isEmpty()) { 
        continue; 
      }
      else {
        break;
      }
    }

    input = null;
    input = line.split("\\s+");

    int numArt =0;
    //looking for keyword artifacts
    if(input[0].equalsIgnoreCase("artifacts")){ 
      numArt = Integer.parseInt(input[1].trim());
    }
    
    //making new artifacts
    for(int i=0; i<numArt;i++){
      Artifact temp = new Artifact(s); 
    }

    // adding special artifact extenstion
    Artifact magicCoin = new Artifact("Magic Coin", "This is the only magical coin in the room. \nWith this coin, you will be able to access all the locked \nrooms and bypass any Gate Keepers. Use it wisely...");
    // throwing the coin in a random room
    Place temp = Place.getRandomPlace();
    temp.addArtifact(magicCoin);

    System.out.println ("The magic coin got thrown in : " + temp.name());
    Place.printAll(); 
  }


  public static boolean removeCharFromGame(Character c){
    if(Game.allCharacters.remove(c)){
      return true;
    }
    return false;
  }

  static public String cleanLine( Scanner s){ //static function to clean the line
    String text = s.nextLine();
    while(text.length()==0||text.charAt(0)=='/'){
      text = s.nextLine();
    }
    return text; 
  }

  public void play(){
    // adding ccharacters to the game
    if(Game.numPlayerCharacters<=0){
      System.out.println("Please enter the number of players in the game");
      Scanner input = new Scanner(System.in);
      while(!input.hasNextInt()){
        System.out.println("Please enter the number of players in the game");
        input = new Scanner(System.in);
        
      }

      int numPlayers = input.nextInt();
      System.out.println("Number of Players: " + numPlayers);
      int i=0;

      //create the players
      while(i<numPlayers){ 
        String name=""; 
        String desc ="No description available";
        System.out.println("Enter a name for the player "+(i+1)+ ":");
        input = new Scanner(System.in); 
        
        while(!input.hasNext()){
          System.out.println("*Enter a name for the player "+i+1+ ":");
          input= new Scanner(System.in);
          
        }
        
        name = input.next();
        System.out.println("please enter a description for "+name);
        input = new Scanner(System.in);
        while(!input.hasNext()){
          System.out.println("please enter a description for "+name);
          input = new Scanner(System.in);
          
        }
        desc = input.next();        
        Player temp = new Player(i+1,name,desc );
        temp.curPlace = first; 
        first.addCharacter(temp); 
        Game.allCharacters.addElement(temp);
        Game.numPlayerCharacters++;
        i++;
      }
    }
    Character toRemove = null;
    //have the initial number of players at the start of the game
    int prevPlayerNum = Game.numPlayerCharacters;    
    while(Game.numPlayerCharacters >0){
      for(Character c :Game.allCharacters){
        c.makeMove(); //prompt to make a move
        if(prevPlayerNum != Game.numPlayerCharacters){
          toRemove = c;
          break;
        } 
      }
    
      if(toRemove!=null){
        System.out.println("Removing... "+toRemove.name);
         //remove that character from the game
        Game.allCharacters.remove(toRemove);
        //reset the players in the game
        prevPlayerNum = Game.numPlayerCharacters; 
        toRemove= null;
      }
    }
    System.out.println("All players exited the game...GAME OVER");
  }
}