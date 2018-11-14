make all: AI.java Artifact.java Character.java CleanLineScanner.java DecisionMaker.java Direction.java DropMove.java Game.java GameTester.java GateKeeper.java GetMove.java GoMove.java InventoryMove.java KeyboardScanner.java LookMove.java Move.java NPC.java PayGateKeeperMove.java Place.java Player.java QuitMove.java TeleportationPlace.java TeleportMove.java UI.java UseMove.java	
	javac *.java	
clean:	
	$(RM) *.class
	