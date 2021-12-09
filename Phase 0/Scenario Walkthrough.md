Scenario Walkthrough

Note: *Controllers.UI is implemented but not connected to rest of the skeleton program. 
Instead, skeleton program prints and takes text input from the console/terminal*

Program is started by running the main of the Controllers.Game class.

Controllers.Game initializes one instance of Entity.Map, as well as multiple instances of PlayerChar and Entity.EnemyChar, which are subclasses of the abstract class Entity.Character.

These PlayerChar and Entity.EnemyChar instances are utilized by methods in Entity.Map that place them onto the Entity.Map.

Entity.Map keeps track of these character positions on a 2D array.

The class Controllers.Game runs a loop that takes inputs from the terminal and makes the characters perform actions by calling on the UseCase.Action class.

Controllers.Game also makes checks to ensure that the inputs are usable, and prints to the terminal if they are not.

When a character's health reaches 0, they are removed from Entity.Map.

When all instances of Entity.EnemyChar are removed from Entity.Map, the program ends.
