// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 1

import java.util.Scanner;
import java.util.Arrays;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.Random;

public class Place {
    private int placeId;
    private String name;
    private String description;
    private Vector <Character> characters = new Vector <Character>();
    private Vector <Direction> directions = new Vector<Direction>();
    private Vector <Artifact> artifacts = new Vector<Artifact>();

    // hashmap to keep track of all the places that get added
    private static Map<Integer, Place> allPlaces = new HashMap<>();

    public static Place getRandomPlace(){
        Random rand = new Random();
        int rand_int = rand.nextInt(allPlaces.size());
        int errorCheck = 0;

        for (Place p : allPlaces.values()){
            if (errorCheck == rand_int){
                if (p.placeId == 0  || p.placeId == 1){
                    errorCheck = 0;
                    rand_int = rand.nextInt(allPlaces.size());
                }
                else {
                    return p;
                }
            }
            errorCheck++;
        }
        return null;
    }
    
    // Returns the Place associated with the given ID number, or null.
    public static Place getPlaceById(int num){
        return allPlaces.get(num);
    }

    // original constructor to add the exit and the nowhere place
    public Place(int id, String iname, String desc){
        this.placeId = id;
        this.name = iname;
        this.description = desc;
        if (!allPlaces.containsKey(this.placeId)){
            allPlaces.put(this.placeId, this);
        }

    }

    //constructor to make an instance of place using scanner
    public Place(Scanner scan, float version){
      while(scan.hasNextLine()) {
            // reading in the first line of input for the direciton information
            String line = CleanLineScanner.getCleanLine(scan.nextLine());
            if (line == null || line.isEmpty()){
                continue;
            }
            // first line has the ID and the name
            String[] splits = line.split("\\s+");

            // splitting the name to exlude the "GDF 3.1" text
            this.placeId = Integer.parseInt(splits[0]);
            if (Game.returnFirstPlaceAdded() == 0) Game.changeFirstPlaceAdded( this.placeId);

            this.name = String.join(" ", Arrays.copyOfRange(splits, 1, splits.length));

            // grabbing the description
            line = CleanLineScanner.getCleanLine(scan.nextLine());
            int numDesc = Integer.parseInt(line);
            this.description = "*";

            // formatinng for the description
            int flag = 0;
            // iterating for the number of lines in the file specified for the description
            for (int i=0; i<numDesc; i++){
                if (flag >= numDesc - 1){
                    // are we on the last element?
                    this.description = this.description + CleanLineScanner.getCleanLine(scan.nextLine()) + "\n";
                }
                else{
                    // do we have additional lines to read in?
                    this.description = this.description + CleanLineScanner.getCleanLine(scan.nextLine()) + "\n*";
                }
                flag++;
            }
            // making sure we did not add the same place twice
            if (!allPlaces.containsKey(this.placeId)){
                allPlaces.put(this.placeId, this);
            }
            // only want to read in what we expect for each artifact
            break;
        }
    }

    //returns the name of the place
    public String name(){
        return name;
    }

    //returns the palce description
    public String description(){
        return description;
    }

    public int placeId(){
        return placeId;
    }
    
    // grabs and artifact and puts it in the user inventory
    public Artifact getArtifact(String aname){
        //checks all the artifacts in the current place
        for (Artifact a : this.artifacts){
            // is it in this place ?
            if(a.name().equalsIgnoreCase(aname)){
                // is it movable
                if (a.weight() > 0){
                    // if we can move it, we remove it from the place
                    // and return that artifact
                    artifacts.remove(a);
                    return a;
                }
            }
        }
        // cannot move or does not exist in that room
        return null;
    }

    //adds direction object to places collection of directions
    public void addDirection(Direction dir){
        directions.add(dir);
    }

    //adds direction object to places collection of directions
    public void addArtifact(Artifact a){
        artifacts.add(a);
    }

    public void removeArtifact(Artifact a){
        if (artifacts.contains(a)) artifacts.remove(a);
    }

    public void removeArtifactByName(String n){
        for (Artifact a : artifacts){
            if (a.name().equalsIgnoreCase(n)){
                artifacts.remove(a);
            }
        }
    }

    public Vector<Direction> returnDirs (){
        return directions;
    }

    public Vector<Artifact> returnArtifacts(){
        return artifacts;
    }

    public void addCharacter(Character c){
        characters.add(c);
    }

    //adds direction object to places collection of directions
    public void removeCharacter(Character c){
        if (characters.contains(c)) characters.remove(c);
    }

    // Passes the artifact to the useKey( ) method of all Directions 
    // present in this Place.
    public void useKey( Artifact a){
        for (Direction d: directions){
            d.checkLockPattern(a);
        }
    }
    
    //corresspondingto the string passed
    public Place followDirection(String dir){
        for(Direction d: directions){
            //checks if the direction exists for that room
            if (d.match(dir)){
                //if its locked, we return the same room
                if (d.isLocked()){
                    System.out.println("This direction is locked...");
                    return this;
                }
                //if its unlocked, we enter the room
                Place temp = d.follow();
                //if we entered an exit, we leave the game
                if (temp.placeId == 1){
                    System.out.println("Exiting Game...");
                    System.exit(0);
                }
                // otherwise, we return the new location
                return temp;
            }
        }
        //could not find the direction in that place 
        System.out.println("This direction does not exist.");
        return this;
    }

    //prints out all the information about the place
    public void print(){
        System.out.println("=================");
        System.out.println("Place Information");
        System.out.println("=================");
        System.out.println(">> Name: " + name);
        System.out.println(">> ID: " + placeId);
        System.out.println(">> Description: " + description);
        System.out.println(">> Directions: ");
        for (Direction d : directions){
            System.out.println("    >>>" + d.name());
        }
        for (Artifact a : artifacts){
            System.out.println("    >>>" + a.name());
        }
    }

    // printing out the current place's description and list of artifacts
    public void display(){
        System.out.println("Description : \n" + description);
        System.out.println("Artifacts");
        if (artifacts.size() == 0){
          System.out.println(">>> No availible artifacts");
        }
        else {
          for (Artifact a : artifacts){
            System.out.println(">>>" + a.name());
            System.out.println("   >>> Description: " + a.description());
            System.out.println("   >>> Mobility : " + a.size() + " move points");
            System.out.println("   >>> Value : " + a.value() + " value points");                    
          }  
        }
        System.out.println("\nCharacters Present");
        if (characters.size() < 1){
          System.out.println(">>> No other characters");
        }
        else{
          for (Character c : characters){
            System.out.println(">>>" + c.name());
          }
        }
        System.out.println("================================");
    }


    // printing all info on all the places
    public static void printAll(){
        for (Place p : allPlaces.values()){
            p.print();
        }
    }

}
