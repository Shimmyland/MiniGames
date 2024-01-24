
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args){
        System.out.println("Welcome to Guess the Number game!");

        Random random = new Random();
        int guessTheNumber = random.nextInt(10) + 1;

        Scanner scanner = new Scanner(System.in);
        int myNumber;

        do {
            while (true){
                try {
                    System.out.print("Guess the number between 1 and 10: ");
                    myNumber = scanner.nextInt();
                    break;
                } catch (InputMismatchException e){
                    System.out.println("Wrong input. Try again");
                    scanner.next();
                }
            }

            if (myNumber < guessTheNumber){
                System.out.println("Your number is too low.");
            } else if (myNumber > guessTheNumber){
                System.out.println("Your number is too high.");
            } else {
                System.out.println("Congrats! You got the number!");
            }
        } while (myNumber != guessTheNumber);


    }
}
