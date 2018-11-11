//TODO: rename this class to KeyboardScanner

import java.util.*;
public class KeyboardScanner {
	
	private static Scanner keyBoard =  new Scanner(System.in); //scanner that gets input from the keyboard
	public static Scanner getInstance(){
		
		
		return keyBoard; //return it
		
	}
	
	
}
