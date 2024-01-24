import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int widthAndHeight = 800;
        int tileSize = 40;

        JFrame frame = new JFrame("Snake Game");
        frame.setSize(widthAndHeight, widthAndHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       // close app on closing the window
        frame.setResizable(false);                                  // frame can't be resized, blocks the yellow button
        frame.setLocationRelativeTo(null);                          // frame appears in the middle of screen?

        MyPanel gamePanel = new MyPanel(widthAndHeight, tileSize);
        frame.add(gamePanel);
        frame.addKeyListener(gamePanel);

        frame.setVisible(true);                                     // show the JFrame
        frame.pack();

    }
}
