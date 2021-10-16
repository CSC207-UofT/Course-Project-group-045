Phase 0 Progress Report:

Specification Summary:

Turn based grid based strategy game inspired by the likes of fire emblem, advance wars, and xcom.

Goal of the game is to clear all enemies characters off the map while keeping player characters alive.

Game commands will be represented and issued by GUI through mouse and keyboard commands.

Additional information will also be printed in the console/terminal

-------------------------------------------------------------------------------------

Scenario Walkthrough Summary:

Note:*UI is implemented but not connected to skeleton program. Instead, skeleton program prints and takes text inputs from the console/terminal*

Game class main is run to start the program, and instances of Map, PlayerChar and EnemyChar are created.

Map class takes character classes and places them onto the map.

Game class loops, takes inputs and performs checks to ensure inputs can be translated into commands issued through Action class.

If an Action results in a character having less than 0 health, character is removed from Map.

When all EnemyChar are removed from Map, the program ends.




----------------------------------------------------------------

CRC model:

We have a total of 11 CRC cards as of now, though not all of them have been implemented into code as of Phase 0.

So far for our CRC cards that are implemented into code.

Character Class: we have an abstract character class of which its methods and
attributes are inherited by its subclasses of PlayerChar and EnemyChar.

As of phase 0 we have two subclasses for the abstract character class

Class PlayerChar: This would be a subclass of the Character class inheriting its methods and setting
a personal name for the PlayerChar object

Class EnemyChar: This would be a subclass of the Character class inheriting its methods and setting
its own personal name for this specific EnemyChar object

Action Class: Action class that modifies the attributes of other classes, when an action is performed by a player. It is in charge
of making the necessary changes in other classes when an action if performed by a player, this includes
attributes within classes of character, map and game, which is what we implemented so far as of now, but
in the future it will also modify attributes within classes inventory and item.

Map Class: The Map class essentially creates a 2D array that functions as our grid and coordinates for
characters to be placed on and move around in the array. Although we have yet to implement this feature yet
in the future items will be scattered across coordinates of the 2D array that can be picked up by characters
on the map.

As if phase 0 we have 1 subclass for the Map Class

CLass Grass: This would be a subclass of Class Map and would serve as a default terrain type, not
giving any benefits or penalties for characters

Game Class:

Classes that we have planned in our CRC model, but have yet to implement it as of phase 0.

Inventory Class: The inventory class creates an inventory or storage for item objects. Interacting
with the classes character, item and actions, with actions utilizing items in the inventory and
provide bonuses to characters

Item Class: The abstract item class will store information on the status of different items, perform
a certain action or functionality when used, and they can all be picked up when randomly scattered
on the map by a character in range of the item or dropped, in order to do this it will have to interact
with the character and map classes

As of phase 0 we have 3 subclasses or unique items for the abstract class item

Class AtkPot: As a subclass of item is inherits methods from its parents, enhances a character's
attack power by a certain amount, it will collaborate with classes Characters, any character subclasses,
Actions, map and of course its parent class Item

Class DefPot: As a subclass of item is inherits methods from its parents, enhances a character's
defense power by a certain amount, it will collaborate with classes Characters, any character subclasses,
Actions, map and of course its parent class Item

Class HpPot: As a subclass of item is inherits methods from its parents, enhances a character's
total health by a certain amount, it will collaborate with classes Characters, any character subclasses,
Actions, map and of course its parent class Item

Something to keep in mind is that as of now so far, we have decided that the AI will not have its
own inventory and EnemyChar will not be able to pick up items

----------------------------------------------------------------



----------------------------------------------------------------

What each group member has been working on

We each have been working on specific aspects of the code as well as discussing together
and checking on each other's work whenever we can as well as raising important questions
about the state and potential future of our game.

In particular Jack has been working on the graphics that we will be implementing for
the game as well the UI, Antony and Jonathan constantly reviewed the work we have done by
checking the structure of certain code and editing it to fit intellij's parameters as well
as the rules of clean architechture, and refactoring the code. Jonathan specifically
worked on making a test case for our program, while Antony wrote the progress report and
added the necessary modifications to the CRC cards to match the code that we have so far
as much as possible. Zihao in particular coded the map class and actions class, while James
wrote the Game class.

----------------------------------------------------------------

What has worked well so far with your design as you have started implementing your code

I would say that our interactions between the characters through our action class, has worked
as well as we have expected it to work so far, as it performs the basic actions like attack
and modifies the attributes of the characters exactly as it should.

-----------------------------------------------------------------

We do have one important question for the TA that involves the graphics and UI we are implementing
and that is if we are allowed to use assets that are not personally created by us,
specifically sprites that we will use and if that is allowed. Another question we do have
is
