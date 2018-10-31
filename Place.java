

//TODO: added getRandPlace and  fix **getRandDirection

//TODO: check to make sure the allPlaces vector has all the places
import java.util.*;
import java.util.Map.Entry;


public class Place {
	
	
	//private data
	private int ID;
	private String name="";
	private String description = "";
	private Vector<Direction> directions; //a vector that contains the direction object
	private Vector<Artifact> artifacts; //vector that contains all the artifacts in this place
	private static HashMap<Integer,Place> allPlaces = new HashMap<Integer, Place>(); // collection of all places
	private Vector<Character> characters; //keeps track of which characters are in this palce
	
//-------------------------------------------------------------------------------------
	Place(int id, String Name, String desc){ 
		ID = id;
		name = Name;
		description = desc;
		directions = new Vector<Direction>(); //create a vector for Direction object
		artifacts = new Vector<Artifact>();
		characters = new Vector<Character>();
		if(!allPlaces.containsKey(this.ID)){
			allPlaces.put(this.ID, this);
		}
	}
	

//---------------------------------------------------------------------------
	//constructor for place that takes in a scanner object
	Place(Scanner s, double version){
		ID = -1;
		name = "";
		description = "";
		directions = new Vector<Direction>(); //create a vector for Direction object
		artifacts = new Vector<Artifact>(); //create a new vector for artifacts
		characters = new Vector<Character>();
		//----------------------------------
		

		String text = CleanLineScanner.cleanLine(s); //get the clean line
		Scanner line = new Scanner(text); //scanner for the line of text
		
		
		this.ID = line.nextInt();
		
		String tempName = line.next();
		
		while(tempName.charAt(0)!='/' && line.hasNext()){ //keep adding to the name until the line ends or 
			this.name = this.name+ tempName+" " ;
			 tempName = line.next();
			
		}
		this.name = this.name.trim();
		
		line.close();
		
		text =CleanLineScanner.cleanLine(s);
		line = new Scanner(text); //set up a scanner for the new line
		
		int numDescription = line.nextInt(); //get the next line which has the number of lines of description for the place
		
		text = CleanLineScanner.cleanLine(s);
		for(int i=1; i<numDescription; i++){ //go through the number of lines specified by numDescription
			this.description = this.description + text + "\n"; //add to the description string
			
			text = CleanLineScanner.cleanLine(s);
		}
		this.description = this.description + text + "\n"; //add the to the description line
		
		line.close();
		
		//create a place object
		
		if(!allPlaces.containsKey(this.ID)){
			allPlaces.put(this.ID, this);
		}
		
	  //add it to the hashmap that holds all palces
		
	}
	
	
	
//---------------------------------------------------------------------------
	public static Place getPlaceByID(int placeId){ //place lookup by id **NEED TO FINISH THIS
		return allPlaces.get(placeId);
		
		
		
	}
//---------------------------------------------------------------------------
	public String name(){ //returns the name of the place
		
		return this.name;
	}
//---------------------------------------------------------------------------
	public String description(){ //returns the description of the place
		return description;
	}
	
//---------------------------------------------------------------------------
	public void addDirection (Direction x){ //adds a direction from a place
		
		directions.add(x); //add a direction (to the vector) for this place
	}
//---------------------------------------------------------------------------
	
	
	public Place followDirection(String s){
		
		
		for(Direction d: directions){ //checks if a direction entered by user matches any direction from the room
			
			
			if(d.match(s)){ //if it matches then follow
				
				Place p = d.follow();
				
				/*if(p.ID == 1 ){
					System.out.println("You are now exiting the game...");
					System.exit(0);
				}
				else{
					return p;
				}*/
				
				return p;
				
				
			}
			
			
		}
		
		
		System.out.println();
		System.out.println("That direction is not availbale...SORRY!"); //if not matched then return to the original room
		return this;
	}
//---------------------------------------------------------------------------
	public boolean addCharacter (Character c){ //add character in this room
		System.out.println( c.name() + " is in "+this.name); //debugging
		System.out.println();
		return this.characters.add(c);
		
	}
//---------------------------------------------------------------------------
	public void removeCharacter(Character c){ //removes character from this room
		this.characters.remove(c);
	}
	
//---------------------------------------------------------------------------	
	public Artifact removeArtifactByName(String a){
		
		for(Artifact x: this.artifacts){ //look through all the artifacts in the current place
			
			if(x.name().equalsIgnoreCase(a)){ //if the artifact is found in the place
				
				if(x.size()<0){ //if movable
					System.out.println("Sorry this artifact cannot be moved!");
					return null;
				}
				else{
					System.out.print("removed artifact from the room");
					artifacts.remove(x); //remove the artifact from the place
					return x; //return the artifact to the player
				}
				
				
			}
		}
		return null;
	}
	
	
//---------------------------------------------------------------------------
	public void addArtifact(Artifact a){ 
	
		artifacts.add(a);
		
	}
	
//---------------------------------------------------------------------------
	
