JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
ALL = AI.java Artifact.java  Character.java CleanLineScanner.java DecisionMaker.java Direction.java dropMove.java Game.Java GameTester.java getMove.java getUserInput.java goMove.java inveMove.java lookMove.java Move.java NPC.java Place.java Player.java quitMove.java UI.java useMove.java
default: classes
classes: $(ALL:.java=.class)
clean:
	$(RM) *.class