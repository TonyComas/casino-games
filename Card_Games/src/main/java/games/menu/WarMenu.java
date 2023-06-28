package games.menu;

import games.deck.Card;

import java.util.Queue;
import java.util.Scanner;

public class WarMenu {
    private static final Scanner in = new Scanner(System.in);

    public void playerTwoWinsWar(){
        System.out.println("Player two won the game!");
    }

    public void playerOneWinsWar(){
        System.out.println("Player one won the game!");
    }
    public void battleIsDraw(){
        System.out.println("Was a draw!");
    }

    public void playerTwoWinsBattle(){
        System.out.println("Player two won the battle!");
    }

    public void playerOneWinsBattle(){
        System.out.println("Player one won the battle!");
    }

    public int askUserIfTheyWantToSeeGame(){
        System.out.println("Would you like to watch the game: ");
        System.out.println("1) Yes");
        System.out.println("2) no");
        System.out.print("Enter how many players will be playing against the house: ");
        int userInt = 0;
        while(true) {
            try {
                String userInput = in.nextLine();
                userInt = Integer.parseInt(userInput);
                if (userInt == 1 || userInt == 2) {
                    break;
                } else {
                    System.out.println("Invalid number, enter 1 for Yes or 2 for No");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please type a whole number");
            }
        }
        return userInt;
    }
    public void printWarStatus(Queue<Card> playerOneDeck, Queue<Card> playerTwoDeck){
        System.out.print("Player one has " + playerOneDeck.size() + " cards : player two has "
                + playerTwoDeck.size() + " cards  :  ");
    }

}
