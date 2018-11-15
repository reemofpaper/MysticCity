// Reem Hussein, rhussein
// Maleeha Ahmed, mahmed
// Joshua Horton, jhorton
// CS 342 Project 4

HOW TO RUN AND USE THE PROGRAM 
==========================================

Command line sequence to run the program
	>>make 
	>>java GameTester

Use 
	>>make clean

To get rid of all the .class files

This takes you to the text based game. This game is modeled after the 6 room diagram in the handout. My program supports these three commands:
	>> QUIT or EXIT – Quit the game.
	
	>> LOOK – Redisplay the current place.
	
	>> GO XXX, where XXX is one of N, S, E, W, U, or D - This Checks to see if the current place has a (unlocked) Direction corresponding to the requested direction and moves there. The user can also just move by just entering the direction with using GO
	
	>> GET artifact - Adds the artifact to the user input if it is present in the current room
	
	>> DROP artifact - Removes the artifact from the user inventory and leave it in the current room
	
	>> USE artifact – At this point the only usable Artifacts have a non-zero keyPattern, which will attempt useKey( ) on all Directions in the current place.
	
	>> INVE or INVENTORY – List all artifacts currently in possession of the player, along with the total value and total mobility.

NOTES
===========================================


HOW IT WORKS
===========================================

The program writeup instructed us to use 4 classes: GameTester, Game, Place and Direction. 

	>> DIRECTION CLASS
	basic info on the directions

	>> PLACE CLASS
	contains a static hashmap with all the places added to the game
	contains many static methods to assist with executing the game

	>> GAME CLASS
	Contains a vector of allCharacters, of which keeps track of their own inventory and currentPlace
	Functionality appears here. This class contains the play() method, which opens a new keyboard scanner. It iterates thorguh all the characters
	in allCharacters  and has them all move. based on what type of character they are, they will either have a randomized move (AI) or will be prompted 
	for a move (UI)

	>> GAMETESTER CLASS
	Gametester uses scanner to read in the file. First, it takes in user input to specfy which file it wants, and then passes a scanner that will parse the file.
	Creates instances of directions, places, and artifacts in order to create the space the file specifies.
	Calls on play() from the Game Class to commence functionality. 

	>> CHARACTER CLASS	
	Contains constructor to create a character, who will keep track of all previous game entities. 
	There are two type of characters

			>> PLAYER CLASS
			The is an actual playable character. will implement the UI so that they can enter commands
			>> NPC CLASS
			This is a nonplayable character. will implement the AI to get random commands

	>> MOVE CLASS
	Has all the commands availible
	
			>> QUIT CLASS
			Quit the game.

			>> LOOK
			Redisplay the current place.
			
			>> GO CLASS 
			where XXX is one of N, S, E, W, U, or D - This Checks to see if the current place has a (unlocked) 
			Direction corresponding to the requested direction and moves there. The user can also just move by 
			just entering the direction with using GO
			
			>> GET CLASS
			Adds the artifact to the user input if it is present in the current room
			
			>> DROP CLASS
			Removes the artifact from the user inventory and leave it in the current room
			
			>> USE CLASS 
			At this point the only usable Artifacts have a non-zero keyPattern, which will attempt useKey( ) on 
			all Directions in the current place.
			
			>> INVENTORY – CLASS
			List all artifacts currently in possession of the player, along with the total value and total mobility.