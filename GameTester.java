

//TODO: change it so that file name can be given on the command line

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GameTester {
    public static void main(String arg[]){
        
        
        System.out.println("!!Maleeha Ahmed");
        System.out.println("mahmed58");
        
        Game newGame;
        
        System.out.println("Please enter a filename");
        Scanner scanner = new Scanner(System.in);
        String inLine[] = scanner.nextLine().split(" ");
        
        //new file
        while(inLine.length <1 ){ //if enough arguments are not provided keep prompting user
            System.out.println("retry or enter quit");
            scanner = new Scanner(System.in);
            inLine = scanner.nextLine().split(" ");
            
            
        }
        if(inLine[0].equalsIgnoreCase("quit")){ //if user types quit exit the game
            System.exit(0);
            
        }
        
        
        
        
        //check if the file could be opened and connect to the scanner
        
        
        
        
        
        
        File gameFile = new File(inLine[0]);
        Scanner file;
        try {
            file = new Scanner(gameFile);
            newGame = new Game(file); //send the scanner to the Game class
            
            newGame.play();
            
        } catch (FileNotFoundException e) {
            System.out.println("Sorry couldnt find this file");
            System.exit(0);
        } //check if the file could be opened and connect to the scanner
        
        
        
        
        
        
        
        
        
        
    }
    
}
