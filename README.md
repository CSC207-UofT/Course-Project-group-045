# Course-Project-group-045

Project Specification
- Turn based game, similar to fire emblem

Entity Classes
- Characters: represent in game characters, have stats(health, attack), have a class type, store weapon item and other items. Can perform actions (move, attack, heal)
- Item: has durability, has ability depending on type
- Map: stores position of characters

Use Cases:
- moving a character (changes character's position on map),
- using a character to attack (change character's health stat based on attack stats), 
- using an item (effect varies)

Controllers
- mouse input, keyboard inputs

Command Line Interface
-used to close/open game
  
- different Character classes, items types (abstracct class and interface)
- graphical user interface displaying stats and data
- class representing background/grid/terrain
- basic ai (actions based on algorithm)
- controller linked to graphical interface



Testtesttest

CRC Cards

Character (Abstract/interface)
- Subclasses: whatever we come up with
- Responsibilities: stores information regarding stats, has inventory of items, can perform actions
- Collaborators: item, subclasses, actions

Item (Abstract/interface)
- Subclasses: whatever we come up with
- Responsiblities: stores information (durability?), performs some sort of effect upon use
- Collaborators: character

Map
- Subclasses: 
- Responsibilites: Stores position of characters on grid
- Collaborators:
- takes terrain types of varied properties (stat bonuses, block movement)

Weapon
- Subclasses: diff weapon types
- responsibilities:
      providing more attack points for character ontop of their basic stats.
      provide multiplier for when combating characters with diff weapon types.
- Collaborators:
      Character, Item
