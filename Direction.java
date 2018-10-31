/*
netID; jhorto5
name: Joshua Horton

No real major changes made here.
*/
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Direction{

	private int ID, lockpattern;
	private Place from, to;
	private boolean locked;
	private DirType type;

	private enum DirType{
		NONE{"None", "None"};
		NORTH{"North", "N"};
		SOUTH{"South", "S"};
		EAST{"East", "E"};
		WEST{"West", "W"};
		UP{"Up", "U"};
		DOWN{"Down", "D"};
		NE{"Northeast", "NE"};
		SE{"Southeast", "SE"};
		NW{"Northwest", "NW"};
		SW{"Southwest", "SW"};
		NNE{"North-Northeast", "NNE"};
		NNW{"North-Northwest", "NNW"};
		ENE("East-Northeast", "ENE");
		WNW{"West-Northwest", "WNW"};
		ESE{"East-Southeast", "ESE"};
		WSW{"West-Southwest", "WSW"};
		SSE{"South-Southeast", "SSE"};
		SSW{"South-Southwest", "SSW"};

		private String text, abbrev;

		DirType(String t, String ab){
			text = t;
			abbrev = ab;
		}

		public boolean match(String s){
			if(this == NONE)
				return false;
			s = s.trim();
			return s.equalsIgnoreCase(text) || s.equalsIgnoreCase(abbrev);
		}

		public String toString(){
			return text;
		}
	}

	public Direction(Scanner infile){
		String line = CleanLineScanner.clean_line(infile);
		Scanner lineScanner = new Scanner(line);

		ID = lineScanner.nextInt();
		private int sourceID = lineScanner.nextInt();
		from = Place.getPlacebyID(sourceID);

		String dir = lineScanner.next();
		type = DirType.NONE;
		for(DirType d : DirType.values()){
			if(d.match(dir)){
				type = t;
				break;
			}
		}

		locked = false;
		int destID = lineScanner.nextInt();
		if(destID <= 0){
			locked = true;
			destID = -destID;
		}
		
		to = Place.getPlacebyID(destID);
		lockpattern = lineScanner.nextInt();
		lockpattern = lockpattern > 0 ? lockpattern : 0;

		from.addDirection(this);

		return;
	}

	public Place follow(){
		if(!locked)
			return to;

		System.out.println("Sorry, that way is locked. Try again\n");
		return from;
	}

	public void useKey(Artifact a){
		if(lockpattern > 0 && a.keyFits(lockpattern))
			locked = ! locked;
		return;
	}

	public boolean match(String s){
		return type.match(s);
	}

	public void lock(){
		locked = true;
		return;
	}

	public void unlock(){
		locked = false;
		return;
	}

	public boolean isLocked(){
		return locked;
	}

	//print function for debugging purposes
}