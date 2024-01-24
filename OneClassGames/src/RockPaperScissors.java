import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class RockPaperScissors {
    public static void main(String[] args) {
        int player = setPlayer();
        int enemy = setEnemy();

        displaySelection(player, enemy);
        calculate(player, enemy);
        winnerIs(player,enemy);
        askToRepeat();
    }

    public static int setPlayer() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Choose Rock (0), Paper (1) or Scissors (2): ");
                int input = scanner.nextInt();
                int input2 = scanner.nextInt();

                if (input < 0 || input > 2) { // wrong input number
                    System.out.println("Wrong input, try it again please. Type a number between 0 and 2.");
                    continue;
                }
                return input;
            } catch (InputMismatchException e){ // not a number
                System.out.println("Wrong input, try it again please. Type a number between 0 and 2.");
                scanner.next(); // consume the incorrect input
            }
        }
    }

    public static int setEnemy() {
        Random random = new Random();
        return random.nextInt(3);
    }

    public static void displaySelection(int player, int enemy) {
        System.out.println("---------------------");

        if (player == 0){
            System.out.println("You: Rock");
        } else if (player == 1) {
            System.out.println("You: Paper");
        } else {
            System.out.println("You: Scissors");
        }

        if (enemy == 0){
            System.out.println("Opponent: Rock");
        } else if (enemy == 1) {
            System.out.println("Opponent: Paper");
        } else {
            System.out.println("Opponent: Scissors");
        }
    }

    public static void calculate(int player, int enemy) {
        if ((player == 0 && enemy == 2) || (enemy == 0 && player == 2)){
            System.out.println("Rock crushes Scissors (Rock wins against Scissors).");
        } else if ((player == 2 && enemy == 1) || (enemy == 2 && player == 1)) {
            System.out.println("Scissors cut Paper (Scissors win against Paper).");
        } else {
            System.out.println("Paper covers Rock (Paper wins against Rock).");
        }
    }

    public static void winnerIs(int player, int enemy){
        System.out.println("---------------------");

        if (player == enemy) {
            System.out.println("Draw!");
        } else if ((player == 0 && enemy == 2) || (player == 1 && enemy == 0) || (player == 2 && enemy == 1)) {
            System.out.println("You win!");
        } else {
            System.out.println("Enemy wins!");
        }
    }

    public static void askToRepeat() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------");
        System.out.print("Do you want to play again? (y/n): ");
        String answer = scanner.next().toLowerCase();

        if ("y".equals(answer)) {
            main(null); // reset the game
        } else {
            System.out.println("Thanks for playing!");
        }
    }
}
