package com.snake;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private SoundManager soundManager;
    private final String eatSound = "sounds/eat.wav"; // Path to your sound file
    protected final int SCREEN_WIDTH = 600;
    protected final int SCREEN_HEIGHT = 600;
    protected final int UNIT_SIZE = 25;
    protected final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    protected final int DELAY = 75;

    protected final int x[] = new int[GAME_UNITS];
    protected final int y[] = new int[GAME_UNITS];
    protected int bodyParts = 6;
    protected int applesEaten = 0; // Change to protected
    protected int appleX;
    protected int appleY;
    protected char direction = 'R';
    protected boolean running = false;
    protected Timer timer;
    protected boolean paused = false;

    public GamePanel() {
        // Initialize the SoundManager
        this.soundManager = new SoundManager();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void showPausedMessage(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Paused", (SCREEN_WIDTH - metrics.stringWidth("Paused")) / 2, SCREEN_HEIGHT / 2);
    }

    public void draw(Graphics g) {
        if (running) {
            // Draw apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    
            // Draw snake body
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
    
            // Draw the score
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 20));
            String scoreText = "Score: " + applesEaten;
            g.drawString(scoreText, SCREEN_WIDTH - g.getFontMetrics().stringWidth(scoreText) - 10, 30); // Adjust position as needed
    
            if (paused) {
                showPausedMessage(g);  // Show paused message if the game is paused
            }
        } else {
            gameOver(g);
        }
    }
    
    public void newApple() {
        appleX = (int) (Math.random() * (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = (int) (Math.random() * (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            // Play the sound when an apple is eaten
            soundManager.playSound(eatSound);
            newApple();
        }
        if (applesEaten >= 7) {
            // Move to Level Two
            transitionToLevelTwo();
        }
    }
    private void transitionToLevelTwo() {
    // Stop the current timer
    timer.stop();
    
    // Create and switch to LevelTwo
    LevelTwo levelTwo = new LevelTwo();
    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
    frame.getContentPane().removeAll();
    frame.getContentPane().add(levelTwo);
    frame.revalidate();
    frame.repaint();
}


public void checkCollisions() {
    // Check for regular collisions (snake colliding with itself, walls, or obstacles)
    for (int i = bodyParts; i > 0; i--) {
        if ((x[0] == x[i]) && (y[0] == y[i])) {
            running = false;
            handleGameOver();  // Show dialog when the snake hits itself
        }
    }
    // Check if head touches left border
    if (x[0] < 0) {
        running = false;
        handleGameOver();  // Show dialog when the snake hits the wall
    }
    // Check if head touches right border
    if (x[0] > SCREEN_WIDTH) {
        running = false;
        handleGameOver();  // Show dialog when the snake hits the wall
    }
    // Check if head touches top border
    if (y[0] < 0) {
        running = false;
        handleGameOver();  // Show dialog when the snake hits the wall
    }
    // Check if head touches bottom border
    if (y[0] > SCREEN_HEIGHT) {
        running = false;
        handleGameOver();  // Show dialog when the snake hits the wall
    }
}

   
    protected void handleGameOver() {
        // Stop the game timer when the collision happens
        timer.stop();
        
        // Show option dialog for restart or quit
        int choice = JOptionPane.showOptionDialog(this,
                "Game Over! Would you like to restart or quit?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Restart", "Quit"},
                "Restart");
    
        if (choice == JOptionPane.YES_OPTION) {
            restartGame();  // Restart the game if the user chooses to
        } else {
            System.exit(0);  // Exit the game if the user chooses to quit
        }
    }
    
    private void restartGame() {
        // Reset game variables to initial state
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        running = true;
        paused = false;  // Ensure the game is not paused
    
        // Reset snake's position
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
    
        // Place a new apple and restart the timer
        newApple();
        timer.restart();
    }
    





    public void gameOver(Graphics g) {
        // Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R' && !paused) {
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L' && !paused) {
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D' && !paused) {
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U' && !paused) {
                    direction = 'D';
                }
                break;
            case KeyEvent.VK_P:  // Pause key
                if (running) {
                    paused = !paused;  // Toggle the pause state
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    
}
