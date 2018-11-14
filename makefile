make all: AI.java Artifact.java Character.java CleanLineScanner.java DecisionMaker.java Direction.java Drop.java Game.java GameTester.java GateKeeper.java Get.java Go.java Halt.java Invalid.java Inventory.java KeyboardScanner.java Look.java Move.java NPC.java PayGateKeeper.java Place.java Player.java TeleportationPlace.java TeleportMove.java Quit.java UI.java Use.java
	javac *.java
clean:
	$(RM) *.class
