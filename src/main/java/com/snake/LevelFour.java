package com.snake;

import javax.swing.*;
import java.awt.*;

public class LevelFour extends LevelThree {
    private final int LEVEL_FOUR_DELAY = 22; // Faster speed for level four
    private final int OBSTACLES_COUNT_LEVEL_FOUR = 8; // Extra obstacles on top of levels 2 & 3
    private int obstaclesX[];
    private int obstaclesY[];

    public LevelFour() {
        super();
        timer.stop(); // Stop the timer inherited from LevelThree
        timer = new Timer(LEVEL_FOUR_DELAY, this); // Set a faster speed for Level 4
        this.requestFocusInWindow();  // Request focus to ensure keystrokes are captured

        generateLevelFourObstacles();  // Create this level's own obstacles
        startGame();
    }

    // Give Level Four its own background
    @Override
    protected Color getLevelBackgroundColor() {
        return new Color(35, 10, 45); // Dark purple
    }

    // Draw this level's obstacles on top of the ones from earlier levels
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        if (running) {
            g.setColor(new Color(128, 0, 128)); // Level 4's own obstacle color (purple)
            for (int i = 0; i < OBSTACLES_COUNT_LEVEL_FOUR; i++) {
                g.fillRect(obstaclesX[i], obstaclesY[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    // Generate this level's obstacles
    private void generateLevelFourObstacles() {
        obstaclesX = new int[OBSTACLES_COUNT_LEVEL_FOUR];
        obstaclesY = new int[OBSTACLES_COUNT_LEVEL_FOUR];

        for (int i = 0; i < OBSTACLES_COUNT_LEVEL_FOUR; i++) {
            obstaclesX[i] = (int) (Math.random() * (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            obstaclesY[i] = (int) (Math.random() * (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        }
    }

    // Add collisions with this level's own obstacles
    @Override
    public void checkCollisions() {
        super.checkCollisions();  // Checks collisions with levels 2 & 3's obstacles too

        for (int i = 0; i < OBSTACLES_COUNT_LEVEL_FOUR; i++) {
            if (x[0] == obstaclesX[i] && y[0] == obstaclesY[i]) {
                running = false;
                handleGameOver();
            }
        }
    }

    @Override
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
        if (applesEaten >= 7) {
            transitionToLevelFive();
        }
    }

    private void transitionToLevelFive() {
        timer.stop();

        LevelFive levelFive = new LevelFive();
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(levelFive);
        levelFive.requestFocusInWindow();
        frame.setTitle("Snake Game - Level 5");

        frame.revalidate();
        frame.repaint();
    }
}
