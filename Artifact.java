/*
netID; jhorto5
name: Joshua Horton

No real major changes made here.
*/
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Artifact{

    private int ID, value, mobility, keyPattern;
    private String name, description;

    public Artifact(Scanner infile){
        String line = CleanLineScanner.clean_line(infile);
        Scanner lineScanner = new Scanner(line);
        ID = lineScanner.nextInt();
        value = lineScanner.nextInt();
        mobility = lineScanner.nextInt();
        keyPattern = lineScanner.nextInt();
        lineScanner.skip("[ \t]*");
        name = lineScanner.nextLine();

        line = CleanLineScanner.clean_line(infile);
        lineScanner = new Scanner(line);
        int nLines = lineScanner.nextInt();
        description = "";
        for(int i = 0; i < nLines; i++){
            description += CleanLineScanner.clean_line(infile) + "\n";
        }
        return;
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

    //some functions from previous homeworks
    public static int evaluateInventory(ArrayList<Artifact> stuff){
        int total = 0;
        for(Artifact a : stuff)
            total += a.value;

        return total;
    }

    public static int measureInventory(ArrayList<Artifact> stuff){
        int total;
        for(Artifact a : stuff)
            total += a.mobility > 0 ? a.mobility : 0;

        return total;
    }

    public boolean keyFits(int lockPattern){
        return lockPattern == keyPattern;
    }

    public boolean match(String s){
        return s.trim().equalsIgnoreCase(name);
    }
}
