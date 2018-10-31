// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

import java.util.Scanner;

public class NPC extends Character{
  public NPC(Scanner scan, float version){
    super(scan, version);
    super.setDecision(new AI());
  }
}