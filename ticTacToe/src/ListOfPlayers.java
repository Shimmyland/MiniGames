
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListOfPlayers {
    private ArrayList<Player> players;


    public ListOfPlayers() {
        this.players = new ArrayList<>();
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void add(Player input) {
        players.add(input);
    }

    public void removeAll() {
        players.clear();
    }

    public void addAgain(int i, Player input) {
        if (players.get(i) != null){
            players.set(i, input);
        } else {
            players.add(input);
        }
    }

    public int setNumberOfPlayers(Scanner scanner) {
        int numberOfPlayers;
        while (true) {
            try {
                System.out.print("How many players are going to play: ");
                numberOfPlayers = scanner.nextInt();
                if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
                    break;
                } else if (numberOfPlayers > 4) {
                    System.out.println("Too many players, the maximum is four.");
                } else if (numberOfPlayers == 1) {
                    System.out.println("You can't play only with yourself. Come on buddy, try to be more social.");
                } else {    // numberOfPlayers == 0;
                    System.out.println("Really? You want to play a game, where is ZERO number of players?");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again.");
                scanner.next();
            }
        }
        System.out.println();
        return numberOfPlayers;
    }
}
