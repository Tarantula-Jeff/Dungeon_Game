# Dungeon Game

A completed Java console dungeon crawler built around a custom doubly linked-list data structure. Create an adventurer, explore randomly generated rooms, fight goblins, collect healing potions, gain experience, and find the Moon Gate to reach the next level.

This project began as an unfinished personal dungeon-game prototype and was completed into a playable terminal game while retaining the original linked-list dungeon concept.

## Features

- Randomly generated dungeon rooms for each level
- Custom doubly linked-list dungeon implementation
- Left/right player movement with boundary checks
- Turn-based goblin combat
- Healing potions and inventory management
- Player health, attack, defence, experience, and level progression
- Experience-based level-ups with stronger player statistics
- Exit gate that creates a larger, more difficult next level
- Console map, status, and help commands

## Controls

| Command | Action |
| --- | --- |
| `M` | Display the dungeon map |
| `L` | Move left |
| `R` | Move right |
| `S` | View player statistics |
| `I` | Use a healing potion |
| `H` | View help |
| `Q` | Quit the game |

During combat:

| Command | Action |
| --- | --- |
| `F` | Fight the enemy |
| `P` | Use a potion |
| `S` | View player statistics |

## Data Structures and Concepts

- Custom doubly linked list with `DungeonCell`
- Custom iterator for traversing dungeon rooms
- Object-oriented inheritance for room encounters
- Random dungeon generation using `ArrayList`, `Collections.shuffle`, and `Random`
- Encapsulation of player, enemy, item, and exit state
- Console interaction using `Scanner`

## Project Structure

| File | Purpose |
| --- | --- |
| `DungeonTester.java` | Application entry point |
| `DungeonContainer.java` | Dungeon generation, movement, combat, and main game loop |
| `DungeonCell.java` | Node in the doubly linked-list dungeon |
| `Iterator.java` | Custom dungeon-cell iterator |
| `Encounter.java` | Base class for room encounters |
| `MainCharacter.java` | Player state, inventory, and level progression |
| `GoblinEnemy.java` | Randomised combat enemy |
| `Item.java`, `Empty.java`, `Exit.java` | Additional room encounter types |

## Requirements

- Java Development Kit (JDK) 17 or later

## Run Locally

Compile the project from the repository root:

```bash
javac -d out *.java
```

Run the game:

```bash
java -cp out dungeontester.DungeonTester
```

## Author

Jeffrey Ackah
