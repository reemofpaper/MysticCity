// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 2

import java.util.Scanner;
import java.util.Arrays;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Direction {
    // enumerated type DirType
    enum DirType {        
        // Defined constants for the 19 defined direction types
        NONE("NONE","NONE"), NORTH("NORTH","N"), S("SOUTH","S"), 
        E("EAST","E"), W("WEST","W"), U("UP","U"), D("DOWN","D"), 
        NE("NORTHEAST", "NE"), NW("NORTHWEST","NW"), SE("SOUTHEAST","SE"), 
        SW("SOUTHWEST","SW"), NNE("NORTHNORTHEAST","NNE"), NNW("NORTHNORTHWEST","NNW"), 
        ENE("EASTNORTHEAST","ENE"), WNW("WESTNORTHWEST","WNW"), ESE("EASTSOUTHEAST","ESE"), 
        WSW("WESTSOUTHWEST","WSW"), SSE("SOUTHSOUTHEAST","SSE"), SSW("SOUTHSOUTHWEST","SSW");

        // Two private String data fields, text and abbreviation
        private String abbreviation;
        private String text;

        //constructor for DirType
        DirType(String text, String abbreviation){
            this.text = text;
            this.abbreviation = abbreviation;
        }

        // returns the text field
        public String toString(){
            return text;
        }

        // returns true if the given string matches either the text or the abbreviation
        public boolean match( String s){
            return (s.matches(text) || s.matches(abbreviation));
        } 
    }

    // private members of the direction class
    private int directionId;
    private Place fromPlace, toPlace;
    private DirType dir;
    private boolean locked = false;
    private int lockPattern;

    // hashmap to keep track of all the inputted directions 
    private static Map<Integer, Direction> allDirections = new HashMap<>();

    //constructor to make an instance of the direction class    
    public Direction(Scanner scan, float version){
        while(scan.hasNextLine()) {
            // reading in the first line of input for the direciton information
            String line = CleanLineScanner.getCleanLine(scan.nextLine());
            if (line == null || line.isEmpty()){
                continue;
            }

            // splitting the line based on whitespace
            String[] splits = line.split("\\s+");
            //direction id is the first integer
            this.directionId = Integer.parseInt(splits[0]);
            //from id is the second integer. use getPlaceID to store as a place
            this.fromPlace = Place.getPlaceById(Integer.parseInt(splits[1]));
            // direction type autofills to none unless specfified by the line
            this.dir = DirType.NONE;
            // checks to see if the direciton is a possible direction in the DirType enumeration
            for (DirType d : DirType.values()){
                if (d.match((splits[2]))){
                    //if they match, that is the same direction
                    this.dir = d;
                }
            }
            //from id is the fourth integer. use getPlaceID to store as a place
            int toPlaceId = Integer.parseInt(splits[3]);
            if (toPlaceId <= 0){
                // checking to see if we should lock the door and fixes the negated number
                this.locked = true;
                toPlaceId *= -1;
            }
            this.toPlace = Place.getPlaceById(toPlaceId);
            //lock pattern is the fifth integer.
            this.lockPattern = Integer.parseInt(splits[4]);

            // checkig to make sure we dont write the same direciton twice to our hasmap
            if (!allDirections.containsKey(this.directionId)){
                allDirections.put(this.directionId, this);
            }

            // add the direction to place's collection of directions
            this.fromPlace.addDirection(allDirections.get(directionId));
            // only want to read in what we expect for each direction
            break;
        }
    }

    //returns the name of the direction for place.print()
    public DirType name(){
        return dir;
    }

    // returns true if the string passed matches those of store directions dir
    public boolean match(String s){
        return dir.match(s);
    }

    // locks the direction
    public void lock(){
        if (!locked)
            locked = true;
    }

    // unlocks the direction
    public void unlock(){
        if (locked)
            locked = false;
    }

    // returns true if the direction is locked
    public boolean isLocked(){
        return locked;
    }

    //returns the to place corresponding to this direction 
    //if it is unlocked, otherwise returns from place
    public Place follow(){
        if (!locked)
            return toPlace;
        else
            return fromPlace;
    }

    // If the keyPattern of the Artifact is positive and equal to the lockPattern,
    // toggle the state of the Direction lock.
    public void checkLockPattern(Artifact a){
        //cannot change lock status
        if (this.lockPattern == 0){
            return;
        }
        // checks to make sure we can use this key and inverts the locked status
        else if ((a.keyPattern() > 0) && (a.keyPattern() == this.lockPattern)){
            this.locked = !(this.locked);
        }
    }

    //prints out all direction information
    public void print(){
        System.out.println("=====================");
        System.out.println("Direction Information");
        System.out.println("=====================");
        System.out.println(">> ID: " + directionId);
        System.out.println(">> From Place: " + fromPlace.name());
        System.out.println(">> To Place: " + toPlace.name());
        System.out.println(">> Direction: " + dir);
        System.out.println(">> Direction Locked: " + locked);
        System.out.println(">> Lock Pattern: " + lockPattern);
    }
}