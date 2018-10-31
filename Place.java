/*
netID: jhorto5
name: Joshua Horton

Place class now has a collection of characters that it can use. The printAll() function has been
added along with a display() function.
*/
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Place{

    private int ID;
    private String name, description;
    private ArrayList<Direction> directions;
    private ArrayList<Artifact> artifacts;
    private ArrayList<Character> characters;
    private static boolean firstPlace = true;

    private static HashMap<Int, Place> places = new HashMap<Int, Place>();

    public Place(Scanner infile){

        directions = new ArrayList<Direction>();
        artifacts = new ArrayList<Artifact>();
        characters = new ArrayList<Character>();

        String line = CleanLineScanner.clean_line(infile);
        Scanner lineScanner = new Scanner(line);
        ID = lineScanner.nextInt();
        lineScanner.skip("[\t]*");
        name = lineScanner.nextLine();

        line = CleanLineScanner.clean_line(infile);
        lineScanner = new Scanner(line);
        int nLines = lineScanner.nextInt();
        description = "";
        for(int i = 0; i < nLines; i++)
            description += CleanLineScanner.clean_line(infile) + "\n";

        places.put(ID, this);

        if(firstPlace){
            firstPlace = false;
            new Place(1, "Exit", "Place representing the exit");
            new Place(0, "Nowhere", "Place doesn't exist");
        }
        return;
    }

    public Place(int ID, String name, String description){
        this.ID = ID;
        this.name = name;
        this.description = description;
        directions = new ArrayList<Direction>();
        artifacts = new ArrayList<Artifact>();
        characters = new ArrayList<Character>();
        places.put(ID, this);
        return;
    }

    public static Place getPlacebyID(int id){
        return places.get(id);
    }

    public String name(){
        return name;
    }

    public String description(){
        return description;
    }

    public void addDirection(Direction dir){
        directions.add(dir);
        return;
    }

    public void addArtifact(Artifact art){
        artifacts.add(art);
        return;
    }

    public void addCharacter(Character cha){
        characters.add(cha);
        return;
    }

    public Artifact removeArtifactbyName(String s){
        for(Artifact a : artifacts){
            if(a.match(s)){
                artifacts.remove(a);
                return a;
            }
        }
        return null;
    }

    public void useKey(Artifact a){
        for(Direction d : directions){
            d.useKey(a);
        }
        return;
    }

    public Place followDirection(String s){
        for(Direction d : directions){
            if(d.match(s))
                return d.follow();
        }
        System.out.println("\nSorry, you can't go that way. Try again.\n");
        return this;
    }

    public void showArtifacts(){
        if(artifacts.size() > 0){
            System.out.println("here is: ");
            for(Artifact a : artifacts)
                System.out.println( a.name() + "-" + a.description());
        }
    }

    public boolean isExit(){
        return ID == 1 || ID == 0;
    }

    public void display(){
        System.out.println("\nPlace: " + name);
        System.out.println(description);
    }

    public static void printAll(){
        System.out.println("\nPlace ID # " + ID + " - " + name);
        System.out.println(description);
        if(directions.size() > 0){
            System.out.println("\tThe following directions are available:");
            for(Direction d : directions)
                d.print();
        }
        else{
            System.out.println("There are no directions available :(");
        }

        if(artifacts.size() > 0){
            System.out.println("\tThe following artifacts are available:");
            for(Artifact a : artifacts)
                a.print();
        }
        else{
            System.out.println("There are no artifacts available :(");
        }       

        if(characters.size() > 0){
            System.out.println("\tThe following characters are available:");
            for(Character c : characters)
                c.print();
        }
        else{
            System.out.println("There are no characters available :(");
        }
        return;
    }
}