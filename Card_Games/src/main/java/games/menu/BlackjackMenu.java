package games.menu;

import games.deck.Card;
import games.player.Player;

import java.sql.SQLOutput;
import java.util.Scanner;

public class BlackjackMenu {
    private static final Scanner in = new Scanner(System.in);
    MainMenu menu = new MainMenu();

    public void blackjackMenu(){
        System.out.println();
        System.out.println("Welcome to the BlackJack table!");
        System.out.println("This game is played with 1-7 players and usually 6-8 decks.");
    }
    public int askUserForNumberOfPlayers(){
        int numberOfPlayers = 0;
        while (true) {
            System.out.print("Enter how many players will be playing against the house: ");
            try {
                String userInput = in.nextLine();
                numberOfPlayers = Integer.parseInt(userInput);
                if(numberOfPlayers > 0 && numberOfPlayers <= 7) {
                    break;
                } else {
                    System.out.println("Invalid number of players.");
                    System.out.println("Enter a number 1-7:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please type a whole number");
            }
        }
        return numberOfPlayers;
    }
    public String askUserForName(int playerNumber){
        System.out.print("Enter a name for player " + playerNumber + ": ");
        return in.nextLine();
    }
    public int askUserForNumberOfDecks() {
        System.out.print("Enter how many decks you would like to play with: ");
        int numberOfDecks = 0;
        while (true) {
            try {
                String userInput = in.nextLine();
                numberOfDecks = Integer.parseInt(userInput);
                if (numberOfDecks > 0) {
                    break;
                } else {
                    System.out.print("Invalid number, try again: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please type a whole number");
            }
        }
        return numberOfDecks;
    }

    public void showBlackjackHand(Player player){
        System.out.println("-----------------------------------");
        menu.showHand(player);
        System.out.println("Giving them a score of: " + player.getBjHandValue());
    }
    public void blackjackActionMenu(){
        System.out.println("1) Stand");
        System.out.println("2) Hit");
        System.out.println("3) Double Down");
        System.out.println("4) Split");
        System.out.println("5) Surrender");
        System.out.println("6) Show Card Count");
    }

    public int askForAction(){
        System.out.print("Select an action: ");
        int pickedAction = 0;
        while (true) {
            try {
                String userInput = in.nextLine();
                pickedAction = Integer.parseInt(userInput);
                if (pickedAction >= 1 && pickedAction <= 6) {
                    break;
                } else {
                    System.out.print("Invalid number, try again: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please type a whole number: ");
            }
        }
        return pickedAction;
    }
    public void overLimit(Player player){
        System.out.println(player.getName() + " has gone over 21. Their turn is over.");
        System.out.println();
    }
    public void showDraw(Card card){
        System.out.print("You drew a : ");
        menu.printCard(card);
    }
    public void playerWins(Player player){
        System.out.println(player.getName() + " beat the house");
    }
    public void playerLoses(Player player){
        System.out.println(player.getName() + " loses this round");
    }
    public void playerTies(Player player){
        System.out.println(player.getName() + " tied the house");
    }
    public void playerSurrendered(Player player){
        System.out.println(player.getName() + " surrendered");
    }
    public void clearedBoardMessage(){
        System.out.println("The board has been reset");
        System.out.println();
    }
    public void dealerWilLPlayNow(){
        System.out.println("-----------------------------------");
        System.out.println("It is the dealers Turn");
    }
    public void printCardCount(int count){
        System.out.println("The card count is " + count);
    }
    public boolean askUserToPlayAnotherRound(){
        System.out.println("-----------------------------------");
        System.out.println("Type 1 or yes to play another round");
        System.out.print("Type anything else to return to main menu: ");
        String userInput = in.nextLine();
        if(userInput.equals("1") || userInput.equalsIgnoreCase("yes")){
            return false;
        }
        return true;
    }
}