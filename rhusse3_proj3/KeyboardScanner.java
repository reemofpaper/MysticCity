// // Name : Reem Hussein
// // Netid: rhusse3
// // CS account : rhussein
// // CS342 Project 3


import java.io.*; 
import java.util.Scanner;

class KeyboardScanner { 
  // only ever initalize it once
  private KeyboardScanner(){} 

  private static Scanner keyBoard = new Scanner(System.in);
  
  // return a scanner
  public static Scanner getKeyboardScanner() { 
    return keyBoard;
  } 
} 