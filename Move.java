/*
netID: jhorto5
name: Joshua Horton

The move class has not been implemented yet. I added the ENUM MoveType, but didn't
give myself enough time to fully implement the class and the DecisionMaker interface.
*/
import java.io.*;
import java.util.*;
import java.util.Scanner
import java.util.ArrayList;

public interface DecisionMaker{

    public void getMove(Character characters, Place places){
        System.out.println("I haven't been implented!");
        return;
    }
}

public class Move{

    private int ID;
    private String name;
    private ArrayList<Place> places;
    private ArrayList<Character> characters;

    public Move(){
        //auto generated
    }

    public enum MoveType{
        UP{"UP", "U"};
        DOWN{"DOWN", "D"};
        LEFT{"LEFT", "L"};
        RIGHT{"RIGHT", "R"};

        public String text, abbrev;

        MoveType(String t, String ab){
            text = t;
            abbrev = ab;
        }

		public boolean match(String s){
			if(this == NONE)
				return false;
			s = s.trim();
			return s.equalsIgnoreCase(text) || s.equalsIgnoreCase(abbrev);
		}

		public String toString_move(){
			return text;
		}
    }

    public void makeMove(){
        System.out.println("I haven't been implented!");
        return;
    }
}