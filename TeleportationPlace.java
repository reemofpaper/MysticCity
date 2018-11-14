import java.util.*;

public class TeleportationPlace extends Place {
	private static float version;
	private Place randomPlace;

	// constructor
	TeleportationPlace(Scanner s, float version, int id, String name) {
		super(s, version, id, name); 
		randomPlace = Place.getRandomPlace();
		this.canTeleport = true;
	}

	@Override
	public String description(){
		while(randomPlace.equals(this)){
			randomPlace = Place.getRandomPlace();
		}
		this.description += "\n***There seems to a portal to " + randomPlace.name() + ". Maybe you should explore???***\n"; 
		return this.description;
	}

	public Place returnTeleportationRoom(){
		return randomPlace;
	}

}