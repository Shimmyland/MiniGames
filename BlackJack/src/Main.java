import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        do {
            // start a game
            Game game = new Game();

            // distribution of cards
            game.distribution();

            // show hand
            game.showHands();

            // action
            game.action();

            // win condition
            game.winner();

            // prepare the next game

            // play again
        } while (playAgain());
    }

    public static boolean playAgain(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to play again?(y/n)");
        String answer = scanner.next().toLowerCase();

        while (true) {
            if (Character.isDigit(answer.charAt(0))) {
                System.out.println("Wrong input, try again.");
            } else {
                if (answer.equals("y")) {
                    return true;
                } else if (answer.equals("n")) {
                    return false;
                } else {
                    System.out.println("Wrong input, try again.");
                }
            }
        }
    }

}
