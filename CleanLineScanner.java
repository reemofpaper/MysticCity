// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

import java.util.*;

public class CleanLineScanner{
  //removes the comments and trims the lines
  public static String getCleanLine(String line){
    // is there a comment?
    int index = line.indexOf('/');
    if (index == -1){
      return line.trim();
    }
    // return the line without the comment and trimmed
    return line.substring(0,index).trim();
  }
  
}