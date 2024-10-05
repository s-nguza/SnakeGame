package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelThree extends LevelTwo {
    private final int LEVEL_THREE_DELAY = 30;  // Even faster speed for Level Three
    private final int OBSTACLES_COUNT_LEVEL_THREE = 10; // More obstacles
    private boolean obstaclesMove = true;  // Flag to determine if obstacles should move
    private int obstaclesX[];
    private int obstaclesY[];

    public LevelThree() {
        super();
        timer.stop(); // Stop the timer from LevelTwo if it's running
        timer = new Timer(LEVEL_THREE_DELAY, this); // Set a faster speed for Level 3
        this.requestFocusInWindow();  // Request focus to ensure keystrokes are captured

        generateObstacles();  // Create more obstacles
        startGame();
    }

    // Override the draw method to also draw obstacles
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        // Draw more obstacles for level three if the game is running
        if (running) {
            g.setColor(Color.orange);  // Change color to differentiate
            for (int i = 0; i < OBSTACLES_COUNT_LEVEL_THREE; i++) {
                g.fillRect(obstaclesX[i], obstaclesY[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }

    
    protected void generateObstacles() {
        obstaclesX = new int[OBSTACLES_COUNT_LEVEL_THREE];
        obstaclesY = new int[OBSTACLES_COUNT_LEVEL_THREE];

        for (int i = 0; i < OBSTACLES_COUNT_LEVEL_THREE; i++) {
            obstaclesX[i] = (int) (Math.random() * (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            obstaclesY[i] = (int) (Math.random() * (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        }
    }

    

    // Override the actionPerformed method to update moving obstacles
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            move();
            checkApple();
            checkCollisions();
           
        }
        repaint();
    }

    // Override the collision check to account for moving obstacles
    @Override
    public void checkCollisions() {
        super.checkCollisions();  // Regular snake collision checks

        // Check if the snake head hits a moving obstacle
        for (int i = 0; i < OBSTACLES_COUNT_LEVEL_THREE; i++) {
            if (x[0] == obstaclesX[i] && y[0] == obstaclesY[i]) {
                running = false;  // End game if snake hits a moving obstacle
                handleGameOver();
            }
        }
    }
}
