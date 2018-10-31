


import java.util.*;
public class Direction {
	
	

	private enum DirType{
		NONE("None", "None"),
		N("North", "N"),
		S("South", "S"),
		E("East" ,"E"),
		W("West","W"), 
		U("Up","U"), 
		D("Down","D"), 
		NE("Northeast", "NE"), 
		NW("Northwest","NW"), 
		SE("Southeast","SE"), 
		SW("Southwest","SW"), 
		NNE("North-Northeast","NNE"), 
		NNW("North-Northwest","NNW"), 
		ENE("East-Northeast","ENE"), 
		WNW("West-Northwest","WNW"),
		ESE("East-Southeast","ESE"), 
		WSW("West-Southwest","WSW"), 
		SSE("South-Southeast","SSE"), 
		SSW("South-Southwest","SSW"); //objects of DirType

		//data fields
		private String text; 
		private String abbrev;
		//----------------------------------------------------------------------------------------------
		//constructor
		DirType(String t, String abbreviation){
			text = t;
			abbrev = abbreviation;
		}
		
		
		//----------------------------------------------------------------------------------------------
		public String toString(){ //returns the text field
			
			return text;
			
		}
		//----------------------------------------------------------------------------------------------
		public boolean match(String s){ //return true if the string matches the text or abbreviation, ignoring cases
			
				if(text.equalsIgnoreCase(s) || abbrev.equalsIgnoreCase(s)){ //if the abbreviation or text match return true
					return true;
				}
			
			return false;
		}
		
		
	}
	
//----------------------------------------------------------------------------------------------
	
	//private data
	private int ID;
	private Place placeFrom;
	private Place placeTo;
	private DirType dir; //change this to dirType
	private int lock; //to keep track if direction is locked or unlocked, 1 = lock, 0 = unlocked
	private int lockPattern;
//---------------------------------------------------------------------------	
	//constructor
	Direction(int id, Place from, Place to, DirType direction){
		ID = id;
		placeFrom = from;
		placeTo = to;
		dir = direction; //here go through the enum constants
		lock =0; //initially direction is unlocked
	}
	
//---------------------------------------------------------------------------
	//constructor with the scanner to create a direction object
	Direction(Scanner s, double version){ //constructor that takes in a scanner object
		
		
		String text = CleanLineScanner.cleanLine(s); //get the first clean line
	
		
	
		
		Scanner line = new Scanner(text);  //add a scanner to the current line
		this.ID = line.nextInt(); //get the direction id
		this.placeFrom = Place.getPlaceByID(line.nextInt()); //use the getPlaceByID to get the place
		
		
		
	
		
		
		String direction= line.next(); 
		//here create a loop to go through 
		this.dir = DirType.NONE;
		for(DirType d: DirType.values()){ 
			//set dirtype to none first then find a match
			if(d.match(direction)){
				//set the this.dir to d
				this.dir = d;
			}
		}
		int placeToID = line.nextInt();
		
		//this.placeTo = Place.getPlaceByID(line.nextInt());
		if(placeToID <0){
			placeToID = placeToID*-1; //if the place id is less than 0 then the direction is locked, change it to the positive value to lookup 
			this.lock(); //lock the direction
		}
		this.placeTo=Place.getPlaceByID(placeToID);
		
		this.lockPattern = line.nextInt();  //get the lock pattern
		this.placeFrom.addDirection(this); //add the direction to the "from place"
		
		
		
		
	}
	
	
	
	
	
	
//---------------------------------------------------------------------------	
	//checks if string passed matches dir
	//**BONUS to do: fix for variations of directions
	public boolean match(String s){
	
		
		return dir.match(s);
		
		
	}
//---------------------------------------------------------------------------
	public void checkLockPattern(int artifactPattern){ //checks the artifact key pattern aganist the key pattern of the lock
		if(artifactPattern>0 && artifactPattern == this.lockPattern){ //if the artifact is a key and matches the lock pattern
			if(this.isLocked()){ //if the door is locked
				this.unlock(); //then lock
			}
			else{ 
				this.lock(); //else unlock the door
		
			}
			
		}
		
		
		
	}
	
//---------------------------------------------------------------------------
	//method to lock the direction
	public void lock(){
		lock = 1; //direction is locked
				
	}
//---------------------------------------------------------------------------
	public void unlock(){
		lock = 0; //direction is unlocked
	}
//---------------------------------------------------------------------------
	public boolean isLocked(){
		
		if(lock == 1){
			return true;
		}
		return false;
	}
//---------------------------------------------------------------------------
	public Place follow(){ 
		if(this.isLocked() == false){
			//System.out.println(placeTo.name() + "is unlocked");
			
			return placeTo;
		}
		else if( this.isLocked() == true){
			
			
		}
		
			
			
			return placeFrom;
			
		
		
			
			
	}
//----------------------------------------------------------------------------------------------
	public void useKey(Artifact x){
		//if the key pattern of the artifact == positive & to the lock pattern 
			
		if(x.getKeyPattern()>0 && x.getKeyPattern() == this.lockPattern){
			if(this.isLocked()){ //if the door is locked
				this.unlock(); //then lock
			}
			else{ 
				this.lock(); //else unlock the door
		
			}
		}
	}
	
//---------------------------------------------------------------------------
	public void print(){
		//prints direction information
		//System.out.println("Place From: " +placeFrom.name());
		System.out.println("Place To: " + placeTo.name());
		System.out.println("Direction: " + dir);
		//System.out.println("lock: "+ lock);
		//System.out.println("lock Pattern: "+lockPattern);
		
	}
	
	
	

	
}
