import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener {
    private final int widthAndHeight;
    private final int tileSize;


    // automatic movement
    private int directionX;
    private int directionY;


    // main objects
    private final Tile head;
    private final ArrayList<Tile> body;
    private final Tile food;


    // others
    Random random;
    Timer gameLoop;
    boolean gameOver;


    // Constructor
    public MyPanel(int widthAndHeight, int tileSize) {
        this.widthAndHeight = widthAndHeight;
        this.tileSize = tileSize;
        this.setPreferredSize(new Dimension(widthAndHeight, widthAndHeight));
        this.setBackground(new Color(192, 192, 192));

        random = new Random();

        // set params -> for the 1st time, between 5 and 15 (including)
        head = new Tile(random.nextInt(5) + 5, random.nextInt(5) + 5);
        body = new ArrayList<Tile>();
        food = new Tile(random.nextInt(5) + 5, random.nextInt(5) + 5);

        // set 1st movement
        directionX = 1;
        directionY = 0;

        // start the action loop by timer
        gameLoop = new Timer(100, this);
        gameLoop.start();
    }


    // draw
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // set custom colors;
        Color customGrey = new Color(192, 192, 192);
        Color customOrange = new Color(255, 128, 0);
        Color customBlack = new Color(64, 64, 64);
        Color customRed = new Color(153, 0, 0);
        int colorValue = 64;

        // grid lines
        g.setColor(customGrey);
        for (int i = 0; i < widthAndHeight / tileSize; i++) {
            g.drawLine(i * tileSize, 0, i * tileSize, widthAndHeight);
            g.drawLine(0, i * tileSize, widthAndHeight, i * tileSize);
        }

        // food
        g.setColor(customOrange);
        g.fillOval(food.getX() * tileSize, food.getY() * tileSize, tileSize, tileSize);

        // head
        g.setColor(customBlack);
        g.fillRect(head.getX() * tileSize, head.getY() * tileSize, tileSize, tileSize);

        // body
        for (Tile tmp : body) {
            colorValue += colorValue / 20; // every body part will have a slightly brighter color (+5%)
            g.setColor(new Color(colorValue, colorValue, colorValue));
            g.fillRect(tmp.getX() * tileSize, tmp.getY() * tileSize, tileSize, tileSize);
        }

        // statistics
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.ITALIC, 16));
        g.drawString("Score: " + body.size(), 15,30);

        // game over
        if (gameOver) {
            // set the message
            g.setColor(customRed);
            String gameOverMessage1 = "Game Over! Your score is " + body.size();
            g.setFont(new Font("Arial", Font.BOLD, 30));

            // calculate position
            int messageX = (getWidth() - g.getFontMetrics().stringWidth(gameOverMessage1)) / 2;
            int messageY = getHeight() / 3;

            // draw
            g.drawString(gameOverMessage1, messageX, messageY);


            String gameOverMessage2 = "Play again by pressing SPACE";
            g.setFont(new Font("Arial", Font.ITALIC, 20));

            messageX = (getWidth() - g.getFontMetrics().stringWidth(gameOverMessage2)) / 2;
            messageY = (getHeight() + g.getFontMetrics().stringWidth(gameOverMessage2)) / 3;

            g.drawString(gameOverMessage2, messageX, messageY);
        }
    }


    // moving objects
    public void move() {
        // spawn new food if it was taken
        if (collision(head, food)) {
            body.add(new Tile(food.getX() * tileSize, food.getY() * tileSize));
            food.setX(random.nextInt(16));
            food.setY(random.nextInt(16));
        }

        // move body parts of snake
        for (int i = body.size() - 1; i >= 0; i--) {
            if (i == 0) {
                body.get(i).setX(head.getX());
                body.get(i).setY(head.getY());
            } else {
                body.get(i).setX(body.get(i - 1).getX());
                body.get(i).setY(body.get(i - 1).getY());
            }
        }

        // move head of snake
        head.move(directionX, directionY);

        // Validation - head hit into body
        for (Tile tmp : body) {
            if (collision(head, tmp)) {
                gameOver = true;
            }
        }
        // Validation - head hit wall
        if (outOfBorder()) {
            System.out.println("ende!");
            gameOver = true;
        }
    }


    // conditions
    public boolean collision(Tile input1, Tile input2) {
        return input1.getX() * tileSize == input2.getX() * tileSize && input1.getY() * tileSize == input2.getY() * tileSize;
    }

    public boolean outOfBorder() {
        return head.getX() * tileSize < 0 || head.getX() * tileSize > widthAndHeight ||
                head.getY() * tileSize < 0 || head.getY() * tileSize > widthAndHeight;
    }


    // Action while timer is on
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if (gameOver) {
            gameLoop.stop();
        }
    }


    // Movement during the game
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP && directionY != 1) {
            directionX = 0;
            directionY = -1;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && directionY != -1) {
            directionX = 0;
            directionY = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && directionX != 1) {
            directionX = -1;
            directionY = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && directionX != -1) {
            directionX = 1;
            directionY = 0;
        }
    }


    // PlayAgain
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && gameOver) {
            resetGame();
            gameLoop.start();
        }
    }

    public void resetGame() {
        food.setX(random.nextInt(5) + 5);
        food.setY(random.nextInt(5) + 5);

        head.setX(random.nextInt(5) + 5);
        head.setY(random.nextInt(5) + 5);

        body.clear();

        directionX = 1;
        directionY = 0;

        gameOver = false;
    }


    // not needed
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
