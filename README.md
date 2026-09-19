# Snake Game
A classic Snake game developed in Java. The game challenges players to control a snake, collect apples, and avoid obstacles while progressing through multiple levels, each with increasing difficulty.

- **Classic Gameplay:** Control a snake to eat apples and grow longer without hitting the walls or itself.
- **Level Progression:** Unlock new levels with unique challenges as you collect more apples.
- **Score Tracking:** Displays the player's score based on the number of apples eaten.
- **Simple Controls:** Navigate the snake using keyboard arrows.
- **Pause:** Pause and resume the game at any time.

## Objective
Eat as many apples as possible to grow the snake and progress through all 5 levels.

## Levels
Each level has its own background color and its own obstacle color, so you can tell at a glance how far you've progressed. Obstacles are cumulative — each new level adds its obstacles on top of the ones from earlier levels, on top of a faster snake.

| Level | Speed | Background | Obstacle color | Notes |
|---|---|---|---|---|
| 1 | Moderate | Black | — | Basic gameplay, no obstacles |
| 2 | Faster | Dark navy blue | Blue | 5 obstacles added |
| 3 | Faster still | Dark maroon | Green | 10 more obstacles added |
| 4 | Very fast | Dark purple | Purple | 8 more obstacles added |
| 5 | Fastest | Near-black | Yellow | 7 more obstacles added — final level |

Eat 7 apples on a level to advance to the next one. Clear Level 5 and you win the game, with the option to play again from Level 1.

## Installation
Requires a JDK (Java 17 or later).

Clone the repository:
```bash
git clone https://github.com/your-username/snakegame.git
cd snakegame
```

Compile the game:
```bash
javac -d target/classes src/main/java/com/snake/*.java
```

Run the game:
```bash
java -cp target/classes com.snake.SnakeGame
```

## How to Play
Launch the game using the instructions above.

- **Move the Snake:** Use the arrow keys to move the snake up, down, left, or right.
- **Pause / Resume:** Press `P` to pause the game, press `P` again to resume.
- **Collect Apples** to grow the snake and gain points.
- **Avoid Obstacles** and the snake's own tail to stay alive.
- **Progress Through Levels** as you reach 7 apples eaten on the current level.
- **Win the Game** by clearing Level 5.

## Project Structure
```
src/main/java/com/snake/
├── SnakeGame.java   Entry point (main method)
├── GameFrame.java   The game window
├── GamePanel.java   Level 1 - game loop, rendering, scoring, pause, game over/win handling
├── LevelTwo.java    Level 2 - adds obstacles and increases speed
├── LevelThree.java  Level 3 - adds more obstacles and increases speed further
├── LevelFour.java   Level 4 - adds more obstacles and increases speed further
└── LevelFive.java   Level 5 - final level, adds more obstacles and the win condition
```

## Credits
Developed by Siyabonga Nguza.
