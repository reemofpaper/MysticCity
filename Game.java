//TODO: when artifact needs to be in a random palce, figure out how to pick the random place and put artifact there

//finish the game version compatibility
//asking for player via command line
//finish AI implementation

//parses the file version correctly but how to play game with the old file version?
//fix look command to include directions and artifacts in/from that room

import java.util.*;


import java.lang.reflect.Array;
import java.util.*;


public class Game {

	private  boolean createChar = false; //keeps track of if user needs to be prompted to create a character
	private String gameName;// naem of the game
	private static Vector<Character> allCharacters; //all characters in the game
	public static int numPlayerCharacters =0; //should this be public?
	private double gameVersion; //game version
	private Place first;
//---------------------------------------------------------------------------	
	//constructor
	/*Game(String s){
		gameName =s;
		places = new Vector<Place>(); //creates a new vector of Place Objects
		
		
	}*/

//---------------------------------------------------------------------------	
	//constructor
	Game(Scanner s){
		//playersArtifacts = new Vector<Artifact>(); //create a new vector for the players artifacts
		//places = new Vector<Place>(); //create a new vector for the places vector
		allCharacters = new Vector<Character>();
		//start by reading the first line which gives the name
		String text = s.nextLine(); 
		//String text = Game.cleanLine(s);
		int numPlaces =0;
		
		//connect a scanner to that line
		Scanner line = new Scanner(text);
		
		
		
		//----GET NAME-----------
		gameName = ""; //initialize name variable
		while(line.hasNext()){ //while the line has something
			String temp = line.next(); //scan the next word
			if(temp.equals("//")){ //if there is a comment detected
				break; //break out of the loop
			}
			gameName = gameName+  temp+" "; //if  not then join the string	
		}
		gameName = gameName.trim();
		System.out.println("game name: "+gameName );
		
		Scanner version = new Scanner(gameName); //gets the game version
		version.next();
		this.gameVersion = version.nextDouble();
		System.out.println("this is the game version " +gameVersion);
		version.close();
		
		if(gameVersion != 4.0){ //if the game version is not 4.0 
			createChar  = true; //ask user to input player info
		}
		
		
		//------GET THE PLACES keyword-----------
		
		text = CleanLineScanner.cleanLine(s);
		
		line = new Scanner(text); //scan this new line
		
		if(line.next().equalsIgnoreCase("places")){ //look for the palces keyword
			numPlaces = line.nextInt(); //get the number of places in the game
			
		}
		line.close();
		
		
		
		
		
		
		//create place here:
		
		
		for(int i=0; i<numPlaces; i++){ //set up a loop to go through the number of places specified in the file
			
			//call the place constructor 
			//then add the Place to the places vector
			Place temp = new Place(s, gameVersion); //create a new place
		
			
		
			
			if(i==0){
				first = temp; //set the current place as the first place created
			}
			
			
			
		}
		Place Exit = new Place(1,"Exit","");
		//places.addElement(Exit);
		Place Nowhere = new Place(0,"Nowhere","");
		//places.addElement(Nowhere);
		
		
		
		
		//-----DIRECTIONS HERE----------------------------
		//after done processing all the palces look for the next line that is not blank or commented
		
		text = CleanLineScanner.cleanLine(s);
		
		line = new Scanner(text);
		
		//if keyword directions is found then look for how many directions there are
		int numDirections =0;
		if(line.next().equalsIgnoreCase("DIRECTIONS")){
			numDirections = line.nextInt();
		
		}
		//ELSE DO SOMETHING
		for(int i=0; i<numDirections;i++){
			
			Direction d = new Direction(s, gameVersion);

				
		}
		line.close();
		
	if(gameVersion == 4.0){	
		//-----CHARACTERS HERE----------------------------
				text = CleanLineScanner.cleanLine(s); //get the next clean line
				line = new Scanner(text);
				int numCharacters=0;
				if(line.next().equalsIgnoreCase("CHARACTERS")){ //check for character in teh file
					numCharacters = line.nextInt(); //get the number of characters
				}
				if(numCharacters ==0){  //if no characters are specified in the file
					createChar = true; //set create character to true;
				}
		for(int i=0; i<numCharacters;i++){
			
			
			
			String type = s.next();
			System.out.println(type);
			System.out.println("this is the type in game: "+ type);
			
			if(type.equalsIgnoreCase("player")){ //if character is of the type player
				Player p = new Player(s);
				this.allCharacters.addElement(p);
				Game.numPlayerCharacters++;
			}
			else if(type.equalsIgnoreCase("npc")){ //if chracter is of the type npc
				NPC nonP = new NPC(s);
				this.allCharacters.addElement(nonP);
				
			}
			else{ //if its neither one of those
				Character c = new Character(s,gameVersion);
				this.allCharacters.addElement(c);
				
				
			}
			
			
			
			
			
			
		}
		
		
	}
		if(Game.numPlayerCharacters ==0){
			createChar =true; //if there are no PLAYER type specified in the file need to prompt the user
		}
		line.close();
		
		//-------ARTIFACTS HERE------------------------
		text = CleanLineScanner.cleanLine(s);
		
		line = new Scanner(text); //create a new scanner for the line
		text =line.next();
		int numArt =0;
		if(text.equalsIgnoreCase("artifacts")){ //if the text contains artifacts key word
			numArt = line.nextInt(); //get the number of artifacts
			
		}
		
		//a loop for calling the artifacts constructor
		for(int i=0; i<numArt;i++){
			
			
			Artifact temp = new Artifact(s);
			
			
			
		}
		Place.printAll();
		
		
		
		
	
		
	}

//---------------------------------------------------------------------------
	public static boolean removeCharFromGame(Character c){
		if(Game.allCharacters.remove(c)){
			return true;
		}
		return false;
	}
//---------------------------------------------------------------------------	
	public void print(){
	/*
		for(Place p : places){
			p.print();
		}
		*/
		
	}
	
//---------------------------------------------------------------------------	
	/*
	static public String cleanLine( Scanner s){ //static function to clean the line
		String text = s.nextLine();
		while(text.length()==0||text.charAt(0)=='/'){
			text = s.nextLine();
		}
		return text;
		
		
	}
	*/
//---------------------------------------------------------------------------
	/*public static Place getCurrentPlace(){
		
		return curPlace; //returns the current place
	}
	*/
	
//---------------------------------------------------------------------------
	public void play(){
	
		if(Game.numPlayerCharacters<=0){
			System.out.println("Please enter the number of players in the game"); //ask to enter number of players
			Scanner input = new Scanner(System.in);
			while(!input.hasNextInt()){
				System.out.println("Please enter the number of players in the game");
				input = new Scanner(System.in);
				
			}
			int numPlayers = input.nextInt();
			
			System.out.println("number of players: "+numPlayers);
			int i=0;
			while(i<numPlayers){ //create the players
				String name=""; 
				String desc ="No description available";
				System.out.println("Enter a name for the player "+(i+1)+ ":"); //get the name of the player
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
				temp.curPlace = first; //make the characters current place the first place in the file
				first.addCharacter(temp); //add character to the place
				Game.allCharacters.addElement(temp);
				Game.numPlayerCharacters++;
				i++;
			}
		}
		Character toRemove = null;
		int prevPlayerNum = Game.numPlayerCharacters; //have the initial number of players at the start of the game
		System.out.println("prev: "+ prevPlayerNum+ " numPlayer: " +Game.numPlayerCharacters);
		while(Game.numPlayerCharacters >0)	{//keep game going as long as there are characters
		for(Character c :Game.allCharacters){ //iterate through all the characters
			
			c.makeMove(); //prompt to make a move
			
			System.out.println("*prev: "+ prevPlayerNum+ " *numPlayer: " +Game.numPlayerCharacters);
			if(prevPlayerNum != Game.numPlayerCharacters){ //if a player exits
				
				toRemove = c;
				break;
				
				
			}
			
			
			
		}
		
		
		if(toRemove!=null){
		System.out.println("Removing... "+toRemove.name);
		 //remove that character from the game
		Game.allCharacters.remove(toRemove);
		prevPlayerNum = Game.numPlayerCharacters; //reset the players in the game
		
		toRemove= null;
		}
		
		
		
		}
		
	System.out.println("All players exited the game...GAME OVER");
	}
	
	
}
