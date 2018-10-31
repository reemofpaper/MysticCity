
//TODO: fix use() function
//getting the current place of a character 

import java.util.*;
public class Artifact {
	private int ID;
	private String name;
	private String description;
	private int value;
	private int mobility; //size or weight of the object  //negative value for this = imovable object
	private int keyPattern;  //key pattern is zero for an artifact that cant act as a key //negative keypattern can be applied to master key

//---------------------------------------------------------------------------	
	//constructor
	Artifact(Scanner s){ 
		this.description = "";
		this.name ="";
		String text = CleanLineScanner.cleanLine(s);
		
		
		Scanner line = new Scanner(text);
		
		 int placeCharID =line.nextInt(); //ignore the first value?? this is the artifact
		System.out.println("placeCharID: "+placeCharID);
		if(placeCharID < 0){
			placeCharID = placeCharID*-1;				
			Character c =Character.getCharacterByID(placeCharID);
			c.addArtifact(this); 
				
				
				
			}
			else if(placeCharID>0){
				
				Place.getPlaceByID(placeCharID).addArtifact(this);
				System.out.println("added to this place "+Place.getPlaceByID(placeCharID).name());
			}
			else{
				
				//Add artifact in a random room
				Place.getRandPlace().addArtifact(this);
			}
			this.ID = ID;
			line.close();
			text =CleanLineScanner.cleanLine(s);
			line = new Scanner(text);
	
		
		
		this.ID = line.nextInt();
		this.value = line.nextInt(); //get the value 
		this.mobility = line.nextInt();
		this.keyPattern = line.nextInt();
		
		
		
		String tempName = line.next();
		
		while(tempName.charAt(0)!='/' && line.hasNext()){
			this.name = this.name+ tempName+" " ;
			 tempName = line.next();
			
		}
		this.name = this.name+ tempName+" " ;
		String[] parts = this.name.split("/");
		this.name = parts[0];
		this.name = this.name.trim();
		System.out.println("artifact name:" +this.name);
		
		line.close();
		
		//here get how many lines the description is
		
		text = CleanLineScanner.cleanLine(s);
		line = new Scanner(text);
		int desNum = line.nextInt(); // this gets how many lines the descirption is
		for(int i=0; i<desNum;i++){
			
			
			text = CleanLineScanner.cleanLine(s);
			description = description + text +"\n"; //something wrong here
			
			
		}
		
	}
//---------------------------------------------------------------------------
	
	
//---------------------------------------------------------------------------
	//returns the value of the artifact
	public int value(){
		return this.value;
	}
	
//---------------------------------------------------------------------------
	public int size(){ //returns the mobility of the artifact
		return this.mobility;
	}
//---------------------------------------------------------------------------
	public String name(){ //returns the name of the artifact
		return this.name;
	}
//---------------------------------------------------------------------------
	public String description(){ //returns the description of the artifact
 		return this.description;
	}
//---------------------------------------------------------------------------
	public void use(Character c, Place p){
		
		
		p.useKey(this); //use the artifact on the current place
		
		
	}
//---------------------------------------------------------------------------
//*****NOT IN THE HANDOUT
	public int getKeyPattern(){ //gets the key pattern of the artifact
		return this.keyPattern;
	}
	
//---------------------------------------------------------------------------	
	public void print(){ //for debugging
		
		System.out.println("Name: "+this.name);
		System.out.println("Value: "+this.value);
		System.out.println("Mobility: "+this.mobility);
		
		System.out.println();
		
	}
	
}
