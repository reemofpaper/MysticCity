import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameTester {
  public static void main(String arg[]){
	  
	//to replace system for printing  
	IO print = new IO();
	  
	print.display("hw5");
	print.display("Maleeha Ahmed, netid: mahmed58, accc account: mahmed");
	print.display("Johsua Horton, netid: jhorto5, accc account: jhorton");
	print.display("Reem Hussein,  netid: rhusse3, accc account: rhussein");
	
	
	
	
	
    String filename = "mysticcity.txt";
    
    //checks if filename is provided
    if(arg.length>0){
      filename = arg[0];
    }
    
    int minPlayer =1;
    
    //if second argument for number of players is provided
    if(arg.length>1){
      minPlayer = Integer.parseInt(arg[1]);
    }
    
    //if negative players is given
    if(minPlayer<1){
      minPlayer =1;
    }
    
    Scanner openFile = null;
    
    try{
      openFile = new Scanner(new File(filename));
      
    }
    catch(FileNotFoundException e){
      System.err.println("File Not Found: "+filename);
      System.exit(-3);      
    }
    
     Game g = new Game(openFile, minPlayer);
     g.play();
  }
}
