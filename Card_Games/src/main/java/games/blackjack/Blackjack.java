package games.blackjack;

import games.deck.Card;
import games.deck.Deck;
import games.menu.BlackjackMenu;
import games.player.Player;

public class Blackjack {
    private final BlackjackMenu menu = new BlackjackMenu();
    private Deck deck = new Deck();
    private Player[] players;
    private final int STAND = 1;
    private final int HIT = 2;
    private final int DOUBLE_DOWN = 3;
    private final int SPLIT = 4;
    private final int SURRENDER = 5;
    private final int SHOW_CARD_COUNT = 6;
    private final int MINIMUM_STARTING_DECK_SIZE = 52;
    private int cardCount = 0;
    private boolean isTurn;
    public Blackjack(){}
    public void run(){
        int numberOfDecks = setUpTable();
        int startingDeckSize = deck.getMainDeck().size();
        while(true){
            if(deck.getMainDeck().size() < startingDeckSize/2 || deck.getMainDeck().size() < MINIMUM_STARTING_DECK_SIZE){
                deck = new Deck(numberOfDecks, true);
            }
            dealCards();
            play();
            if(menu.askUserToPlayAnotherRound()){
                break;
            }
            clearBoard();
        }
    }
    private int setUpTable(){
        menu.blackjackMenu();
        players = createPlayers();
        int numberOfDecks = menu.askUserForNumberOfDecks();
        deck = new Deck(numberOfDecks, true);
        return numberOfDecks;
    }
    private Player[] createPlayers(){
        int numberOfPlayers = menu.askUserForNumberOfPlayers();
        Player[] players = new Player[numberOfPlayers+1];
        players[0] = new Player("The Dealer");
        for(int i = 0; i < numberOfPlayers; i++){
            players[i+1] = new Player(menu.askUserForName(i + 1));
        }
        return players;
    }

    private void dealCards(){
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.drawCard(deck.dealCard());
            }
        }
    }

    private void blackjackInput(int input, Player player){
        if(input == STAND){
            stand();
        } else if (input == HIT) {
            hit(player);
        } else if (input == DOUBLE_DOWN) {
            doubleDown(player);
        } else if (input == SPLIT) {
            splt(player);
        } else if (input == SURRENDER) {
            surrender(player);
        } else if (input == SHOW_CARD_COUNT){
            showCardCount();
        }
    }
    private void stand(){
        isTurn = false;
    }
    private void hit(Player player){
        Card card = deck.dealCard();
        player.drawCard(card);
        menu.showDraw(card);
        if(player.getBjHandValue() > 21){
            menu.overLimit(player);
            isTurn = false;
        }
    }
    private void doubleDown(Player player){
        hit(player);
        stand();
    }
    private Player[] splt(Player player){
        //TODO
        Player player1 = new Player(player.getName());
        Player player2 = new Player(player.getName());
        player1.drawCard(player.dealCard());
        player2.drawCard(player.dealCard());
        Player[] tempPlayers = new Player[]{player1, player2};
        return tempPlayers;
    }
    private void surrender(Player player){
        deck.discardHand(player.emptyHand());
        isTurn = false;
    }

    private void dealerPlays(){
        if(players[0].getBjHandValue() < 17){
            hit(players[0]);
        } else {
            stand();
        }
    }

    private void determineWinners(){
        int dealerScore = players[0].getBjHandValue();
        menu.showBlackjackHand(players[0]);
        for(int i = 1; i < players.length; i++){
            int playerScore = players[i].getBjHandValue();
            if(playerScore == 0) {
                menu.playerSurrendered(players[i]);
            } else if((playerScore > 21 && dealerScore > 21) || (playerScore == dealerScore)){
                menu.playerTies(players[i]);
            } else if(playerScore > 21){
                menu.playerLoses(players[i]);
            } else if(dealerScore > 21){
                menu.playerWins(players[i]);
            } else if (playerScore > dealerScore) {
                menu.playerWins(players[i]);
            } else {
                menu.playerLoses(players[i]);
            }
        }
    }
    private void clearBoard(){
        for(Player player : players){
            deck.discardHand(player.emptyHand());
        }
        menu.clearedBoardMessage();
    }
    private void countCard(Card card){
        if(card.getBlackJackValue() >= 10){
            cardCount--;
        } else if(card.getBlackJackValue() <= 6){
            cardCount++;
        }
    }
    private void showCardCount(){
        cardCount = 0;
        for(Card card : deck.getDiscardDeck()){
            countCard(card);
        }
        for(int i = 1; i < players.length; i++){
            for(Card card : players[i].getHand()){
                countCard(card);
            }
        }
        countCard(players[0].getHand().get(0));
        menu.printCardCount(cardCount);
    }
    private void play(){
        for(int i = 1; i < players.length; i++){
            isTurn = true;
            while(isTurn) {
                menu.showBlackjackHand(players[i]);
                menu.blackjackActionMenu();
                blackjackInput(menu.askForAction(), players[i]);
            }
        }
        isTurn = true;
        menu.dealerWilLPlayNow();
        while(isTurn) {
            dealerPlays();
        }
        determineWinners();
    }

}
