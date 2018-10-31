/*
netID: jhorto5
name: Joshua Horton

Unfortunately for me, this semester has been kicking my butt between my job and a full
time schedule, and this class has been the most behind. I always get caught up in doing
other work for my other classes that are due earlier than these assignments and end up
screwing myself. But, I can't take another zero, or incomplete, I have to at least try.
So, I'm turning in what I have even though it's incomplete.
*/
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class GameTester{
    
    public GameTester(){
        //auto-generated
    }

    public static void main(String[] args){
        String filename = "MysticCity31.gdf";
        if(args.length > 0){
            filename = args[0];
        }

        Scanner infile = null;
        try{
            infile = new Scanner(new File(filename));
        }
        catch(FileNotFoundException e){
            System.err.println("File not found: " + filename);
            
            Scanner userInput = new Scanner(System.ln);
            System.out.println("Try entering a suitable file: ");
            String replacement = userInput.nextLine().trim();
            
            if(replacement != filename){
                System.err.println("File not found: " + replacement + "Try entering filename: MysticCity31.gdf");
                System.exit(-3);
            }
        }

        Game g = new Game(infile);
        g.print();

        System.out.println("name: Joshua Horton\n");
        System.out.println("netID: jhorto5\n");
        System.out.println("\nLet's play! \n\n");

        g.play();
    }
}