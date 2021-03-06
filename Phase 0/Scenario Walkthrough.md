Scenario Walkthrough

Note: *UI is implemented but not connected to rest of the skeleton program. 
Instead, skeleton program prints and takes text input from the console/terminal*

Program is started by running the main of the Game class.

Game initializes one instance of Map, as well as multiple instances of PlayerChar and EnemyChar, which are subclasses of the abstract class Character.

These PlayerChar and EnemyChar instances are utilized by methods in Map that place them onto the Map.

Map keeps track of these character positions on a 2D array.

The class Game runs a loop that takes inputs from the terminal and makes the characters perform actions by calling on the Action class.

Game also makes checks to ensure that the inputs are usable, and prints to the terminal if they are not.

When a character's health reaches 0, they are removed from Map.

When all instances of EnemyChar are removed from Map, the program ends.
