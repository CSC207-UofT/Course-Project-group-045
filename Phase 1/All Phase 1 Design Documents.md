Design Documentation

To run the program, run the main method in the Controllers.Game class. The game uses mouse controls.


Updated Specification: we have added movement for the characters, where we allow our characters to 
mover around the map and change each charater's location each turn, the extent of how much they can
move is dependent on their speed, basically it dictates how many spaces they can move like a chess board.
We expanded on solid characters for the games, rather than having dummy characters with the same stats,
these characters all have different stats based on their unqiue traits and fighting styles.
We also added the new Entity.Item features, objects that characters use and consume so far, only once to 
modify a character's stats, like their health, attack power and speed.
We also have a increased Controllers.UI functionality to provide a better player experience for the game.
As for the map we have also expanded the size of the map from 2x2 grid to a 6x8 grid. 

Design Decisions: We have decided to implement that a maximum of 5 characters for purposes of time and 
enough room to expand on functionality. For Phase 1 we have decided to implement only 2 for now
to test out functionality and how it interacts with the other classes. We have also decided
to leave items as only consumables for now, meaning once a character uses one item it is gone
for good, this is for the decision to keep it simple as of Phase 1, but the possibility of weapons
has been discussed.

Clean Architecture: We have segregated our classes into packages based on their roles in the clean architecture
hierarchy. Characters, items and the map count as entities. The UseCase.Action class is our use case. The Controllers.Game class is
a gateway, and the Controllers.UI class is self-explanatory in its purpose. 

Solid Design Principles:
Single Responsibility principle - All of our classes have a single responsibility.
Open/closed principle- All of our entities can be extended and we can modify details to
subclasses without changing the functionality of the entity.
Liskov Substitution method - Our item interface, where you may make an item that is a consumable
can be replaced by the parent type of Entity.Item, or Hppot the type can be substituted as a consumable.
Interface Segregation Principle - Our item interface is relatively small, with just a view key 
functionalities.
Dependency Inversion Principle - Changing individual pieces of our code, wouldn't require us to change
other pieces of our code, such as characters and item, changes to either of them are independent.

Design Patterns Implemented:
When it comes to implementation of the interactions between items and characters, I would say
we used the Dependency Injection pattern, where we avoid hard dependency between the two classes
and instead pass an object of character to a certain item method which prevents hard dependency.
In a way we also implemented the Strategy design pattern, where we use an Entity.Item interface, where multiple
different items with different statistical modifiers implement Entity.Item, so when implementing interactions
like characters, the action class, or the game class, it relies upon the Entity.Item interface, which means
it is only dependent on the particular functions that all items have.


Progress Report:
Open Questions our group is struggling with: How to make our Controllers.UI, less bloated in our code, we just
don't know how to. 
With the exception of the Controllers.UI, we believe modifying parts of our codes, was efficient and easy,
as we didn't need to modify other parts of our code while doing it.
What each group member is working on
Jack: Jack has currently been working on the Controllers.UI, making the sprites and the animations for the 
different characters that we have implemented, and he will continue to do that as we work 
towards Phase 2.

Jonathan: Jonathan has been working on the abstract character class, while helping and inspecting the design
pattern and functionality of our other classes such as items and the subclasses of characters, providing
suggestions and implementing the core functions for every character as in the actions that they 
can do, and he will continue to expand on implementing character functionality and new character 
abilities that may be implemented in phase 2.

Antony: Antony has been working on the item functionality, implementing the Entity.Item interface and 
creating the new consumable class that implements the item interface, where it's subclasses are three
new solid items that change the stats of a character upon consumption, while he also impemented 
the new solid characters based on the ideas and implemtations made by Jonathan, he will continue to work
on new subclass of items that implements item, known as weapons.

James: James has been implemeting the game class, that starts the game and essentially ties everything 
together, implementing functionality that conincides with the Controllers.UI that Jack has been working on and 
putting together other functionality like characters, items and the action class to essentially
create tha skeleton of the game, and he has pretty much layed out how the game uses each specific functionality
availible, and will continue to work on this while further functionality will be implemented in phase 2.

Zihao: Zihao has built the action class that interacts with characters, items and as well as the map
class that creates the grid where the characters would be layed out and would also interact with 
the Controllers.UI system. He has added additonal functionality to the action class to further interact with 
new implementations like the Entity.Item interface and the new expanded character functionality, as well
as expanind the map into a larger grid, specifically 6x8 map, and he will continue expand 
on the action class to interact with new functionaities implemented in phase 2
and further expansions of maps and terrain as we progress further.
