# Reem Hussein, rhussein
# Maleeha Ahmed, mahmed
# Joshua Horton, jhorton
# CS 342 Project 4

make all: AI.java Artifact.java Character.java CleanLineScanner.java DecisionMaker.java Direction.java DropMove.java Game.java GameTester.java GateKeeper.java GetMove.java GoMove.java GUI_1.java GUI_2.java GUI_3.java InventoryMove.java IO.java KeyboardScanner.java LookMove.java Move.java NPC.java PayGateKeeperMove.java Place.java Player.java QuitMove.java TeleportationPlace.java TeleportMove.java TextInterface.java UI.java UseMove.java	UserInterface.java
	javac *.java	

clean:	
	$(RM) *.class
	