package games;

import java.util.LinkedList;
import java.util.Queue;

public class War {
    private static Queue<Card> playerOneDeck = new LinkedList<Card>();
    private static Queue<Card> playerTwoDeck = new LinkedList<Card>();
    private static Queue<Card> tempCardStack = new LinkedList<Card>();
    public static void main(String[] args) {
        Deck deck = new Deck(true);
        dealCards(deck);
        while(!playerOneDeck.isEmpty() && !playerTwoDeck.isEmpty()){
            System.out.print("Player one has "+ playerOneDeck.size() + " cards : player two has " + playerTwoDeck.size() + " cards     : ");
            compareCards(playerOneDeck.poll(), playerTwoDeck.poll());
        }
        if(playerOneDeck.isEmpty()){
            System.out.println("Player two won the game!");
        } else {
            System.out.println("Player one won the game!");
        }
    }

    private static void dealCards(Deck deck){
        int i = 0;
        for(Card card : deck.getDeckOfCards()){
            if(i % 2 == 0){
                playerOneDeck.offer(card);
            } else {
                playerTwoDeck.offer(card);
            }
            i++;
        }
    }
    private static void compareCards(Card playerOneCard, Card playerTwoCard) {

        if (playerOneCard.getValue() > playerTwoCard.getValue()) {
            tempCardStack.offer(playerOneCard);
            tempCardStack.offer(playerTwoCard);
            playerOneWinsBattle();
        } else if (playerTwoCard.getValue() > playerOneCard.getValue()) {
            tempCardStack.offer(playerTwoCard);
            tempCardStack.offer(playerOneCard);
            playerTwoWinsBattle();
        } else {
            tempCardStack.offer(playerTwoCard);
            tempCardStack.offer(playerOneCard);
            battleIsADraw();
        }
    }
    private static void playerOneWinsBattle() {
        System.out.println("Player one won the battle!");
        while (!tempCardStack.isEmpty()) {
            playerOneDeck.offer(tempCardStack.poll());
        }
    }
    private static void playerTwoWinsBattle(){
        System.out.println("Player two won the battle!");
        while (!tempCardStack.isEmpty()){
            playerTwoDeck.offer(tempCardStack.poll());
        }
    }
    private static void battleIsADraw(){
        System.out.println("Was a draw!");
        if(!playerOneDeck.isEmpty() && !playerTwoDeck.isEmpty()){
            tempCardStack.offer(playerOneDeck.poll());
            tempCardStack.offer(playerTwoDeck.poll());
            if(!playerOneDeck.isEmpty() && !playerTwoDeck.isEmpty()) {
                compareCards(playerOneDeck.poll(), playerTwoDeck.poll());
            }
        }
    }
}
