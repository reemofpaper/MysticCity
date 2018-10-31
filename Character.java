/*
netID; jhorto5
name: Joshua Horton

The Character class has a collection of places and directions to use. The arguments 
constructor maybe a little incomplete, but most of the work in the scanner constructor
should be done. Some extra functions have been added at the end to implement the Game
class.
*/
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Character{

    private int ID;
    private String name, description;
    private ArrayList<Place> places;
    private ArrayList<Direction> directions;
    private Place curPlace;

    private static HashMap<Int, Character> characters = new HashMap<Int, Character>();

    public Character(Scanner infile){
        
        places = new ArrayList<Place>();
        directions = new ArrayList<Direction>();

        String line = CleanLineScanner.clean_line(infile);
        Scanner lineScanner = new Scanner(line);

        ID = lineScanner.nextInt();
        lineScanner.skip("[\t]*");
        name = lineScanner.nextLine();
        private int id = lineScanner.nextInt();  

        line = CleanLineScanner.clean_line(infile);
        lineScanner = new Scanner(line);
        private int nCharacters = lineScanner.nextInt();
        description = "";
        for(int i = 0; i < nCharacters; i++)
            description += CleanLineScanner.clean_line(infile) + "\n";

        characters.put(ID, this); 
        Place curPlace = Place.getPlacebyID(id).addCharacter(this);
        return; 
    }

    public Character(int ID, String name, String description){

        this.ID = ID;
        this.name = name;
        this.description = description;

        characters.put(ID, this);
        return;
    }

    public int getCharacterbyID(int id){
        return characters.get(id);
    }

    public void showPlace(){
        if(places.size() > 0){
            System.out.println("You are in: ");
            for(Place p : places)
                System.out.println( p.name() + "-" + p.description());
        }
    }

    public Character followDirection(String s){
        for(Direction d : directions){
            if(d.match(s))
                return d.follow();
        }
        System.out.println("\nSorry, you can't go that way. Try again.\n");
        return this;
    }
}