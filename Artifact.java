import java.util.*;

public class Artifact{
    private int ID, value, mobility, keyPattern;
    private String name, description;

    public Artifact(Scanner infile){
      String line = "";
      while(infile.hasNextLine()) {
        //gets the first line and cleans it to get the place ID
        line = CleanLineScanner.getCleanLine(infile.nextLine());
        if (line == null || line.isEmpty()) continue;
        else break;
      }

      // putting in the numerival entites

      String[] input = line.split("\\s+");
      int charOrPlaceId = Integer.parseInt(input[0]);

      while(infile.hasNextLine()) {
        //gets the first line and cleans it to get the place ID
        line = CleanLineScanner.getCleanLine(infile.nextLine());
        if (line == null || line.isEmpty()) continue;
        else break;
      }
      input = null;
      input = line.split("\\s+");
      for (int i=0; i<input.length; i++){
        System.out.println("i: " + i + "    array Elements: " + input[i]);
      }
      this.ID = Integer.parseInt(input[0]);
      this.value = Integer.parseInt(input[1]);
      this.mobility = Integer.parseInt(input[2]);
      this.keyPattern = Integer.parseInt(input[3]);
      this.name = String.join(" ", Arrays.copyOfRange(input, 4, input.length));

      while(infile.hasNextLine()) {
        //gets the first line and cleans it to get the place ID
        line = CleanLineScanner.getCleanLine(infile.nextLine());
        if (line == null || line.isEmpty()) continue;
        else break;
      }

      // reading in the description
      int nLines = Integer.parseInt(line);
      this.description = "";
      for(int i = 0; i < nLines; i++){
        this.description = this.description + CleanLineScanner.getCleanLine(infile.nextLine()) + "\n";
      }

      if (charOrPlaceId == 0){
        Place.getRandomPlace().addArtifact(this);
      }
      // < 0 for a character’s possessions. ( Character ID is the positive value. )
      else if (charOrPlaceId < 0){
        charOrPlaceId *= -1;
        Character.getCharacterByID(charOrPlaceId).addArtifact(this);
      }
      // > 0 to put the artifact in a specified Place
      else if  (charOrPlaceId > 0){
        Place.getPlaceById(charOrPlaceId).addArtifact(this);
      }
    }

    public String name(){
        return name;
    }

    public String description(){
        return description;
    }

    public int size(){
        return mobility;
    }
    public int weight(){
      return mobility;
  }

    public int value(){
        return value;
    }

    public void use(Character c, Place p){
        p.useKey(this);
    }
    
    public void print(){ 
  		System.out.println("Name: " + name);
  		System.out.println("Value: " + value);
  		System.out.println("Mobility: " + mobility);
      System.out.println("KeyPattern: " + keyPattern);
  		System.out.println();	
  	}

    public int keyPattern(){
      return keyPattern;
    }
    public static int evaluateInventory(ArrayList<Artifact> stuff){
      int total = 0;
      for(Artifact a : stuff)
        total += a.value;
      return total;
    }

    public static int measureInventory(ArrayList<Artifact> stuff){
      int total = 0;
      for(Artifact a : stuff)
          total += a.mobility > 0 ? a.mobility : 0;
      return total;
    }

}
