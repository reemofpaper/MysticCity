/*
netID: jhorto5
name: Joshua Horton

The implementation of the Game class stripped down a little for me. The use of artifacts
are gone, but I have added in a character(only one type of character) and some functions
that have to with where they are currently. The move class has not been implemented,
so no DecisionMaker interface was used. But it should accept both versions 3.1 and 4.0 :)
*/
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Game{
    
    private String name;
    private ArrayList<Character> characters;
    private Place curPlace;

    public Game(Scanner infile){

        characters = new ArrayList<Character>();

        String line = CleanLineScanner.clean_line(infile);
        Scanner lineScanner = new Scanner(line);
        String word = lineScanner.next();
        if(!word.equalsIgnoreCase("GDF")){
            System.err.println("Error parsing input file \"GDF\" not found.\n");
            System.exit(-1);
        }
        double version = lineScanner.nextDouble();
        if(version != 3.1 || version != 4.0){
            System.err.println("Error parsing input file. Wrong version\n" + version);
            System.exit(-2);
        }
        lineScanner.skip("[\t]*");

        name = lineScanner.nextLine();
        int nCharacters = keywordCount(infile, "CHARACTERS");
        for(int i = 0; i < nCharacters; i++){
            characters.add(new Character(infile));
        }
        
        return;
    }

    //for debugging only
    public void print(){
        System.out.println("Game " + name + ": ");
        for(Character c : characters){
            c.print();
        }
        return;
    }

    public void play(){
        System.out.println("Now playing " + name + ".");

        if(characters.size() < 1)
            return;

        curCharacter = characters.get(0);

        Scanner cin = new Scanner(System.in);
        String line;

        while(true){
            System.out.println("\nYou are currently the character " + curCharacter.name());
            System.out.println(curCharacter.description());
            curCharacter.showPlace();

            System.out.print("> ");
            line = cin.nextLine();
            line = line.trim();

            if(line.equalsIgnoreCase("EXIT") || line.equalsIgnoreCase("QUIT"))
                break;

            if(line.equalsIgnoreCase("CONTINUE") || line.equalsIgnoreCase("CONT"))
                continue;
            
            if(line.length() > 3 && line.substring(0, 3).equalsIgnoreCase("GO "))
                curCharacter = curCharacter.followDirection(line.substring(3));
            else   
                curCharacter = curCharacter.followDirection(line);

            int sourceID = lineScanner.nextInt();
            Place curPlace = Place.getPlacebyID(sourceID).isExit();
            }

        //all done
        System.out.println("Thanks for playing!");
        cin.close();
        return;
    }

    public static Place getCurCharacter(){
        return curCharacter;
    }

    private int keywordCount(Scanner infile, String keyword){

        String line = CleanLineScanner.clean_line(infile);
        Scanner lineScanner = new Scanner(line);
        String word = lineScanner.next();
        if(!word.equalsIgnoreCase(keyword)){
            System.err.println("Error parsing input file. \"" + keyword + "\" not found\n");
            System.exit(-4);
        }
        int count = lineScanner.nextInt();
        if(count <= 0){
            System.err.println("Error - Invalid counter found in \"" + line + "\"");
            System.exit(-5);
        }

        lineScanner.close();
        return count;
    }
}

