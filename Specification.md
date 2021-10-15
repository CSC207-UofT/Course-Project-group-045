Specification

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