		public void useKey(Artifact x){
		 
				
			for(Direction d: directions){ //for all direction of the place
				
				d.checkLockPattern(x.getKeyPattern()); //check if the lockof the place matches the lock pattern of the artifact
				
				
			}
		}
	
//---------------------------------------------------------------------------
	/*public Artifact containsArtifact(String a){
		
		for(Artifact x: this.artifacts){ //look through all the artifacts in the current place
			
			if(x.name().equalsIgnoreCase(a)){ //if the artifact is found in the place
				
				if(x.size()<0){ //if movable
					System.out.println("Sorry this artifact cannot be moved!");
					return null;
				}
				else{
					
					artifacts.remove(x); //remove the artifact from the place
					return x; //return the artifact to the player
				}
				
				
			}
		}
		return null;
	}
	*/
//---------------------------------------------------------------------------
	static Place getRandPlace(){ //a method that gets a randomPlace
		List<Integer> keys = new ArrayList<Integer>(allPlaces.keySet());
		Random random = new Random();
		Integer randomPlaceID = keys.get(random.nextInt(keys.size()));
		Place randomPlace = allPlaces.get(randomPlaceID);
		//System.out.println("this is the random place generated "+randomPlace.name()); debugging
		return randomPlace;
	}
	
//---------------------------------------------------------------------------
	public  Direction getRandDir(){
		Collections.shuffle(directions);
		System.out.println("randDir room: "+this.name);
		return this.directions.get(0);
		
	}
	
	
	
//---------------------------------------------------------------------------	
	static void printAll(){ //print debugging info for all known places
		
		for(HashMap.Entry<Integer, Place> e: allPlaces.entrySet()){ //enhanced for loop to iterate through the hashmap
			Place temp = e.getValue();
		
			temp.print();
			
			
		}
		
	}
	
//---------------------------------------------------------------------------
	public void print(){
		System.out.println(name); //prints the name of the room
		
		System.out.println(this.ID);
		System.out.println(this.description);
		
		//displays the characters in this room
		System.out.println();
		System.out.println("These are the characters in " + this.name + " :");
		for(Character c: this.characters){
			c.print();
			System.out.println();
		}
		
		
		//displays the artifacts in this room
		System.out.println("The artifacts in "+this.name+ " :");
		for(Artifact a: artifacts){
			a.print();
			System.out.println();
		}
		
		//prints out all the directions from this room
		System.out.println("Here are the directions from "+this.name+ " :");
		for(Direction d: directions){
		
		d.print(); //and the direction information
		System.out.println();
		
		
		}
	
		System.out.println("********************************");
		System.out.println();
		
		
	}
	
//---------------------------------------------------------------------------
	public void display(){
		
		System.out.println();
		System.out.println("Room Name: " + name);
		System.out.println("Description: " + description);
		System.out.println("Artifacts:");
	for(Artifact a: artifacts){
          a.print();
      }
	System.out.println("Directions from "+name+":");
      for(Direction d: directions){
    	  d.print();
      }
	}
	
	
	
}
