package com.snake;

public class LevelManager {
    private int currentLevel = 1;
    private int applesEaten = 0;
    private int applesToNextLevel = 5; // Base apples needed for the first level
    private int score = 0;
    private int delay = 75; // Base delay for the first level

    public LevelManager() {
    }

    // Check if the player should move to the next level
    public void checkLevelProgress() {
        if (applesEaten >= applesToNextLevel) {
            nextLevel();
        }
    }

    // Move to the next level
    public void nextLevel() {
        currentLevel++;
        applesEaten = 0; // Reset apples eaten for the next level
        applesToNextLevel += 5; // Increase the number of apples needed for the next level
        increaseDifficulty();
    }

    // Adjust the difficulty as levels progress
    private void increaseDifficulty() {
        delay -= 10; // Decrease delay to speed up the game
    }

    // Increment apples eaten
    public void appleEaten() {
        applesEaten++;
        score += 10; // Increment score for each apple eaten
    }

    // Getters for important data
    public int getCurrentLevel() {
        return currentLevel;
    }

    public int getScore() {
        return score;
    }

    public int getDelay() {
        return delay;
    }
}
