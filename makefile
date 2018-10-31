JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
        $(JC) $(JFLAGS) $*.java

CLASSES = \
        Artifact.java \
        Character.java \
        CleanLineScanner.java \
        Direction.java \
        Game.java \
        GameTester.java \
        Move.java \
        Place.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
        $(RM) *.class