package games.menu;

import games.deck.Card;
import games.deck.Deck;
import games.player.Player;

import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner in = new Scanner(System.in);

    public void welcomeMessage(){
        System.out.println("-----------------------------");
        System.out.println(" Welcome to my card game app");
        System.out.println("-----------------------------");
    }
    public int showMenu(){
        System.out.println();
        System.out.println("---Games listed below---");
        System.out.println("0) Exit Game Menu");
        System.out.println("1) War (Played automatically between two players)");
        System.out.println("2) BlackJack (Played with 1-7 players)");
        System.out.print("Enter which game you would like to play: ");
        return askUserForGameChoice();
    }
    public int askUserForGameChoice(){
        int gameId = 0;
        while (true) {
            try {
                String userInput = in.nextLine();
                gameId = Integer.parseInt(userInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input, please type a whole number");
            }
        }
        return gameId;
    }
    public void invalidSelection(){
        System.out.println("Invalid selection");
    }
    public void printCard(Card card){
        System.out.println(card.getRank() + " of " + card.getSuit());
    }
    public void printDeck(Deck deck){
        for (Card currentCard : deck.getMainDeck()){
            printCard(currentCard);
        }
    }
    public void printDiscard(Deck deck){
        for (Card currentCard : deck.getDiscardDeck()){
            printCard(currentCard);
        }
    }
    public void printHand(Player player){
        for(Card card : player.getHand()){
            printCard(card);
        }
    }
    public void printPlayers(Player[] players){
        for(int i = 0; i<players.length;i++){
            System.out.println(players[i].getName());
        }
    }
    public void showHand(Player player){
        List<Card> playerHand = player.getHand();
        System.out.println(player.getName() + " has:");
        for(Card card : playerHand){
            printCard(card);
        }
    }
}
