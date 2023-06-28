package games.war;

import games.deck.Card;
import games.deck.Deck;
import games.menu.WarMenu;

import java.util.LinkedList;
import java.util.Queue;

public class War {
    private static Queue<Card> playerOneDeck = new LinkedList<Card>();
    private static Queue<Card> playerTwoDeck = new LinkedList<Card>();
    private static Queue<Card> tempCardStack = new LinkedList<Card>();
    WarMenu menu = new WarMenu();
    private boolean isShown = false;

    public War(){
        reset();
    }
    public void run(){
        Deck deck = new Deck(true);
        dealCards(deck);
        int userInput = menu.askUserIfTheyWantToSeeGame();
        if(userInput == 1) {
            isShown = true;
        }
        while (!playerOneDeck.isEmpty() && !playerTwoDeck.isEmpty()) {
            if(isShown){
                menu.printWarStatus(playerOneDeck, playerTwoDeck);
            }
            compareCards(playerOneDeck.poll(), playerTwoDeck.poll());
        }
        if(playerOneDeck.isEmpty()){
            menu.playerTwoWinsWar();
        } else {
            menu.playerOneWinsWar();
        }
    }

    private void dealCards(Deck deck){
        int i = 0;
        for(Card card : deck.getMainDeck()){
            if(i % 2 == 0){
                playerOneDeck.offer(card);
            } else {
                playerTwoDeck.offer(card);
            }
            i++;
        }
    }
    private void compareCards(Card playerOneCard, Card playerTwoCard) {

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
    private void playerOneWinsBattle() {
        if(isShown) {
            menu.playerOneWinsBattle();
        }
        while (!tempCardStack.isEmpty()) {
            playerOneDeck.offer(tempCardStack.poll());
        }
    }
    private void playerTwoWinsBattle(){
        if(isShown) {
            menu.playerTwoWinsBattle();
        }
        while (!tempCardStack.isEmpty()){
            playerTwoDeck.offer(tempCardStack.poll());
        }
    }
    private void battleIsADraw(){
        if(isShown){
            menu.battleIsDraw();
            menu.printWarStatus(playerOneDeck, playerTwoDeck);
        }
        if(!playerOneDeck.isEmpty() && !playerTwoDeck.isEmpty()){
            tempCardStack.offer(playerOneDeck.poll());
            tempCardStack.offer(playerTwoDeck.poll());
            if(!playerOneDeck.isEmpty() && !playerTwoDeck.isEmpty()) {
                compareCards(playerOneDeck.poll(), playerTwoDeck.poll());
            }
        }
    }
    private void reset(){
        while(!playerOneDeck.isEmpty()) {
            playerOneDeck.poll();
        }
        while(!playerTwoDeck.isEmpty()) {
            playerTwoDeck.poll();
        }
        while(!tempCardStack.isEmpty()) {
            tempCardStack.poll();
        }
    }
}
