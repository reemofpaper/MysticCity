make all: AI.java Artifact.java Character.java CleanLineScanner.java DecisionMaker.java Direction.java DropMove.java Game.java GameTester.java GetMove.java GoMove.java InventoryMove.java KeyboardScanner.java LookMove.java Move.java NPC.java Place.java Player.java QuitMove.java UI.java UseMove.java
	javac *.java
clean:
	$(RM) *.class

