
//TODO: need to add another character constructor to create a character if it wasnt mentioned in teh file

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
	
	//-------constructor that takes in a scanner--------------------------------------------------------------------	
	
	Character(Scanner s,double version){
		this.name="";
		this.description="";
		this.playersArtifacts = new Vector<Artifact>();
		
		int placeID = s.nextInt(); //get the placeID
		
		
	
		if(placeID >0){ //if id is greater than 0
			
			Place temp=Place.getPlaceByID(placeID); //set the players current place as that place
			this.curPlace=temp;
			
			
			this.curPlace.addCharacter(this);
			//add character to the current place
			
		}
		else{
			//set character to a random place
			//System.out.println("the place ID is zero: "+placeID); //debugging
			this.curPlace = Place.getRandPlace();
			while(curPlace.name().equalsIgnoreCase("exit")||curPlace.name().equalsIgnoreCase("nowhere")){
				this.curPlace = Place.getRandPlace();
			}
			this.curPlace.addCharacter(this);
			
		}
		
		 s.nextLine();
		 String text = CleanLineScanner.cleanLine(s); //get the next clean line
		 Scanner line = new Scanner(text);  //start parsing
		 
		this.ID = line.nextInt(); //gets the id
		
		//get the next word in the line which is a name
		String tempName = line.next();
		
		while(tempName.charAt(0)!='/' && line.hasNext()){ //keep adding to the name until the line ends or there is a comment
			this.name = this.name+ tempName+" " ;
			 tempName = line.next();
			
		}
		this.name = this.name + tempName;
		this.name = this.name.trim();
		
		line.close();
		
		text = CleanLineScanner.cleanLine(s); //get the next clean line
		line = new Scanner(text);
		int numDescription = line.nextInt(); //get the number of description lines
		
		text = CleanLineScanner.cleanLine(s);//get next clean line
		for(int i=1; i<numDescription; i++){ //go through the number of lines specified by numDescription
			this.description = this.description + text + "\n"; //add to the description string
			
			text = CleanLineScanner.cleanLine(s);
		}
		this.description = this.description + text + "\n"; //add the to the description line
		if(!allCharacters.containsKey(this.ID)){
			allCharacters.put(this.ID, this);
			Character.charNum++;
		}
		System.out.println("parsing char "+this.name);
		
		
		
		
		
	}
	
//-----constructor that creates a character----------------------------------------------------------------------	
	Character(int id, String name, String desc){
		this.ID =id;
		this.name = name;
		this.description=desc;
		
	}
//------------------------return a character depending on ID---------------------------------------------------	
	public static Character getCharacterByID(int id){
		return allCharacters.get(id);
	}
//-------------------adds artifact to the characters collection--------------------------------------------------------	
	public void addArtifact(Artifact a){ 
		this.playersArtifacts.addElement(a);
	}
	
//---------------------------------------------------------------------------	
	public void makeMove(){
		//calls getMove which returns an encapsulated move object
	
		
	}
//---------------------------------------------------------------------------	
	public void print(){
		System.out.println(this.name);
		
		
	}
//---------------------------------------------------------------------------	
	public void display(){
		
	}

//---------------------------------------------------------------------------	
	public String name(){
		return this.name;
	}

	
//---------------------------------------------------------------------------		
	public Place getCurPlace(){
		return this.curPlace;
	}

}
