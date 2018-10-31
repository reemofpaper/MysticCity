// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

import java.util.Scanner;
import java.util.Arrays;

public class Artifact {
	// private members
	private int placeId;
	private int artifactId;
	private String name;
	private String description;
	private int value;
	private int mobility;
	private int keyPattern;

	// constructor
  public Artifact(Scanner scan, float version){
    while(scan.hasNextLine()) {
      //gets the first line and cleans it to get the place ID
      String line = CleanLineScanner.getCleanLine(scan.nextLine());
      if (line == null || line.isEmpty()){
        continue;
      }
      // splitting the line based on white space
      String[] splits = line.split("\\s+");
      //artifact id is the first integer
      this.artifactId = Integer.parseInt(splits[0]);
      //value is the second integer
      this.value = Integer.parseInt(splits[1]);
      //mobility is the second integer
      this.mobility = Integer.parseInt(splits[2]);
      //key pattern is the second integer
      this.keyPattern = Integer.parseInt(splits[3]);
      // joins the rest of the line in case the name has spaces between it
      this.name = String.join(" ", Arrays.copyOfRange(splits, 4, splits.length));

      // reading in the description pattern
      line = CleanLineScanner.getCleanLine(scan.nextLine());
      int numDesc = Integer.parseInt(line);
      this.description = "";
      // iterating for the number of lines in the file specified for the description
      for (int i=0; i<numDesc; i++){
        this.description = this.description + CleanLineScanner.getCleanLine(scan.nextLine()) + "\n";
      }
      // only want to read in what we expect for each artifact
      break;
   }
  }

	// Returns the value of the artifact.
	public int value(){
		return value;
	}

	// Returns the movability value.
	public int size (){
		return mobility;
	}

	// Returns the movability value.
	public int weight(){
		return mobility;
	}

	// Returns the name of the artifact.
	public String name(){
		return name;
	}

	// Returns the description of the artifact.
	public String description(){
		return description;
	}

	public int keyPattern(){
		return keyPattern;
	}

	// In the case of keys, this will involve getting the current place from the Game class, 
	// and then passing the artifact to the useKey( ) method of the current Place.
	public void use(Character c, Place p){
    // the current room
    	p.useKey(this);
  	}

	// prints out all artifact information
    public void print(){
        System.out.println("=====================");
        System.out.println("Artifact Information");
        System.out.println("=====================");
        System.out.println(">> Name: " + name);
        System.out.println(">> Description: " + description);
        System.out.println(">> Mobility: " + mobility);
        System.out.println(">> Key Pattern: " + keyPattern);
    }

    public void display(){
	    System.out.println("=====================");
	    System.out.println("Artifact Information");
	    System.out.println("=====================");
	    System.out.println(">> Name: " + name);
	    System.out.println(">> Value: " + value);
	    System.out.println(">> Mobility: " + mobility);
    
  	}

}