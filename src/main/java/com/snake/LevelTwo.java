package com.snake;

import javax.swing.*;
import java.awt.*;

public class LevelTwo extends GamePanel {
    private final int LEVEL_TWO_DELAY = 50; // Faster speed for level two
    private int obstaclesX[];
    private int obstaclesY[];
    private final int OBSTACLES_COUNT = 5;
    
    public LevelTwo() {
        super();
        timer.stop(); // Stop the timer from GamePanel if it's running
        timer = new Timer(LEVEL_TWO_DELAY, this); // Set a faster speed for Level 2
        generateObstacles();  // Create obstacles
        startGame();
    }

    // Override the draw method to also draw the obstacles
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // Draw obstacles if the game is running
        if (running) {
            g.setColor(Color.blue);
            for (int i = 0; i < OBSTACLES_COUNT; i++) {
                g.fillRect(obstaclesX[i], obstaclesY[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    // Generate obstacles randomly on the map
    private void generateObstacles() {
        obstaclesX = new int[OBSTACLES_COUNT];
        obstaclesY = new int[OBSTACLES_COUNT];

        for (int i = 0; i < OBSTACLES_COUNT; i++) {
            obstaclesX[i] = (int) (Math.random() * (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            obstaclesY[i] = (int) (Math.random() * (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        }
    }

    // Override the checkCollisions method to add collisions with obstacles
    @Override
    public void checkCollisions() {
        super.checkCollisions();  // Check for regular collisions

        // Check if the snake head hits an obstacle
        for (int i = 0; i < OBSTACLES_COUNT; i++) {
            if (x[0] == obstaclesX[i] && y[0] == obstaclesY[i]) {
                running = false;  // End game if snake hits an obstacle
                timer.stop();
            }
        }
    }

    // Optionally, make it harder to collect apples by adding more conditions or increasing frequency
    @Override
    public void checkApple() {
        super.checkApple();
        if (applesEaten % 5 == 0 && applesEaten != 0) {
            generateObstacles();  // Every 5 apples, add more obstacles to the game
        }
    }
}
