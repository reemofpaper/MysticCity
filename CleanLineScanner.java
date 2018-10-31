/*
netID: jhorto5
name: Joshua Horton

This CleanLineScanner file is a template from the professor's code. I tried not to copy it
completely, but I guess that is up to the grader's discretion
*/
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class CleanLineScanner{
    public CleanLineScanner(){
        //Auto-Generated
    }

    public static String clean_line(Scanner s){
        String line;
        while(true){
            if(!s.hasNextLine())
                break;
        
            line = s.nextLine();
            int com_start = line.indexOf("//");
            if(com_start == 0)
                continue;

            if(com_start > 0)
                line = line.substring(0, com_start);
            line = line.trim();
        
            if(line.length() > 0)
                return line;
        }
        return null
    }
}