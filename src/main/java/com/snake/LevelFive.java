package com.snake;

import javax.swing.*;
import java.awt.*;

public class LevelFive extends LevelFour {
    private final int LEVEL_FIVE_DELAY = 16; // Fastest speed - final level
    private final int OBSTACLES_COUNT_LEVEL_FIVE = 7; // Extra obstacles on top of levels 2, 3 & 4
    private int obstaclesX[];
    private int obstaclesY[];

    public LevelFive() {
        super();
        timer.stop(); // Stop the timer inherited from LevelFour
        timer = new Timer(LEVEL_FIVE_DELAY, this); // Set the fastest speed for Level 5
        this.requestFocusInWindow();  // Request focus to ensure keystrokes are captured

        generateLevelFiveObstacles();  // Create this level's own obstacles
        startGame();
    }

    // Give Level Five its own background - darkest of all, final level
    @Override
    protected Color getLevelBackgroundColor() {
        return new Color(20, 10, 10); // Near-black with a faint red tint
    }

    // Draw this level's obstacles on top of the ones from earlier levels
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if (running) {
            g.setColor(Color.yellow); // Level 5's own obstacle color
            for (int i = 0; i < OBSTACLES_COUNT_LEVEL_FIVE; i++) {
                g.fillRect(obstaclesX[i], obstaclesY[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    // Generate this level's obstacles
    private void generateLevelFiveObstacles() {
        obstaclesX = new int[OBSTACLES_COUNT_LEVEL_FIVE];
        obstaclesY = new int[OBSTACLES_COUNT_LEVEL_FIVE];

        for (int i = 0; i < OBSTACLES_COUNT_LEVEL_FIVE; i++) {
            obstaclesX[i] = (int) (Math.random() * (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            obstaclesY[i] = (int) (Math.random() * (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        }
    }

    // Add collisions with this level's own obstacles
    @Override
    public void checkCollisions() {
        super.checkCollisions();  // Checks collisions with levels 2, 3 & 4's obstacles too

        for (int i = 0; i < OBSTACLES_COUNT_LEVEL_FIVE; i++) {
            if (x[0] == obstaclesX[i] && y[0] == obstaclesY[i]) {
                running = false;
                handleGameOver();
            }
        }
    }

    // Level Five is the final level - clearing it wins the game instead of
    // transitioning to another level.
    @Override
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
        if (applesEaten >= 7) {
            handleWin();
        }
    }
}
