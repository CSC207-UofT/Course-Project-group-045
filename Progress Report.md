Phase 0 Progress Report:

Specification:

Running the project will open a turn-based game where a player will be able to issue commands to characters with the goal of defeating all enemies on the map.

The game map will be represented by a 2D grid on which characters will be placed. Each cell of the map contains a terrain type which may provide certain stat bonuses or be impassable to certain character types.

Characters will have stats that determine their damage in combat as well as other utility. When a character’s health stat reaches zero, they will be removed from the map.

By selecting characters on the graphical interface and using keyboard and mouse commands, the player will be able to:
-	Move characters across the map based on their movement stat
-	Use a character to attack an opposing character
-	Have a character use an item
-	Have a character equip/change weapons

Information regarding game data (ex. Position of characters on the map, damage calculation of a battle) will appear in the command line

Each character will be allowed to perform one action per turn. Once all of the player character’s actions have been completed, the turn ends and the enemy characters will take their turn performing actions. Turns repeat until the player is victorious or loses all of their characters.

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

UI Class: The UI class is responsible for implimenting the features of the JFrame created in the Game class. It creates all of the 
graphics and animations for the game. This feature is currently not fully finished as the UI is not connected to the other classes
other than Game, it is currently more of a demonstration of what the UI would look like when it's fully connected to the other classes.

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
specifically sprites that we will use and if that is allowed.
