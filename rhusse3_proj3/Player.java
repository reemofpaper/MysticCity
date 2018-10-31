// Name : Reem Hussein
// Netid: rhusse3
// CS account : rhussein
// CS342 Project 3

import java.util.Scanner;

public class Player extends Character {
    public Player(Scanner scan, float version) {
        super(scan, version);
        super.setDecision(new UI());
    }
    //Used to manually create players if none are present in gdf file
    public Player (int id, String name, String description, int placeID) {
        super(id, name, description, placeID);
        super.setDecision(new UI());
    }
}
