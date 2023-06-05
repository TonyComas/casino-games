package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {
        List<Player> playerList = new ArrayList<Player>();
        playerList.add(addHouse());
        playerList = addPlayers(playerList);
        Deck deck = new Deck(setNumberOfDecks(), true);
        Deck discardDeck = new Deck();
        dealCards(playerList, deck, 2);
        printPlayersHands(playerList);
    }
    private static void dealCards(List<Player> playersBeingDealtCards, Deck deckCardsAreDrawnFrom, int numberOfCards){
        for(int i = 0; i < numberOfCards; i++){
            for(Player player : playersBeingDealtCards){
                player.addCard(deckCardsAreDrawnFrom.dealCard());
            }
        }
    }
    private static int setNumberOfPlayers(){
        System.out.print("How many people are playing against the house: ");
        Scanner userInput = new Scanner(System.in);
        String numberOfPlayersInput = userInput.nextLine();
        return Integer.parseInt(numberOfPlayersInput);
    }

    private static int setNumberOfDecks(){
        System.out.print("Enter how many decks of cards you want to play with: ");
        Scanner userInput = new Scanner(System.in);
        String numberOfDecksInput = userInput.nextLine();
        return Integer.parseInt(numberOfDecksInput);
    }
    private static Player addHouse(){
        return new Player("House");
    }
    private static List<Player> addPlayers(List<Player> playerList){
        Scanner userInput = new Scanner(System.in);
        int numberOfPlayers = setNumberOfPlayers();
        for(int i = 0; i < numberOfPlayers; i++){
            System.out.print("Enter the name for player " + (i+1) + ": ");
            String inputName = userInput.nextLine();
            playerList.add(new Player(inputName));
        }
        return playerList;
    }
    private static void printPlayersHands(List<Player> playerList){
        for(Player player : playerList){
            System.out.println(player.getName() + player.getHand());
        }
    }
}
