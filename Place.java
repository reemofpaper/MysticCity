import java.util.*;

public class Place {
    protected int placeId;
    protected String name;
    protected String description;
    protected Vector <Character> characters = new Vector <Character>();
    protected Vector <Direction> directions = new Vector<Direction>();
    protected Vector <Artifact> artifacts = new Vector<Artifact>();
    protected Boolean canTeleport;
    protected GateKeeper gateKeeper;

    // hashmap to keep track of all the gateKeepers that get added
    protected static Map<Integer, GateKeeper> allGateKeepers = new HashMap<>();
    // hashmap to keep track of all the places that get added
    protected static Map<Integer, Place> allPlaces = new HashMap<>();

    public static Place getRandomPlace(){
        List<Integer> keys = new ArrayList<Integer>(allPlaces.keySet());
        Random random = new Random();
        Integer randomPlaceID = keys.get(random.nextInt(keys.size()));
        Place randomPlace = allPlaces.get(randomPlaceID);
        return randomPlace;
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
    public Place(Scanner scan, float version, int id, String name){
      this.gateKeeper = null;
      this.canTeleport = false;
      while(scan.hasNextLine()) {
        this.placeId = id;
        this.name = name;
        // reading in the first line of input for the direciton information
        String line = CleanLineScanner.getCleanLine(scan.nextLine());
        if (line == null || line.isEmpty()){
            continue;
        }
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

    public Boolean hasGateKeeper(){
        if (gateKeeper) return true;
        else return false;
    }
    //returns the place description
    public String description(){
        return description;
    }

    // return placeID
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
                break;
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
                return d.follow();
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
        System.out.println(">> Description: " + this.description());
        System.out.println(">> Directions: ");
        for (Direction d : directions){
            System.out.println("    >>>" + d.name());
        }
        System.out.println(">> Artifacts: ");
        for (Artifact a : artifacts){
            System.out.println("    >>>" + a.name());
        }
        System.out.println("\n\n\n");

    }

    public boolean canTeleport(){
      return canTeleport;
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
