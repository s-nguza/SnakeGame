package com.snake;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.add(new GamePanel());  // Start with Level 1
        this.setTitle("Snake Game - Level 1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public void startLevelTwo() {
        this.getContentPane().removeAll();
        this.add(new LevelTwo());  // Switch to Level 2
        this.setTitle("Snake Game - Level 2");
        this.pack();
        this.revalidate();
    }
}
