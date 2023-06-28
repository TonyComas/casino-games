package games.blackjack;

import games.deck.Card;
import games.deck.Deck;
import games.menu.BlackjackMenu;
import games.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Blackjack {
    private final BlackjackMenu menu = new BlackjackMenu();
    private Deck deck = new Deck();
    private List<Player> players;
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
    private List<Player> createPlayers(){
        int numberOfPlayers = menu.askUserForNumberOfPlayers();
        List<Player> players = new ArrayList<>();
        players.add(new Player("The Dealer"));
        for(int i = 0; i < numberOfPlayers; i++){
            players.add(new Player(menu.askUserForName(i + 1)));
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
    private void splt(Player player){
        Player player1 = new Player(player.getName());
        Player player2 = new Player(player.getName());
        player1.drawCard(player.dealCard());
        player2.drawCard(player.dealCard());
        players.remove(player);
        players.add(player1);
        players.add(player2);
    }
    private void surrender(Player player){
        deck.discardHand(player.emptyHand());
        isTurn = false;
    }

    private void dealerPlays(){
        if(players.get(0).getBjHandValue() < 17){
            hit(players.get(0));
        } else {
            stand();
        }
    }

    private void determineWinners(){
        int dealerScore = players.get(0).getBjHandValue();
        menu.showBlackjackHand(players.get(0));
        for(int i = 1; i < players.size(); i++){
            int playerScore = players.get(i).getBjHandValue();
            if(playerScore == 0) {
                menu.playerSurrendered(players.get(i));
            } else if((playerScore > 21 && dealerScore > 21) || (playerScore == dealerScore)){
                menu.playerTies(players.get(i));
            } else if(playerScore > 21){
                menu.playerLoses(players.get(i));
            } else if(dealerScore > 21){
                menu.playerWins(players.get(i));
            } else if (playerScore > dealerScore) {
                menu.playerWins(players.get(i));
            } else {
                menu.playerLoses(players.get(i));
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
        for(int i = 1; i < players.size(); i++){
            for(Card card : players.get(i).getHand()){
                countCard(card);
            }
        }
        countCard(players.get(0).getHand().get(0));
        menu.printCardCount(cardCount);
    }
    private void play(){
        for(int i = 1; i < players.size(); i++){
            isTurn = true;
            while(isTurn) {
                menu.showBlackjackHand(players.get(i));
                menu.blackjackActionMenu();
                blackjackInput(menu.askForAction(), players.get(i));
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
