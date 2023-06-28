package games.player;

import games.deck.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand = new ArrayList<Card>();
    private String name;
    private int balance = 0;
    private int currentBet = 0;

    public Player(){}
    public Player(String name){
        this.name = name;

    }
    public Player(List<Card> hand){
        this.hand = hand;
    }

    public Player(List<Card> hand, String name){
        this.name = name;
        this.hand = hand;
    }

    public void drawCard(Card card){
        this.hand.add(card);
    }
    public Card dealCard(){
        return hand.remove(0);
    }
    public int getBjHandValue(){
        int handValue = 0;
        int numberOfAces = 0;
        for(Card card : getHand()){
            handValue += card.getBlackJackValue();
            if(card.getRank() == "Ace"){
                numberOfAces++;
            }
        }
        while(handValue > 21 && numberOfAces > 0){
            handValue -= 10;
            numberOfAces--;
        }
        return handValue;
    }

    public List<Card> emptyHand(){
        List<Card> discardedCards = new ArrayList<>();
        while(hand.size() > 0){
            discardedCards.add(hand.remove(0));
        }
        return discardedCards;
    }
    public int changeBalance(int amountToChange){
        balance += amountToChange;
        return balance;
    }
    public void bet(int amountToBet){
        changeBalance(-amountToBet);
    }

    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
}
